package com.wzf.method;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.wzf.model.User;
/**
 * 表数据放入javabean中操作（需明确一个概念:对象 = [Map键值对]）
 * 
 * */
public class Dbeditor {
	
	 /**
	  *  转换对象为<String,String>键值对（方便执行定义的sql）
	  * @param o 传入对象参数（属性名称需与数据库表格对应）
	  * @return  返回对应键值对
	  */
	 public static Map<String,String> changeMapType(Object o){
		 Field[] fields=o.getClass().getDeclaredFields();
		 Map<String,String>m=new HashMap<String, String>();
		 String key="";
		 Method method=null;
		 for(Field f:fields)
			{
			 key=f.getName();
			 try {
				method=o.getClass().getMethod("get"+Character.toUpperCase(key.charAt(0))+key.substring(1), null);
					Object value=method.invoke(o, null);
					if(value!=null)
						m.put(key, value.toString());
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
			 } catch (SecurityException e) {
				
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				
				e.printStackTrace();
			}
			
			 
			}
		 return m;
	 }
	//insert
	 /**
	  * 插入sql语句
	  * @param m  插入的字段及值（<String,String>键值对）
	  * @param table 表名
	  */
	 public String InsertSql(Map<String,String> m,String table){
		 //statment();
		 String key="",value="";
		ArrayList<Map.Entry<String, String>> map=new ArrayList<Map.Entry<String, String>>(m.entrySet());
		for(Map.Entry<String, String>entry:map){
			key+=","+entry.getKey();
			value+=",'"+entry.getValue()+"'";
		}
		key=key.substring(1);
		value=value.substring(1);
		String sql="insert into "+table+"("+key+") values("+value+")";
		return sql;
	 }
	 /**
	  * 批量插入sql语句
	  * @param m  插入的字段及值（<String,String>键值对）
	  * @param table 表名
	  */
	 public String BatchInsertSql(ArrayList<Map<String,String>> m,String table){
		 //statment();
		 String key="",value="",allval="";
		ArrayList<Map.Entry<String, String>> map0=new ArrayList<Map.Entry<String, String>>(m.get(0).entrySet());
		for(Map.Entry<String, String>entry:map0){
			key+=","+entry.getKey();
		}
		key=key.substring(1);
		
		ArrayList<Map.Entry<String, String>> map;
		for(Map<String,String> map00:m){
		map=new ArrayList<Map.Entry<String, String>>(map00.entrySet());
		value="";
		for(Map.Entry<String, String>entry:map){
			value+=",'"+entry.getValue()+"'";
		}
		value=value.substring(1);
		allval+=",("+value+")";
		}
		allval=allval.substring(1);
		String sql="insert into "+table+"("+key+") values"+allval;
		return sql;
	 }
	 //update
	 /**
	  * 修改sql语句
	  * @param m 修改字段（<String,String>键值对）
	  * @param table 表名
	  * @param m0 修改条件
	  */
	 public String UpdateSql(Map<String,String> m,String table,Map<String,String> m0){
		 String items="",condition="";
		 ArrayList<Map.Entry<String, String>> map=new ArrayList<Map.Entry<String, String>>(m.entrySet());
			for(Map.Entry<String, String>entry:map){
				//是否加个null判断？用于空值免修改
				items+=entry.getKey()+"='"+entry.getValue()+"',";
			}
			ArrayList<Map.Entry<String, String>> map0=new ArrayList<Map.Entry<String, String>>(m0.entrySet());
			for(Map.Entry<String, String>entry:map0){
				condition+=entry.getKey()+"='"+entry.getValue()+"' and ";
			}
			items=items.substring(0,items.length()-1);
			condition+="1=1";
			String sql="update "+table+" set "+items+" where "+condition;
		return sql;
	 }
	 
