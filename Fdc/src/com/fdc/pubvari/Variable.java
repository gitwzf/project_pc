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
	//����ʼ��ȫ�ֱ���
	    //�û�
//	public static Map<String,User> map_user=new HashMap<String, User>();
	public static Map<String,User> map_actuser=new HashMap<String, User>();
	    //�
	public static Map<String,Activity> map_activ=new HashMap<String, Activity>();
	   //����ǩ�������
	public static Map<String,Apply> map_apply=new HashMap<String, Apply>();
	//��ɽ�����
	public static ArrayList<User> list_sgameuser=new ArrayList<User>();
	//��ɽ�ÿҳ����
	public static int number_pagesgame=20;
	 
	//����Ա
	public static Map<String,String> manager_user=new HashMap<String, String>();
	static{
		manager_user.put("admin1234!", "admin");
	}
	
	//mysql���ݿ�
    public String database="fangdc";
    public String port="3306";
    public String username="root";
    public String password="12345678";

}
