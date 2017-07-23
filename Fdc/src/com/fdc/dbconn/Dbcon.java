package com.fdc.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import org.apache.log4j.Logger;

import com.fdc.model.Activity;
import com.fdc.model.Apply;
import com.fdc.model.Tabletitle;
import com.fdc.model.User;
import com.fdc.pubvari.Variable;

public class Dbcon {
	Logger log=Logger.getLogger("logfile");
	Variable vari=new Variable();
//	private String database=vari.database;
//	private String url="jdbc:mysql://localhost:"+vari.port+"/";//数据库名
//	private String username=vari.username;
//	private String password=vari.password;
	private Connection conn;
	private Statement stat;
//	
//	public String getDatabase() {
//		return database;
//	}
//	public void setDatabase(String database) {
//		this.database = database;
//	}
//	static{
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			
//			e.printStackTrace();
//		}	
//	}
//	private void connection(){
//		try {
//			conn=DriverManager.getConnection(url+database,username,password);
//		} catch (SQLException e) {
//			
//			log.error("error:",e);
//		}
//		                 
//	}
//	private void statment(){//预编译的话 直接用conn
//		 connection();
//		 try {
//			stat=conn.createStatement();
//		} catch (SQLException e) {
//			
//			log.error("error:",e);
//		}
//	}
	
	private void statment(){//预编译的话 直接用conn
		 try {
			 conn=getConnection();
			stat=conn.createStatement();
		} catch (SQLException e) {
			
			log.error("error:",e);
		}
	}
	
	 public Connection getConnection(){  
	        Context context;
	        DataSource ds = null;
			try {
				context = new InitialContext();
	        // 获取数据源  
	         ds = (DataSource) context.lookup("java:comp/env/jdbc/MySQLfdc");  
			} catch (NamingException e) {
				
				log.error("error:", e);
			} 
	        // 获取数据库连接  
	        Connection conn;
			try {
				conn = ds.getConnection();
	        if (conn != null && !conn.isClosed()) {  
	            return conn;  
	        } else {  
	            return null;  
	        } 
			} catch (SQLException e) {
				
				log.error("error:", e);
			} 
			return null;
	    } 
	 
