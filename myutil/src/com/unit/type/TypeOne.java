package com.unit.type;

/**
 * @author WZF
 *
 */
public class TypeOne {
	
	private static String  VERIFY_ISINT="^[\\-1-9]\\d{0,9}";
	private static String  VERIFY_FLOAT="\\d+(\\.\\d+)?";//建议添加最大值 最小值比较  （系统位数可以考虑）
	
	/**
	 *判断字符串是否int类型 
	 */
	public static boolean isInt(String str){
		if(str==null || str.length()==0)
			return false;
		if(str.matches(VERIFY_ISINT))
			return true;
		return false;
		
	}
	
	/**
	 * @description : 返回字符串是否为空值或空
	 * @param str
	 * @return
	 * boolean
	 */
	public static boolean isEmpty(String str){
		if(str==null || str.isEmpty())
			return true;
		return false;
	}
	
	
    public static void main(String[] args) {
		System.out.println(isInt("1233333333"));
	}
}
