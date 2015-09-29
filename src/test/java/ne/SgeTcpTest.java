package ne;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.nxm.bookstore.util.DES;
import com.nxm.bookstore.util.JaxbUtil;
import com.nxm.bookstore.util.MD5Util;

/*
 * 交易端口 45001
 * 查询端口 46001
 * 风控端口 47001
 * 开销户接口 51077
 * 签约接口 52077
 */
@RunWith(JUnit4.class)
public class SgeTcpTest {
	
	private int read(byte[] b, InputStream is) throws IOException {
		int count = 0;
		while (count < b.length) {
			int cnt = is.read(b, count, (b.length - count));
			count += cnt;
		}
		return count;
	}
	private String sendRequest(int port, String body, boolean encrypt) throws Exception{
		byte[] desbody = body.getBytes("GBK");
		if(encrypt){
			desbody = DES.encrypt(desbody, "ABCDEF0123456789abcdef11".getBytes("GBK"));
		}
		String outstr = String.format("%08d", desbody.length);
		System.out.println("req: " + outstr);
		System.out.println(body);
		try(
			Socket socket = new Socket("210.21.197.124",port);
			OutputStream osm = socket.getOutputStream();
			InputStream ism = socket.getInputStream();){
			
	        socket.setSoTimeout(6000);
			
			osm.write(outstr.getBytes("GBK"));
			osm.write(desbody);
			osm.flush();
			
			byte[] datalen = new byte[8];
			read(datalen, ism);
			String datelenstr = new String(datalen);
			
			System.out.println("res: " +datelenstr);
			
			int datalength = Integer.valueOf(new String(datalen));
			if (datalength < 0 || datalength > 1000) {
				throw new Exception("res length wrong!");
			}
			byte[] databbb = new byte[datalength];
			read(databbb, ism);
			return new String(databbb, "gbk");
		}
	}
	
	@Test
    public void testOpenAccount() throws Exception{
		SgeTcpRequest request = new SgeTcpRequest();
		SgeTcpHead head = new SgeTcpHead();
		head.setAdapter("880120");
		head.setBankNumber("0077");
		head.setTermType("02");
		head.setBranchId("B0077001");
		head.setTellerId("C09100");
		head.setBankSeq("bk14435111063297206352");
		head.setWorkDate("20150526");
		head.setExchangeDate("20150526");
		SgeTcpRecord record = new SgeTcpRecord();
		record.setAreaCode("110000");
		record.setZipCode("100001");
		record.setTel("13621236892");
		record.setAddress("北京");
		record.setCertType("s");
		record.setCustomerName("元辰");
		record.setMobile("13621236892");
		record.setCertNo("441700198910125249");
		record.setTradePassword(MD5Util.MD5("147369a"));
		record.setBrokerList("00771");
		record.setFundPassword(MD5Util.MD5("147369"));
		record.setGradeId("007701");
		record.setBranchId("B0077001");
		record.setCustType("C90001");
		List<SgeTcpRecord> body = new ArrayList<SgeTcpRecord>();
		body.add(record);
		request.setHead(head);
		request.setBody(body);
		
		String requestBody = JaxbUtil.convertToXml(request,"GBK");
		
        String res = sendRequest(51077, requestBody, false);
        
        System.out.println(res);
    }
	@Test
    public void testSignAccount() throws Exception{
		SgeTcpRequest request = new SgeTcpRequest();
		SgeTcpHead head = new SgeTcpHead();
		head.setSignAdapter("610001");
		head.setSignSDBId("611111");
		head.setSignWorkDate("20150930");
		head.setSignBankId("0077");
		head.setSignSeqNo("14435801116118769279");
		SgeTcpRecord record = new SgeTcpRecord();
		record.setSignCustName("元辰");
		record.setSignCertNo("441700198910125249");
		record.setSignFirmid("1080124488");
		record.setSignCertType("s");
		record.setSignCurrencyType("RMB");
		record.setSignBankInfo("015:666615484348888888");
		List<SgeTcpRecord> body = new ArrayList<SgeTcpRecord>();
		body.add(record);
		request.setHead(head);
		request.setBody(body);
		
		String requestBody = JaxbUtil.convertToXml(request,"GBK");
		
        String res = sendRequest(52077, requestBody, false);
        
        System.out.println(res);
    }
	
	@Test
    public void testLoginPartner() throws Exception{
		SgeTcpRequest request = new SgeTcpRequest();
		SgeTcpHead head = new SgeTcpHead();
		head.setAdapter("800101");
		head.setUserId("1080010279");
		head.setBankNumber("1111");
		head.setBranchId("B00000");
		head.setFactDate("20150128");
		head.setFactTime("13:40:20");
		head.setExchangeDate("20150526");
		head.setSerialNumber("12345678");
		head.setRspCode("");
		head.setRspMessage("");
		SgeTcpRecord record = new SgeTcpRecord();
		record.setUserPassword("e10adc3949ba59abbe56e057f20f883e");
		record.setLoginIp("127.0.0.1");
		List<SgeTcpRecord> body = new ArrayList<SgeTcpRecord>();
		body.add(record);
		request.setHead(head);
		request.setBody(body);
		
		String requestBody = JaxbUtil.convertToXml(request,"GBK");
		
        String res = sendRequest(45001, requestBody, true);
        
        System.out.println(res);
    }
}