	 public boolean addSgame(String userid,String fuserid){
		 statment();
		 //一人只能助力一个
		 String sql="select fuserid from specialgame where userid="+userid;
		 ResultSet re;
		 boolean flag=true;
		 try {
			re=stat.executeQuery(sql);
			while(re.next())
				flag=false;
			if(flag){
				sql="replace into specialgame(userid,fuserid) values('"+userid+"','"+fuserid+"')";
				 isUpdate(sql);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 return flag; 
	 }
	 
	 public ArrayList<User> getAllSgameUser(){
		 statment();
		 ArrayList<User> array=new ArrayList<User>();
		 User u=null;
		 ResultSet re;
		 String sql="select a.*,count(*) as aa,b.fuserid from user a left join specialgame b on a.id=b.fuserid " +
		 		"where b.status=1 group by b.fuserid order by aa desc limit 0,1500 ";
		 try {
			re=stat.executeQuery(sql);
			while(re.next()){
				u=new User(re.getString("id"), re.getString("name"), re.getString("tel"),
						re.getString("gender"), re.getString("iswish"), re.getString("fuserid"),"11" );
				u.setNumbersvote(re.getString("aa"));
				array.add(u);
			}
			
		}catch (SQLException e) {
			   
			   log.error("error:", e);log.info(sql);
			  }finally{
			   try {
			    stat.close();
			  conn.close();
			   } catch (SQLException e) {
			    log.info(e);
			   }
			  }
		  return array; 
	 }
	 
	 public User getUser(String tel,String name){
		 statment();
		 String nam="";
		 if(name!=null)nam=" and a.name='"+name+"'";
		 String sql="select a.*,b.fuserid,b.status as ss from user a left join specialgame b on a.id=b.userid where a.tel="+tel+nam;
		 ResultSet re;
		 User u=null;
		 try {
			re=stat.executeQuery(sql);
			while(re.next()){
				u=new User(re.getString("id"),re.getString("name") , re.getString("tel"), 
						re.getString("gender"), re.getString("iswish"), re.getString("fuserid"), re.getString("ss"));
			}
			if(u!=null){
				sql="select ifnull(count(*),0) as aa from specialgame where fuserid="+u.getId();
				re=stat.executeQuery(sql);
				while(re.next())
					u.setNumbersvote(re.getString("aa"));
			}
		}catch (SQLException e) {
			   
			   log.error("error:", e);log.info(sql);
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
	 
	 public void updateActUser(Map<String,User> map){
		 statment();
		 String sql="";
		 try {
		 for(User u:map.values()){
			 if(u.getFuserid()!=null&&u.getSstatus()!=null&&u.getSstatus().equals("1")){
				 sql="replace into specialgame(userid,fuserid) values('"+u.getId()+"','"+u.getFuserid()+"')";
			    	stat.executeUpdate(sql);
			 }
		 }
		 } catch (SQLException e) {
			   
			   log.error("error:", e);log.info(sql);
			  }finally{
			   try {
			    stat.close();
			  conn.close();
			   } catch (SQLException e) {
			    log.info(e);
			   }
			  }
		 
	 }
	 
	 public boolean delByFirst(String svalue,String table,String field){
			statment();
			if(field==null||"".equals(field))return false;
			String sql="delete from "+table+" where "+field+"='"+svalue+"'";
			return isUpdate(sql);
		}
	 public void AddOrUpdate(String[] str,String table,ArrayList<Tabletitle> titles){
			statment();
			String s="",t="";
			for(String ss:str)
				s+=",'"+ss+"'";
			for(Tabletitle tt:titles )
			    t+=","+tt.getName();
			s=s.substring(1);t=t.substring(1);
			
			String sql="replace into "+table+"("+t+") values("+s+")";
			isUpdate(sql);
			
		}
	 public ArrayList<String[]> getDatalist(String table,String field,String value){
			statment();
			String f="";
			if(field!=null&&!"".equals(field))f=" where "+field+" like '%"+value+"%'";
			String sql="select * from "+table+f;
			String sql0="show full columns from "+table;
			ArrayList<String[]> array=new ArrayList<String[]>();
			ResultSet re;
			try {
				int i=0;
				re=stat.executeQuery(sql0);
				while(re.next())
					i++;
				String[] s=new String[i];
				re=stat.executeQuery(sql);
					while(re.next()){
						s=new String[i];
					for(int j=0;j<i;j++ ){
						s[j]=re.getString(j+1);
					}
					array.add(s);
				}
			} catch (SQLException e) {
				
				log.error("error:"+e);
			}
			return array;
		}
	 public ArrayList<Tabletitle> getTabTitles(String table,int[] ex){
			statment();
			String sql="show full columns from "+table;
			ArrayList<Tabletitle> array=new ArrayList<Tabletitle>();
			Tabletitle t=null;
			ResultSet re;
			try {
				re=stat.executeQuery(sql);
				int i=1;
				while(re.next())
					if(!isexis(ex,i++)){
						t=new Tabletitle();
						t.setComment(re.getString("Comment").equals("")?re.getString("Field"):re.getString("Comment"));
						
						Pattern p=Pattern.compile("(\\d+)");
						Matcher matcher=p.matcher(re.getString("Type"));
					if(matcher.find())t.setLength(matcher.group());
					else
						t.setLength("0");
						
						t.setName(re.getString("Field"));
						t.setType(getTType(re.getString("Type")));
						
					array.add(t);}
			} catch (SQLException e) {
				
				log.error("error:", e);
			}
			return array;
		}public boolean isexis(int[] ex,int i){
			for(int j:ex)
				if(j==i)return true;
			return false;
		}public String getTType(String str){
			if(str==null)return "char";
			if(str.indexOf("int")>0)return "int";
			if(str.indexOf("datetime")>0)return "datetime";
			if(str.indexOf("date")>0)return "date";
			
			
			return "char";
		}
	 
	 
	public boolean delApply(String userid,String actid){
		statment();
		String sql="delete from apply where userid="+userid+" and actid="+actid;
		return isUpdate(sql);
	}
	
	public Apply addApply(String userid,String actid){
		statment();
		ResultSet re;
		Apply a=null;
		String sql="insert into apply(userid,actid,actname) values('"+userid+"','"+actid+"'," +
				"(select title from activity where id="+actid+"))";
		try {
			stat.executeUpdate(sql);
			sql="select * from apply where userid="+userid+" and actid="+actid;
			re=stat.executeQuery(sql);
			while(re.next())
			return	a=new Apply(re.getString("id"), re.getString("userid"), re.getString("actid"), 
						re.getString("actname"), re.getString("issignup"), re.getString("signtim"));
		} catch (SQLException e) {
			   
			   log.error("error:", e);log.info(sql);
			  }finally{
			   try {
			    stat.close();
			  conn.close();
			   } catch (SQLException e) {
			    log.info(e);
			   }
			  }
			  return a;
	}
	
	public User addUser(String name,String tel,String gender,String iswish){
		statment();
		if(name==null||tel==null)return null;
		String sql="insert into user(name,tel,gender,iswish) values('"+name+"','"+tel+"',"+gender+","+iswish+")";
      if(isUpdate(sql))
		return getUser(tel,name);
      else return null;
	}
	
	public boolean signUp(String userid,String actid,String tim){
		statment();
		String sql="update apply set issignup=1,signtim='"+tim+"' where userid="+userid+" and actid="+actid;
	  return  isUpdate(sql);
	}
	
	public Map<String,Apply> getAllApply(){
		statment();
		ResultSet re;
		Map<String,Apply> map=new HashMap<String, Apply>();
		Apply a;
		String sql="select * from apply where status=1";
		try {
			re=stat.executeQuery(sql);
			while(re.next()){
				a=new Apply(re.getString("id"), re.getString("userid"), re.getString("actid"),
						re.getString("actname"), re.getString("issignup"), re.getString("signtim"));
				map.put(a.getUserid()+";"+a.getActid(), a);
			}
		} catch (SQLException e) {
			
			log.error("error:", e);log.info(sql);
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
	
	public Map<String,Activity> getAllActiv(){
		statment();
		ResultSet re;
		Map<String,Activity> map=new HashMap<String, Activity>();
		Activity a;
		String sql="select * from activity where status=1";
		try {
			re=stat.executeQuery(sql);
			while(re.next()){
				a=new Activity(re.getString("id"), re.getString("title"), re.getString("concate"),
						re.getString("begtim").replace(".0", "").replace(" 00:00:00", ""), re.getString("endtim").replace(".0", "").replace(" 00:00:00", ""));
				map.put(a.getId(), a);
			}
		} catch (SQLException e) {
			
			log.error("error:", e);log.info(sql);
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
	
	public Map<String,User> getAllUser(){
		statment();
		ResultSet re;
		Map<String,User> map=new HashMap<String, User>();
		User u;
		String sql="select * from user where status=1";
		try {
			re=stat.executeQuery(sql);
			while(re.next()){
				u=new User(re.getString("id"), re.getString("name"), re.getString("tel"),
						re.getString("gender"), re.getString("iswish"),null,null);
				map.put(u.getTel(), u);
			}
		} catch (SQLException e) {
			
			log.error("error:", e);log.info(sql);
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
	
	public boolean isUpdate(String sql){
		statment();
		try {
			stat.executeUpdate(sql);
			return true;
		}catch (SQLException e) {
			
			log.error("error:", e);log.info(sql);return false;
		}finally{
			   try {
				    stat.close();
				  conn.close();
				   } catch (SQLException e) {
				    log.info(e);
				   }
				  }
	}

}
