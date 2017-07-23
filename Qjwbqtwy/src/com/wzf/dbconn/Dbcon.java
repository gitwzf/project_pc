package com.wzf.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import com.wzf.model.Adv;
import com.wzf.model.College;
import com.wzf.model.Company;
import com.wzf.model.Gamerule;
import com.wzf.model.Hunter;
import com.wzf.model.Manageruser;
import com.wzf.model.Modeltype;
import com.wzf.model.Position;
import com.wzf.model.User;
import com.wzf.model.Work;
import com.wzf.pubvari.Variable;

public class Dbcon {
	Variable vari = new Variable();
	Logger log = Logger.getLogger("logfile");
	private String database = vari.database;
	private String url = "jdbc:mysql://"+vari.IP+":" + vari.port + "/";// 数据库名
	private String username = vari.username;
	private String password = vari.password;
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
			conn = DriverManager.getConnection(url + database, username,
					password);
		} catch (SQLException e) {
			
			log.error("Exception:",e);
		}

	}

	private void statment() {
		connection();
		try {
			stat = conn.createStatement();
		} catch (SQLException e) {
			
			log.error("Exception:",e);
		}
	}

	public boolean isUser(String name,String pass){
		connection();
		PreparedStatement pstat=null;
		boolean flag=false;
		String sql="select KH_DLM from APP_BIZ_KH where KH_DLM=? and KH_DLMM=?";
//		String sql="select name from users where name=? and phone=?";
		try {
			 pstat=conn.prepareStatement(sql);
			 pstat.setString(1, name);
			 pstat.setString(2, pass);
			 ResultSet re=pstat.executeQuery();
			 while(re.next())
			     flag=true;
		} catch (SQLException e) {
			   
			   log.error("error:", e);log.info(sql);
			  }finally{
			   try {
			  pstat.close();
			  conn.close();
			   } catch (SQLException e) {
			    log.error("数据库关闭错误：",e);
			   }
			  }
		return flag;
	}
	
	public Gamerule getGamerule() {
		statment();
		Gamerule g=new Gamerule();
		String sql = "select * from lotteryinfo where status=1";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next()){
				g.setTitle(re.getString("gametitle"));
				g.setContent(re.getString("content"));
			}
			return g;
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return g;
	}

	public String getIndexId(String resumeid) {
		statment();
		String indexid="0";
		String sql = "select * from users where status=1 and resumeid=" + resumeid;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next())
				 indexid=re.getString("win_state");
			return indexid;
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return indexid;
	}

	public String getIndex(String phone, String resumeid) {// 返回id及信息
		statment();
		String tim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		String sql = "select radix_num from radix where status=1";
		ResultSet re;
		try {
			long l = 0l, k;
			re = stat.executeQuery(sql);
			while (re.next())
				l = Long.parseLong(re.getString("radix_num"));
			if (l != 0) {
				String str = "0", num = "0";
				k = (long) (Math.random() * l) + 1;
				sql = "select * from awards where  status=1";
				re = stat.executeQuery(sql);
				while (re.next()) {
					if (k <= Long.parseLong(re.getString("amount"))) {
						str = re.getString("awardid");
						num = re.getString("amount");
						break;
					} else {
						k -= Long.parseLong(re.getString("amount"));
					}
				}
				if (Integer.parseInt(num) != 0) {// 有奖
					if (!"0".equals(str)) {// 是否有剩余奖品
						sql = "select count(*) as aa from users where  status=1 and win_state="
								+ str;
						re = stat.executeQuery(sql);
						while (re.next())
							if (num.equals(re.getString("aa"))) {
								str = "0";
							}
					}
					if (!"0".equals(str)) {// 插入中奖数据
						sql = "update users set win_state=" + str
								+ " where  status=1 and resumeid=" + resumeid;
						stat.executeUpdate(sql);

						String userid = "0", awardname = "something";
						sql = "select * from users where status=1 and resumeid=" + resumeid;
						re = stat.executeQuery(sql);
						while (re.next())
							userid = re.getString("userid");
						sql = "select * from awards where status=1 and awardid=" + str;
						re = stat.executeQuery(sql);
						while (re.next())
							awardname = re.getString("awardname");

						sql = "insert into awardrecord(userid,rank,awardname,timadd) values('"
								+ userid
								+ "','"
								+ str
								+ "','"
								+ awardname
								+ "','" + tim + "')";
						stat.executeUpdate(sql);
						// sql="select * from awards where awardid="+str;
						// re=stat.executeQuery(sql);
						// while(re.next())
						// str+=";"
					}
				} else {
					sql = "update users set win_state=0 where status=1 and resumeid="
							+ resumeid;
					stat.executeUpdate(sql);
				}
				return str;
			}
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return "0";
	}

	public boolean isGamePhone(String phone, String resumeid) {
		statment();
		boolean flag=false;
		String sql = "select * from users where status=1 and win_state=-2 and phone='"
				+ phone + "' and resumeid='" + resumeid + "'";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next())
				 flag=true;
			return flag;
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return flag;
	}

	public String addGameUser(String phone, String name, String resumeid) {
		statment();
		String tim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		String sql = "select * from users where status=1 and resumeid=" + resumeid;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next())
				return "00";

			sql = "insert into users(resumeid,name,phone,win_state,timadd) values('"
					+ resumeid
					+ "','"
					+ name
					+ "','"
					+ phone
					+ "','-2','"
					+ tim + "')";
			stat.executeUpdate(sql);
			return phone + ";" + name;

		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return "00";
	}

	public String getModeltypeName(String id) {
		statment();
		String str="全部";
		if (id == null)
			return str;
		String sql = "select * from modeltype where status=1 and id=" + id;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next())
				str= re.getString("name");
			return str;
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return str;
	}

	public void addClicktimes(String id) {
		statment();
		String sql = "update modeltype set clicktimes=clicktimes+1 where status=1 and type=1 and id="
				+ id;
		try {
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
	}

	public Adv getAd() {
		statment();
		String sql = "select count(*) as aa from modeltype where type=1 and status=1";
		ResultSet re;

		int a = 0;
		try {
			re = stat.executeQuery(sql);
			while (re.next())
				a = Integer.parseInt(re.getString("aa"));
			Adv[] b = new Adv[a];
			String[] c = new String[a];
			sql = "select * from modeltype where type=1 and status=1";
			re = stat.executeQuery(sql);
			int i = 0;
			while (re.next()) {
				b[i] = new Adv();
				b[i].setId(re.getString("id"));
				b[i].setPic_url(vari.wa_pic_path + re.getString("pic_url"));
				b[i].setName(re.getString("name"));
				b[i].setUrl(re.getString("url"));
				c[i] = re.getString("id");
				i++;
			}
			Random rand = new Random();
			int d = rand.nextInt(a);
			sql = "update modeltype set showtimes=showtimes+1 where status=1 and id=" + c[d];
			stat.executeUpdate(sql);
			return b[d];

		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return null;
	}

	public ArrayList<ArrayList> getModeltypeSupcompanylist() {
		statment();
		ArrayList<ArrayList> arr = new ArrayList<ArrayList>();
		ArrayList<Modeltype> array = new ArrayList<Modeltype>();
		Map<String, Modeltype> hm = new HashMap<String, Modeltype>();
		Modeltype c = null;
		String str = "";
		String sql = "select * from modeltype where type=0 and status=1 order by rank desc";
		ResultSet re;
		try {
			int i = 1;
			re = stat.executeQuery(sql);
			while (re.next()) {
				c = new Modeltype();
				c.setId(re.getString("id"));
				c.setPic_url(vari.wa_pic_path + re.getString("pic_url"));
				c.setType("0");
				str = re.getString("rank") == null ? "0" : re.getString("rank")
						+ ".";
				for (;;) {
					if (hm.containsKey(str)) {
						str = str + "0";
					} else {
						hm.put(str, c);
						break;
					}
				}

			}

			sql = "select * from t2company where status=1 and type=1 order by rank desc";
			re = stat.executeQuery(sql);
			while (re.next()) {
				c = new Modeltype();
				c.setId(re.getString("id"));
				c.setPic_url(vari.wa_pic_path + re.getString("pic_url"));
				c.setType("1");
				str = re.getString("rank") == null ? "0" : re.getString("rank")
						+ ".";
				for (;;) {
					if (hm.containsKey(str)) {
						str = str + "0";
					} else {
						hm.put(str, c);
						break;
					}
				}
				// if(i%3==0){arr.add(array);array=new ArrayList<Modeltype>();}
				// i++;
			}

			ArrayList<Map.Entry<String, Modeltype>> infoIds = new ArrayList<Map.Entry<String, Modeltype>>(
					hm.entrySet());

			Collections.sort(infoIds,
					new Comparator<Map.Entry<String, Modeltype>>() {
						public int compare(Map.Entry<String, Modeltype> o1,
								Map.Entry<String, Modeltype> o2) {
							return (o2.getKey()).compareTo(o1.getKey());
						}
					});

			for (int j = 1; j <= infoIds.size(); j++) {
				array.add(infoIds.get(j - 1).getValue());
				if (j % 3 == 0) {
					arr.add(array);
					array = new ArrayList<Modeltype>();
					System.out.println("j=" + j);
				}
			}

			if (array.size() == 1 || array.size() == 2)
				arr.add(array);
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return arr;
	}

	public void delWorkexperi(String id) {
		statment();
		String sql = "delete from t6workexperience where id=" + id;
		try {
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
	}

	public boolean addWorkexperi(String userid, String companyname,
			String department, String beg_tim, String end_tim, String position,
			String salary, String info) {
		statment();
		String tim = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String sql = "insert into t6workexperience(resumeid,companyname,department,position,salary,info,beg_tim,end_tim,timadd) values('"
				+ userid
				+ "','"
				+ companyname
				+ "','"
				+ department
				+ "','"
				+ position
				+ "','"
				+ salary
				+ "','"
				+ info
				+ "','"
				+ beg_tim
				+ "','" + end_tim + "','" + tim + "')";
		try {
			stat.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return false;
	}

	public ArrayList<Work> getWorklist(String userid) {
		statment();
		ArrayList<Work> array = new ArrayList<Work>();
		Work w = null;
		String sql = "select * from t6workexperience where status=1 and resumeid=" + userid;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next()) {
				w = new Work();
				w.setId(re.getString("id"));
				w.setBeg_tim(re.getString("beg_tim"));
				w.setEnd_tim(re.getString("end_tim"));
				w.setCompanyname(re.getString("companyname"));
				w.setDepartment(re.getString("department"));
				w.setInfo(re.getString("info"));
				w.setPosition(re.getString("position"));
				w.setSalary(re.getString("salary"));
				array.add(w);
			}
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return array;
	}

	public String getCompanyId(String positionid) {
		statment();
		String sql = "select * from t3position where status=1 and id=" + positionid;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next())
				return re.getString("companyid");
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return "0";
	}

	public ArrayList<Position> getSendPlist(String userid) {
		statment();
		ArrayList<Position> array = new ArrayList<Position>();
		Position p = null;
		ResultSet re;
		String sql="";
		try {
			 sql = "select a.timadd,b.position_name,c.companyname,c.address,b.salary,b.end_tim,b.id from positionid_userid as a left join t3position as b on a.positionid=b.id left join t2company as c on b.companyid=c.id where b.status=1 and c.status=1 and a.userid="
					+ userid;
			re = stat.executeQuery(sql);
			while (re.next()) {
				p = new Position();
				p.setId(re.getString("id"));
				p.setAdd_tim(re.getString("timadd"));
				p.setPosition_name(re.getString("position_name"));
				p.setCompanyname(re.getString("companyname"));
				p.setAddress(re.getString("address"));
				p.setSalary(vari.map_salary.get(re.getString("salary")));
				p.setEnd_tim(re.getString("end_tim"));
				array.add(p);
			}
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return array;
	}

	public boolean addResume(String id, String openid, String real_nam,
			String birthday, String gender, String telphone, String email,
			String years, String school, String gradu_tim, String degree,
			String major, String salary, String expectsalary, String address,
			String self_evaluate) {
		statment();
		birthday = "".equals(birthday) ? null : ("'" + birthday + "'");
		gradu_tim = "".equals(gradu_tim) ? null : ("'" + gradu_tim + "'");
		boolean flag = true;
		String tim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		ResultSet re;
		String sql="";
		try {
			 sql = "select * from t5resume where status=1 and id=" + id;
			re = stat.executeQuery(sql);
			while (re.next()) {
				flag = false;
			}
			if (flag)
				sql = "insert into t5resume(jobid,wx_openid,real_nam,birthday,gender,telphone,email,years,school,gradu_tim,degree,major,salary,expectsalary,address,self_evaluate,timadd)"
						+ " values('"
						+ id
						+ "','"
						+ openid
						+ "','"
						+ real_nam
						+ "',"
						+ birthday
						+ ",'"
						+ gender
						+ "','"
						+ telphone
						+ "','"
						+ email
						+ "','"
						+ years
						+ "','"
						+ school
						+ "',"
						+ gradu_tim
						+ ",'"
						+ degree
						+ "','"
						+ major
						+ "','"
						+ salary
						+ "','"
						+ expectsalary
						+ "','"
						+ address
						+ "','" + self_evaluate + "','" + tim + "')";
			else
				sql = "update t5resume set real_nam='" + real_nam
						+ "',birthday=" + birthday + ",gender='" + gender
						+ "',telphone='" + telphone + "',email='" + email
						+ "',years='" + years + "',school='" + school
						+ "',gradu_tim=" + gradu_tim + ",degree='" + degree
						+ "',major='" + major + "',salary='" + salary
						+ "',expectsalary='" + expectsalary + "',address='"
						+ address + "',self_evaluate='" + self_evaluate
						+ "' where  status=1 and id=" + id;
			stat.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return false;
	}

	public Company getFcompany(String companyid) {
		statment();
		Company c = null;
		String sql = "select * from t2company where status=1 and id=" + companyid;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next()) {
				c = new Company();
				c.setName(re.getString("companyname"));
				c.setPic_url(vari.wa_pic_path + re.getString("pic_url"));
				c.setInfo(re.getString("info"));
			}
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return c;
	}

	public College getCollege(String fairid) {
		statment();
		College c = null;
		String sql = "select address,activname as name,type,beg_day,end_day,info from t8jobfair  where status=1 and id="
				+ fairid;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next()) {
				c = new College();
				c.setName(re.getString("name"));
				c.setAddress(re.getString("address"));
				c.setBeg_day(re.getString("beg_day"));
				c.setEnd_day(re.getString("end_day"));
				c.setInfo(re.getString("info"));
			}
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return c;
	}

	public ArrayList<Company> getFcompanylist(String fairid) {
		statment();
		String ids = "0";
		ArrayList<Company> array = new ArrayList<Company>();
		Company c;
		String sql = "select * from t8jobfair where status=1 and id=" + fairid;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next())
				ids = re.getString("companyids");
			if (!"0".equals(ids)) {
				String[] idids = ids.split("\\|");
				for (int i = 0; i < idids.length&&idids[i].matches("\\d+"); i++) {
					sql = "select * from t2company where status=1 and id=" + idids[i];
					re = stat.executeQuery(sql);
					while (re.next()) {
						c = new Company();
						c.setId(idids[i]);
						c
								.setPic_url(vari.wa_pic_path
										+ re.getString("pic_url"));
						c.setInfo(re.getString("info"));
						c.setName(re.getString("companyname"));
						array.add(c);
					}
				}
			}
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return array;
	}

	public ArrayList<College> getCollegelist(int p, String type) {
		statment();
		String tim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		ArrayList<College> array = new ArrayList<College>();
		College c;
		String lim = "";
		if (p > 0)
			lim = " limit " + (p - 1) * vari.p_size + "," + vari.p_size;
		if (type == null)
			type = "1";
		String sql = "select address,head_url,info,beg_day,end_day,activname as name,id from t8jobfair where status=1 and  end_day>'"
				+ tim + "' and type=" + type + lim;
		// if("0".equals(type))
		// sql =
		// "select address,head_url,info,beg_day,end_day,activname as name,id from t8jobfair where  end_day>'"
		// + tim + "' and type=" + type+lim;
		// else //高校
		// sql =
		// "select b.address,b.head_url,b.info,a.beg_day,a.end_day,b.name,a.id from t8jobfair as a left join college as b on a.collegeid=b.id where  a.end_day>'"
		// + tim + "' and a.type=" + type+lim;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			String headimg="";
			while (re.next()) {
				c = new College();
				c.setAddress(re.getString("address"));
				c.setHead_url((headimg=re.getString("head_url")).matches("\\d{13}.(jpg|gif|png)")?(vari.wa_pic_path +headimg):("./imf/headimg.jpg") );
				c.setId(re.getString("id"));
				c.setInfo(re.getString("info"));
				c.setBeg_day(re.getString("beg_day"));
				c.setEnd_day(re.getString("end_day"));
				c.setName(re.getString("name"));
				array.add(c);
			}
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return array;
	}

	public Position getPosition(String positionid) {
		statment();
		String tim = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Position p = null;
		String sql = "select * from t3position where status=1 and beg_tim<='" + tim
				+ "' and end_tim>='" + tim + "' and id=" + positionid;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next()) {
				p = new Position();
				p.setAdd_tim(re.getString("timadd"));
				p.setBeg_tim(re.getString("beg_tim"));
				p.setDegree(vari.map_degree.get(re.getString("degree")));
				p.setEnd_tim(re.getString("end_tim"));
				p.setExperience(vari.map_workexperi.get(re
						.getString("workexperience")));
				p.setId(re.getString("id"));
				p.setInfo(re.getString("info"));
				p.setPeoples(re.getString("peoples"));
				p.setPosi_type("1".equals(re.getString("posi_type")) ? "全职"
						: "兼职");
				p.setPosition_name(re.getString("position_name"));
				p.setSalary(vari.map_salary.get(re.getString("salary")));
				p.setSex(re.getString("sex"));
				p.setAddress(re.getString("typetwoid"));
				p.setType(re.getString("type"));
				p.setCompanyid(re.getString("companyid"));
			}
			if (p != null && p.getAddress().matches("\\d+")) {
				sql = "select * from shenshi where id=" + p.getAddress();
				re = stat.executeQuery(sql);
				while (re.next())
					p.setAddress(re.getString("aa"));
			}
			if(p!=null&&p.getType().equals("1")){
				sql="select * from t2company where id="+p.getCompanyid();
				re=stat.executeQuery(sql);
				while(re.next())
					p.setCompanyname(re.getString("companyname"));
			}
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return p;
	}

	public ArrayList<Position> getPositionlist(String companyid,int type) {
		statment();
		String tim = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		ArrayList<Position> array = new ArrayList<Position>();
		Position p;
		String sql = "select * from t3position where status=1 and  beg_tim<='" + tim
				+ "' and end_tim>='" + tim + "' and type="+type+" and companyid="
				+ companyid;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next()) {
				p = new Position();
				p.setAdd_tim(re.getString("timadd").split(" ")[0]);
				p.setId(re.getString("id"));
				p.setPosition_name(re.getString("position_name"));
				p.setSalary(vari.map_salary.get(re.getString("salary")));
				p.setPeoples(re.getString("peoples"));
				array.add(p);

			}
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return array;
	}

	public Company getCompany(String companyid) {
		statment();
		Company c = null;
		String sql = "select * from t2company where status=1 and id=" + companyid;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next()) {
				c = new Company();
				c.setId(re.getString("id"));
				c.setAddress(re.getString("address"));
				c.setInfo(re.getString("info"));

				c.setName(re.getString("companyname"));
				c.setProperties(vari.map_properties.get(re
						.getString("properties")));
				c.setScale(vari.map_scale.get(re.getString("scale")));
				c.setConnector(re.getString("connector"));
				c.setEmail(re.getString("email"));
				c.setProf_url(re.getString("prof_url"));
				c.setTel(re.getString("tel"));

			}
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}
		finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return c;
	}

	public ArrayList<Company> getCompanylist(int p, String typeid) {
		statment();
		ArrayList<Company> array = new ArrayList<Company>();
		Company c;
		String t = "";
		String lim = "";
		if (p > 0)
			lim = " limit " + (p - 1) * vari.p_size + "," + vari.p_size;
		if (typeid != null && !"null".equals(typeid))
			t = " and modeltypeid=" + typeid;
		String sql = "select * from t2company where status=1 and type=0" + t
				+ " order by rank desc" + lim;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next()) {
				c = new Company();
				c.setId(re.getString("id"));
				c.setAddress(re.getString("address"));
				c.setName(re.getString("companyname"));
				c.setPosition_tim(re.getString("timadd").split(" ")[0]);
				array.add(c);
			}
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return array;
	}

	public String[][] getExperience(String userid) {
		statment();
		String sql = "select count(*) as aa from t6workexperience where status=1 and resumeid="
				+ userid;
		String[][] ex = null;
		int i = 0;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next()) {
				i = Integer.parseInt(re.getString("aa"));
			}
			ex = new String[i][7];
			int j = 0;
			sql = "select * from t6workexperience where status=1 and resumeid=" + userid;
			re = stat.executeQuery(sql);
			while (re.next()) {
				ex[j][0] = re.getString("companyname");
				ex[j][1] = re.getString("department");
				ex[j][2] = re.getString("position");
				ex[j][3] = re.getString("salary");
				ex[j][4] = re.getString("info");
				ex[j][5] = re.getString("beg_tim");
				ex[j][6] = re.getString("end_tim");
				j++;
			}
			for(int m=0;m<ex.length;m++)
				for(int n=0;n<7;n++)
					if(ex[m][n]==null)ex[m][n]="";
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return ex;
	}

	public String[] getResume(String userid) {
		statment();
		String[] r = null;
		String sql = "select * from t5resume where status=1 and id=" + userid;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next()) {
				r = new String[14];
				r[0] = re.getString("real_nam");
				r[1] = re.getString("gender");
				r[2] = re.getString("telphone");
				r[3] = re.getString("email");
				r[4] = re.getString("years");
				r[5] = re.getString("school");
				r[6] = re.getString("gradu_tim");
				r[7] = re.getString("degree");
				r[8] = re.getString("major");
				r[9] = re.getString("salary");
				r[10] = re.getString("expectsalary");
				r[11] = re.getString("address");
				r[12] = re.getString("self_evaluate");
				r[13] = re.getString("birthday");
			}
			for(int i=0;i<r.length;i++)
				if(r[i]==null)r[i]="";
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return r;
	}

	public String getEmail(String hunterid) {
		statment();
		String sql = "select * from hunter where status=1 and id=" + hunterid;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next())
				return re.getString("email");
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return "";
	}

	public String getPEmail(String positionid) {
		statment();
		String sql = "select b.email as aa from t3position as a left join t2company as b on a.companyid=b.id where a.status=1 and b.status=1 and a.id="
				+ positionid;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next())
				return re.getString("aa");
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return "";
	}

	public boolean isHaveHPU(String hpid, String userid, String hptype,String hpositionid) {// true
		// 可以
		statment();
		boolean flag=true;
		String addstr="",s1="",s2="";
		if("huntid".equals(hptype)&&hpositionid!=null&&!"".equals(hpositionid)){//委托猎头下的职位
			addstr=" and positionid="+hpositionid;
			s1=",positionid";s2=","+hpositionid;
		}
		String tim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		String time = null;
		String sql = "select * from " + hptype + "_userid where status=1 and " + hptype
				+ "=" + hpid + " and userid=" + userid+addstr;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next())
				time = re.getString("timadd");
			if (time != null) {
				sql = "select date_format (date_add('" + time
						+ "', interval 7 day),'%Y-%c-%d %H:%i:%s') as aa";
				re = stat.executeQuery(sql);
				while (re.next())
					time = re.getString("aa");

				Date d = null;
				try {
					d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
				} catch (ParseException e) {
					
					log.error("Exception:",e);log.info(sql);
				}
				if(d.before(new Date())){
					sql="update " + hptype + "_userid set status=0 where " + hptype
					+ "=" + hpid + " and userid=" + userid+addstr;
					stat.executeUpdate(sql);
				}else //7天内
				flag=false;
			} 
				if(flag){
				sql = "insert into " + hptype + "_userid(" + hptype
						+ ",userid"+s1+",timadd) values(" + hpid + "," + userid+s2
						+ ",'" + tim + "')";
				stat.executeUpdate(sql);
				return flag;
			}

		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return false;
	}

	public Hunter getHunterDetail(String id) {
		statment();
		Hunter h = null;
		String sql = "";
		ResultSet re;
		sql = "select * from hunter where status=1 and id=" + id;
		try {
			re = stat.executeQuery(sql);
			while (re.next()) {
				h = new Hunter();
				h.setHead_url(vari.wa_pic_path + re.getString("head_url"));
				h.setId(id);
				h.setInfo(re.getString("info"));
				h.setName(re.getString("real_nam"));
				h.setRank(re.getString("rank"));
				h.setTypeone(re.getString("typeoneid"));

				h.setEname(re.getString("en_nam"));
				h.setBackground(re.getString("background"));
				h.setFocuscnt(re.getString("focuscnt"));
				h.setPosition(re.getString("position"));
				h.setWorkyear(re.getString("workyear"));
			}
			if (h != null) {
				sql = "select * from t1typeone where id=" + h.getTypeone();
				re = stat.executeQuery(sql);
				while (re.next()) {
					h.setTypeone(re.getString("name"));
				}
			}

			// 改关注度
			sql = "update hunter set focuscnt=focuscnt+1 where status=1 and id=" + id;
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return h;
	}

	public ArrayList<Hunter> getHunterlist(int p) {
		statment();
		ArrayList<Hunter> array = new ArrayList<Hunter>();
		Hunter h;
		String sql = "";
		ResultSet re;
		String lim = "";
		if (p > 0)
			lim = " limit " + (p - 1) * vari.p_size + "," + vari.p_size;
		sql = "select * from hunter where  status=1 order by rank0 desc" + lim;
		try {
			re = stat.executeQuery(sql);

			while (re.next()) {
				h = new Hunter();
				h.setHead_url(vari.wa_pic_path + re.getString("head_url"));
				h.setId(re.getString("id"));
				h.setInfo(re.getString("info"));
				h.setName(re.getString("real_nam"));
				array.add(h);
			}
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return array;
	}

	public void isAddOpenid(String id, String openid) {
		statment();
		String sql = "";
		ResultSet re;
		boolean flag = false;
		sql = "select id from t5resume where status=1 and wx_openid='" + openid + "'";
		try {
			re = stat.executeQuery(sql);
			while (re.next())
				flag = true;
			if (!flag) {
				sql = "insert into t5resume(jobid,wx_openid,timadd) values('"
						+ id + "','" + openid + "',now())";
				stat.executeUpdate(sql);
			}
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
	}

	public User getUser(String openid) {
		statment();
		User u = null;
		String sql = "";
		ResultSet re;
		sql = "select * from t5resume where status=1 and wx_openid='" + openid + "'";
		try {
			re = stat.executeQuery(sql);
			while (re.next()) {
				u = new User();
				u.setId(re.getString("id"));
				u.setName(re.getString("real_nam"));
				u.setOpenid(re.getString("wx_openid"));
				if ((re.getString("telphone") != null && !"".equals(re
						.getString("telphone")))
						|| (re.getString("email") != null && !"".equals(re
								.getString("email")))) {
					u.setState("1");
				} else
					u.setState("0");

			}
			if (u != null) {// 抽奖状态
				sql = "select * from users where status=1 and resumeid=" + u.getId();
				re = stat.executeQuery(sql);
				while (re.next()) {
					u.setCjstate(vari.map_ranktype.get(re
							.getString("win_state")));
					u.setCjname(re.getString("name"));
					u.setCjphone(re.getString("phone"));
				}
			}
			if ("0".equals(u.getCjstate()) || "1".equals(u.getCjstate())
					|| "2".equals(u.getCjstate())) {// 奖品名
				sql = "select * from awards where status=1 and awardid=" + u.getCjstate();
				re = stat.executeQuery(sql);
				while (re.next())
					u.setCjstatename(re.getString("awardname"));
			}
			// u.setCjstate(u.getCjstate()==null?"3":u.getCjstate());
		} catch (SQLException e) {
			
			log.error("Exception:",e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.error("Exception:",e);
			}
			
		}
		return u;
	}
}
