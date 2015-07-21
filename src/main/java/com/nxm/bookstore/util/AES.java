package com.nxm.bookstore.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	private static final char[] bcdLookup = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private static final char[] upBcdLookup = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static byte[] encrypt(byte[] src, byte[] kv) throws Exception {
		SecretKey key = new SecretKeySpec(kv, "AES");
		Cipher cp = Cipher.getInstance("AES");
		cp.init(Cipher.ENCRYPT_MODE, key);
		byte[] ctext = cp.doFinal(src);
		return ctext;
	}

	public static byte[] decrypt(byte[] src, byte[] kv) throws Exception {
		SecretKey key = new SecretKeySpec(kv, "AES");
		Cipher cp = Cipher.getInstance("AES");
		cp.init(Cipher.DECRYPT_MODE, key);
		byte[] ptext = cp.doFinal(src);
		return ptext;
	}

	public final static String decrypt(String data, String key) {
		try {
			return new String(decrypt(hexStrToBytes(data), hexStrToBytes(key)),
					"utf-8");
		} catch (Exception e) {
		}
		return "";
	}

	public final static byte[] decryptBase64(byte[] data, String key) {
		try {
			return Base64.decode(decrypt(data, hexStrToBytes(key)));
		} catch (Exception e) {
		}
		return null;
	}

	public final static String encrypt(String password, String key) {
		try {
			return bytesToUpperHexStr(encrypt(
					password.toString().getBytes("utf-8"), hexStrToBytes(key)));
		} catch (Exception e) {
		}
		return "";
	}

	public final static byte[] encryptBase64(byte[] password, String key) {
		try {
			return encrypt(Base64.encode(password), hexStrToBytes(key));
		} catch (Exception e) {
		}
		return null;
	}

	public static final String bytesToHexStr(byte[] bcd) {
		StringBuffer s = new StringBuffer(bcd.length * 2);
		for (int i = 0; i < bcd.length; i++) {
			s.append(bcdLookup[(bcd[i] >>> 4) & 0x0f]);
			s.append(bcdLookup[bcd[i] & 0x0f]);
		}
		return s.toString();
	}

	public static final String bytesToUpperHexStr(byte[] bcd) {
		StringBuffer s = new StringBuffer(bcd.length * 2);
		for (int i = 0; i < bcd.length; i++) {
			s.append(upBcdLookup[(bcd[i] >>> 4) & 0x0f]);
			s.append(upBcdLookup[bcd[i] & 0x0f]);
		}
		return s.toString();
	}

	public static final byte[] hexStrToBytes(String s) {
		byte[] bytes;
		bytes = new byte[s.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}
}
