package com.wzf.pubvari;

import java.util.HashMap;
import java.util.Map;

import com.wzf.pubvari.Variable;

public class Variable {
	//服务器域名
	public String URL="http://ecs.touclick.com";
	
	//常量      表名_字段
	public static final int POSITION_TYPE_HUNTER=0; //猎头
	public static final int POSITION_TYPE_COMPANY=1; //公司
	
	public static final String HUNTID_USERID="huntid";// 投递猎头
	public static final String POSITIONID_USERID="positionid";//投递公司
	
	// 微信公众账号
	public String WX_NAME = "钱江晚报";
	public String SIMPLE_WX_NAME = "qjwb";

	//多条目每次加载个数
	public int p_size=10;
	
	//图片、音频地址
		//wabacus
public String wa_pic_path=URL+"/image/"+SIMPLE_WX_NAME+"/";

	// 包路径
//	public String project = Variable.class.getClassLoader().getResource("")
//			.getPath().split("webapps/")[1].split("/WEB-INF")[0];
//	public String project_path = "D:/Tomcat 6.0/webapps/" + project;
     public String project="Qjwbqtwy";
     //代发简历的邮箱
     public String email_name="qtwy@8531.cn";
     public String email_pass="qwertyuiop";
     
	// mysql数据库
    public String IP="127.0.0.1";
	public String database = "qjwb_qtwy";
	public String port = "3306";
	public String username = "root";
	public String password = "12345678";
	
	//map
	//工作经验
	public static Map<String,String> map_years=new HashMap<String, String>(),
	//学历
	 map_degree=new HashMap<String, String>(),
	//年薪
	 map_salary=new HashMap<String, String>(),
	//公司规模
	  map_scale=new HashMap<String, String>(),
	//公司性质
	map_properties=new HashMap<String, String>(),
	//行业类别
	//map_typeoneid0=new HashMap<String, String>(),
	//月薪待遇
	//map_msalary=new HashMap<String, String>(),
	//工作经验要求
	map_workexperi=new HashMap<String, String>(),
	//抽奖奖品编号跟图片对应
	map_ranktype=new HashMap<String, String>();
	static{
		map_years.put("0", "在读学生");
		map_years.put("1", "应届毕业生");
		map_years.put("2", "一年以上");
		map_years.put("3", "二年以上");
		map_years.put("4", "四年以上");
		map_years.put("5", "五年以上");
		map_years.put("6", "八年以上");
		map_years.put("7", "十年以上");
		
		map_degree.put("0", "博士");
		map_degree.put("1", "硕士");
		map_degree.put("2", "大学本科");
		map_degree.put("3", "大专");
		map_degree.put("4", "高中");
		map_degree.put("5", "职高");
		map_degree.put("6", "初中");
		map_degree.put("7", "中技");
		map_degree.put("8", "中专");
		
		map_salary.put("0", "4w以下");
		map_salary.put("1", "4-7w");
		map_salary.put("2", "7-10w");
		map_salary.put("3", "10w以上");
		
		map_scale.put("0", "20人");
		map_scale.put("1", "100人");
		map_scale.put("2", "500人");
		map_scale.put("3", "1000人");
		map_scale.put("4", "1000人以上");
		
		map_properties.put("0", "内资有限责任公司");
		map_properties.put("1", "外商投资企业");
		map_properties.put("2", "外商投资合伙企业");
		map_properties.put("3", "股份有限公司");
		map_properties.put("4", "非公司制企业");
		map_properties.put("5", "个体工商户");
		map_properties.put("6", "合伙企业");
		map_properties.put("7", "大学生创业");
		
		map_workexperi.put("0", "无");
		map_workexperi.put("1", "1年以上");
		map_workexperi.put("2", "2年以上");
		map_workexperi.put("3", "3年以上");
		map_workexperi.put("4", "4年以上");
		map_workexperi.put("5", "5年以上");
		
		map_ranktype.put("1", "0");//应该包括所有状态，不然会被认为不存在而让重登
		map_ranktype.put("2", "1");
		map_ranktype.put("3", "2");
		map_ranktype.put("0", "3");
		map_ranktype.put("-2", "-1");
		map_ranktype.put("-1", "4");
	}
	
}
