package com.nxm.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {

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
			return new String(decrypt(CodecUtils.hexStrToBytes(data), CodecUtils.hexStrToBytes(key)),
					"utf-8");
		} catch (Exception e) {
		}
		return "";
	}

	public final static byte[] decryptBase64(byte[] data, String key) {
		try {
			return Base64.decode(decrypt(data, CodecUtils.hexStrToBytes(key)));
		} catch (Exception e) {
		}
		return null;
	}

	public final static String encrypt(String password, String key) {
		try {
			return CodecUtils.bytesToUpperHexStr(encrypt(
					password.toString().getBytes("utf-8"), CodecUtils.hexStrToBytes(key)));
		} catch (Exception e) {
		}
		return "";
	}

	public final static byte[] encryptBase64(byte[] password, String key) {
		try {
			return encrypt(Base64.encode(password), CodecUtils.hexStrToBytes(key));
		} catch (Exception e) {
		}
		return null;
	}
}
