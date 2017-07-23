package com.unit.change;
/**
 * @author WZF
 *
 */
public class Changekinds {
	
	/**
	 * 
	 * @param  封装对象 
	 * @return 必须有返回 ：0或具体值
	 */
	public static int returnInt(Object o){
		if(o==null)return 0;
		String ostring=o.toString();
		for(int i=0;i<ostring.length();i++)
			if(!Character.isDigit(ostring.charAt(i)))return 0;
		return Integer.parseInt(ostring);
	}
	
	/**
	 *@param 处理对象
	 *@return 返回空字符串或具体值 
	 */
	public static String returnStr(Object o){
		if(o==null)return "";
		return o.toString();
	}
	
	
	

}
