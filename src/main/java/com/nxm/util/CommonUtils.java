package com.nxm.util;

public class CommonUtils {
	
	public static boolean containsAny(String word, String keywords){
		return containsAny(word, keywords, ",");
	}
	public static boolean containsAny(String word, String keywords, String delimeter){
		if(word==null||keywords==null||delimeter==null){
			return false;
		}
		for(String keyword:keywords.split(delimeter)){
			if(word.contains(keyword.trim())){
				return true;
			}
		}
		return false;
	}
	
	public static boolean inAny(String word, String keywords){
		return inAny(word, keywords, ",");
	}
	
	public static boolean inAny(String word, String keywords, String delimeter){
		if(word==null||keywords==null||delimeter==null){
			return false;
		}
		for(String keyword:keywords.split(delimeter)){
			if(word.trim().equals(keyword.trim())){
				return true;
			}
		}
		return false;
	}
}
