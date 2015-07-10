package com.nxm.bookstore.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
	private static Logger logger=Logger.getLogger(JsonUtil.class);
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
			e.printStackTrace();
			logger.error("parse json string error:" + jsonString, e);
			return null;
		}
	}
	
	public static JsonNode readTree(String jsonString){
		try {
			return mapper.readTree(jsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String args[]){
		Map<String,Object> map = new HashMap<>();
		map.put("retCode", 200);
		map.put("ret", "test");
		String json = objectToJson(map);
		System.out.println(json);
		map = jsonToObject(json,Map.class);
		System.out.println(map.get("retCode"));
	}
}
