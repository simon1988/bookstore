package com.nxm.bookstore.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nxm.bookstore.service.QRService;

@Controller
public class TestController {
	
	private static Logger logger=LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	QRService qrService;
	
	@ResponseBody
	@RequestMapping(value = "/qrcode", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] qrcode() throws IOException {
		byte[] qrpic = qrService.generateQRImage("http://www.baidu.com");
	    return qrpic;
	}
	
	@ResponseBody
	@RequestMapping("/upload")
	public String upload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
		logger.info("upload "+name+"!");
		if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
	}
	
	@ResponseBody
	@RequestMapping("/test/json")
	public Map<String, Object> jsonTest(){
		Map<String, Object> map = new HashMap<>();
		map.put("code", 200);
		map.put("msg", "OK");
		return map;
	}
}
