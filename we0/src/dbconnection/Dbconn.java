package dbconnection;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pubvari.Variable;

import model.Fans;

import model.Chatrecord;
import model.Manageruser;
import model.Pubid;
import model.Retxt;
import model.Retype;

public class Dbconn {
	Variable vari=new Variable();
	private String database=vari.database;
	private String url="jdbc:mysql://"+vari.host+":"+vari.port+"/";//数据库名
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
	
	public String[] getApp(){
		statment();
		String sql="select appid,appsecret from pubid";
		ResultSet re;
		String str[]=new String[]{"",""};
		try {
			re = stat.executeQuery(sql);
		while(re.next()){
			str[0]=re.getString("appid");
			str[1]=re.getString("appsecret");
		}
		
		} catch (SQLException e) {
			   
			 e.printStackTrace();
			  }finally{
			   try {
			    stat.close();
			  conn.close();
			   } catch (SQLException e) {
			    e.printStackTrace();
			   }
			  }
		return str;
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
	public String addNesItem(String type,String[] title,String[] main,String[] urlo,String[] url,int count) throws SQLException{
		statment();
		String id="";
		System.out.println("tl="+title.length);
		System.out.println("ml="+main.length);
		System.out.println("uol="+urlo.length);
		System.out.println("count="+count);
		for(int i = 0;i<count;i++){
		String sql0="insert into pic_mus_reback(type,title,main,urlo,url) values(1,'"+title[i]+"','"+main[i]+"','"+urlo[i]+"','"+url[i]+"')";
		stat.executeUpdate(sql0);
		String sql1="select max(id) from pic_mus_reback";
		ResultSet res=stat.executeQuery(sql1);
		while(res.next())
			id+=res.getInt("max(id)")+";";
		}
		 stat.close();
		  conn.close();
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
		public String getGroupname() throws SQLException{
			statment();
			String str0="";
			String sql="select distinct(groupname) from wxfans";
			ResultSet re=stat.executeQuery(sql);
			while(re.next())
				str0+=re.getString("groupname")+";";
			 stat.close();
			  conn.close();
			return str0;
		}
		public String getP1() throws SQLException{
			statment();
			String str0="";
			String sql="select distinct(Country) from wxfans";
			ResultSet re=stat.executeQuery(sql);
			while(re.next())
				str0+=re.getString("Country")+";";
			 stat.close();
			  conn.close();
			return str0;
		}
		public String getP2(String p1) throws SQLException{
			statment();
			String str0="";
			String sql="select distinct(Province) from wxfans";
			if(!"".equals(p1))
				sql="select distinct(Province) from wxfans where Country='"+p1+"'";
			ResultSet re=stat.executeQuery(sql);
			while(re.next())
				str0+=re.getString("Province")+";";
			 stat.close();
			  conn.close();
			return str0;
		}public String getP3(String p2) throws SQLException{
			statment();
			String str0="";
			String sql="select distinct(City) from wxfans";
			if(!"".equals(p2))
				sql="select distinct(City) from wxfans where Province='"+p2+"'";
			ResultSet re=stat.executeQuery(sql);
			while(re.next())
				str0+=re.getString("City")+";";
			 stat.close();
			  conn.close();
			return str0;
		}
	public ArrayList<Fans> getFans(String start,String end,String s1,String s2,String[] s3) throws SQLException{
		statment();
		ArrayList array=new ArrayList();
		if(!"2013-10-1".equals(start))start=" and o.sub_time>='"+start+"'";
		else start="";
		if(!"2013-10-1".equals(end))end=" and o.sub_time<='"+end+"'";
		else end="";
		if(s1!=null&&!"".equals(s1))s1=" and w.groupname='"+s1+"'";
		else s1="";
		if(s2!=null&&!"".equals(s2))s2=" and w.Sex='"+s2+"'";
		else s2="";
		if(s3!=null){
			if(!"".equals(s3[0]))s3[0]=" and w.Country='"+s3[0]+"'";
			if(!"".equals(s3[1]))s3[1]=" and w.Province='"+s3[1]+"'";
			if(!"".equals(s3[2]))s3[2]=" and w.City='"+s3[2]+"'";
			}
		else{
			s3=new String[3];
			s3[0]="";s3[1]="";s3[2]="";
		}
		String sql="select * from wxfans w,openid_wid o where w.FakeId=o.wid and o.value='1'"+start+end+s1+s2+s3[0]+s3[1]+s3[2];
		System.out.println(sql);
		ResultSet res=stat.executeQuery(sql);
		while(res.next()){
			Fans f=new Fans();
			f.setGroupid(res.getString("w.groupid"));
			f.setGroupname(res.getString("w.groupname"));
			f.setFakeid(res.getString("w.FakeId"));
			f.setNickname(res.getString("w.NickName"));
			f.setCity(res.getString("w.City"));
			f.setSex(res.getString("w.Sex"));
			f.setSub_time(res.getString("o.sub_time"));
			array.add(f);
		}
		 stat.close();
		  conn.close();
		return array;
	}
	public ArrayList getFans() throws SQLException{
		statment();
		ArrayList array=new ArrayList();
		String groupid,fakeid,nickname;
		String sql="select * from wxfans";
		ResultSet res=stat.executeQuery(sql);
		while(res.next()){
			groupid=res.getString("groupid");
			fakeid=res.getString("fakeid");
			nickname=res.getString("nickname");
			Fans f=new Fans();
			f.setGroupid(groupid);
			f.setFakeid(fakeid);
			f.setNickname(nickname);
			array.add(f);
		}
		 stat.close();
		  conn.close();
		return array;
	}
	
	public ArrayList<Retxt> getTxtarray() throws SQLException{
		statment();
		boolean flag=false;
		Retxt txt=new Retxt();
		StringBuffer buffer=new StringBuffer(),buffer1=new StringBuffer();
		String[] str;
		ArrayList array=new ArrayList();
		String sql0="select * from re_keyword";
		ResultSet re=stat.executeQuery(sql0);
		while(re.next()){
			 if(!txt.name.equals(re.getString("name"))){
				 txt=new Retxt(); 
				 buffer=new StringBuffer();
				 buffer1=new StringBuffer();
				 flag=true;
			 }
			 txt.setName(re.getString("name"));
			 buffer.append(re.getString("key0")+" ");
			 buffer1.append(re.getString("re_id")+" ");
			txt.setKey(buffer.toString().split(" "));
			txt.setKeystyle(buffer1.toString().split(" "));
			txt.setContent(re.getString("content"));
	   if(flag)
			array.add(txt);
	   flag=false;
		}
		 stat.close();
		  conn.close();
		return array;
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
    public void addPubId(String name,String id,String user,String pass,String api,String token,String appid,String secret) throws SQLException{
    	statment();
    	String sql="insert into pubid(name,wx_id,user,pass,apiurl,token,appid,appsecret) values('"+name+"','"+id+"','"+user+"','"+pass+"','"+api+"','"+token+"','"+appid+"','"+secret+"')";
        stat.executeUpdate(sql);
        stat.close();
        conn.close();
    }
    
    public ArrayList<Pubid> getPubId() throws SQLException{
    	statment();
    	String sql="select * from pubid";
    	ResultSet res=stat.executeQuery(sql);
    	Pubid pub;
    	ArrayList array=new ArrayList();
    	while(res.next()){
    		pub=new Pubid();
    		pub.setWxname(res.getString("user"));
    		pub.setName(res.getString("name"));
    		pub.setPass(res.getString("pass"));
    		pub.setApiurl(res.getString("apiurl"));
    		pub.setToken(res.getString("token"));
    		pub.setAppid(res.getString("appid"));
    		pub.setAppsecret(res.getString("appsecret"));
    		array.add(pub);
    	}
    	 stat.close();
    	  conn.close();
    	return array;
    	
    }
    public ArrayList<Chatrecord> getAllChatRecord() throws SQLException{
    	statment();
    	ArrayList array=new ArrayList();
    	String sql="select * from chat_record";
    	ResultSet res=stat.executeQuery(sql);
    	while(res.next()){
    		Chatrecord c=new Chatrecord();
    		c.setUserid(res.getString("user_id"));
    		c.setUsersay(res.getString("user_say"));
    		c.setWreply(res.getString("wx_reply").substring(0,res.getString("wx_reply").length()>10?10:res.getString("wx_reply").length()));
    		c.setWreply_type(res.getString("wx_reply_type"));
    		c.setTime(res.getString("time"));
    		array.add(c);
    	}
    	 stat.close();
    	  conn.close();
    	return array;
    }
    public String getPubByWxname(String username) throws SQLException{
    	statment();
    	String str="";
    	String sql="select * from pubid where user='"+username+"'";
    	ResultSet res=stat.executeQuery(sql);
    	while(res.next())
    		str= res.getString("name");
    	 stat.close();
    	  conn.close();
    	return str;
    	
    }
    public void updatePub(String wxname,String url,String token) throws SQLException{
    	statment();
    	String sql="update pubid set apiurl='"+url+"',token='"+token+"' where user='"+wxname+"'";
    	System.out.println("user="+wxname);
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
	public String[] getPubUserPassName(String name) throws SQLException{
		statment();
		String s[]=new String[3];
		String sql="select * from pubid where name='"+name+"'";
		ResultSet re=stat.executeQuery(sql);
		while(re.next()){
			s[0]=re.getString("user");
			s[1]=re.getString("pass");
			s[2]=re.getString("database");
		}
		 stat.close();
		  conn.close();
		return s;	
		
	}
	public void clearWxfans() throws SQLException{
		statment();
		String sql0="truncate table wxfans";
		stat.executeUpdate(sql0);
		 stat.close();
		  conn.close();
	}
	public void freashen(String groupid,String groupname,String FakeId,String NickName,String Country,String Province,String City,String Sex) throws SQLException, UnsupportedEncodingException{
		statment();
		byte[] b;
		int i=0;
		for(byte bb:b=NickName.getBytes())
			b[i++]=(byte) (bb>127?(bb-127):bb);//去16进制
		NickName=new String(b);
		//System.out.println("Nick="+NickName);
		//if(NickName.endsWith("西瓜"))NickName="西瓜";
		String sql="insert into wxfans(groupid,groupname,FakeId,NickName,Country,Province,City,Sex) values('"+groupid+"','"+groupname+"','"+FakeId+"','"+(NickName.length()>50?NickName.substring(0,50):NickName)+"','"+Country+"','"+Province+"','"+City+"','"+Sex+"')";
		stat.executeUpdate(sql);
		String sql0="select * from openid_wid where wid='"+FakeId+"'";
		ResultSet re=stat.executeQuery(sql0);
		if(!re.next())
		{ String sql1="insert into openid_wid(wid,value) values('"+FakeId+"',1)";
			stat.executeUpdate(sql1);
		}	
		 stat.close();
		  conn.close();
	}
	public ArrayList<Manageruser> getManagerarray(Manageruser mana) throws SQLException{
		statment();
		ArrayList array=new ArrayList();
		String sql="select * from manageruser where power<="+mana.power;
		ResultSet res=stat.executeQuery(sql);
		while(res.next()){
			Manageruser mana0=new Manageruser();
			mana0.setUser(res.getString("user"));
			mana0.setPass(res.getString("pass"));
			mana0.setPower(res.getInt("power")+"");
			mana0.setValue(res.getInt("value")+"");
			array.add(mana0);
		}
		 stat.close();
		  conn.close();
		return array;
	}
	public Manageruser getManager(String user) throws SQLException{
		statment();
		String sql="select * from manageruser where user='"+user+"'";
		ResultSet re=stat.executeQuery(sql);
		Manageruser mana=new Manageruser();
		while(re.next()){
			mana.setUser(re.getString("user"));
		//	mana.setPass(re.getString("pass"));
			mana.setPower(re.getInt("power")+"");
			mana.setValue(re.getInt("value")+"");	
		}
		 stat.close();
		  conn.close();
		return mana;
	}
	public void delManager(String user) throws SQLException{
		statment();
		String sql="delete from manageruser where user='"+user+"'";
		stat.executeUpdate(sql);
		 stat.close();
		  conn.close();
	}
	public void setManagerDisable(String user,String value) throws SQLException{
		statment();
		String sql="update manageruser set value='"+Integer.parseInt(value)+"'where user='"+user+"'";
		stat.executeUpdate(sql);
		 stat.close();
		  conn.close();
	}
	public void setManagerPower(String user,String power,String username) throws SQLException{
		statment();
		String sql="update manageruser set power='"+power+"' where user='"+user+"'";
		stat.executeUpdate(sql);
		 stat.close();
		  conn.close();
	}
	public boolean isManageruser(String user,String pass) throws SQLException{
		statment();
		boolean flag=false;
		String sql="select * from manageruser where user='"+user+"' and pass='"+pass+"'";
	    ResultSet re=stat.executeQuery(sql);
	    while(re.next())
	    	flag= true;
	    stat.close();
	    conn.close();
	    return flag;
	}
	public void addManageruser(String user,String pass,String instruction) throws SQLException{
		boolean flag=this.isHaveUser(user);
		String sql="insert into manageruser(user,pass,instruction) values('"+user+"','"+pass+"','"+instruction+"')";
		if(flag)
			sql="update manageruser set pass='"+pass+"',instruction='"+instruction+"' where user='"+user+"'";
		stat.executeUpdate(sql);
		 stat.close();
		  conn.close();
	}
	public boolean isHaveUser(String username) throws SQLException{
		statment();
		boolean flag=false;
		String sql="select * from manageruser where user='"+username+"'";
		ResultSet re=stat.executeQuery(sql);
		while(re.next())
			flag= true;
		 stat.close();
		  conn.close();
		return flag;
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
}
