package pubvari;

import java.io.File;


public class Variable {
	//服务器域名  
	public static String URL="http://ecs.touclick.com";
	public static String URL0="http://ecs.touclick.com/we";
	//微信公众账号
	public static String weixin_name="非公党刊";
	       //图片、音频地址
	  			//wabacus
	      public static String wa_pic_path=URL+"/image/dk/";
	       		//upload
	       public static String up_pic_path="\\file\\image";
	       public static String up_audio_path="\\file\\audio";
	//回复图文jsp
	public static String url="http://dkbbs.touclick.com/mobile/Index.aspx";
	public static String news_item="新闻</option><option value=\"\">请选择</option><option value=\"1\" selected='selected'>活动</option><option value=\"3\">抽奖链接</option><option value=\"4\">网络投票";
	//微信access_token  从数据库取
    public static String appid="wx8e7a17f471ce6f5a";
    public static String appsecret="833f8a0ef34c15e5660ea445e14cf920";
    //mysql数据库
    public static String database="w_1";
    public static String host="127.0.0.1";
    public static String port="3306";
    public static String username="root";
    public static String password="12345678";
    
    
    
	
	//servlet 包路径
	public String project=Variable.class.getClassLoader().getResource("").getPath().split("webapps/")[1].split("/WEB-INF")[0];
	public String project_path="D:/Tomcat 6.0/webapps/"+project;
	
	       public static String access_token=null;  
	       
	      
	             
	             static{//1.upload上传需确定有该路径（包括子文件）
		        		File ff0=new File(up_pic_path);
		        		  System.out.println(ff0.mkdirs());
		        		  File ff00=new File(up_audio_path);
		        		  System.out.println(ff00.mkdirs());
		        		  System.setProperty ("jsse.enableSNIExtension", "false");
		        	}
}
