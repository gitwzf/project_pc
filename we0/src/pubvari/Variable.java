package pubvari;

import java.io.File;


public class Variable {
	//����������  
	public static String URL="http://ecs.touclick.com";
	public static String URL0="http://ecs.touclick.com/we";
	//΢�Ź����˺�
	public static String weixin_name="�ǹ�����";
	       //ͼƬ����Ƶ��ַ
	  			//wabacus
	      public static String wa_pic_path=URL+"/image/dk/";
	       		//upload
	       public static String up_pic_path="\\file\\image";
	       public static String up_audio_path="\\file\\audio";
	//�ظ�ͼ��jsp
	public static String url="http://dkbbs.touclick.com/mobile/Index.aspx";
	public static String news_item="����</option><option value=\"\">��ѡ��</option><option value=\"1\" selected='selected'>�</option><option value=\"3\">�齱����</option><option value=\"4\">����ͶƱ";
	//΢��access_token  �����ݿ�ȡ
    public static String appid="wx8e7a17f471ce6f5a";
    public static String appsecret="833f8a0ef34c15e5660ea445e14cf920";
    //mysql���ݿ�
    public static String database="w_1";
    public static String host="127.0.0.1";
    public static String port="3306";
    public static String username="root";
    public static String password="12345678";
    
    
    
	
	//servlet ��·��
	public String project=Variable.class.getClassLoader().getResource("").getPath().split("webapps/")[1].split("/WEB-INF")[0];
	public String project_path="D:/Tomcat 6.0/webapps/"+project;
	
	       public static String access_token=null;  
	       
	      
	             
	             static{//1.upload�ϴ���ȷ���и�·�����������ļ���
		        		File ff0=new File(up_pic_path);
		        		  System.out.println(ff0.mkdirs());
		        		  File ff00=new File(up_audio_path);
		        		  System.out.println(ff00.mkdirs());
		        		  System.setProperty ("jsse.enableSNIExtension", "false");
		        	}
}
