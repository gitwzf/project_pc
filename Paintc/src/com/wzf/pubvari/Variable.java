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
	//服务器域名
	public String URL="http://ecs.touclick.com";
	//阿里云域名
	public String aliyun_url="http://motepic.oss.aliyuncs.com";
	
	public static final String bucketName = "motepic";
	public static final String ACCESS_ID = "XQlr8x1MMQM4PVEm";
	public static final String ACCESS_KEY = "GfIBARPMtrfPW8C3NQxIafez79iGqa";
	public static final String OSS_ENDPOINT = "http://oss.aliyuncs.com/";
	
	//微信公众账号
	public String weixin_name="爱童学";
	public static String simple_wx_name="painting";
	 //包路径
	public String project=Variable.class.getClassLoader().getResource("").getPath().split("webapps/")[1].split("/WEB-INF")[0];
	public String project_path="D:/Tomcat 6.0/webapps/"+project;

	  //mysql数据库
    public String database="db_appraise";
    public String port="3306";
    public String username="root";
    public String password="12345678";
    
    
    //分页
        //小模特
    public static int items=20;
        //小画家
    public static int items_p=10;
        //画作比赛
    public static int items_pc=20;
     
  //图片、音频地址
		//wabacus
public String wa_pic_path="http://ecs.touclick.com/image/"+simple_wx_name+"/";

//抽奖奖品编号跟图片对应
public static Map<String,String> map_ranktype=new HashMap<String, String>();
static{
map_ranktype.put("1", "0");//应该包括所有状态，不然会被认为不存在而让重登
map_ranktype.put("2", "1");
map_ranktype.put("3", "2");
map_ranktype.put("0", "3");
map_ranktype.put("-2", "-1");
map_ranktype.put("-1", "4");
}

//问卷
    public static Map<String,Askpage> map_askpaper=new HashMap<String, Askpage>();
//小画家比赛
    public static Competepaint vobj_competepaint=null;
     //比赛画作   画家
    public static ArrayList<Workss> array_competepaint=new ArrayList<Workss>();
    public static Map<String,Manageruser> map_compete_user=new HashMap<String, Manageruser>();
    
  //所有投票用户  out of memeroy!
  ///  public static Map<String,Voteuser> map_voteuser=new HashMap<String, Voteuser>();
    //变动过的投票用户
    public static Map<String,Voteuser> map_actvoteuser=new HashMap<String, Voteuser>();
    //票数
    public static long number_vote=0l;
    //说明页缓存
    public static Map<String,Infopage> map_pageinfo=new HashMap<String, Infopage>();
    
  //upload
    public static String up_pic_path="D:/Tomcat 6.0/webapps/file/image/"+simple_wx_name;
    public static String up_pic_path_cmd="D:\\\\\"Tomcat 6.0\"\\webapps\\file\\image\\"+simple_wx_name;
    
    static{//1.upload上传需确定有该路径（包括子文件）
		File ff0=new File(up_pic_path);
		  System.out.println(ff0.mkdirs());
	}
}
