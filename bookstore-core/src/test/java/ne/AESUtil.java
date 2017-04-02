package ne;

import com.nxm.util.AES;

public class AESUtil {
	
	public static void main(String args[]){
		String key;
		String word;
		key = "11AD13934502F2242D4C38DCA7011F6D";
		word = "partnerId=njs&login_token=fbf19ed4cc22f6e74157cf96e652eae7&signProtocal=Y";
//		key = "D496238689718F72C5C21BAA4F7B7BFE";
//		word = "1EB4C03405552943A9F9599E3688BF8C9698B4DA4F4E795B269D33ADE3E251514B06178168F5BCF49A54A53FBBBACB8124C64A68F9679A2E19A332C864A6A45CDEF14AEF288C7A3ED000AAF9D7DF79227E45250C7B5084134C21356124F01ECA";
		System.out.println(word.contains("=")?AES.encrypt(word, key):AES.decrypt(word, key));
	}

}
