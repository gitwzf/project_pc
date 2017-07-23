package com.unit.pubvari;

import java.util.HashMap;
import java.util.Map;

public class Variable {
	//ENCODE
	public static final String UTF8="utf-8";
	public static final String GB2312="gb2312";
	public static final String ISO88591="iso-8859-1";
	public static Map codeTypeMap=new HashMap<String,String>();
	static{
	codeTypeMap.put(UTF8, UTF8);
	codeTypeMap.put(GB2312, GB2312);
	codeTypeMap.put(ISO88591, ISO88591);
	}
	
	//METHOD URLCONN
	public static final String METHOD_GET="GET";
	public static final String METHOD_POST="POST";

}
