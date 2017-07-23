package wxtry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import pubvari.Variable;

import model.News;
import model.SimplePubid;
import model.WxUser;

public class Dbcon {
	static Logger log = Logger.getLogger("logfile");
	Variable vari=new Variable();
	private String database=vari.database;
	private String url="jdbc:mysql://localhost:"+vari.port+"/";//数据库名
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

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}

	private void connection() {
		try {
			conn = DriverManager.getConnection(url+database, username, password);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	private void statment() {
		connection();
		try {
			stat = conn.createStatement();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public boolean addOpenid(String openid){
		return exeSql("insert into openid_wid(openid,wid,sub_time) values('"
				+ openid + "','"
				+ openid + "','" + null + "')");
	}
	
	public int getRep_style(String s) throws SQLException {
		statment();
		int re = -1;
		String sql = "select rep_style from weix where rep='" + s + "'";
		System.out.println(sql);
		ResultSet res = stat.executeQuery(sql);// 中文不能处理-->加'号
		while (res.next())
			re = res.getInt("rep_style");
		 stat.close();
		  conn.close();
		return re;
	}

	public boolean isVis(String user) throws SQLException {
		statment();
		boolean flag=false;
		String sql = "select * from virecord where name='" + user + "'";
		ResultSet res = stat.executeQuery(sql);
		while (res.next())
			flag= true;
		 stat.close();
		  conn.close();
		return flag;

	}

	public String getInstruction(String user) throws SQLException {
		statment();
		String str="none";
		String sql = "select instruction from virecord where name='" + user
				+ "'";
		ResultSet res = stat.executeQuery(sql);
		while (res.next())
			str= res.getString("instruction");
		 stat.close();
		  conn.close();
		return str;
	}
	public String getParamLoca(String user) throws SQLException {
		statment();
		String str="none";
		String sql = "select * from virecord where name='" + user
				+ "'";
		ResultSet res = stat.executeQuery(sql);
		while (res.next())
			str= res.getString("param_loca");
		 stat.close();
		  conn.close();
		return str;
	}

	public int getTimes(String user) throws SQLException {
		statment();
		int a=0;
		int times = 0;
		String sql = "select times from virecord where name='" + user + "'";
		ResultSet res = stat.executeQuery(sql);
		while (res.next()) {
			times = res.getInt("times");
			String sql1 = "update virecord set times=" + (times + 1)
					+ " where name='" + user + "'";
			stat.executeUpdate(sql1);
			a= times - 1;
		}
		 stat.close();
		  conn.close();
		return a;
	}

	public void addUser(String user) throws SQLException {
		statment();
		String sql = "insert into virecord(name) values('" + user + "')";
		stat.executeUpdate(sql);
		 stat.close();
		  conn.close();
	}

	public void updateStr(String user, int reid) throws SQLException {
		statment();
		String sql = "update virecord set mode='" + reid
				+ "' , instruction='' where name='" + user + "'";
		stat.executeUpdate(sql);
		 stat.close();
		  conn.close();
	}

	public String getMode(String user) throws SQLException {
		statment();
		String str = null;
		String sql = "select mode from virecord where name='" + user + "'";
		ResultSet res = stat.executeQuery(sql);
		while (res.next())
			str= res.getString("mode");
		 stat.close();
		  conn.close();
		return str;

	}

	public String getReback(String user) throws SQLException {
		statment();
		String str="";
		String sql00 = "select * from keyword";
		ResultSet res = stat.executeQuery(sql00);
		ArrayList array = new ArrayList();
		while (res.next()) {
			ArrayList ar = new ArrayList();
			ar.add(res.getString("word"));
			ar.add(res.getString("reback"));
			array.add(ar);
		}
		for (int i = 0; i < array.size(); i++) {
			String sql = "select * from virecord where instruction like '%"
					+ ((ArrayList) array.get(i)).get(0) + "%' and name='"
					+ user + "'";
			ResultSet res1 = stat.executeQuery(sql);
			while (res1.next())
				str= ((ArrayList) array.get(i)).get(1) + "";

		}
		 stat.close();
		  conn.close();
		return str;
	}

	public void addInstruction(String user, String str) throws SQLException {
		statment();
		byte[] b;
		int i=0;
		for(byte bb:b=str.substring(0, str.length() > 200 ? 199 : str.length()).trim().getBytes())
			b[i++]=(byte) (bb>127?(bb-127):bb);//去16进制
		str=new String(b).replace("\'", "\\\'");
		String sql = "update virecord set instruction='"
				+ str + "' where name='" + user + "'";
		stat.executeUpdate(sql);
		 stat.close();
		  conn.close();
	}
	public void addParamLoca(String user, String str) throws SQLException {
		statment();
		String sql = "update virecord set param_loca='"
				+ str.substring(0, str.length() > 200 ? 199 : str.length())
						.trim() + "' where name='" + user + "'";
		stat.executeUpdate(sql);
		 stat.close();
		  conn.close();
	}

	public boolean isChinese(String user) throws SQLException {
		statment();
		boolean flag=false;
		String sql = "select * from virecord where name='" + user
				+ "' and instruction regexp '[\u0391-\uFFE5]'";
		ResultSet res = stat.executeQuery(sql);
		while (res.next()) { //
			System.out.println("instruction="+res.getString("instruction"));
			flag= true;
		}
		 stat.close();
		  conn.close();
		return flag;

	}

	public ArrayList getThirdArr(String str) throws SQLException {
		statment();
		ArrayList arr = new ArrayList();
		String sql = "select * from picedit where title like '%" + str + "%'";
		ResultSet res = stat.executeQuery(sql);
		while (res.next()) {
			ArrayList ar = new ArrayList();
			ar.add(res.getString("title"));
			ar.add(res.getString("main"));
			ar.add(res.getString("picurl"));
			ar.add(res.getString("link"));
			arr.add(ar);
		}
		 stat.close();
		  conn.close();
		return arr;

	}

	public boolean isMenuKeyWord(String str) throws SQLException {
		str = str.toLowerCase();
		statment();
		boolean flag=false;
		String sql = "select * from menukeyword where word='" + str + "'";
		ResultSet res = stat.executeQuery(sql);
		while (res.next()) {
			flag= true;
		}
		 stat.close();
		  conn.close();
		return flag;

	}

	public int isReKeyWord(String str){
		str = str.toLowerCase().replace("\'", "\\\'");
		statment();
		String sql = "select * from re_keyword where key0='" + str + "' and re_id='1'";
		System.out.println("sql="+sql);
		ResultSet res;
		try {
			res = stat.executeQuery(sql);
		
		while (res.next())
			return Integer.parseInt(res.getString("id"));
		String sql1 = "select * from re_keyword where locate(key0,'" + str
				+ "')>0 and re_id='2'";
		System.out.println("sql1="+sql1);
		ResultSet re = stat.executeQuery(sql1);
		while (re.next())
			return Integer.parseInt(re.getString("id"));
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
		return -2;

	}

	public int getDefault(){
		statment();
		String sql = "select * from re_keyword where identity='default'";
		ResultSet res;
		try {
			res = stat.executeQuery(sql);
		
		while (res.next())
			return res.getInt("id");
		sql="select * from re_keyword where key0='default'";
		res=stat.executeQuery(sql);
		while (res.next())
			return res.getInt("id");
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
		return -2;
	}

	public int getType0ById(int id) throws SQLException {
		statment();
		int a=0;
		String sql = "select * from re_keyword where id=" + id;
		ResultSet res = stat.executeQuery(sql);
		while (res.next())
			a= res.getInt("type0");
		 stat.close();
		  conn.close();
		return a;
	}

	public String getReContentById(int id) throws SQLException {
		statment();
		String str="";
		String sql = "select * from re_keyword where id=" + id;
		ResultSet res = stat.executeQuery(sql);
		while (res.next())
			str= res.getString("content");
		 stat.close();
		  conn.close();
		return str;

	}

	public void addChatrecorder(String uid, String wid, String usay,
			String usaytype, String wrep, String wreptype, String time) {
		statment();
		byte[] b;
		int i=0;
		for(byte bb:b=usay.substring(0, usay.length() > 200 ? 200 : usay.length()).getBytes())
			b[i++]=(byte) (bb>127?(bb-127):bb);//去16进制
		usay=new String(b).replace("\'", "\\\'");
		String sql = "insert into chat_record(user_id,wx_id,user_say,user_say_type,wx_reply,wx_reply_type,time) values('"
				+ uid
				+ "','"
				+ wid
				+ "','"
				+ usay
				+ "','"
				+ usaytype
				+ "','"
				+ wrep.substring(0, wrep.length() > 200 ? 200 : wrep.length())
						.replace("'", "").replace("\"", "")
				+ "','"
				+ wreptype
				+ "','" + time + "')";
		try {
			stat.executeUpdate(sql);
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
		
	}

	public boolean checkId(String id) throws SQLException {
		statment();
		boolean flag=true;
		String sql = "select * from openid_wid where wid=" + id;
		ResultSet res = stat.executeQuery(sql);
		while (res.next())
			flag= false;
		 stat.close();
		  conn.close();
		return flag;
	}

	public void addId(String wid, String openid, String time)
			throws SQLException {
		statment();
		String sql0 = "select * from openid_wid where wid='" + wid + "'";
		ResultSet res = stat.executeQuery(sql0);
		String sql = "insert into openid_wid(wid,openid,sub_time) values('"
				+ wid + "','" + openid + "','" + time + "')";
		while (res.next())
			sql = "update openid_wid set sub_time='" + time
					+ "',value='1',openid='"+openid+"' where wid='" + wid + "'";
		stat.executeUpdate(sql);
		 stat.close();
		  conn.close();
	}
	public void addId2(String wid, String openid)
	throws SQLException {
		statment();
		boolean flag=false;  //是否多openid
		String sql0 = "select * from openid_wid where wid='" + wid + "'";
		ResultSet res = stat.executeQuery(sql0);
		String sql = "update openid_wid set wid='"+wid+"' where openid='"+openid+"'";
		while (res.next())
		{	flag=true;
			sql = "update openid_wid set value='1',openid='"+openid+"' where wid='" + wid + "'";}
		stat.executeUpdate(sql);
		if(flag){
		sql="delete from openid_wid where wid='"+openid+"'";
		stat.executeUpdate(sql);
		}
		 stat.close();
		  conn.close();
}
	public boolean isaddId(String openid)//有没添加过
	 {
		return isHaveSql("select * from openid_wid where openid='" + openid + "'");
	}
	public String getIdByOpenid(String openid){
		statment();
		String sql="select * from openid_wid where openid='"+openid+"'";
		ResultSet res;
		try {
			res = stat.executeQuery(sql);
		
		while(res.next())
			if(res.getString("wid").equals(openid))return "";
			else
			return res.getString("wid");
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
		return "";
	}

	public int getSubEventType0() throws SQLException {
		statment();
		int a=3;
		String sql = "select * from re_keyword where identity='welcome'";
		ResultSet res = stat.executeQuery(sql);
		while (res.next())
			a= Integer.parseInt(res.getString("type0"));
		stat.close();
		  conn.close();
		return a;
	}

	public String getTxtback(String name, String str) throws SQLException {
		statment();
		String str0="";
		String sql = "select * from re_keyword where name='" + name
				+ "' and key0='" + str + "'";
		ResultSet res = stat.executeQuery(sql);
		while (res.next())
			str0= res.getString("content");
		stat.close();
		  conn.close();
		return str0;
	}

	public void setOpenIdval(String str, String s) throws SQLException {
		statment();
		String sql = "update openid_wid set value='" + s + "' where openid='"
				+ str + "'";
		stat.executeUpdate(sql);
		stat.close();
		  conn.close();
	}

	public String getReContent(int id,int type) throws SQLException {
		statment();
		String str="";
		String sql = "select * from pic_mus_reback where keyid=" + id+" and type="+type;
		ResultSet res = stat.executeQuery(sql);
		while (res.next())
			str+= res.getString("id")+";";
		stat.close();
		  conn.close();
		return str;
	}
	public String getReContent(int id) throws SQLException {
		statment();
		String str="";
		String sql = "select * from re_keyword where id=" + id;
		ResultSet res = stat.executeQuery(sql);
		while (res.next())
			str= res.getString("content");
		stat.close();
		  conn.close();
		return str;
	}

	public ArrayList <News>getPicMusById(String[] id) throws SQLException {
		statment();
		ArrayList<News>arr = new ArrayList<News>();
		for (String i : id) {
			String sql = "select * from pic_mus_reback where id='" + i + "'";
			ResultSet res = stat.executeQuery(sql);
			while (res.next()) {
				News n = new News();
				n.setTitle(res.getString("title"));
				System.out.println("title="+res.getString("title"));
				n.setMain(res.getString("main"));
				n.setUrlo(res.getString("urlo"));
				n.setUrl(res.getString("url"));
				arr.add(n);
			}
		}
		stat.close();
		  conn.close();
		return arr;
	}

	public SimplePubid getPubid(String openid) throws SQLException {
		statment();
		SimplePubid sp = new SimplePubid();
		String sql = "select * from pubid where wx_id='" + openid + "'";
		ResultSet res = stat.executeQuery(sql);
		while (res.next()) {
			sp.setUser(res.getString("user"));
			sp.setPass(res.getString("pass"));
		}
		stat.close();
		  conn.close();
		return sp;
	}
	
	
	public void freashen(String groupid,String groupname,String FakeId,String NickName,String Country,String Province,String City,String Sex) throws SQLException{
		statment();
		byte[] b;
		int i=0;
		for(byte bb:b=NickName.getBytes())
			b[i++]=(byte) (bb>127?(bb-127):bb);//去16进制
		NickName=new String(b).replace("\'", "\\\'");
		String sql0="select * from wxfans where FakeId='"+FakeId+"'";
		String sql="insert into wxfans(groupid,groupname,FakeId,NickName,Country,Province,City,Sex) values('"+groupid+"','"+groupname+"','"+FakeId+"','"+NickName+"','"+Country+"','"+Province+"','"+City+"','"+Sex+"')";
		ResultSet re=stat.executeQuery(sql0);
		while(re.next())
			 sql="update wxfans set groupid='"+groupid+"',NickName='"+NickName+"',Country='"+Country+"',Province='"+Province+"',City='"+City+"',Sex='"+Sex+"' where FakeId='"+FakeId+"'";
		stat.executeUpdate(sql);
		stat.close();
		  conn.close();
	}
	public String getPubDatabase(String pid) throws SQLException{
		statment();
		String str="";
		String sql="select * from pubid where wx_id='"+pid+"'";
		ResultSet res=stat.executeQuery(sql);
		while(res.next())
		{	//System.out.println("database"+res.getString("database"));
			str= res.getString("database");}
		stat.close();
		  conn.close();
		return str;
		
	}
	
	public WxUser getWxfansByOpenid(String openid) throws SQLException{
		statment();
		String sql="select * from wxfans w,openid_wid o where w.FakeId=o.wid and o.openid='"+openid+"'";
		ResultSet re=stat.executeQuery(sql);
		WxUser wx=new WxUser();
		while(re.next()){
			wx.setCity(re.getString("w.City"));
			wx.setCountry(re.getString("w.Country"));
			wx.setFakeId(re.getString("w.FakeId"));
			wx.setGender(re.getString("w.Sex"));
			wx.setGroupid(re.getString("w.groupid"));
			wx.setProvince(re.getString("w.Province"));
			wx.setNickName(re.getString("w.NickName"));
			wx.setGroupname(re.getString("w.groupname"));
		}
		stat.close();
		  conn.close();
		return wx;
	}
//	public int getReIDByRule(String rule_name,String rule_key) throws SQLException{
//		statment();
//		String sql="select * from re_keyword where name='"+rule_name+"' and key0='"+rule_key+"'";
//		ResultSet re=stat.executeQuery(sql);
//		while(re.next())
//			return Integer.parseInt(re.getString("id"));
//		return 0;
//		
//	}
	public int isHaveWelcome() throws SQLException{
		statment();
		int a=0;
		String sql="select * from re_keyword where identity='welcome'";
		ResultSet re=stat.executeQuery(sql);
		while(re.next())
			a= re.getInt("id");
		stat.close();
		  conn.close();
		return a;
	}
	
	public String getSubContent() throws SQLException{
		statment();
		String str="";
		String sql="select * from re_keyword where identity='welcome'";
		ResultSet re=stat.executeQuery(sql);
		while(re.next())
			str= re.getString("content");
		stat.close();
		  conn.close();
		return str;
	}
	public boolean exeSql(String sql){
		statment();
		try {
			stat.execute(sql);
		} catch (SQLException e) {
			
			log.error(e);return false;
		}finally{
			   try {
				    stat.close();
				  conn.close();
				   } catch (SQLException e) {
				    log.info(e);return false;
				   }
				  }
		return true;
	}
	public boolean isHaveSql(String sql){
		statment();
		try {
			ResultSet re=stat.executeQuery(sql);
			while(re.next())
				return true;
		} catch (SQLException e) {
			
			log.error(e);return false;
		}finally{
			   try {
				    stat.close();
				  conn.close();
				   } catch (SQLException e) {
				    log.info(e);return false;
				   }
				  }
		return false;
	}
	
}
