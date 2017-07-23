package com.wzf.method;
/*
 * @author wzf
 * 
 * */
public class Returnkinds {
	
	/**
	 * 
	 * @param  封装对象 
	 * @return 返回0或具体值
	 */
	public int returnInt(Object o){
		if(o==null)return 0;
		String ostring=o.toString();
		for(int i=0;i<ostring.length();i++)
			if(!Character.isDigit(ostring.charAt(i)))return 0;
		return Integer.parseInt(ostring);
	}
	
	
	
	
	

}
