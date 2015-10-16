package com.nxm.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static Logger logger=LoggerFactory.getLogger(JsonUtil.class);
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static String objectToJson(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			logger.error("write to json string error:" + object, e);
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
			logger.error("parse json string error:" + jsonString, e);
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
	
	@SuppressWarnings("unchecked")
	public static void main(String args[]){
		Map<String,Object> map = new HashMap<>();
		map.put("retCode", 200);
		map.put("ret", "test");
		String json = objectToJson(map);
		System.out.println(json);
		map = jsonToObject(json,Map.class);
		int retCode = (Integer)map.get("retCode");
		System.out.println(retCode);
	}
}
