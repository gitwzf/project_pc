package com.wzf.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.wzf.model.Annouce;
import com.wzf.model.Annouce_index;
import com.wzf.model.Clas;
import com.wzf.model.Ruser;
import com.wzf.model.Tans;
import com.wzf.model.Tqusans;
import com.wzf.model.User;
import com.wzf.pubvari.Variable;

public class Dbcon {
	Variable vari=new Variable();
	private String database=vari.database;
	private String url="jdbc:mysql://w.rdc.sae.sina.com.cn:"+vari.port+"/";//数据库名
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
	
	public boolean isUser(String username,String password){
		statment();
		String sql="select * from tuserr where name_tiny='"+username+"' and paswd='"+password+"'";;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		while(re.next())
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	public boolean isHaveUser(String username){
		statment();
		String sql="select * from tuserr where name_tiny='"+username+"'";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		while(re.next())
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	
	public User getUser(String username){
		statment();
		User u=new User();
		String sql="select * from tuserr where name_tiny='"+username+"'";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		while(re.next()){
			u.setId(re.getInt("id"));
			u.setUsername(re.getString("name_tiny"));
			u.setPassword(re.getString("paswd"));
			u.setHeight(re.getString("height"));
			u.setID(re.getString("cardID"));
			u.setName(re.getString("name"));
			u.setSex(re.getString("sex"));
			u.setTel(re.getString("telephone"));
			u.setAge(re.getString("age"));
			return u;
		}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Annouce_index> getAnnList(){
		statment();
		ArrayList arr=new ArrayList<Annouce_index>();
		Annouce_index a;
		String sql="select * from tannouce where status=1 order by timPub desc";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		while(re.next()){
			a=new Annouce_index();
			a.setId(re.getInt("id"));
			a.setTitle(re.getString("title"));
			arr.add(a);
		}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return arr;
	}
	
	public ArrayList<Clas> getClassList(String group,String period,String time,String class_addr,User user){
		statment();
		HashMap hm=new HashMap<String, String>();
		if(user!=null){
			int k=0;
			String sql0="select * from ruser_sign_class where userid="+user.getId();
			ResultSet re;
			try {
				re = stat.executeQuery(sql0);
			
			while(re.next()){
				hm.put(++k+"", re.getString("classid"));
			}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
		String s1="",s2="",s3="",s4="";
		if(group!=null&&!"0".equals(group))s1=" and `group0`="+group;
		if(period!=null&&!"0".equals(period))s2=" and period="+period;
		if(time!=null&&!"0".equals(time))
			if("1".equals(time))s3=" and tim_end<'12:00:00'";
			else if("2".equals(time))s3=" and tim_beg>'12:00:00' and tim_end<'18:00:00'";
			else if("3".equals(time))s3=" and tim_beg>'18:00:00'";
		if(class_addr!=null&&!"0".equals(class_addr))s4=" and class_addr='"+class_addr+"'";;
		ArrayList arr=new ArrayList<Clas>();
		Clas c;
		String sql="select * from tclass where status=2 "+s1+s2+s3+s4+" order by id";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		while(re.next()){
			c=new Clas();
			c.setClass_addr(re.getString("class_addr"));
			c.setGroup(re.getString("group0"));
			c.setId(re.getInt("id"));
			c.setPeriod(re.getString("period"));
			c.setTime_beg(re.getString("tim_beg"));
			c.setTime_end(re.getString("tim_end"));
			c.setMaxpeople(re.getString("maxpeople"));
			c.setClass_teacher(re.getString("class_teacher"));
			c.setTel_teacher(re.getString("tel_teacher"));
			c.setStatus(hm.containsValue(""+re.getInt("id"))?"1":"0");
			arr.add(c);
		}
		for(int i=0;i<arr.size();i++){//加剩余人数
			sql="select count(*) from ruser_sign_class where classid="+((Clas)arr.get(i)).getId();
			re=stat.executeQuery(sql);
			while(re.next())
				((Clas)arr.get(i)).setResidue(re.getString("count(*)"));
		}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return arr;
	}
	
	public Annouce getAnnById(int id){
		statment();
		Annouce ann=new Annouce();
		String sql="select * from tannouce where id="+id;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		
		while(re.next()){
			ann.setId(id);
			ann.setAdmid(re.getString("admid"));
			ann.setContent(re.getString("content"));
			ann.setRankid(re.getString("rankid"));
			ann.setSource(re.getString("source"));
			ann.setTimAdd(re.getString("timAdd"));
			ann.setTimPub(re.getString("timPub"));
			ann.setTitle(re.getString("title"));
		}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ann;
	}
	
	public void addEnlist(User user,String classid){
		statment();
		String tim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime());
		String sql0="insert into ruser_sign_class(userid,user_tinyName,classid,timAdd) values("+user.getId()+",'"+user.getUsername()+"',"+classid+",'"+tim+"')";
		String sql="select * from ruser_sign_class where user_tinyName='"+user.getUsername()+"' and classid='"+classid+"'";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		while(re.next())//判断有无
		{	
		sql0="update ruser_sign_class set status=1,timMdfy='"+tim+"',cntMdfy=cntMdfy+1 where user_tinyName='"+user.getUsername()+"' and classid='"+classid+"'";
			}
		stat.executeUpdate(sql0);//执行插入或修改操作
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void delRuserSign(String name,String classid){
		statment();
		String sql="delete from ruser_sign_class where user_tinyName='"+name+"' and classid='"+classid+"'";
		try {
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public ArrayList<Ruser> getRuserlist(String name){
		statment();
		Ruser r;
		ArrayList arr=new ArrayList<Ruser>();
		String sql="select a.classid,c.`group0`,c.period,c.class_num,c.tim_beg,c.tim_end,c.class_addr,c.class_teacher,c.tel_teacher,c.`status`,b.name_tiny from ruser_sign_class as a  left join tclass as c on a.classid=c.id left join tuserr as b on a.userid=b.id and b.`status`=1 where a.user_tinyName='"+name+"' order by a.timAdd desc";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		while(re.next()){
			r=new Ruser();
			r.setGroup(re.getString("group0"));
			r.setClass_addr(re.getString("class_addr"));
			r.setClass_teacher(re.getString("class_teacher"));
			r.setClassid(re.getString("classid"));
			r.setName(re.getString("name_tiny"));
			r.setPeriod(re.getString("period"));
			r.setStatus(re.getString("status"));
			r.setTel_teacher(re.getString("tel_teacher"));
			r.setTim_beg(re.getString("tim_beg"));
			r.setTim_end(re.getString("tim_end"));
			arr.add(r);
		}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return arr;
	}
	
	public void addTuserr(String tname,String name,String age,String ID,String height,String sex,String tel,String password){
		statment();
		 String tim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime());
		String sql0="select * from tuserr where name_tiny='"+tname+"'";
		String sql="insert into tuserr(name,paswd,name_tiny,telephone,age,height,sex,cardID,timAdd) values('"+name+"','"+password+"','"+tname+"',"+tel+","+age+","+height+",'"+sex+"','"+ID+"','"+tim+"')";
		try {
		ResultSet re=stat.executeQuery(sql0);
		while(re.next())
			sql="update tuserr set name='"+name+"',age="+age+",cardID="+ID+",height="+height+",sex='"+sex+"',telephone="+tel+",paswd='"+password+"' where name_tiny='"+tname+"'";
		
		stat.executeUpdate(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public ArrayList<Tqusans> getQusanslist(String page){
		statment();
		ArrayList arr=new ArrayList<Tqusans>();
		Tqusans t;
		Tans ta;
		ArrayList array;
		int index=(Integer.parseInt(page)-1)*5;
		String sql="select * from tqus_ans where tim_qus is not null order by tim_qus desc limit "+index+",5 ";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			
		while(re.next()){
			t=new Tqusans();
			t.setUsertname(re.getString("usertname"));
			t.setQ_info(re.getString("qus_info"));
			t.setQ_time(re.getString("tim_qus"));
			t.setIsAnswer(re.getString("isAnswer"));
			t.setChilds(re.getString("childids"));
			ta=new Tans();
			  ta.setA_time(re.getString("tim_answ"));
			  ta.setAdmname(re.getString("admtname"));
			  ta.setAnswer(re.getString("answer"));
			  array=new ArrayList<Tans>();
			  array.add(ta);
			  t.setTans(array);
			  arr.add(t);
		  }
	  
		
//		for(int i=0;i<arr.size();i++){
//			if(((Tqusans)arr.get(i)).getChilds()!=null&&!"".equals(((Tqusans)arr.get(i)).getChilds())) // ？？？多回复
//			{
//			String ids[]=((Tqusans)arr.get(i)).getChilds().split("\\|");
//			  for(String id:ids){
//				  sql="select * from tqus_ans where id="+id;
//				  re=stat.executeQuery(sql);
//				  while(re.next()){
//					  ta=new Tans();
//					  ta.setA_time(re.getString("tim_answ"));
//					  ta.setAdmname(re.getString("admtname"));
//					  ta.setAnswer(re.getString("answer"));
//					  array.add(ta);
//				  }
//			  }
//			  ((Tqusans)arr.get(i)).setTans(array);
//			}
//		}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return arr;
	}
	
	
	public boolean addTqus(User user,String area){
		statment();
		String tim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime());
		String sql="insert tqus_ans(userid,usertname,qus_title,qus_info,tim_qus) values("+user.getId()+",'"+user.getUsername()+"','"+area+"','"+area+"','"+tim+"')";
		try {
			stat.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();return false;
		}
	}
	
	public  int getPagenum(){
		statment();
		String sql="select count(*) from tqus_ans where tim_qus is not null";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
	
		while(re.next())
			return re.getInt("count(*)");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 1;
		
	}
}
