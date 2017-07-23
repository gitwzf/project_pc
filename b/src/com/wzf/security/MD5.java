package com.wzf.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static String Md5(String plainText ) {
		String str="";
		try { 
		MessageDigest md = MessageDigest.getInstance("MD5"); 
		md.update(plainText.getBytes()); 
		byte b[] = md.digest(); 

		int i; 

		StringBuffer buf = new StringBuffer(""); 
		for (int offset = 0; offset < b.length; offset++) { 
		i = b[offset]; 
		if(i<0) i+= 256; 
		if(i<16) 
		buf.append("0"); 
		buf.append(Integer.toHexString(i)); 
		} 

		str=buf.toString();//32位的加密 

	//	str=buf.toString().substring(8,24);//16位的加密 

		} catch (NoSuchAlgorithmException e) { 
		 
		e.printStackTrace(); 
		} 
		return str;
		} 
}
