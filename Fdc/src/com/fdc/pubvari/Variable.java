package com.fdc.pubvari;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fdc.model.Activity;
import com.fdc.model.Apply;
import com.fdc.model.User;

public class Variable {
	//待初始化全局变量
	    //用户
//	public static Map<String,User> map_user=new HashMap<String, User>();
	public static Map<String,User> map_actuser=new HashMap<String, User>();
	    //活动
	public static Map<String,Activity> map_activ=new HashMap<String, Activity>();
	   //报名签到情况表
	public static Map<String,Apply> map_apply=new HashMap<String, Apply>();
	//登山活动排名
	public static ArrayList<User> list_sgameuser=new ArrayList<User>();
	//登山活动每页行数
	public static int number_pagesgame=20;
	 
	//管理员
	public static Map<String,String> manager_user=new HashMap<String, String>();
	static{
		manager_user.put("admin1234!", "admin");
	}
	
	//mysql数据库
    public String database="fangdc";
    public String port="3306";
    public String username="root";
    public String password="12345678";

}
