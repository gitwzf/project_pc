package com.unit.change;
/**
 * @author WZF
 *
 */
public class Changekinds {
	
	/**
	 * 
	 * @param  ��װ���� 
	 * @return �����з��� ��0�����ֵ
	 */
	public static int returnInt(Object o){
		if(o==null)return 0;
		String ostring=o.toString();
		for(int i=0;i<ostring.length();i++)
			if(!Character.isDigit(ostring.charAt(i)))return 0;
		return Integer.parseInt(ostring);
	}
	
	/**
	 *@param �������
	 *@return ���ؿ��ַ��������ֵ 
	 */
	public static String returnStr(Object o){
		if(o==null)return "";
		return o.toString();
	}
	
	
	

}
