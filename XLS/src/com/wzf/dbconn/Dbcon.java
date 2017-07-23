package com.wzf.dbconn;

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

import org.apache.log4j.Logger;

import com.wzf.model.AskItems;
import com.wzf.model.AskPaper;
import com.wzf.model.AskQuestion;
import com.wzf.model.AskResult;
import com.wzf.model.Askpage;
import com.wzf.model.School;
import com.wzf.pubvari.Variable;

public class Dbcon {
	Logger log=Logger.getLogger("logfile");
	Variable vari=new Variable();
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
	
	
	public Dbcon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Dbcon(String database,String IP, String port, String username, String password) {
		super();
		this.database = database;
		this.url = "jdbc:mysql://"+IP+":"+port+"/";
		this.username = username;
		this.password = password;
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
			
			log.error("error:",e);
		}
		                 
	}
	private void statment(){
		 connection();
		 try {
			stat=conn.createStatement();
		} catch (SQLException e) {
			
			log.error("error:",e);
		}
	}
	
	
	
	public void addAsk(){
		statment();
		String sql="insert into ask_suit(title) values('"+Variable.asksuit.getTitle()+"')";
		ResultSet re;
		try {
			stat.executeUpdate(sql);
			String suitid="0";
			sql="select max(id) as aa from ask_suit";
			re=stat.executeQuery(sql);
			while(re.next())
				suitid=re.getString("aa");
			
			for(Askpage askpage:Variable.array_askpage){
	System.out.println("问卷："+askpage.getAskpaper().getTitle()+"  "+askpage.getAskpaper().getBegTim());
				AskPaper askpaper=askpage.getAskpaper();
				//,'"+askpaper.getBegTim()+"','"+askpaper.getEndTim()+"'
			    sql="insert into ask_paper(suitid,title,questions,score,counttime,url,info) values("+suitid+",'"+askpaper.getTitle()+
			    "','"+(askpaper.getQuestions().equals("")?0:askpaper.getQuestions())+"','"+(askpaper.getScore().equals("")?0:askpaper.getScore())+"','"+(askpaper.getCounttime().equals("")?0:askpaper.getCounttime())+"','"+askpaper.getUrl()+"','"+askpaper.getInfo()+"')";
				stat.executeUpdate(sql);
			  String pageid="0";	
			  sql="select max(id) as aa from ask_paper";
				re=stat.executeQuery(sql);
				while(re.next())
					pageid=re.getString("aa");
	System.out.println("result.....");
				for(AskResult askresults:askpage.getAskresults()){
	 System.out.println(askresults.getTitle()+"  "+askresults.getDowngoal());
	 			sql="insert into ask_result(pageid,downgoal,upgoal,title,content,picurl,uptime) values("+pageid+",'"+(askresults.getDowngoal().equals("")?0:askresults.getDowngoal())+
	 			"','"+(askresults.getUpgoal().equals("")?0:askresults.getUpgoal())+"','"+askresults.getTitle()+"','"+askresults.getContent()+"','"+askresults.getPicurl()+"',now())";
	 			stat.executeUpdate(sql);
				}
		System.out.println("..question");
				for(AskQuestion askquestion:askpage.getAskpaper().getAskquestin()){
	System.out.println(askquestion.getQuestion());
				sql="insert into ask_question(pageid,queurl,question) values("+pageid+",'"+askquestion.getQueurl()+"','"+askquestion.getQuestion()+"')";
				stat.executeUpdate(sql);
	  			String questionid="0";
	  			sql="select max(id) as aa from ask_question";
				re=stat.executeQuery(sql);
				while(re.next())
					questionid=re.getString("aa");
					for(AskItems askitems:askquestion.getAskitems()){
	System.out.println(askitems.getContent()+"  "+askitems.getCore());
				sql="insert into ask_items(questionid,content,core,picurl) values("+questionid+",'"+askitems.getContent()+"','"+(askitems.getCore().equals("")?0:askitems.getCore())+"','"+askitems.getPicurl()+"')";
				stat.executeUpdate(sql);
					}
					
				}
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void addSchool(List<School> array){
		statment();
		int userid=0,teacid=0;
		School s=null;
		String sql="";
		ResultSet re;
		try{
      for(int i=0;i<array.size();i++){
      	s=array.get(i);
      	System.out.println(i+s.getSname()+" "+s.getAddr()+" "+s.getChuanzhen()+" "+s.getYoubian()+" "+s.getFname()+s.getFduty()+s.getFmail()+s.getFtel());
      	
      	sql="select max(id) from t1user";
      	re=stat.executeQuery(sql);
      	while(re.next()){
      		userid=re.getInt("id");
      	}
      	sql="select max(id) from t5teacher";
      	re=stat.executeQuery(sql);
      	while(re.next()){
      		teacid=re.getInt("id");
      	}
      	
      	sql="insert into t1user(id,timAdd,exdtable,exdid,UserName) values("+(++userid)+",now(),1,"+(++teacid)+")";
      	
      	
      }
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public void upDate(String sql){
		statment();
		try {
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public void methodvotes(){
		statment();
		Map<String,String> m=new HashMap<String, String>();
		String sql="select count(*) as aa,photoid from votes group by photoid";
		ResultSet re;
		try {
			re=stat.executeQuery(sql);
			while(re.next()){
				m.put(re.getString("photoid"), re.getString("aa"));
			}
			
			List<Map.Entry<String, String>> infoIds =
		        new ArrayList<Map.Entry<String, String>>(m.entrySet());
		    Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
		        public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
		            return (o1.getValue()).compareTo(o2.getValue());
		        }
		    }); 
		  int i=0;
		    for(Map.Entry<String,String>map:infoIds){
		    	sql="update modelphotos set vote="+map.getValue()+" where photoid="+map.getKey();
		    	stat.executeUpdate(sql);
		    	System.out.println(++i);
		    }
		    
		} catch (SQLException e) {
			
			log.error("error",e);
		}
		
	}
}
