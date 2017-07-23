package wxtry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Dbcon {
	private String url="jdbc:mysql://r.rdc.sae.sina.com.cn:3307/app_xwangzi";//数据库名
	private String username="zljn1wmj2x";
	private String password="yh043wljkk22mxlyhw0z2xzlkjx44k5ijm4x4l04";
	private Connection conn;
	private Statement stat;
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
			conn=DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void statment(){
		 connection();
		 try {
			stat=conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean isOk(){
		statment();
		return true;
	}
	public int getRep_style(String s) throws SQLException{
		statment();
		int re=-1;
		String sql="select rep_style from weix where rep='"+s+"'";
		System.out.println(sql);
		ResultSet res=stat.executeQuery(sql);//中文不能处理-->加'号
		       while(res.next())
		    re=res.getInt("rep_style");
		       return re;
	}
	public String getCon(String s) throws SQLException{
		statment();
		String re="";
		String sql="select con from weix where rep='"+s+"'";
		ResultSet res=stat.executeQuery(sql);
		    while(res.next())
		 re=res.getString("con");
		    return re;
	}
	
	public void delId(int id) throws SQLException{
		statment();
		String sql="delete from picedit where id="+id;
		stat.executeUpdate(sql);
	}
	public void addPicurl(int id,String title,String main,String picurl,String link) throws SQLException{
		statment();
		title=title.substring(0,title.length()>199?199:title.length());
		main=main.substring(0,main.length()>199?199:main.length());
		picurl=picurl.substring(0,picurl.length()>199?199:picurl.length());
		link=link.substring(0,link.length()>199?199:link.length());
		String sql0="select * from picedit where id="+id;
		ResultSet res=stat.executeQuery(sql0);
		if(!res.next()){
		String sql1="insert into picedit values("+id+",'"+title+"','"+main+"','"+picurl+"','"+link+"')";
		stat.executeUpdate(sql1);}
		else{
			String sql2="update picedit set title='"+title+"',main='"+main+"',picurl='"+picurl+"',link='"+link+"' where id="+id;
		stat.executeUpdate(sql2);
		}
	}
	public ArrayList<ArrayList> repArr() throws SQLException{
		statment();
		String sql="select * from picedit";
		ResultSet res=stat.executeQuery(sql);
		ArrayList arr=new ArrayList();
		while(res.next()){
			ArrayList ar=new ArrayList();
			ar.add(res.getInt("id"));
			ar.add(res.getString("title"));
			ar.add(res.getString("main"));
			ar.add(res.getString("picurl"));
			ar.add(res.getString("link"));
			arr.add(ar);
		}
		return arr;	
	}
	public boolean isVis(String user) throws SQLException{
		statment();
		String sql="select * from virecord where name='"+user+"'";
		ResultSet res=stat.executeQuery(sql);
		while(res.next())
			return true;
		return false;
		
	}
	public String getInstruction(String user) throws SQLException{
		statment();
		String sql="select instruction from virecord where name='"+user+"'";
		ResultSet res=stat.executeQuery(sql);
		while(res.next())
			return res.getString("instruction");
		return "none";
	}
	public int getTimes(String user) throws SQLException{
		statment();
		int times=0;
		String sql="select times from virecord where name='"+user+"'";
		ResultSet res=stat.executeQuery(sql);
		while(res.next()){
			times=res.getInt("times");
			String sql1="update virecord set times="+(times+1)+" where name='"+user+"'";
			stat.executeUpdate(sql1);
			return times-1;}
		return 0;
	}
	public void addUser(String user) throws SQLException{
		statment();
		String sql="insert into virecord(name) values('"+user+"')";
		stat.executeUpdate(sql);
	}
	public void updateStr(String user,String str) throws SQLException{
		statment();
		String sql="update virecord set mode='"+str+"' where name='"+user+"'";
		stat.executeUpdate(sql);
	}
	
	public String getMode(String user) throws SQLException{
		statment();
		String sql="select mode from virecord where name='"+user+"'";
		ResultSet res=stat.executeQuery(sql);
		while(res.next())
			return res.getString("mode");
		return "";
		
	}
	public String getReback(String user) throws SQLException{
		statment();
		String sql00="select * from keyword";
	ResultSet res=stat.executeQuery(sql00);
	ArrayList array=new ArrayList();
	while(res.next()){
	    ArrayList ar=new ArrayList();
	    ar.add(res.getString("word"));
	    ar.add(res.getString("reback"));
	    array.add(ar);
	}
		for(int i=0;i<array.size();i++){
		String sql="select * from virecord where instruction like '%"+((ArrayList)array.get(i)).get(0)+"%' and name='"+user+"'";
		ResultSet res1=stat.executeQuery(sql);
		while(res1.next())
			return ((ArrayList)array.get(i)).get(1)+"";
		
	}   return "";
	}
	public void addInstruction(String user,String str) throws SQLException{
		statment();
		String sql="update virecord set instruction='"+str.substring(0,str.length()>200?199:str.length()).trim()+"' where name='"+user+"'";
		stat.executeUpdate(sql);
	}
	
	public boolean isChinese(String user) throws SQLException{
		statment();
		String sql="select * from virecord where name='"+user+"' and instruction regexp '[\u0391-\uFFE5]'";
		ResultSet res=stat.executeQuery(sql);
		while(res.next())
		{   System.out.println("instruction="+res.getString("instruction"));
			return true;}
		return false;
		
	}
	public String getTitle(int id) throws SQLException{
		statment();
		String sql="select title from picedit where id="+id;
		ResultSet res=stat.executeQuery(sql);
		while(res.next())
			return res.getString("title");
		return "";
	}
	public String getMain(int id) throws SQLException{
		statment();
		String sql="select main from picedit where id="+id;
		ResultSet res=stat.executeQuery(sql);
		while(res.next())
			return res.getString("main");
		return "";
	}
	public String getPicurl(int id) throws SQLException{
		statment();
		String sql="select picurl from picedit where id="+id;
		ResultSet res=stat.executeQuery(sql);
		while(res.next())
			return res.getString("picurl");
		return "";
	}
	public String getLink(int id) throws SQLException{
		statment();
		String sql="select link from picedit where id="+id;
		ResultSet res=stat.executeQuery(sql);
		while(res.next())
			return res.getString("link");
		return "";
	}
	public ArrayList getThirdArr(String str) throws SQLException{
		statment();
		ArrayList arr=new ArrayList();
		String sql="select * from picedit where title like '%"+str+"%'";
		ResultSet res=stat.executeQuery(sql);
		while(res.next()){
			ArrayList ar=new ArrayList();
			ar.add(res.getString("title"));
			ar.add(res.getString("main"));
			ar.add(res.getString("picurl"));
			ar.add(res.getString("link"));
			arr.add(ar);
		}
		return arr;
		
	}

}
