package pubvari;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class Variable {
	/*ע�⣺2.index.jsp��Ҫ�ֶ�����  �����<%session.setMaxInactiveInterval(-1);%>
	 *     3.�޸�log4j����ļ���ַ
	 *     4.wabacus�ļ��������޸�1.���xml�ļ�  2.ueditor��� ��xml���޸�
	 * 
	 * */
	//����������
	public String URL="http://xiaowangzi.touclick.com/Westruts2";
	//jsp
	public String url="http://dkbbs.touclick.com/mobile/Index.aspx";
	public String news_item="����</option><option value=\"1\">�</option><option value=\"3\">�齱����</option><option value=\"4\">����ͶƱ";
	
	
	
	//servlet
	        //��·��
//	public String project=Variable.class.getClassLoader().getResource("").getPath().split("webapps/")[1].split("/WEB-INF")[0];
//	public String project_path="D:/Tomcat 6.0/webapps/"+project;
	
	//΢�Ź����˺�
	public String weixin_name="�ǹ�����";
	
	       //ͼƬ����Ƶ��ַ
	  			//wabacus
	      public String wa_pic_path="http://xiaowangzi.touclick.com/image/dk/";
	       		//ueditor
	   //    public String ue_pic_path="http://xiaowangzi.touclick.com/uploadxwz/";
	       		//upload
	       public static String up_pic_path="D:/Tomcat 6.0/webapps/file/image/dk";
	       public static String up_audio_path="D:/Tomcat 6.0/webapps/file/audio/dk";
	       
	       //mysql���ݿ�
	             public String database="w_1";
	             public String port="3306";
	             public String username="root";
	             public String password="12345678";
	             
	           //������Ӧ
		   	     public static String[][] array_weather_sunny;
		   	//ʱ��
		   	public static Map<String,String> map_weather_l=new HashMap<String, String>();
		   	     //�������
		   		public static String[] array_weather_wind=new String[]{
		   			"�޳�������","������","����","���Ϸ�","�Ϸ�","���Ϸ�","����","������","����","��ת��"};
		   		public static String[] array_weather_mh=new String[]{
		   			"΢��","3-4��","4-5��","5-6��","6-7��","7-8��","8-9��","9-10��","10-11��","11-12��"};       
	             
	             
	             static{//1.upload�ϴ���ȷ���и�·�����������ļ���
	            	 
	            	 array_weather_sunny=new String[][]{
	     	   				{"��","����","��","����","������","��������б���","���ѩ","С��","����","����"},
	     	   				{"����","����","�ش���","��ѩ","Сѩ","��ѩ","��ѩ","��ѩ","��","����"},
	     	   				{"ɳ����","С������","�е�����","�󵽱���","���굽����","���굽�ش���","С����ѩ","�е���ѩ","�󵽱�ѩ","����"},
	     	   				{"��ɳ","ǿɳ����","","","","","","","",""},
	     	   				{"","","","","","","","","",""},
	     	   				{"","","","��","","","","","",""},
	     	   				{"","","","","","","","","",""},
	     	   				{"","","","","","","","","",""},
	     	   				{"","","","","","","","","",""},
	     	   				{"","","","","","","","","","��"}};
	            	 
		        		File ff0=new File(up_pic_path);
		        		  System.out.println(ff0.mkdirs());
		        		  File ff00=new File(up_audio_path);
		        		  System.out.println(ff00.mkdirs());
		        		  System.setProperty ("jsse.enableSNIExtension", "false");
		        	}
}
