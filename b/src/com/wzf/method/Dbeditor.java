package com.wzf.method;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.wzf.model.User;
/**
 * �����ݷ���javabean�в���������ȷһ������:���� = [Map��ֵ��]��
 * 
 * */
public class Dbeditor {
	
	 /**
	  *  ת������Ϊ<String,String>��ֵ�ԣ�����ִ�ж����sql��
	  * @param o ���������������������������ݿ����Ӧ��
	  * @return  ���ض�Ӧ��ֵ��
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
	  * ����sql���
	  * @param m  ������ֶμ�ֵ��<String,String>��ֵ�ԣ�
	  * @param table ����
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
	  * ��������sql���
	  * @param m  ������ֶμ�ֵ��<String,String>��ֵ�ԣ�
	  * @param table ����
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
	  * �޸�sql���
	  * @param m �޸��ֶΣ�<String,String>��ֵ�ԣ�
	  * @param table ����
	  * @param m0 �޸�����
	  */
	 public String UpdateSql(Map<String,String> m,String table,Map<String,String> m0){
		 String items="",condition="";
		 ArrayList<Map.Entry<String, String>> map=new ArrayList<Map.Entry<String, String>>(m.entrySet());
			for(Map.Entry<String, String>entry:map){
				//�Ƿ�Ӹ�null�жϣ����ڿ�ֵ���޸�
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
	  * �����޸�sql���
	  * @param m �޸��ֶΣ�<String,String>��ֵ�ԣ�
	  * @param table ����
	  * @param m0 �޸�����
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
	  * ��ѯsql��䣨������
	  * @deprecated
	  * @param m ��ѯ�ֶ�
	  * @param table ����
	  * @param m0 ��ѯ����
	  * @param orderby ������ֶε�������<String,String>��ֵ�ԣ�
	  * @return ����map������
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
	  * ɾ��sql���
	  * @param table ����
	  * @param m0 ɾ������
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
	  * ����ɾ��sql���  �����1000��Ϊһ��ִ������
	  * @param table ����
	  * @param m0 ɾ������
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
