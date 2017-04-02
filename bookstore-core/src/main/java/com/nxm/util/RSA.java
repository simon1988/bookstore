package com.nxm.util;

import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

public class RSA {

	public String sign(String priKey, String src){
		try{
			Signature sigEng = Signature.getInstance("SHA1withRSA");
			byte[] pribyte = CodecUtils.hexStrToBytes(priKey);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pribyte);
			KeyFactory fac = KeyFactory.getInstance("RSA");
	
			RSAPrivateKey privateKey = (RSAPrivateKey) fac.generatePrivate(keySpec);
			sigEng.initSign(privateKey);
			sigEng.update(src.getBytes("utf-8"));
	
			byte[] signature = sigEng.sign();
			return CodecUtils.bytesToHexStr(signature);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
