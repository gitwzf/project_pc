package com.wzf.pubvari;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.wzf.method.Dbconn;
import com.wzf.model.Province;

public class Variable {
	public static Dbconn db=new Dbconn();
	public static Map<String,String> map_modeltable=new HashMap<String, String>();
	static String classpag="com.wzf.model";
//	static{
//		System.out.println("111");
//		map_modeltable.put("user",classpag+"."+"User");
//		map_modeltable.put("pubid",classpag+"."+"Pub");
//		if(!db.isModelMatchTable()){System.out.println("221");}
//		else{System.out.println("222");}
//	}
	
	public static ArrayList<Province> array_province=new ArrayList<Province>();
	public static String str_province;
	
	
	
   //Ԥ��
	public static Map<String,String> map_weather_f=new HashMap<String, String>();
	     //������Ӧ
	     public static String[][] array_weather_sunny;
	//ʱ��
	public static Map<String,String> map_weather_l=new HashMap<String, String>();
	     //�������
		public static String[] array_weather_wind=new String[]{
			"�޳�������","������","����","���Ϸ�","�Ϸ�","���Ϸ�","����","������","����","��ת��"};
		public static String[] array_weather_mh=new String[]{
			"΢��","3-4��","4-5��","5-6��","6-7��","7-8��","8-9��","9-10��","10-11��","11-12��"};
	
	static{
		map_weather_f.put("fa", "����");
		map_weather_f.put("fb", "ҹ��");
		map_weather_f.put("fc", "����");
		map_weather_f.put("fd", "ҹ��");
		map_weather_f.put("fe", "�����");
		map_weather_f.put("ff", "ҹ����");
		map_weather_f.put("fg", "�����");
		map_weather_f.put("fh", "ҹ����");
		map_weather_f.put("fi", "�ճ�|����");
		
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
		
		map_weather_l.put("l1", "��ǰ�¶�");
		map_weather_l.put("l2", "��ǰʪ��");
		map_weather_l.put("l3", "��ǰ����");
		map_weather_l.put("l4", "��ǰ����");
		map_weather_l.put("l5", "");
		map_weather_l.put("l6", "");
		map_weather_l.put("l7", "ʵ������ʱ��");
		
		
	}
	
}
