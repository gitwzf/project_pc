package com.wzf.pubvari;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.wzf.model.Askpage;
import com.wzf.model.Competepaint;
import com.wzf.model.Infopage;
import com.wzf.model.Manageruser;
import com.wzf.model.Voteuser;
import com.wzf.model.Workss;

public class Variable {
	//����������
	public String URL="http://ecs.touclick.com";
	//����������
	public String aliyun_url="http://motepic.oss.aliyuncs.com";
	
	public static final String bucketName = "motepic";
	public static final String ACCESS_ID = "XQlr8x1MMQM4PVEm";
	public static final String ACCESS_KEY = "GfIBARPMtrfPW8C3NQxIafez79iGqa";
	public static final String OSS_ENDPOINT = "http://oss.aliyuncs.com/";
	
	//΢�Ź����˺�
	public String weixin_name="��ͯѧ";
	public static String simple_wx_name="painting";
	 //��·��
	public String project=Variable.class.getClassLoader().getResource("").getPath().split("webapps/")[1].split("/WEB-INF")[0];
	public String project_path="D:/Tomcat 6.0/webapps/"+project;

	  //mysql���ݿ�
    public String database="db_appraise";
    public String port="3306";
    public String username="root";
    public String password="12345678";
    
    
    //��ҳ
        //Сģ��
    public static int items=20;
        //С����
    public static int items_p=10;
        //��������
    public static int items_pc=20;
     
  //ͼƬ����Ƶ��ַ
		//wabacus
public String wa_pic_path="http://ecs.touclick.com/image/"+simple_wx_name+"/";

//�齱��Ʒ��Ÿ�ͼƬ��Ӧ
public static Map<String,String> map_ranktype=new HashMap<String, String>();
static{
map_ranktype.put("1", "0");//Ӧ�ð�������״̬����Ȼ�ᱻ��Ϊ�����ڶ����ص�
map_ranktype.put("2", "1");
map_ranktype.put("3", "2");
map_ranktype.put("0", "3");
map_ranktype.put("-2", "-1");
map_ranktype.put("-1", "4");
}

//�ʾ�
    public static Map<String,Askpage> map_askpaper=new HashMap<String, Askpage>();
//С���ұ���
    public static Competepaint vobj_competepaint=null;
     //��������   ����
    public static ArrayList<Workss> array_competepaint=new ArrayList<Workss>();
    public static Map<String,Manageruser> map_compete_user=new HashMap<String, Manageruser>();
    
  //����ͶƱ�û�  out of memeroy!
  ///  public static Map<String,Voteuser> map_voteuser=new HashMap<String, Voteuser>();
    //�䶯����ͶƱ�û�
    public static Map<String,Voteuser> map_actvoteuser=new HashMap<String, Voteuser>();
    //Ʊ��
    public static long number_vote=0l;
    //˵��ҳ����
    public static Map<String,Infopage> map_pageinfo=new HashMap<String, Infopage>();
    
  //upload
    public static String up_pic_path="D:/Tomcat 6.0/webapps/file/image/"+simple_wx_name;
    public static String up_pic_path_cmd="D:\\\\\"Tomcat 6.0\"\\webapps\\file\\image\\"+simple_wx_name;
    
    static{//1.upload�ϴ���ȷ���и�·�����������ļ���
		File ff0=new File(up_pic_path);
		  System.out.println(ff0.mkdirs());
	}
}
