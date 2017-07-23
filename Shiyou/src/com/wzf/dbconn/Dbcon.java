package com.wzf.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.wzf.model.User;
import com.wzf.pubvari.Variable;


public class Dbcon {
	Variable vari=new Variable();
	Logger log = Logger.getLogger("logger");
	private String database=vari.database;
	private String url="jdbc:mysql://"+vari.IP+":"+vari.port+"/";//Êý¾Ý¿âÃû
	private String username=vari.username;
	private String password=vari.password;
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
//	private void statment0(){
//		 try {
//			 conn=DriverManager.getConnection("jdbc:mysql://42.121.58.161:3306/syw","touclick","touclick123");
//			stat=conn.createStatement();
//		} catch (SQLException e) {
//			
//			log.error("error:",e);
//		}
//	}
	public String updateSql(String sql){
		statment();
		try {
			stat.executeUpdate(sql);
			return "11";
		} catch (SQLException e) {
			
			log.error("errot:",e);log.info(sql);
		}finally{
			   try {
				    stat.close();
				  conn.close();
				   } catch (SQLException e) {
				    log.error("errot:",e);
				   }
				  }
		return "00";
	}
	
	public boolean isManageruser(String username,String password){
		statment();
		String sql="select * from t2admin where UserNam='"+username+"' and paswd='"+password+"'";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		
		while(re.next())
			return true;
		} catch (SQLException e) {
			
			log.error("errot:",e);log.info(sql);
		}finally{
			   try {
				    stat.close();
				  conn.close();
				   } catch (SQLException e) {
				    log.info(e);
				   }
				  }
		return false;
	}
	
	public User getManageruser(String username){
		statment();
		User u = null;
		String sql="select * from t2admin where UserNam='"+username+"' and statusAudit=1";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		
		while(re.next()){
			u=new User();
			u.setId(re.getString("id"));
			u.setUsername(username);
			u.setPrivlg(re.getString("authority"));
			
			
		}
		} catch (SQLException e) {
			
			log.error("errot:",e);log.info(sql);
		}finally{
			   try {
				    stat.close();
				  conn.close();
				   } catch (SQLException e) {
				    log.info(e);
				   }
				  }
		return u;
	}
	
}
