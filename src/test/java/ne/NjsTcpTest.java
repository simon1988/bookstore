package ne;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.nxm.bookstore.util.JsonUtil;

@RunWith(JUnit4.class)
public class NjsTcpTest {

    private Socket socket;
    private OutputStream osm;
    private InputStream ism;

    @Before
    public void init() throws Exception {
        socket = new Socket("127.0.0.1",51000);
        socket.setSoTimeout(6000);
        osm = socket.getOutputStream();
        ism = socket.getInputStream();
        initParams() ;
        loginMiddleWare() ;
    }
    
    private void initParams() {
    	/*
    	{"FIRMCARDNO":"412827198709237522","BANKCODE":"041","BANKNO":"666612345678456312",
    	"TRADERID":"16380320","TRADEPWD":"qwe123","FIRMCARDTYPE":"1","FULLNAME":"罗倩倩",
    	"TELNO":"18612680469","SIGNBANKNAME":"农业银行","FIRMID":"163","BANKPWD":"158110"}
    	*/
    }
    
    public void loginMiddleWare() throws Exception{
        osm.write("015000000000N00000000000".getBytes());
        osm.flush();
        byte b[] = new byte[24];
        int x = ism.read(b);
        if(x < 0) {
            System.out.println("登陆交易所失败");
        }
    }
    
	@After
    public void close() throws Exception{
        ism.close();
        osm.close();
        socket.close();
    }
	
	public String buildSendStr(String adapter,Map<String,String> paramsMap) throws UnsupportedEncodingException{
        String params = JsonUtil.objectToJson(paramsMap) ;
        String firstRandom = String.valueOf(System.currentTimeMillis()).substring(2);
        int secondLength =  params.getBytes("GBK").length ;
        int firstLength = secondLength+24 ;
        return String.format("016005%06dN%s%s%06dN%s%s",firstLength,firstRandom,adapter,secondLength,firstRandom,params);
    }
	
	@SuppressWarnings("unused")
	private StringBuilder readResult(boolean isFirst) throws Exception {
        StringBuilder allData = new StringBuilder() ;
        if(isFirst) {
            byte[] discard = readByteArrayFromInputStream(24) ;
        }
        byte[] adapter = readByteArrayFromInputStream(6) ;
        byte[] datalen = readByteArrayFromInputStream(6) ;
        byte[] next = readByteArrayFromInputStream(1) ;
        byte[] random = readByteArrayFromInputStream(11) ;
        int rev_data_length = Integer.valueOf(new String(datalen));
        byte[] data = readByteArrayFromInputStream(rev_data_length) ;
        allData.append(new String(data, "gbk"));
        String rev_next = new String(next);
        if ("Y".equals(rev_next)) {
            return allData.append(readResult(false)) ;
        } else {
            return allData ;
        }
    }
	public int read(byte [] b,InputStream is) throws Exception {
		int count = 0;
        while (count < b.length) {
            int cnt = is.read(b, count, (b.length - count));
            count += cnt;
        }
        return count;
    }
	private byte[] readByteArrayFromInputStream( int length) throws Exception {
        if (length <= 0) {
            return null ;
        }
        byte[] bytes = new byte[length] ;
        read(bytes,ism) ;
        return bytes ;
    }
	private String sendRequest(Map<String,String> paramMap,String adapter) throws Exception {
        String sendStr = buildSendStr(adapter,paramMap);
        System.out.println(sendStr);
        osm.write(sendStr.getBytes("GBK"));
        osm.flush();
        return readResult(true).toString();
    }
	@Test
    public void testOpenAccount() throws Exception{
		Map<String,String> params = new HashMap<String,String>();
		params.put("FULLNAME","狄好宇") ;
        params.put("TELNO","18612680469") ;
        params.put("FIRMCARDTYPE","1") ;
        params.put("FIRMCARDNO","511900199112114493") ;
        params.put("SIGNBANKNAME","农业银行") ;
        params.put("BANKNO","666612345678456318") ;
        params.put("TRADERID","16370325") ;
        params.put("FIRMID","163") ;
        params.put("BANKCODE","041") ;
        params.put("TRADEPWD","teamfa") ;
        params.put("BANKPWD","158110") ;
        String result = sendRequest(params,"014009");
        System.out.println(result);
    }
}
