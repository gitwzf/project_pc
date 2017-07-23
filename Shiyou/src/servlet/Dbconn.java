package servlet;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Dbconn {
	private String database="w";
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
		return s;	
		
	}
	public void clearWxfans() throws SQLException{
		statment();
		String sql0="truncate table wxfans";
		stat.executeUpdate(sql0);
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
		return mana;
	}
	public void delManager(String user) throws SQLException{
		statment();
		String sql="delete from manageruser where user='"+user+"'";
		stat.executeUpdate(sql);
		
	}
	public void setManagerDisable(String user,String value) throws SQLException{
		statment();
		String sql="update manageruser set value='"+Integer.parseInt(value)+"'where user='"+user+"'";
		stat.executeUpdate(sql);
		
	}
	public void setManagerPower(String user,String power,String username) throws SQLException{
		statment();
		String sql="update manageruser set power='"+power+"' where user='"+user+"'";
		stat.executeUpdate(sql);
		
	}
	public boolean isManageruser(String user,String pass) throws SQLException{
		statment();
		String sql="select * from manageruser where user='"+user+"' and pass='"+pass+"'";
	    ResultSet re=stat.executeQuery(sql);
	    while(re.next())
	    	return true;
	    return false;
	}
	public void addManageruser(String user,String pass,String instruction) throws SQLException{
		boolean flag=this.isHaveUser(user);
		String sql="insert into manageruser(user,pass,instruction) values('"+user+"','"+pass+"','"+instruction+"')";
		if(flag)
			sql="update manageruser set pass='"+pass+"',instruction='"+instruction+"' where user='"+user+"'";
		stat.executeUpdate(sql);
		
	}
	public boolean isHaveUser(String username) throws SQLException{
		statment();
		String sql="select * from manageruser where user='"+username+"'";
		ResultSet re=stat.executeQuery(sql);
		while(re.next())
			return true;
		return false;
	}
}
