package com.nxm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	public static boolean matchAny(String src, String[] regs){
		for(String reg : regs){
			Matcher matcher = Pattern.compile(reg).matcher(src);
			if(matcher.matches())return true;
		}
		return false;
	}
}