	 /**
	  * 批量修改sql语句
	  * @param m 修改字段（<String,String>键值对）
	  * @param table 表名
	  * @param m0 修改条件
	  */
	 public String BatchUpdateSql(Map<String,String> m,String table,ArrayList<Map<String,String>> m0){
		 String items="",condition="",allcon="";
		 ArrayList<Map.Entry<String, String>> map=new ArrayList<Map.Entry<String, String>>(m.entrySet());
			for(Map.Entry<String, String>entry:map){
				items+=","+entry.getKey()+"='"+entry.getValue()+"'";
			}
			ArrayList<Map.Entry<String, String>> map0;
			for(Map<String, String> m00:m0){
			map0=new ArrayList<Map.Entry<String, String>>(m00.entrySet());
			for(Map.Entry<String, String>entry:map0){
				condition+=" and "+entry.getKey()+"='"+entry.getValue()+"'";
			}
			items=items.substring(1);
			condition=condition.substring(4);
			allcon+=","+condition;
			}
			allcon=allcon.substring(1);
			String sql="update "+table+" set "+items+" where "+allcon;
		return sql;
	 }
	 //select
	 /**
	  * 查询sql语句（基本）
	  * @deprecated
	  * @param m 查询字段
	  * @param table 表名
	  * @param m0 查询条件
	  * @param orderby 排序的字段的序数（<String,String>键值对）
	  * @return 返回map对象列
	  */
	 public ArrayList<Map> SelectSql(Map<String,String> m,String table,Map m0,int orderby){
		 ArrayList<Map> array=new ArrayList<Map>();
		 Map<String,String> maptype;
		 String condition="",items="",tail="";
		 int i=1;
		 ArrayList<Map.Entry<String, String>> map=new ArrayList<Map.Entry<String, String>>(m.entrySet());
			for(Map.Entry<String, String>entry:map){
				items+=entry.getKey()+",";
				if(i++==orderby)tail=entry.getKey();
			}
		 ArrayList<Map.Entry<String, String>> map0=new ArrayList<Map.Entry<String, String>>(m0.entrySet());
			for(Map.Entry<String, String>entry:map0){
				condition+=entry.getKey()+"='"+entry.getValue()+"' and ";
			}
			items=items.substring(0,items.length()-1);
			condition+="1=1";
		 String sql="select "+items+" from"+table+" where "+condition+" order by "+tail;
		 while(true){
			 maptype=new HashMap<String, String>();
			 for(Map.Entry<String, String>entry:map){
			 maptype.put(entry.getKey(),"re.getstring("+entry.getKey()+")");
			 }
		 }
	 }
	 
	 //delete
	 /**
	  * 删除sql语句
	  * @param table 表名
	  * @param m0 删除条件
	  */
	 public String DeleteSql(String table,Map<String,String> m0){
		 String condition="";
		 ArrayList<Map.Entry<String, String>> map0=new ArrayList<Map.Entry<String, String>>(m0.entrySet());
			for(Map.Entry<String, String>entry:map0){
				condition+=entry.getKey()+"='"+entry.getValue()+"' and ";
			}
			condition+="1=1";
			String sql="delete from "+table+" where "+condition;
			return sql;
	 }
	 
	 /**
	  * 批量删除sql语句  （最好1000条为一次执行量）
	  * @param table 表名
	  * @param m0 删除条件
	  */
	 public String BatchDeleteSql(String table,ArrayList<Map<String,String>> m0){
		 String condition="",allcon="";
		 ArrayList<Map.Entry<String, String>> map0;
		 for(Map<String,String> m:m0){
		 map0=new ArrayList<Map.Entry<String, String>>(m.entrySet());
		 condition="";
			for(Map.Entry<String, String>entry:map0){
				condition+=" and "+entry.getKey()+"='"+entry.getValue()+"'";
			}
			condition=condition.substring(4);
			allcon+="or ("+condition+")";}
		 allcon=allcon.substring(3);
			String sql="delete from "+table+" where "+allcon;
			return sql;
	 }

}
