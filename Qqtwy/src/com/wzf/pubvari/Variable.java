package com.wzf.pubvari;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.wzf.pubvari.Variable;

public class Variable {
	//����������
	public String URL="http://ecs.touclick.com";
	//tomcatĿ¼
	public static String str_local_dir="D:/Tomcat 6.0/webapps/";
	
	
	// ΢�Ź����˺�
	public String WX_NAME = "Ǯ����";
	public static String SIMPLE_WX_NAME = "qjwb";
	//jsp
	//public String news_item="΢�Ķ�</option><option value=\"1\">΢����</option><option value=\"2\">΢����</option><option value=\"3\">�齱����";
	public String url=URL+"/Qjwbqtwy/Index";
	public String news_item="΢�Ķ�</option><option value=\"\">��ѡ��</option><option value=\"3\" selected='selected'>�齱����";
	
	
	// cookie�˻�
	public String COOKIE_NAME = SIMPLE_WX_NAME + "clientuser";// �û���
	public String COOKIE_ACCOUNT = SIMPLE_WX_NAME + "client";// �˺�

	// ��·��
	public String project = "Qqtwy";
//	public String project_path = "D:/Tomcat 6.0/webapps/" + project;

	// mysql���ݿ�
	public static String database = "qjwb_qtwy";
	public static String port = "3306";
	public static String username = "root";
	public static String password = "12345678";
	public static String IP="127.0.0.1";
	
	//ͼƬ����Ƶ��ַ
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
	
public  static //ҳ��
Map<String, String> map_key = new HashMap();

	static{//1.upload�ϴ���ȷ���и�·�����������ļ���
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
