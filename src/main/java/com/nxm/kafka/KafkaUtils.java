package com.nxm.kafka;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaUtils {
	private static Logger logger = LoggerFactory.getLogger("kafka");
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static String objectToJson(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			logger.warn("write to json string error:" + object, e);
			return null;
		}
	}
	public static <T> T jsonToObject(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return mapper.readValue(jsonString, clazz);
		} catch (Exception e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}
	public static JsonNode readTree(String jsonString){
		try {
			return mapper.readTree(jsonString);
		} catch (Exception e) {
			logger.error("read json tree error:" + jsonString, e);
		}
		return null;
	}
	public static ObjectMapper getObjectMapper(){
		return mapper;
	}

	
	public static String getUUID(){
		String s = UUID.randomUUID().toString();
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}

	public static String getCurrentTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}
