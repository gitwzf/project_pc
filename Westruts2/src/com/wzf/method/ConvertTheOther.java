package com.wzf.method;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

public class ConvertTheOther {
         static Logger log=Logger.getLogger("logfile");
        /**���ַ���str����urlencode����
         * @param str Ҫ������ַ��� 
         * @return ��Чֵ���ؿ��ַ���
         * */
         public static String EncodeUtf8(String str){
     		String s="";
     		try {
     			if(str!=null) s=URLEncoder.encode(str,"utf-8");
     		} catch (UnsupportedEncodingException e) {
     			// TODO Auto-generated catch block
     			log.error("EncodeUtf8 error", e);
     		}
     		return s;
     	}
         
         /**���ַ���str����urldecode����
          * @param str Ҫ������ַ��� 
          * @return ��Чֵ���ؿ��ַ���
          * */
     	public static String DecodeUtf8(String str){
     		String s="";
     		try {
     			if(str!=null)s= URLDecoder.decode(str,"utf-8");
     		} catch (UnsupportedEncodingException e) {
     			// TODO Auto-generated catch block
     			log.error("DecodeUtf8 error", e);
     		}
     		return s;
     	}
     	
     	/**���ַ���str�����iso8859-1ת��utf-8
         * @param str Ҫת����ַ��� 
         * @return ��Чֵ���ؿ��ַ���
         * */
     	public static String ISO88591ToUtf8(String str){
     		String s="";
     		if(str!=null)
     			try {
     				s=new String(str.getBytes("iso-8859-1"),"utf-8");
     			} catch (UnsupportedEncodingException e) {
     				// TODO Auto-generated catch block
     				log.error("ISO88591ToUtf8 error",e);
     			}
     		return s;
     	}
     	
     	/**���󷵻� int
    	 * @param  obj ��װ���� 
    	 * @return ��Чֵ����0����ֵ������ֵ
    	 */
    	public static int returnInt(Object obj){
    		if(obj==null)return 0;
    		String ostring=obj.toString();
    		for(int i=0;i<ostring.length();i++)
    			if(!Character.isDigit(ostring.charAt(i)))return 0;
    		return Integer.parseInt(ostring);
    	}
}
