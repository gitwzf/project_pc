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
	
	
	
   //预报
	public static Map<String,String> map_weather_f=new HashMap<String, String>();
	     //天气对应
	     public static String[][] array_weather_sunny;
	//时况
	public static Map<String,String> map_weather_l=new HashMap<String, String>();
	     //风向风力
		public static String[] array_weather_wind=new String[]{
			"无持续风向","东北风","东风","东南风","南风","西南风","西风","西北风","北风","旋转风"};
		public static String[] array_weather_mh=new String[]{
			"微风","3-4级","4-5级","5-6级","6-7级","7-8级","8-9级","9-10级","10-11级","11-12级"};
	
	static{
		map_weather_f.put("fa", "昼气");
		map_weather_f.put("fb", "夜气");
		map_weather_f.put("fc", "昼温");
		map_weather_f.put("fd", "夜温");
		map_weather_f.put("fe", "昼风向");
		map_weather_f.put("ff", "夜风向");
		map_weather_f.put("fg", "昼风力");
		map_weather_f.put("fh", "夜风力");
		map_weather_f.put("fi", "日出|日落");
		
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
		
		map_weather_l.put("l1", "当前温度");
		map_weather_l.put("l2", "当前湿度");
		map_weather_l.put("l3", "当前风力");
		map_weather_l.put("l4", "当前风向");
		map_weather_l.put("l5", "");
		map_weather_l.put("l6", "");
		map_weather_l.put("l7", "实况发布时间");
		
		
	}
	
}
