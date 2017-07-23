package com.wzf.method;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

public class ConvertTheOther {
         static Logger log=Logger.getLogger("logfile");
        /**将字符串str进行urlencode编码
         * @param str 要编码的字符串 
         * @return 无效值返回空字符串
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
         
         /**将字符串str进行urldecode解码
          * @param str 要解码的字符串 
          * @return 无效值返回空字符串
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
     	
     	/**将字符串str编码从iso8859-1转成utf-8
         * @param str 要转码的字符串 
         * @return 无效值返回空字符串
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
     	
     	/**对象返回 int
    	 * @param  obj 封装对象 
    	 * @return 无效值返回0，有值返具体值
    	 */
    	public static int returnInt(Object obj){
    		if(obj==null)return 0;
    		String ostring=obj.toString();
    		for(int i=0;i<ostring.length();i++)
    			if(!Character.isDigit(ostring.charAt(i)))return 0;
    		return Integer.parseInt(ostring);
    	}
}
