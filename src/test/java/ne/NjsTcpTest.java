package ne;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.fasterxml.jackson.databind.JsonNode;
import com.nxm.bookstore.util.JsonUtil;

@RunWith(JUnit4.class)
public class NjsTcpTest {
	private static Socket socket;
	private static OutputStream osm;
	private static InputStream ism;

	@BeforeClass
	public static void init() throws Exception {
		socket = new Socket("127.0.0.1", 51000);
		socket.setSoTimeout(6000);
		osm = socket.getOutputStream();
		ism = socket.getInputStream();

		osm.write("015000000000N00000000000".getBytes());
		osm.flush();
		byte b[] = new byte[24];
		int x = ism.read(b);
		if (x < 0) {
			System.out.println("login to middleware failed!");
		}
	}

	@AfterClass
	public static void close() throws Exception {
		ism.close();
		osm.close();
		socket.close();
	}

	private byte[] readByteArray(InputStream ism, int length) throws Exception{
		int count = 0;
		byte[] bytes = new byte[length];
		while (count < bytes.length) {
			int cnt = ism.read(bytes, count, (bytes.length - count));
			count += cnt;
		}
		return bytes;
	}

	private String buildSendStr(String adapter, Map<String, String> paramsMap) throws UnsupportedEncodingException {
		String params = JsonUtil.objectToJson(paramsMap);
		String firstRandom = String.valueOf(System.currentTimeMillis()).substring(2);
		int secondLength = params.getBytes("GBK").length;
		int firstLength = secondLength + 24;
		return String.format("016005%06dN%s%s%06dN%s%s", firstLength, firstRandom, adapter, secondLength, firstRandom, params);
	}

	@SuppressWarnings("unused")
	private String sendRequest(String adapterId, Map<String, String> paramMap) throws Exception {
		String sendStr = buildSendStr(adapterId, paramMap);
		System.out.println(sendStr);
		osm.write(sendStr.getBytes("GBK"));
		osm.flush();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream() ;
        String revNext = "Y" ;
        while("Y".equals(revNext)) {
			byte[] adapter = readByteArray(ism, 6);
            byte[] datalen = readByteArray(ism, 6);
            byte[] next = readByteArray(ism, 1);
            byte[] random = readByteArray(ism, 11);
            int rev_data_length = Integer.valueOf(new String(datalen));
            byte[] data = readByteArray(ism, rev_data_length) ;
            out.write(data);
            revNext = new String(next);
        }
        String result = out.toString("gbk");
        return result.substring(24);
	}

	@Test
	public void testOpenAccount() throws Exception {
		/*
		{"FIRMCARDNO":"412827198709237522","BANKCODE":"041","BANKNO":"666612345678456312",
		"TRADERID":"16380320","TRADEPWD":"qwe123","FIRMCARDTYPE":"1","FULLNAME":"罗倩倩",
		"TELNO":"18612680469","SIGNBANKNAME":"农业银行","FIRMID":"163","BANKPWD":"158110"}
		*/
		Map<String, String> params = new HashMap<String, String>();
		params.put("FULLNAME", "狄好宇");
		params.put("TELNO", "18612680469");
		params.put("FIRMCARDTYPE", "1");
		params.put("FIRMCARDNO", "511900199112114493");
		params.put("SIGNBANKNAME", "农业银行");
		params.put("BANKNO", "666612345678456318");
		params.put("TRADERID", "16370325");
		params.put("FIRMID", "163");
		params.put("BANKCODE", "041");
		params.put("TRADEPWD", "teamfa");
		params.put("BANKPWD", "158110");
		String result = sendRequest("014009", params);
		System.out.println(result);
	}

	private String getLoginPartnerToken() throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("TRADERID", "16370325");
		params.put("TRADEPASS", "teamfa");
		String result = sendRequest("011001", params);
		System.out.println(result);
		
		JsonNode jsonNode = JsonUtil.readTree(result);
		if(jsonNode.get("TOKEN")==null || StringUtils.isBlank(jsonNode.get("TOKEN").asText())){
			Assert.fail("get token failed after login!");
		}
		return jsonNode.get("TOKEN").asText();
	}
	
	@Test
    public void testQueryFund() throws Exception {
		String token = getLoginPartnerToken();
		Map<String, String> params = new HashMap<String, String>();
		params.put("TRADERID", "16370325");
		params.put("TOKEN", token);
		String result = sendRequest("011008", params);
		System.out.println(result);
	}
	
	@Test
    public void testChangePassword() throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("TRADERID", "16370325");
		params.put("OLDPWD", "teamfa1");
		params.put("NEWPWD", "teamfa");
		String result = sendRequest("011032", params);
		System.out.println(result);
	}
}
