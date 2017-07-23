package com.wzf.method;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wzf.model.Province;
import com.wzf.pubvari.Variable;

public class Dbconn {
	Variable vari=new Variable();
	Logger log=Logger.getLogger("logfile");
	private String database="db_appraise";
	private String url="jdbc:mysql://localhost:3306/";//数据库名
	private String username="root";
	private String password="12345678";
	private Connection conn;
	private Statement stat;
	
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}	
	}
	private void connection(){
		try {
			conn=DriverManager.getConnection(url+database,username,password);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	private void statment(){
		 connection();
		 try {
			stat=conn.createStatement();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void setBlobData(){
		statment();
		String sql="insert into testtable values(3,'gg','购物街')";
		try {
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public ArrayList<Province> getProvinceList(){
		statment();
		String sql="select province,count(1) as aa from wxfans group by province";
		ResultSet re;
		ArrayList<Province> array=new ArrayList<Province>();
		Province p=null;
		try {
			re=stat.executeQuery(sql);
			while(re.next()){
				p=new Province(re.getString("province"), re.getString("aa"));
			array.add(p);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return array;
	}
	
	public void getBlobClobData(){
		statment();
		String sql="select * from testtable";
		ResultSet re;
		try {
			Blob b = null ;
			re=stat.executeQuery(sql);
			while(re.next()){
				b=re.getBlob(3);
			}
		try {
			System.out.println(new String(b.getBytes(1, (int) b.length()),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public void addvoteusers(ArrayList<Map<String,String>> m,String table){
		statment();
		try {
			stat.executeUpdate(new Dbeditor().BatchInsertSql(m, table));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
 public boolean isModelMatchTable(){
	 statment();
	 boolean flag=true;
	 String sql="show tables";
	 ResultSet re;
	 ArrayList<String>arr=new ArrayList<String>();
	 ArrayList<String>arr1=new ArrayList<String>();
	 String classpath;
	 try {
		re=stat.executeQuery(sql);
		while(re.next()){
			arr.add(re.getString("Tables_in_"+database));
		}
		for(String a:arr){
			sql="show full columns from "+a;
			re=stat.executeQuery(sql);
			while(re.next()){
				arr1.add(re.getString("Field"));
			}
			classpath=vari.map_modeltable.get(a);
			if(classpath!=null){
					try {
					Class class2 = Class.forName(classpath);
					 Object object = class2.newInstance();
					 Field[] fields=object.getClass().getDeclaredFields();
					 for(Field field:fields){
						 if(!arr1.contains(field.getName())){
							 flag=false;log.info("model&table：表"+a+"跟类"+classpath+"  "+field.getName()+"字段不完全匹配"); break;
						 }
					 }
					} catch (InstantiationException e) {
						
						log.info(e);
					} catch (IllegalAccessException e) {
						
						log.info(e);
					}
				 catch (ClassNotFoundException e) {
					
					 log.info(e);
				}
			}
			else{log.info("model&table：公共类中没有"+a+"表对应的model类");}
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally{
		try {
			stat.close();
			conn.close();
		} catch (SQLException e) {
			
			log.info(e);
		}
	}
		 return flag;
	 }
	
	
	
	
	
	
	public void addSs(String s1,String s2){
		statment();
		String tim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String sql="";
		String fid="0";
		ResultSet re;
		try{//加父层
			sql="select * from t7typetwo where hierarchy=0 and name='"+s1+"'";
			re=stat.executeQuery(sql);
			while(re.next())fid=re.getString("id");
			if("0".equals(fid)){
				sql="insert into t7typetwo(hierarchy,name,timadd) values(0,'"+s1+"','"+tim+"')";
				stat.executeUpdate(sql);
				sql="select * from t7typetwo where hierarchy=0 and name='"+s1+"'";
				re=stat.executeQuery(sql);
				while(re.next())fid=re.getString("id");
			}
			
			//加子层
			sql="insert into t7typetwo(hierarchy,fid,name,timadd) values(1,"+fid+",'"+s2+"','"+tim+"')";
			stat.executeUpdate(sql);
		}
		catch(SQLException e){e.printStackTrace();}
	}
	
	public String addFirst(String str){
		statment();
		String tim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String sql="";
		ResultSet re;
		String maxid="0";
		try{
			sql="insert into t1typeone(hierarchy,name,timadd) values(0,'"+str+"','"+tim+"')";
			stat.executeUpdate(sql);
			
			sql="select max(id) as aa from t1typeone";
			re=stat.executeQuery(sql);
			while(re.next()){
				maxid=re.getString("aa");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return maxid;
	}
	
	public String addSecond(String fid,String str){
		statment();
		String tim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String sql="";
		ResultSet re;
		String maxid="0";
		try{
			sql="insert into t1typeone(hierarchy,fid,name,timadd) values(1,"+fid+",'"+str+"','"+tim+"')";
			stat.executeUpdate(sql);
			
			sql="select max(id) as aa from t1typeone";
			re=stat.executeQuery(sql);
			while(re.next()){
				maxid=re.getString("aa");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return maxid;
	}
	
	public void addThird(String sid,String str){
		statment();
		String tim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String sql="";
		ResultSet re;
		try{
			sql="insert into t1typeone(hierarchy,fid,name,timadd) values(2,"+sid+",'"+str+"','"+tim+"')";
			stat.executeUpdate(sql);
			
		}catch(SQLException e){e.printStackTrace();}
	}
}
