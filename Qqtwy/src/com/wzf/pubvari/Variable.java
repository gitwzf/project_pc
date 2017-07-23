package com.wzf.pubvari;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.wzf.pubvari.Variable;

public class Variable {
	//服务器域名
	public String URL="http://ecs.touclick.com";
	//tomcat目录
	public static String str_local_dir="D:/Tomcat 6.0/webapps/";
	
	
	// 微信公众账号
	public String WX_NAME = "钱江晚报";
	public static String SIMPLE_WX_NAME = "qjwb";
	//jsp
	//public String news_item="微阅读</option><option value=\"1\">微互动</option><option value=\"2\">微生活</option><option value=\"3\">抽奖链接";
	public String url=URL+"/Qjwbqtwy/Index";
	public String news_item="微阅读</option><option value=\"\">请选择</option><option value=\"3\" selected='selected'>抽奖链接";
	
	
	// cookie账户
	public String COOKIE_NAME = SIMPLE_WX_NAME + "clientuser";// 用户名
	public String COOKIE_ACCOUNT = SIMPLE_WX_NAME + "client";// 账号

	// 包路径
	public String project = "Qqtwy";
//	public String project_path = "D:/Tomcat 6.0/webapps/" + project;

	// mysql数据库
	public static String database = "qjwb_qtwy";
	public static String port = "3306";
	public static String username = "root";
	public static String password = "12345678";
	public static String IP="127.0.0.1";
	
	//图片、音频地址
	//wabacus
     public String wa_pic_path=URL+"/image/qjwb/";
     //ueditor
     public static String path_pic_upload="image/qjwb/upload";
		//upload
public static String pic_path="file/image/"+SIMPLE_WX_NAME;
public static String audio_path="file/audio/"+SIMPLE_WX_NAME;
//public static String up_pic_path=str_local_dir+pic_path;
//public static String up_audio_path=str_local_dir+audio_path;
	
//access_token
public static String appid="wx9cd6dae77dac3eb7";
public static String appsecret="768afece63f0ce7a8afb85ce3d14c752";
public static String access_token=null;
	
public  static //页面
Map<String, String> map_key = new HashMap();

	static{//1.upload上传需确定有该路径（包括子文件）
		File ff0=new File( str_local_dir+pic_path);
		  System.out.println(ff0.mkdirs());
		  File ff00=new File(str_local_dir+audio_path);
		  System.out.println(ff00.mkdirs());
		  System.setProperty ("jsse.enableSNIExtension", "false");
		  
		  map_key.put("GameServ","01");
		  map_key.put("HunterServ","11");
		  map_key.put("ModeltypeServ","12");
		  map_key.put("CollegeServ","21");
		  map_key.put("SpecialServ","22");
		  map_key.put("Resume","31");
		  map_key.put("Workexperi","32");
		  map_key.put("Sendposition","33");
	}
}
