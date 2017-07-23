package com.easyop.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;


public class Dbcon {
	Logger log=Logger.getLogger("logfile");
	private String database="dk";
	private String url="jdbc:mysql://localhost:3306/";//Êý¾Ý¿âÃû
	private String username="root";
	private String password="12345678";
	private Connection conn;
	private Statement stat;

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
	
	public ArrayList<String> getTabTitles(String table,int[] ex){
		statment();
		String sql="show full columns from "+table;
		ArrayList<String> array=new ArrayList<String>();
		ResultSet re;
		try {
			re=stat.executeQuery(sql);
			int i=1;
			while(re.next())
				if(!isexis(ex,i++))
				array.add(re.getString("Comment"));
		} catch (SQLException e) {
			
			log.error("error:", e);
		}
		return array;
	}
	public boolean isexis(int[] ex,int i){
		for(int j:ex)
			if(j==i)return true;
		return false;
	}
}
