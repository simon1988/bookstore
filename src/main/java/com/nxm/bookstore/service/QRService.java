package com.nxm.bookstore.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QRService {

	public byte[] generateQRImage(String url){
		try {
			int width = 200, height = 200; 
			Map<EncodeHintType,Integer> hints = new HashMap<>();
			hints.put(EncodeHintType.MARGIN, 1);
			BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);
		    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
			for (int x = 0; x < width; x++){
				for (int y = 0; y < height; y++) {
					image.setRGB(x, y, bitMatrix.get(x, y)?0xFF000000:0xFFFFFFFF);
				}
			}
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", bos);
			return bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
