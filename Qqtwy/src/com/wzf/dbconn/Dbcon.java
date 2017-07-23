package com.wzf.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wzf.model.Retxt;

import com.wzf.model.Manageruser;
import com.wzf.model.Retype;
import com.wzf.pubvari.Variable;

public class Dbcon {
	Variable vari=new Variable();
	Logger log = Logger.getLogger("logfile");
	private String database=vari.database;
	private String url="jdbc:mysql://"+vari.IP+":"+vari.port+"/";//数据库名
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	private void connection(){
		try {
			conn=DriverManager.getConnection(url+database,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info(e);
		}
		
	}
	private void statment(){
		 connection();
		 try {
			stat=conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info(e);
		}
	}
	public String updateSql(String sql){
		statment();
		try {
			stat.executeUpdate(sql);
			return "11";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			   try {
				    stat.close();
				  conn.close();
				   } catch (SQLException e) {
				    e.printStackTrace();
				   }
				  }
		return "00";
	}
	public void addTxtrekey(String type,String name,String key,String keystyle,String con) throws SQLException{
		statment();
		con=con.replace("\'", "\\\'").replace(";", "|");
		String sql="insert into re_keyword(type0,name,key0,re_id,content) values('"+type+"','"+name+"','"+key+"','"+keystyle+"','"+con+"')";
		String sql0="select * from re_keyword where name='"+name+"' and key0='"+key+"'";
		ResultSet re=stat.executeQuery(sql0);
		while(re.next())
			sql="update re_keyword set type0='"+type+"',re_id='"+keystyle+"',content='"+con+"' where name='"+name+"' and key0='"+key+"'";
		stat.executeUpdate(sql);	
		  stat.close();
		  conn.close();
		}

	public void addNewsrekey(String name,String key,String keystyle,String n_id) throws SQLException{
		statment();
		key = key.toLowerCase().trim();
		n_id=n_id.replace(";", "|");
		String sql2="insert into re_keyword(type0,name,key0,re_id,content) values(1,'"+name+"','"+key+"','"+keystyle+"','"+n_id+"')";
		String sql0="select * from re_keyword where name='"+name+"' and key0='"+key+"'";
		String del_ids="0";
		ResultSet re=stat.executeQuery(sql0);
		while(re.next()){
			del_ids=re.getString("content");
			sql2="update re_keyword set re_id='"+keystyle+"',content='"+n_id+"' where name='"+name+"' and key0='"+key+"'";}
		stat.executeUpdate(sql2);
		String d_ids[]=del_ids.split("\\|");
		String sql3;
		 for(int i=0;i<d_ids.length;i++){
			 sql3="delete from pic_mus_reback where id="+Integer.parseInt(d_ids[i]);
			 stat.executeUpdate(sql3);
		 }
		  stat.close();
		  conn.close();
	}
	public String addNesItem(String type,String[] title,String[] main,String[] urlo,String pty[],String pid[],String[] url,int count){
		statment();
		String id="";
		System.out.println("tl="+title.length);
		System.out.println("ml="+main.length);
		System.out.println("uol="+urlo.length);
		System.out.println("count="+count);
		String sql0="";
		try {
			for(int i = 0;i<count;i++){
				 sql0="insert into pic_mus_reback(type,title,main,urlo,pty,pid,url) values(1,'"+title[i]+"','"+main[i]+"','"+urlo[i]+"','"+pty[i]+"','"+pid[i]+"','"+url[i]+"')";
				stat.executeUpdate(sql0);
		String sql1="select max(id) from pic_mus_reback";
		ResultSet res=stat.executeQuery(sql1);
		while(res.next())
			id+=res.getInt("max(id)")+";";
			}
		}catch (SQLException e) {
			   // TODO Auto-generated catch block
			   log.error("error:", e);log.info(sql0);
			  }finally{
			   try {
			    stat.close();
			  conn.close();
			   } catch (SQLException e) {
			    log.info(e);
			   }
			  }
		
		return id;
	}
	
	public void addMusrekey(String name,String key,String keystyle,int id) throws SQLException{
		String sql2="insert into re_keyword(type0,name,key0,re_id,content) values(2,'"+name+"','"+key+"','"+keystyle+"','"+id+"')";
		String sql0="select * from re_keyword where name='"+name+"' and key0='"+key+"'";
		String del_id="0";
		ResultSet re=stat.executeQuery(sql0);
		while(re.next()){
			del_id=re.getString("content");
			sql2="update re_keyword set re_id='"+keystyle+"',content='"+id+"' where name='"+name+"' and key0='"+key+"'";}
		stat.executeUpdate(sql2);
		    String sql3="delete  from pic_mus_reback where id="+Integer.parseInt(del_id);
		    stat.executeUpdate(sql3);
		    stat.close();
		    conn.close();
	}
	
	public int addMusItem(String type,String title,String main,String urlo,String url) throws SQLException{
		statment();
		int a=0;
		String sql0="insert into pic_mus_reback(type,title,main,urlo,url) values('"+type+"','"+title+"','"+main+"','"+urlo+"','"+url+"')";
		stat.executeUpdate(sql0);
		String sql1="select max(id) from pic_mus_reback";
		ResultSet res=stat.executeQuery(sql1);
		while(res.next())
			a= res.getInt("max(id)");
		  stat.close();
		  conn.close();
		return a;
	}
	
	public Retxt getMusarrayByname(String name) throws SQLException{
		statment();
		Retxt txt=new Retxt();
		StringBuffer buffer=new StringBuffer(),buffer1=new StringBuffer(),buffer2=new StringBuffer();
		String[] str;
		String sql0="select * from re_keyword where name='"+name+"' and type0='2'";
		ResultSet re=stat.executeQuery(sql0);
		while(re.next()){
			 txt.setName(name);
			 txt.setType("2");
			 buffer.append(re.getString("key0")+" ");
			 buffer1.append(re.getString("re_id")+" ");
			 buffer2.append(re.getString("content")+" ");
		}
		txt.setKey(buffer.toString().split(" "));
		txt.setKeystyle(buffer1.toString().split(" "));
		String[] ids=buffer2.toString().split(" ");
	String cons = null;
	      System.out.println(ids[0]+" "+ids[0].replace("|", ""));
			String sql="select * from pic_mus_reback where id='"+ids[0].replace("|", "")+"'";
			ResultSet re1=stat.executeQuery(sql);
			while(re1.next())
				cons=re1.getString("title")+"|"+re1.getString("main")+"|"+re1.getString("urlo")+"|"+re1.getString("pty")+"|"+re1.getString("pid")+"|"+re1.getString("url")+"http;";
		txt.setContent(cons);
		  stat.close();
		  conn.close();
		return txt;
	}
	
	public Retxt getTxtarrayByname(String name) throws SQLException{
		statment();
		Retxt txt=new Retxt();
		StringBuffer buffer=new StringBuffer(),buffer1=new StringBuffer();
		String[] str;
		String sql0="select * from re_keyword where name='"+name+"'";
		ResultSet re=stat.executeQuery(sql0);
		while(re.next()){
			 txt.setName(name);
			 txt.setType(re.getString("type0"));
			 buffer.append(re.getString("key0")+" ");
			 buffer1.append(re.getString("re_id")+" ");
			txt.setKey(buffer.toString().split(" "));
			txt.setKeystyle(buffer1.toString().split(" "));
			txt.setContent(re.getString("content"));
	   System.out.println("content="+re.getString("content"));
		}
		  stat.close();
		  conn.close();
		return txt;
	}
	
	public Retxt getNewsarrayByname(String name) throws SQLException{
		statment();
		Retxt txt=new Retxt();
		StringBuffer buffer=new StringBuffer(),buffer1=new StringBuffer(),buffer2=new StringBuffer();
		String[] str;
		String sql0="select * from re_keyword where name='"+name+"' and type0='1'";
		ResultSet re=stat.executeQuery(sql0);
		while(re.next()){
			 txt.setName(name);
			 txt.setType("1");
			 buffer.append(re.getString("key0")+" ");
			 buffer1.append(re.getString("re_id")+" ");
			 buffer2.append(re.getString("content")+" ");
		}
		txt.setKey(buffer.toString().split(" "));
		txt.setKeystyle(buffer1.toString().split(" "));
		String[] ids=buffer2.toString().split(" ");
		String cons="";
		//for(int i=0;i<ids.length;i++){   System.out.println("ds="+ids[i]);  单行就行
		for(String id:ids[0].split("\\|")){
			String sql="select * from pic_mus_reback where id='"+id+"'";
			ResultSet re1=stat.executeQuery(sql);
			while(re1.next())
				cons+=re1.getString("title")+"|"+re1.getString("main")+"|"+re1.getString("urlo")+"|"+re1.getString("pty")+"|"+re1.getString("pid")+"|"+re1.getString("url")+";";
		}
	//	}
		System.out.println("fircon="+cons);
		txt.setContent(cons);
		  stat.close();
		  conn.close();
		return txt;
	}
	
	public void delKeyword(String name) throws SQLException{
    	statment();
    	String id="";
    	String ids="";
    	String sql0="select * from re_keyword where name='"+name+"'";
    	ResultSet re=stat.executeQuery(sql0);
    	while(re.next()){
    		id+=re.getString("id")+";";
    		ids+=re.getString("content");
    	}
      for(String str:id.split(";")){
	String sql1="delete from pic_mus_reback where keyid='"+str+"'";
	stat.executeUpdate(sql1);
	String sql2="update virecord set mode='0' where mode='"+str+"'";
	stat.execute(sql2);
	}
	for(String str:ids.split(";")){
	String	sql1="delete from pic_mus_reback where id='"+str+"'";
		stat.executeUpdate(sql1);}
    	String sql="delete from re_keyword where name='"+name+"'";
    	stat.executeUpdate(sql);
    	  stat.close();
    	  conn.close();
    }
	
	public void setWelcome(String name) throws SQLException{
		statment();
		String sql0="update re_keyword set identity='' where identity='welcome'";
		stat.executeUpdate(sql0);
		String sql="update re_keyword set identity='welcome' where name='"+name+"'";
		stat.executeUpdate(sql);
		  stat.close();
		  conn.close();
	}
	public void setDefault(String name) throws SQLException{
		statment();
		String sql0="update re_keyword set identity='' where identity='default'";
		stat.executeUpdate(sql0);
		String sql="update re_keyword set identity='default' where name='"+name+"'";
		stat.executeUpdate(sql);
		  stat.close();
		  conn.close();
	}
	
	 public ArrayList<Retype> getRearray() throws SQLException{
			statment();
			boolean flag=false;
			Retype txt=new Retype();
			StringBuffer buffer=new StringBuffer(),buffer1=new StringBuffer(),buffer2=new StringBuffer(),buffer3=new StringBuffer();
			String[] str;
			ArrayList array=new ArrayList();
			String sql0="select * from re_keyword";
			ResultSet re=stat.executeQuery(sql0);
			while(re.next()){
				 if(!txt.name.equals(re.getString("name"))){
					 txt=new Retype(); 
					 buffer=new StringBuffer();
					 buffer1=new StringBuffer();
					 buffer2=new StringBuffer();
					 flag=true;
				 }
				 txt.setName(re.getString("name"));
				 txt.setIdentity(re.getString("identity"));
				 txt.setType(re.getString("type0"));
				 buffer.append(re.getString("key0")+" ");
				 buffer1.append(re.getString("re_id")+" ");
				 buffer2.append(re.getString("content")+" ");
				txt.setKey(buffer.toString().split(" "));
				txt.setKeystyle(buffer1.toString().split(" "));
				txt.setContent(buffer2.toString().split(" "));
		   if(flag)
				array.add(txt);
		   flag=false;
			}
			  stat.close();
			  conn.close();
			return array;
		}
		public ArrayList<Retype> getRearray(String key) throws SQLException{
			statment();
			boolean flag1=false,flag2=false;
			Retype txt=new Retype();
			StringBuffer buffer=new StringBuffer(),buffer1=new StringBuffer(),buffer2=new StringBuffer(),buffer3=new StringBuffer();
			String[] str;
			ArrayList array=new ArrayList();
			String sql0="select * from re_keyword";
			ResultSet re=stat.executeQuery(sql0);
			while(re.next()){
				System.out.println("name="+re.getString("name"));
				 if(!txt.name.equals(re.getString("name"))){
					 txt=new Retype(); 
					 buffer=new StringBuffer();
					 buffer1=new StringBuffer();
					 buffer2=new StringBuffer();
					 flag1=true;flag2=false;
				 }
				 txt.setName(re.getString("name"));
				 txt.setIdentity(re.getString("identity"));
				 txt.setType(re.getString("type0"));
				 buffer.append(re.getString("key0")+" ");
				 String[] str1=buffer.toString().split(" ");
				 for(int i=0;i<str1.length;i++)
					 if(key.equals(str1[i]))flag2=true;
				 buffer1.append(re.getString("re_id")+" ");
				 buffer2.append(re.getString("content")+" ");
				txt.setKey(buffer.toString().split(" "));
				txt.setKeystyle(buffer1.toString().split(" "));
				txt.setContent(buffer2.toString().split(" "));
		   if(flag1&&flag2)
				array.add(txt);
		   flag1=false;
			}
			  stat.close();
			  conn.close();
			return array;
		}
	
	public boolean isManageruser(String username,String pass){
		statment();
		String sql="select * from t10manager where nam_tiny='"+username+"' and paswd='"+pass+"'";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		while(re.next())
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("登陆错误：",e);
		}
		finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.info(e);
			}
		}
		return false;
	}
	
	public Manageruser getManager(String username){
		statment();
		Manageruser a = null;
		String sql="select * from t10manager where nam_tiny='"+username+"'";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		
		while(re.next()){
			a=new Manageruser();
			a.setId(re.getInt("id"));
			a.setPass(re.getString("paswd"));
			a.setPrivlg(re.getString("privlg"));
			a.setTname(re.getString("nam_tiny"));
			a.setJson_type("{id:"+a.getId()+",pass:'"+a.getPass()+"',privlg:'"+a.getPrivlg()+"',tname:'"+a.getTname()+"'}");
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("获取用户失败：", e);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.info(e);
			}
		}
		return a;
	}
}
