package ne;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.nxm.bookstore.util.JaxbUtil;

@RunWith(JUnit4.class)
public class SgeTcpTest {

    private Socket socket;
    private OutputStream osm;
    private InputStream ism;

    @Before
    public void init() throws Exception {
    	/*
    	 * 交易端口 50051
    	 * 查询端口 46001
    	 * 风控端口 47001
    	 * 开销户接口 51077
    	 * 签约接口 52077
    	 */
        socket = new Socket("210.21.197.124",51077);
        socket.setSoTimeout(6000);
        osm = socket.getOutputStream();
        ism = socket.getInputStream();
        initParams() ;
        loginMiddleWare() ;
    }
    
    private void initParams() {

    }
    
    public void loginMiddleWare(){

    }
    
	@After
    public void close() throws Exception{
        ism.close();
        osm.close();
        socket.close();
    }
	
	public static int read(byte[] b, InputStream is) throws IOException {
		int count = 0;
		while (count < b.length) {
			int cnt = is.read(b, count, (b.length - count));
			count += cnt;
		}
		return count;
	}
	@Test
	public void testJaxb(){
		/*
		<h_exch_code>880111</h_exch_code>
		<h_bank_no>1111</h_bank_no>
		<h_term_type>02</h_term_type>
		<h_teller_id>1111</h_teller_id>
		<h_teller_id_1></h_teller_id_1>
		<h_teller_id_2></h_teller_id_2>
		<h_bk_seq_no>123456></h_bk_seq_no>
		<h_work_date>20150526</h_work_date>
		<h_exch_date>20150526</h_exch_date>
		 */

		System.out.println(getOpenaccRequest());
	}
	
	private String getOpenaccRequest(){
		SgeTcpRequest request = new SgeTcpRequest();
		SgeTcpHead head = new SgeTcpHead();
		head.setAdapter("880111");
		head.setBankId("1111");
		head.setTermType("02");
		head.setTellerId("1111");
		head.setTellerId1("");
		head.setTellerId2("");
		head.setBankSeq("123456");
		head.setWorkDate("20150526");
		head.setExchangeDate("20150526");
		SgeTcpRecord record = new SgeTcpRecord();
		record.setBranchId("B0077001");
		List<SgeTcpRecord> body = new ArrayList<SgeTcpRecord>();
		body.add(record);
		request.setHead(head);
		request.setBody(body);
		
		return JaxbUtil.convertToXml(request,"GBK");
	}
	
	@Test
    public void testOpenAccount() throws Exception{
		String body = getOpenaccRequest();
		//加密方式
//		byte[] desbody = DESedeCoder.encrypt(body.getBytes("GBK"), key.getBytes("GBK"));
		//开户签约不加密
		byte[] desbody = body.getBytes("GBK");


		String strFormat = "%08d";
		String outstr = String.format(strFormat, desbody.length);
		osm.write(outstr.getBytes("GBK"));
		osm.write(desbody);
		System.out.println("req: " + outstr);
		System.out.println(body);
		
		osm.flush();
		byte[] datalen = new byte[8];
		read(datalen, ism);
		String datelenstr = new String(datalen);
		System.out.println("res: " +datelenstr);
		if (datelenstr.matches("\\d*")) {
			int datalength = Integer.valueOf(new String(datalen));
			if (datalength < 0 || datalength > 1000) {
				System.err.println("res length wrong!");
			}
			byte[] databbb = new byte[datalength];
			read(databbb, ism);
			System.out.println(new String(databbb, "gbk"));
		} else {
			System.err.println("res length wrong!");
		}
    }
}
