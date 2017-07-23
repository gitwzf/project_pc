package com.ssh.pubvari;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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

import com.ssh.model.User;
import com.ssh.pubvari.Variable;

public class Dbconn {
	Variable vari=new Variable();
	Logger log=Logger.getLogger("logfile");
	private String database="test";
	private String url="jdbc:mysql://localhost:3306/";//Êý¾Ý¿âÃû
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
	
	public Map<String,User> getUsers(){
		statment();
		String sql="select * from user where status=1";
		Map<String,User> map=new HashMap<String, User>();
		User u;
		ResultSet re;
		try {
			re=stat.executeQuery(sql);
			while(re.next()){
				u=new User(re.getString("email"), re.getString("pass"));
				map.put(re.getString("email"),u);
			}
		} catch (SQLException e) {
			
			log.error("error:"+e);
		}finally{
			   try {
				    stat.close();
				  conn.close();
				   } catch (SQLException e) {
				    log.info(e);
				   }
				  }
		return map;
	}
	
}
