package pubvari;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class Variable {
	/*注意：2.index.jsp需要手动复制  ：添加<%session.setMaxInactiveInterval(-1);%>
	 *     3.修改log4j输出文件地址
	 *     4.wabacus文件需另行修改1.添加xml文件  2.ueditor添加 及xml中修改
	 * 
	 * */
	//服务器域名
	public String URL="http://xiaowangzi.touclick.com/Westruts2";
	//jsp
	public String url="http://dkbbs.touclick.com/mobile/Index.aspx";
	public String news_item="新闻</option><option value=\"1\">活动</option><option value=\"3\">抽奖链接</option><option value=\"4\">网络投票";
	
	
	
	//servlet
	        //包路径
//	public String project=Variable.class.getClassLoader().getResource("").getPath().split("webapps/")[1].split("/WEB-INF")[0];
//	public String project_path="D:/Tomcat 6.0/webapps/"+project;
	
	//微信公众账号
	public String weixin_name="非公党刊";
	
	       //图片、音频地址
	  			//wabacus
	      public String wa_pic_path="http://xiaowangzi.touclick.com/image/dk/";
	       		//ueditor
	   //    public String ue_pic_path="http://xiaowangzi.touclick.com/uploadxwz/";
	       		//upload
	       public static String up_pic_path="D:/Tomcat 6.0/webapps/file/image/dk";
	       public static String up_audio_path="D:/Tomcat 6.0/webapps/file/audio/dk";
	       
	       //mysql数据库
	             public String database="w_1";
	             public String port="3306";
	             public String username="root";
	             public String password="12345678";
	             
	           //天气对应
		   	     public static String[][] array_weather_sunny;
		   	//时况
		   	public static Map<String,String> map_weather_l=new HashMap<String, String>();
		   	     //风向风力
		   		public static String[] array_weather_wind=new String[]{
		   			"无持续风向","东北风","东风","东南风","南风","西南风","西风","西北风","北风","旋转风"};
		   		public static String[] array_weather_mh=new String[]{
		   			"微风","3-4级","4-5级","5-6级","6-7级","7-8级","8-9级","9-10级","10-11级","11-12级"};       
	             
	             
	             static{//1.upload上传需确定有该路径（包括子文件）
	            	 
	            	 array_weather_sunny=new String[][]{
	     	   				{"晴","多云","阴","阵雨","雷阵雨","雷阵雨伴有冰雹","雨夹雪","小雨","中雨","大雨"},
	     	   				{"暴雨","大暴雨","特大暴雨","阵雪","小雪","中雪","大雪","暴雪","雾","冻雨"},
	     	   				{"沙尘暴","小到中雨","中到大雨","大到暴雨","暴雨到大暴雨","大暴雨到特大暴雨","小到中雪","中到大雪","大到暴雪","浮尘"},
	     	   				{"扬沙","强沙尘暴","","","","","","","",""},
	     	   				{"","","","","","","","","",""},
	     	   				{"","","","霾","","","","","",""},
	     	   				{"","","","","","","","","",""},
	     	   				{"","","","","","","","","",""},
	     	   				{"","","","","","","","","",""},
	     	   				{"","","","","","","","","","无"}};
	            	 
		        		File ff0=new File(up_pic_path);
		        		  System.out.println(ff0.mkdirs());
		        		  File ff00=new File(up_audio_path);
		        		  System.out.println(ff00.mkdirs());
		        		  System.setProperty ("jsse.enableSNIExtension", "false");
		        	}
}
