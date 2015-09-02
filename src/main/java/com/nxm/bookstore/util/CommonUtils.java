package com.nxm.bookstore.util;

public class CommonUtils {
	
	public static boolean containsAny(String word, String keywords){
		if(word==null||keywords==null){
			return false;
		}
		for(String keyword:keywords.split(",")){
			if(word.contains(keyword.trim())){
				return true;
			}
		}
		return false;
	}
	
	public static boolean inAny(String word, String keywords){
		if(word==null||keywords==null){
			return false;
		}
		for(String keyword:keywords.split(",")){
			if(word.trim().equals(keyword.trim())){
				return true;
			}
		}
		return false;
	}
}
