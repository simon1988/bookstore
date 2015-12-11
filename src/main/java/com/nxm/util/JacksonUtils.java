package com.nxm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class JacksonUtils {
	private static Logger logger=LoggerFactory.getLogger(JacksonUtils.class);
	private static ObjectMapper objectMapper = new ObjectMapper();
	private static ObjectMapper xmlMapper = new XmlMapper();
	static{
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public static String objectToJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
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
			return objectMapper.readValue(jsonString, clazz);
		} catch (Exception e) {
			logger.error("parse json string error:" + jsonString, e);
			return null;
		}
	}
	
	public static <T> T jsonToObject(String jsonString, TypeReference<T> valueTypeRef) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return objectMapper.readValue(jsonString, valueTypeRef);
		} catch (Exception e) {
			logger.error("parse json string error:" + jsonString, e);
			return null;
		}
	}
	
	public static JsonNode readJsonTree(String jsonString){
		try {
			return objectMapper.readTree(jsonString);
		} catch (Exception e) {
			logger.error("read json tree error:" + jsonString, e);
			return null;
		}
	}
	
	public static String objectToXml(Object object) {
		try {
			return xmlMapper.writeValueAsString(object);
		} catch (Exception e) {
			logger.error("write to xml string error:" + object, e);
			return null;
		}
	}
	
	public static <T> T xmlToObject(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return xmlMapper.readValue(jsonString, clazz);
		} catch (Exception e) {
			logger.error("parse xml string error:" + jsonString, e);
			return null;
		}
	}
	
	public static <T> T xmlToObject(String jsonString, TypeReference<T> valueTypeRef) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return xmlMapper.readValue(jsonString, valueTypeRef);
		} catch (Exception e) {
			logger.error("parse xml string error:" + jsonString, e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String args[]){
		Map<String,Object> map = new HashMap<>();
		map.put("retCode", 200);
		map.put("ret", "test");
		List<Object> list = new ArrayList<>();
		list.add("test1");
		list.add("test2");
		map.put("list", list);
		String json = objectToJson(map);
		System.out.println(json);
		
		json = "{\"ret\":\"test\",\"retCode\":200,\"list\":[{\"m\":\"m1\"},{\"m\":\"m2\"}]}";
		map = jsonToObject(json, new TypeReference<Map<String,Object>>(){});
		int retCode = (Integer)map.get("retCode");
		System.out.println(retCode);
		list = (List<Object>)map.get("list");
		System.out.println(list);
	}
}
