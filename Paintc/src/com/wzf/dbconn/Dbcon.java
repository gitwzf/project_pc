package com.wzf.dbconn;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wzf.model.Add;
import com.wzf.model.Askitems;
import com.wzf.model.Askpage;
import com.wzf.model.Askpaper;
import com.wzf.model.Askquestion;
import com.wzf.model.Askresult;
import com.wzf.model.Askresultitems;
import com.wzf.model.Compete;
import com.wzf.model.Competepaint;
import com.wzf.model.Gamerule;
import com.wzf.model.Infopage;
import com.wzf.model.Manageruser;
import com.wzf.model.Model;
import com.wzf.model.Regform0;
import com.wzf.model.School;
import com.wzf.model.Teacher;
import com.wzf.model.Train;
import com.wzf.model.User;
import com.wzf.model.Vip;
import com.wzf.model.Voteuser;
import com.wzf.model.Week;
import com.wzf.model.Winner;
import com.wzf.model.Workss;
import com.wzf.pubvari.Variable;

public class Dbcon {
	Logger log=Logger.getLogger("logfile");
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
	private void statment0(){//操作结果集
		 connection();
		 try {
			stat=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			
			log.error("error:",e);
		}
	}
	
	public void uphead(String phone,String headurl){
		if(phone==null||headurl==null)return;
		String sql="update students set headimg='"+headurl+"' where phone="+phone;
		updateSql(sql);
		
	}
	
	public Map<String,Infopage> getPage(){
		statment();
		String sql="select id,diytitle,content from page";
		ResultSet re;
		Map<String,Infopage> map=new HashMap<String, Infopage>();
		try {
			re=stat.executeQuery(sql);
			while(re.next())
				map.put(re.getString("id"),new Infopage(re.getString("id"),re.getString("diytitle") ,re.getString("content") ));
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
	
	
	
	public Map<String,Manageruser> updateCompete(){
		Map<String,Manageruser> map=new HashMap<String, Manageruser>();
		for(int i=0;i<Variable.array_competepaint.size();i++){
			Workss w=Variable.array_competepaint.get(i);
			if(!map.containsKey(w.getStudentid()))
				map.put(w.getStudentid(), new Manageruser(i+";",Integer.parseInt(w.getScore())));
			else {
				map.get(w.getStudentid()).setCompetepaintids(map.get(w.getStudentid()).getCompetepaintids()+i+";");	
				map.get(w.getStudentid()).setVote(map.get(w.getStudentid()).getVote()+Integer.parseInt(w.getScore()));
			}
		}
		int competeid=isCompetePaint();
		statment();
		String sql="select c.studentid,c.studentname,format(date_format(now(), '%Y')-date_format(c.birthday, '%Y'),0) as age,c.headimg " +
				"from works b left join students c on b.studentid=c.studentid where b.status=2 and competeid="+competeid+" order by b.createtime";
		ResultSet re;
		try {
			re=stat.executeQuery(sql);
			while(re.next()){
				if(map.containsKey(re.getString("studentid"))){
					map.get(re.getString("studentid")).setStname(re.getString("studentname"));
					map.get(re.getString("studentid")).setAge(re.getString("age"));
					String headimg="images/photo.png";
					if(re.getString("headimg")!=null&&!re.getString("headimg").equals(""))headimg=re.getString("headimg");
					map.get(re.getString("studentid")).setHeadimg(headimg);
					map.get(re.getString("studentid")).setId(re.getString("studentid"));
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
			  return map;
		
	}
	
	public void updateCompetepaint(ArrayList<Workss> array){
		statment();
		String sql="";
		try {
			int i;
		for(i=0;i<array.size();i++){
			sql="update works set core="+array.get(i).getScore()+" where workid="+array.get(i).getWorkid();
				stat.addBatch(sql);
				if(i==1000){stat.executeBatch();i=0;}
		}
		if(i!=0)stat.executeBatch();
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
		
	}
	
	public void updateAskpaper(Map<String,Askpage> map){
		statment();
		String sql="";
		try {
		for(String str:map.keySet()){
			sql="update ask_paper set clicktimes="+map.get(str).getAskpaper().getClicktimes()+" where id="+str;
		stat.executeUpdate(sql);
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
	}
	
	public Map<String,Askpage> getAllAskPaper(){
		statment0();
		Map<String,Askpage> map=new HashMap<String, Askpage>();
		String ids[];
		int pages;
		String sql="select * from ask_paper where status=1";
		ResultSet re;
		try {
			re=stat.executeQuery(sql);
			re.last();
		ids=new String[pages=re.getRow()];
		//问卷
		Askpaper[] askpaper=new Askpaper[pages];
		//结果
		Askresult[] askresult=new Askresult[pages];
		    re.beforeFirst();
		    int i=0;
		    String url="";
		    while(re.next()){
		    	if(re.getString("url")!=null)url=re.getString("url");
		askpaper[i]=new Askpaper(re.getString("id"),re.getString("title"),
				url.matches("\\d{13}.(jpg|gif|png)")?(vari.wa_pic_path+url):url,re.getString("info") ,re.getString("clicktimes") );
		askresult[i++]=new Askresult(re.getString("id"));
		    }
		    //遍历问卷
		    int ques;
		    for(i=0;i<askpaper.length;i++){
		    	String id=askpaper[i].getId();
		    	sql="select * from ask_question where pageid="+id;
		    	re=stat.executeQuery(sql);
		    	re.last();ques=re.getRow();re.beforeFirst();
		    	Askquestion[] askquestion=new Askquestion[ques];
		        int j=0;
		        String queurl="";
		    	while(re.next()){
		    		if(re.getString("queurl")!=null)queurl=re.getString("queurl");
		    		askquestion[j++]=new Askquestion(re.getString("id"),
		    				queurl.matches("\\d{13}.(jpg|gif|png)")?(vari.wa_pic_path +queurl):queurl,re.getString("question"));
		    	}
		    	//遍历题目
		    	int items;
		    	for(j=0;j<askquestion.length;j++){
		    		String qid=askquestion[j].getId();
		    		sql="select * from ask_items where questionid="+qid;
		    		re=stat.executeQuery(sql);
			    	re.last();items=re.getRow();re.beforeFirst();
			    	Askitems[] askitems=new Askitems[items];
			    	int k=0;
			    	String picurl="";
			    	while(re.next()){
			    		if(re.getString("picurl")!=null)picurl=re.getString("picurl");
			    		askitems[k++]=new Askitems(re.getString("questionid"), re.getString("content"),re.getString("core") ,
			    				picurl.matches("\\d{13}.(jpg|gif|png)")?(vari.wa_pic_path+picurl):picurl);
			    	}
			    	askquestion[j].setAskitems(askitems);
		    	}
		    	askpaper[i].setAskquestion(askquestion);
		    }
		  //遍历问卷结果
		    int res;
		    for(i=0;i<askresult.length;i++){
		    	String rid=askresult[i].getId();
		    	sql="select * from ask_result where pageid="+rid;
		    	re=stat.executeQuery(sql);
		    	re.last();res=re.getRow();re.beforeFirst();
		    	Askresultitems[] askresultitem=new Askresultitems[res];
		    	int j=0;
		    	String picurl="";
		    	while(re.next()){
		    		if(re.getString("picurl")!=null)picurl=re.getString("picurl");
		    		askresultitem[j++]=new Askresultitems(re.getString("title"),re.getString("downgoal"),re.getString("upgoal") ,re.getString("content") ,
		    				picurl.matches("\\d{13}.(jpg|gif|png)")?(vari.wa_pic_path+picurl):picurl );
		    	}
		    	askresult[i].setAskresultitems(askresultitem);
		    }
		
		
		
		//遍历 返回json
		    for(i=0;i<askpaper.length;i++){
		    	Askpage askpage=new Askpage(askpaper[i], askresult[i]);
		    	map.put(askpaper[i].getId(), askpage);
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
	
	public ArrayList<Workss> getPainterlist(long p,String name){
		statment();
		ArrayList<Workss> array=new ArrayList<Workss>();
		Workss w;
		String nam="",lim="";
		if(name!=null)nam=" and c.studentname like '%"+name+"%'";
		if(p!=0l)lim=" limit "+(p-1)*vari.items_p+","+vari.items_p;
		String sql="select a.worksid,a.score,a.`comment`,b.studentid,b.url,b.isAppraised," +
				"c.studentname,format(date_format(now(), '%Y')-date_format(c.birthday, '%Y'),0) as age from appraisedworks a left join works b on a.worksid=b.workid left join students c " +
				"on b.studentid=c.studentid where b.status=1"+nam+" order by a.appraisetime desc "+lim;
		ResultSet re;
		try {
			re=stat.executeQuery(sql);
			while(re.next()){
				w=new Workss();
				w.setWorkid(re.getString("worksid"));
				w.setPicurl(re.getString("url"));
				w.setScore(re.getString("score"));
				w.setTname(re.getString("studentname"));
				w.setAge(re.getString("age"));
				array.add(w);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return array;
	}
	
	public ArrayList<Workss> getPainterlist(){
		int competeid=isCompetePaint();
		statment();
		ArrayList<Workss> array=new ArrayList<Workss>();
		Workss w;
		String sql="select b.worktitle,b.core,b.workid,b.studentid,b.url,c.studentname,format(date_format(now(), '%Y')-date_format(c.birthday, '%Y'),0) as age " +
				"from works b left join students c on b.studentid=c.studentid where b.status=2 and competeid="+competeid+" order by b.createtime";
		ResultSet re;
		try {
			re=stat.executeQuery(sql);
			while(re.next()){
				w=new Workss();
				w.setWorkid(re.getString("workid"));
				w.setStudentid(re.getString("studentid"));
				w.setPicurl(re.getString("url"));
				w.setTname(re.getString("studentname"));
				w.setAge(re.getString("age"));
				w.setScore(re.getString("core"));
				w.setTitle(re.getString("worktitle"));
				array.add(w);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return array;
	}
	
	public String addGameUser(String name, String phone) {
		statment();
		String sql = "select * from users where status=1 and phone=" + phone+" and name='"+name+"'";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next())
				return phone + ";" + name;

			sql= "select * from users where status=1 and phone=" + phone;
			re = stat.executeQuery(sql);
			while (re.next())
				return "01";
			
			sql = "insert into users(name,phone,win_state,timadd) values('"
					+ name
					+ "','"
					+ phone
					+ "','-2',now())";
			stat.executeUpdate(sql);
			return phone + ";" + name;

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
		return "00";
	}
	
	public String getIndexId(String phone) {
		statment();
		String indexid="0";
		String sql = "select * from users where status=1 and phone=" + phone;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next())
				 indexid=re.getString("win_state");
			return indexid;
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
		return indexid;
	}
	
	public String getIndex(String phone) {// 返回id及信息
		statment();
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
								+ " where  status=1 and phone=" + phone;
						stat.executeUpdate(sql);

						String userid = "0", awardname = "something";
						sql = "select * from users where status=1 and phone=" + phone;
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
								+ "',now())";
						stat.executeUpdate(sql);
						// sql="select * from awards where awardid="+str;
						// re=stat.executeQuery(sql);
						// while(re.next())
						// str+=";"
					}
				} else {System.out.println("update 0");
					sql = "update users set win_state=0 where status=1 and phone="
							+ phone;
					stat.executeUpdate(sql);
				}
				return str;
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
		return "0";
	}
	
	public boolean isGamePhone(String phone) {
		statment();
		boolean flag=false;
		String sql = "select * from users where status=1 and win_state=-2 and phone='"
				+ phone + "'";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
			while (re.next())
				 flag=true;
			return flag;
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
			
			log.error("error:", e);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.info(e);
			}
			
		}
		return g;
	}
	
	public User getUser(String name,String phone) {
		statment();
		User u = null;
		String sql = "";
		ResultSet re;
		sql = "select * from users where status=1 and name='"+name+"' and phone='"+phone+"'";
		try {
			re = stat.executeQuery(sql);
			while (re.next()) {
				u = new User();
				u.setId(re.getString("userid"));
				u.setCjstate(vari.map_ranktype.get(re
							.getString("win_state")));
				u.setCjname(re.getString("name"));
				u.setCjphone(re.getString("phone"));
				}
			if(u!=null)
			if ("0".equals(u.getCjstate()) || "1".equals(u.getCjstate())
					|| "2".equals(u.getCjstate())) {// 奖品名
				sql = "select * from awards where status=1 and awardid=" + u.getCjstate();
				re = stat.executeQuery(sql);
				while (re.next())
					u.setCjstatename(re.getString("awardname"));
			}
			// u.setCjstate(u.getCjstate()==null?"3":u.getCjstate());
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
		return u;
	}
	
	public String getCompetetion(){
		statment();
		String com=null;
		String sql="select * from competetions where competetionid=participantnumber and status=1";
		ResultSet re;
		try {
			re=stat.executeQuery(sql);
			while(re.next())
				com=re.getString("competetionid");
			return com;
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
		return com;
	}
	
	public ArrayList<Compete>  getCompetelist(String widentity){
		statment();
		ArrayList<Compete> array=new ArrayList<Compete>();
		Compete c;
		String sql="select * from paintcompetetion where status=1 and win_identity="+widentity;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		
		while(re.next()){
			c=new Compete();
			c.setId(re.getString("id"));
			c.setName(re.getString("name"));
			array.add(c);
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
		return array;
	}
	
	public String getAction(String id){
		statment();
		String sql="select * from paintcompetetion where id="+id;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		
		while(re.next())
			return re.getString("win_identity");
		
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
		return "";
	}
	
	public ArrayList<Winner> getWinnerList0(String id){
		statment();
		ArrayList<Winner>array = null;
		Winner w;
		ResultSet  re;
		boolean isStatus=false;
		String begtime="",endtime="",competename="",win_identity="",identi_num="";
		String sql="select * from paintcompetetion where status=1 and id="+id;
		try{
          re=stat.executeQuery(sql);
          while(re.next())
          { isStatus=true;
             begtime=re.getString("begtime");
             endtime=re.getString("endtime");
             competename=re.getString("name");
             win_identity=re.getString("win_identity");
             identi_num=re.getString("identi_num");
          }
         if(isStatus){//取名单
        	 sql="select aa.* from ( select a.studentid,a.win_identity,a.identi_num,a.place,a.degree,d.studentname,d.gender,d.birthday,b.workid,b.worktitle,b.url,c.score from paintwinner as a inner join works as b on a.studentid=b.studentid and b.isAppraised=1 and a.win_identity="+win_identity+" and a.status=1 and b.status=1 and a.identi_num="+identi_num+" and a.begtime='"+begtime+"' and a.endtime='"+endtime+"' left join appraisedworks as c on b.workid=c.worksid and c.status=1 left join students as d on a.studentid=d.studentid and d.status=1 order by c.score desc) as aa group by aa.studentid order by aa.place";
        	 re=stat.executeQuery(sql);
        	 array=new ArrayList<Winner>();
        	 while(re.next()){
        		 w=new Winner();
        		 w.setBirthday(re.getString("birthday"));
        		 w.setGender(re.getString("gender"));
        		 w.setId(re.getString("studentid"));
        		 w.setIdenti_num(re.getString("identi_num"));
        		 w.setName(re.getString("studentname"));
        		 w.setPicid(re.getString("workid"));
        		 w.setCompetename(competename);
        		 w.setPlace(re.getString("place"));
        		 w.setDegree(re.getString("degree"));
        		 w.setScore(re.getString("score"));
        		 w.setTitle(re.getString("worktitle"));
        		 w.setUrl(re.getString("url"));
        		 w.setWin_identity(re.getString("win_identity"));
        		 array.add(w);
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
		return array;
	}
	
	public ArrayList<Winner> getWinnerList(String win_identity){
		statment();
		ArrayList<Winner>array = null;
		Winner w;
		String sql="";
		String k="";
		boolean flag=false,isStatus=false;
		ResultSet  re;
		String identi_num="";
		String tim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String yyyy=new SimpleDateFormat("yyyy").format(new Date());
		String yyyy0=Integer.parseInt(yyyy)-1+"";
		String btim=Integer.parseInt(yyyy)-2+"-1-1";
		String etim=yyyy+"-1-1";
		String begtime="",endtime="",competename="";
		int mon=Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
		try {
		if("0".equals(win_identity)){identi_num=yyyy0;flag=true;}
		else if("1".equals(win_identity)){identi_num=mon/3+(mon%3==0?0:1)-1+"";
		if("0".equals(identi_num)){identi_num="4";flag=true;}
		}
		else if("2".equals(win_identity)){
			if(mon==1){mon=13;flag=true;}
			identi_num=mon-1+"";
		}
		else if("3".equals(win_identity)){
			sql="select week('"+tim+"')";
				re=stat.executeQuery(sql);
			while(re.next())
				identi_num=Integer.parseInt(re.getString("week('"+tim+"')"))-1+"";
			if(identi_num!=null&&identi_num.equals("0")){identi_num="52";flag=true;}
		}
		if(flag)k=" and begtime>'"+btim+"' and begtime<'"+etim+"'";

          sql="select * from paintcompetetion where win_identity="+win_identity+" and status=1 and identi_num="+identi_num+k;
          re=stat.executeQuery(sql);
          while(re.next())
          { isStatus=true;
             begtime=re.getString("begtime");
             endtime=re.getString("endtime");
             competename=re.getString("name");
          }
         if(isStatus){//取名单
        	 sql="select aa.* from ( select a.studentid,a.win_identity,a.identi_num,a.place,a.degree,d.studentname,d.gender,d.birthday," +
        	 		"b.workid,b.worktitle,b.url,c.score from paintwinner as a inner join works as b on a.studentid=b.studentid and b.isAppraised=1 and a.win_identity="+win_identity+" and a.status=1 and b.status=1 and a.identi_num="+identi_num+" and a.begtime='"+begtime+"' and a.endtime='"+endtime+"' left join appraisedworks as c on b.workid=c.worksid and c.status=1 left join students as d on a.studentid=d.studentid and d.status=1 order by c.score desc) as aa group by aa.studentid order by aa.place";
        	 re=stat.executeQuery(sql);
        	 array=new ArrayList<Winner>();
        	 while(re.next()){
        		 w=new Winner();
        		 w.setBirthday(re.getString("birthday"));
        		 w.setGender(re.getString("gender"));
        		 w.setId(re.getString("studentid"));
        		 w.setIdenti_num(re.getString("identi_num"));
        		 w.setName(re.getString("studentname"));
        		 w.setPicid(re.getString("workid"));
        		 w.setCompetename(competename);
        		 w.setPlace(re.getString("place"));
        		 w.setDegree(re.getString("degree"));
        		 w.setScore(re.getString("score"));
        		 w.setTitle(re.getString("worktitle"));
        		 w.setUrl(re.getString("url"));
        		 w.setWin_identity(re.getString("win_identity"));
        		 array.add(w);
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
		return array;
	}
	
	public String addSchool0(String stname,String isTrain){
		statment();
		String sql0="";
		if("1".equals(isTrain))
			sql0="select schoolid as aa from schools where schoolname='"+stname+"'";
		else if("0".equals(isTrain))
		sql0="select orgid as aa from trainingorgs where orgname='"+stname+"'";
		try {
			ResultSet re=stat.executeQuery(sql0);
			while(re.next())
				return re.getString("aa");
		} catch (SQLException e) {
			
			log.error("error:", e);log.info(sql0);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.info(e);
			}
			
		}
		return "0";
	}
	
	public String addSchool(String stname,String isTrain){
		String str="0";
		if(!(str=addSchool0(stname,isTrain)).equals("0"))return str;
		statment();
		String sql="",sql0="";
		if("1".equals(isTrain))
			{sql="insert into schools(schoolname,timadd) values('"+stname+"',now())";
			sql0="select schoolid as aa from schools where schoolname='"+stname+"'";
			}
		else if("0".equals(isTrain))
		{ sql="insert into trainingorgs(orgname,timadd) values('"+stname+"',now())";
		sql0="select orgid as aa from trainingorgs where orgname='"+stname+"'";
		}
		try {
			stat.executeUpdate(sql);
			
			ResultSet re=stat.executeQuery(sql0);
			while(re.next())
				return re.getString("aa");
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
		return "0";
	}
	
	
	public void upBill(String buyid,String zfbid){
		statment();
		String sql="update productbill set zfbID='"+zfbid+"',status=1 where buyID='"+buyid+"'";
	try {
		stat.executeUpdate(sql);
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
	
	public boolean addBasebill(String buyid,String seller,String productid,String studentid,String proname,String pronumbers,String unitprice){
		statment();
		String sql="insert into productbill(buyID,seller,productid,studentid,proname,pronumbers,unitprice,totalprice,timadd) values('"+buyid+"','"+seller+"','"+productid+"','"+studentid+"','"+proname+"','"+pronumbers+"','"+unitprice+"','"+Integer.parseInt(pronumbers)*Double.parseDouble(unitprice)+"',now())";
	   try {
		stat.executeUpdate(sql);
		return true;
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
	return false;
	}
	
	public ArrayList<Vip> getVip(){
		statment();
		String sql="select * from virtualproducts where status=1";
		ArrayList array=new ArrayList<Vip>();
		Vip v;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		
		while(re.next()){
			v=new Vip();
			v.setId(re.getString("productid"));
			v.setCharge(re.getString("charge"));
			v.setName(re.getString("productname"));
			v.setNumbers(re.getString("numbers"));
			array.add(v);
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
		return array;
	}
	
	public Vip getVip(String vid){
		statment();
		String sql="select * from virtualproducts where status=1 and productid="+vid;
		Vip v = null;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		
		while(re.next()){
			v=new Vip();
			v.setId(re.getString("productid"));
			v.setCharge(re.getString("charge"));
			v.setName(re.getString("productname"));
			v.setNumbers(re.getString("numbers"));
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
		return v;
	}
	
	public Voteuser getVoteuser(String username,String userkey){
		statment();
		Voteuser v = null;
		String sql="select * from voteusers where username='"+username+"' and voteuserkey='"+userkey+"'";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		
		while(re.next()){
			v=new Voteuser();
			v.setId(re.getString("voteuserid"));
			v.setUsername(re.getString("username"));
			v.setVoteuserkey(re.getString("voteuserkey"));
			v.setVotenumbers(re.getString("votenumbers"));
			v.setVotedetailnumbers(re.getString("votedetailnumbers"));
			v.setVotepaintnumbers(re.getString("votepaintnumbers"));
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
		return v;
	}
	
	public void updateAllVoteUser(Map<String,Voteuser> map){
		statment();
		String sql="";
		try {
		for(String str:map.keySet()){
			sql="update voteusers set votepaintnumbers="+map.get(str).getVotepaintnumbers()+
			" where username="+str.split(";")[0]+" and voteuserkey="+str.split(";")[1];
				stat.executeUpdate(sql);
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
	}
	
	public Map<String,Voteuser> getAllVoteUser(){
		statment();
		Voteuser v = null;
		Map<String,Voteuser> map=new HashMap<String, Voteuser>();
		String sql="select * from voteusers";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		while(re.next()){
			v=new Voteuser();
			v.setId(re.getString("voteuserid"));
			v.setUsername(re.getString("username"));
			v.setVoteuserkey(re.getString("voteuserkey"));
			v.setVotenumbers(re.getString("votenumbers"));
			v.setVotedetailnumbers(re.getString("votedetailnumbers"));
			v.setVotepaintnumbers(re.getString("votepaintnumbers"));
			map.put(v.getUsername()+";"+v.getVoteuserkey(),v);
		}
		} catch (SQLException e) {
			
			log.error("error:", e);log.info(sql);log.info(sql);
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
	public boolean isDonedetailuser(String username,String userkey){
		statment();
		String sql="select * from voteusers where username='"+username+"' and voteuserkey='"+userkey+"' and votedetailnumbers>0";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		
		while(re.next())
			return false;
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
		return true;
	}
	public boolean isDoneuser(String username,String userkey){
		statment();
		String sql="select * from voteusers where username='"+username+"' and voteuserkey='"+userkey+"' and votenumbers>0";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		
		while(re.next())
			return false;
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
		return true;
	}
	
	public Voteuser addVoteuser(String username,String userkey,String ip){
		statment();
		Voteuser v = null;
		String sql="insert into voteusers(username,voteuserkey,timadd,ip) values('"+username+"','"+userkey+"',now(),'"+ip+"')";
		try {
			stat.executeUpdate(sql);
			sql="select * from voteusers where username='"+username+"' and voteuserkey='"+userkey+"'";
			ResultSet re = stat.executeQuery(sql);
			while(re.next()){
				v=new Voteuser();
				v.setId(re.getString("voteuserid"));
				v.setUsername(re.getString("username"));
				v.setVoteuserkey(re.getString("voteuserkey"));
				v.setVotenumbers(re.getString("votenumbers"));
				v.setVotedetailnumbers(re.getString("votedetailnumbers"));
				v.setVotepaintnumbers(re.getString("votepaintnumbers"));
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
		return v;
	}
	
	public String addVotedetail(String workid,String username,String userkey,boolean isadd){
		statment();
		String sql="select * from works where status=1 and workid="+workid;
		ResultSet re;
		String studentid="0",voteuserid="0";
		boolean flag=false;
		try {
			 re=stat.executeQuery(sql);
			 while(re.next())
				 studentid=re.getString("studentid");
			 
			 sql="select * from voteusers where username='"+username+"' and voteuserkey='"+userkey+"'";
			 re=stat.executeQuery(sql);
			 while(re.next())
				 voteuserid=re.getString("voteuserid");
			 
			 if(!studentid.equals("0")&&!voteuserid.equals("0")){//有这个投票用户和学生
				   //判断是否已加
				 sql="select * from studentscores where studentid="+studentid+" and workid="+workid+" and voteuserid="+voteuserid;
				 re=stat.executeQuery(sql);
				 while(re.next())
					 flag=true;
				 if(!flag){//可加积分
					  if(isadd){
				 sql="insert studentscores(studentid,workid,voteuserid,scoreadd,timadd) values("+studentid+","+workid+","+voteuserid+",1,now())";
				 stat.executeUpdate(sql);
				 
//				 sql="update voteusers set votedetailnumbers=votedetailnumbers-1 where username='"+username+"' and voteuserkey='"+userkey+"'";
//					stat.executeUpdate(sql);//减可投票数
					 return workid;}
					  else return "000";//可投
				 } 
				 return "0";
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
		return "00";
	}
	
	public String addVote(String phone,String competeid,String username,String userkey){
		statment();
		String a="00";
		String id="0";
		String type_add="countvote=countvote+1",type_name="competeid";
		String sql="select b.photoid from kidmodels as a inner join (select * from modelphotos order by timadd desc) as b on a.modelid=b.modelid and b.competeid="+competeid+" and a.phone='"+phone+"' group by b.modelid order by b.countvote desc";
		try {
				ResultSet re=stat.executeQuery(sql);
		while(re.next())
			id=re.getString("photoid");
		//非父赛投票
		if("0".equals(id)){
			sql="select b.photoid from kidmodels as a inner join (select * from modelphotos order by timadd desc) as b on a.modelid=b.modelid and b.competelevel="+competeid+" and a.phone='"+phone+"' group by b.modelid order by b.vote desc";
			re=stat.executeQuery(sql);
			while(re.next())
				id=re.getString("photoid");
			type_add="vote=vote+1";
			type_name="competelevel";
		}
		
		if(!"0".equals(id))	{
			sql="update modelphotos set "+type_add+" where photoid="+id;
		stat.executeUpdate(sql);
		sql="update voteusers set votenumbers=votenumbers-1 where username='"+username+"' and voteuserkey='"+userkey+"'";
			stat.executeUpdate(sql);//减可投票数
		//添加投票记录	
		String idd="0";
	String sql0="select * from voteusers where username='"+username+"' and voteuserkey='"+userkey+"'";	
	 re=stat.executeQuery(sql0);
	while(re.next())
		idd=re.getString("voteuserid");
	if(!"0".equals(idd)){  //没这个账号
		sql0="insert into votes(photoid,voteuserid,timadd) values("+id+","+idd+",now())";
		stat.executeUpdate(sql0);
	}else return a;
		}
		sql="select b.countvote,b.vote  from kidmodels as a inner join modelphotos as b on a.modelid=b.modelid where a.phone="+phone+" and b."+type_name+"="+competeid;
		 re=stat.executeQuery(sql);
		while(re.next())
		return "countvote=countvote+1".equals(type_add)?re.getString("countvote"):re.getString("vote");
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
	/**
	 * @param p 页码
	 * @param limit 每页条目数
	 * @param sname 姓名
	 * */
	public ArrayList<Model> getModellist(Long p,int limit,String sname){
		statment();
		ArrayList array=new ArrayList<Model>();
		String lim="";
		String name="";
		if(sname!=null&&!"".equals(sname))
			{
			name=" and a.name like '%"+sname+"%' ";
			}
		if(p!=0&&limit!=0)lim="limit "+(p-1)*limit+","+limit;
		Model m;
		String id="0",competestatus="1";
		ResultSet re;
		String sql="";
		try {
			//如果没有进行中的比赛，显示最近未开始的比赛
		 sql="select * from competetions where status in(0,1) order by status desc,timadd desc";
		re=stat.executeQuery(sql);
		while(re.next())
		{id=re.getString("competetionid");competestatus=re.getString("status");break;}
		//显示最新已结束比赛
		if("0".equals(id)){
			sql="select * from competetions order by timadd desc";
			re=stat.executeQuery(sql);
			while(re.next())
			{id=re.getString("competetionid");competestatus="2";break;}
		}
	  if(!"0".equals(id)){	    																					   //这里或许可以自定义规则
		  boolean flag=false;
		  if("0".equals(competestatus))//判断未开始是否有榜单
		{
		  sql="select count(*) as aa from competewinner where competeid="+id;
		 re=stat.executeQuery(sql);
		 while(re.next())
			 if(!re.getString("aa").equals("0"))flag=true;//有榜单的
		}
		  if(!flag)
		  {  
		  sql="select  a.* ,b.countvote,b.picurl,b.competeid from kidmodels as a inner join (select * from modelphotos order by timadd desc) as b on a.modelid=b.modelid and b.competeid="+id+name+" group by b.modelid order by b.modifytime "+lim;
		 re = stat.executeQuery(sql);
		while(re.next()){
			m=new Model();
			m.setVote(re.getString("countvote"));
			m.setStudentname(re.getString("name"));
			m.setGender(re.getString("gender"));
			m.setPicurl(re.getString("picurl").matches("\\d{13}.(jpg|gif|png)")?//经wabacus上传的
					(vari.wa_pic_path+re.getString("picurl")):re.getString("picurl"));
			m.setPhone(re.getString("phone"));
			m.setCompeteid(re.getString("competeid"));
			m.setAge(re.getString("age"));
			m.setHeight(re.getString("height"));
			m.setWeight(re.getString("weight"));
			
			m.setCompetestatus(competestatus);
			m.setCompeteid(id);
			array.add(m);
		}
		//非父赛
		if(array.size()==0){
			sql="select  a.* ,b.vote,b.picurl,b.competeid from kidmodels as a inner join (select * from modelphotos order by timadd desc) as b on a.modelid=b.modelid and b.competelevel="+id+name+" group by b.modelid order by b.modifytime "+lim;
			re = stat.executeQuery(sql);
		while(re.next()){
			m=new Model();
			m.setVote(re.getString("vote"));
			m.setStudentname(re.getString("name"));
			m.setGender(re.getString("gender"));
			m.setPicurl(re.getString("picurl").matches("\\d{13}.(jpg|gif|png)")?//经wabacus上传的
					(vari.wa_pic_path+re.getString("picurl")):re.getString("picurl"));
			m.setPhone(re.getString("phone"));
			m.setCompeteid(re.getString("competeid"));
			m.setAge(re.getString("age"));
			m.setHeight(re.getString("height"));
			m.setWeight(re.getString("weight"));
			
			m.setCompetestatus(competestatus);
			m.setCompeteid(id);
			array.add(m);
		}
		}
		  }else{//显示最新榜单
				sql="select  a.* ,c.vote,b.picurl,b.competeid from competewinner c left join modelphotos b on c.photoid=b.photoid left join kidmodels a on b.modelid=a.modelid where c.competeid="+id+name+" order by c.vote desc "+lim;
				re = stat.executeQuery(sql);
			//	long i=(p-1)*limit+1;
				while(re.next()){
					m=new Model();
					m.setVote(re.getString("vote"));
					m.setStudentname(re.getString("name"));
					m.setGender(re.getString("gender"));
					m.setPicurl(re.getString("picurl").matches("\\d{13}.(jpg|gif|png)")?//经wabacus上传的
							(vari.wa_pic_path+re.getString("picurl")):re.getString("picurl"));
					m.setPhone(re.getString("phone"));
					m.setCompeteid(re.getString("competeid"));
					m.setAge(re.getString("age"));
					m.setHeight(re.getString("height"));
					m.setWeight(re.getString("weight"));
					
					m.setCompetestatus("3");
			//		m.setSequence(i+++"");
					m.setCompeteid(id);
					array.add(m);
				}
				
				
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
		return array;
	}
	
	public Model getModel(String phone,String competeid){
		statment();
		Model m = null;
		if(phone==null||competeid==null)return m;
		String competestatus="",fid=competeid;
		String sql0="select * from competetions where competetionid="+competeid;
		ResultSet re;
		String sql="";
		try {
			re=stat.executeQuery(sql0);
			while(re.next())
				{competestatus=re.getString("status");
				fid=re.getString("participantnumber");
				}
			String c=" and b.competeid='"+fid+"'";
			 sql="select a.*,b.picurl,b.countvote,b.vote,b.competelevel from kidmodels as a inner join (select * from modelphotos order by timadd desc) as b on a.modelid=b.modelid and a.phone="+phone+c+" group by b.modelid order by b.countvote desc ";
		//	log.info("phonesql= "+sql);
			
			re = stat.executeQuery(sql);
		
		while(re.next()){
			m=new Model();
			m.setAge(re.getString("age"));
			m.setEmail(re.getString("email")==null?"":re.getString("email"));
			m.setGender(re.getString("gender"));
			m.setHeight(re.getString("height"));
			m.setParentname(re.getString("parentname"));
			m.setPhone(phone);
			m.setQq(re.getString("qq")==null?"":re.getString("qq"));
			m.setWeight(re.getString("weight"));
			m.setWeixin(re.getString("weixin")==null?"":re.getString("weixin"));
			m.setStudentname(re.getString("name"));
			m.setRelationship(re.getString("relationship"));
			m.setPicurl(re.getString("picurl").matches("\\d{13}.(jpg|gif|png)")?//经wabacus上传的
					(vari.wa_pic_path+re.getString("picurl")):re.getString("picurl"));
			if(re.getString("competelevel")==null||"0".equals(re.getString("competelevel")))
			m.setVote(re.getString("countvote"));
			else
				m.setVote(re.getString("vote"));
			m.setCompetestatus(competestatus);
			m.setCompeteid(competeid);
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
		return m;
	}
	
	public void addModels(String phone,String parentname,String studentname,String gender,String relationship,String age,
			String height,String weight,String qq,String weixin,String email,String picurl,String competeid){
		statment();
		String tim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime());
		String oldtime=tim;
		String a="",b="",c="",aa="",bb="",cc="";
		if(qq!=null&&!"".equals(qq)){a=",qq";aa=",'"+qq+"'";}
		if(weixin!=null&&!"".equals(weixin)){b=",weixin";bb=",'"+weixin+"'";}
		if(email!=null&&!"".equals(email)){c=",email";cc=",'"+email+"'";}
		String sql0="select * from kidmodels where phone="+phone;
		String sql="insert into kidmodels(name,age,gender,height,weight,parentName,relationship,phone"+a+b+c+",timadd) values('"+studentname+"',"+age+","+gender+","+height+","+weight+",'"+parentname+"',"+relationship+","+phone+aa+bb+cc+",'"+tim+"')";
		ResultSet re;
		try {
			String id="0";
			re=stat.executeQuery(sql0);
			while(re.next())
			id=re.getString("modelid");  //phone 对应id
			System.out.println("111");
			
			String sql00="select * from modelphotos where modelid="+id+" order by timadd desc";
			re=stat.executeQuery(sql00);
			while(re.next())
			{oldtime=re.getString("timadd");break;}
			System.out.println("222");
			if("0".equals(id)){    //或取最大id
			stat.executeUpdate(sql);
			sql="select max(modelid) from kidmodels";
			re=stat.executeQuery(sql);
			while(re.next())
				id=re.getString("max(modelid)");}
			if(!"0".equals(id)){
			sql="insert into modelphotos(modelid,picurl,competeid,timadd,modifytime) values("+id+",'"+picurl+"','"+competeid+"','"+tim+"','"+oldtime+"')";
			stat.executeUpdate(sql);}
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
	
	
	public void upManager(String phone,String pass, String teacherphone, String parentname, String studentname, String gender, String birthday,String ex1){
		statment();
		try {
		String a="",b="",c="",d="",e="",f="",g="";
		if(pass!=null)a=",password='"+pass+"'";
		if(teacherphone!=null){
			//获取老师id
			String sql0="select * from teachers where phone="+teacherphone;
			ResultSet re=stat.executeQuery(sql0);
			while(re.next())
				b=",teacherid="+re.getString("teacherid");
		}
		if(parentname!=null)c=",parentname='"+parentname+"'";
		if(studentname!=null)d=",studentname='"+studentname+"'";
		if(gender!=null)e=",gender="+gender;
		if(birthday!=null)f=",birthday='"+birthday+"'";
		if(ex1!=null)g=",ex1='"+ex1+"'";
		 String sql="update students set phone="+phone+a+b+c+d+e+f+g+" where phone="+phone;
		
			stat.executeUpdate(sql);
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.info(e);
			}
			
		}
		
		
	}
	
	public void addPassword(String phone,String password)
	{
		statment();
		String sql="update students set password='"+password+"' where phone="+phone;
		try {
			stat.executeUpdate(sql);
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
	
	public void updateYzmTim(String phone){
		statment();
		String sql="update students set yzmtim=now() where phone="+phone;
		try {
			stat.executeUpdate(sql);
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
	public boolean isYzm(String phone){
		statment();
		ResultSet re;
		String sql="select * from students where TIMESTAMPDIFF(SECOND,yzmtim, now())<60 and phone="+phone;
		try {
			re=stat.executeQuery(sql);
			while(re.next()){
				
				return false;}
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
		return true;
	}
	public boolean isUser(String phone,String password){
		statment();
		String p="";
		if(password!=null)p=" and password='"+password+"'";
		String sql="select * from students where phone="+phone+p;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		while(re.next())
			return true;
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
		return false;
	}
	public boolean isTeacher(String phone,String id,String isTrain){
		statment();
		String sql="select * from teachers where phone="+phone+" and isTrain="+isTrain+" and schoolid="+id;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		while(re.next())
			return true;
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
		return false;
	}
	
	
	
	public ArrayList<Week>  getWeeklist(String studentid){
		statment();
		HashMap<String, Integer> hm=new HashMap<String, Integer>();
		ArrayList<Week> arr=new ArrayList<Week>();
		ArrayList<Week> array=new ArrayList<Week>();
		Week w = null;
		String sql="select * from works where status=1 and studentid="+studentid;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		
		while(re.next()){
			w=new Week();
			w.setSimpledate(re.getString("createtime").split(" ")[0]);
	        w.setYear(w.getSimpledate().split("-")[0]);
			arr.add(w);
		}
		for(int i=0;i<arr.size();i++){
			String time=arr.get(i).getSimpledate();
		//	 select week(date_sub('1990-3-3', interval  select dayofweek('1990-3-3') day));
			sql="select dayofweek('"+time+"')";
			re=stat.executeQuery(sql);
			int j=0;
			while(re.next())
			{	
				j=Integer.parseInt(re.getString("dayofweek('"+time+"')"));
			    j=(j+6)%7==0?7:((j+6)%7);          //星期日为7
			//    arr.get(i).setWeeknumber((Integer.parseInt(time.split("-")[2])-j)/7+1+"");
			}
			sql="select date_format(date_sub('"+time+"', interval "+(j-1)+" day),'%Y-%c-%d')";
			re=stat.executeQuery(sql);
			while(re.next()){
				arr.get(i).setBegdata(re.getString("date_format(date_sub('"+time+"', interval "+(j-1)+" day),'%Y-%c-%d')"));
			    arr.get(i).setSbegdata(arr.get(i).getBegdata().split("-")[2]);
			}
			sql="select date_format(date_add('"+time+"', interval "+(7-j)+" day),'%Y-%c-%d')";
			re=stat.executeQuery(sql);
			while(re.next()){
				arr.get(i).setEnddata(re.getString("date_format(date_add('"+time+"', interval "+(7-j)+" day),'%Y-%c-%d')"));
			}
			
			sql="select week('"+arr.get(i).getBegdata()+"')";//周次
			re=stat.executeQuery(sql);
			while(re.next()){
				arr.get(i).setWeeknumber(re.getString("week('"+arr.get(i).getBegdata()+"')"));
			}
			
			sql="select month(date_sub('"+time+"', interval "+(j-1)+" day))";//月
			re=stat.executeQuery(sql);
			while(re.next()){
				arr.get(i).setMonth(re.getString("month(date_sub('"+time+"', interval "+(j-1)+" day))"));
		}
			arr.get(i).setUpworks("1");
			//添加进map并计数
			int k=0;
			String key="";
			if(hm.containsKey(key=(arr.get(i).getYear()+""+arr.get(i).getWeeknumber()))){
				k=hm.get(key);
				hm.remove(key);hm.put(key, ++k);
			}else{
				hm.put(key, 1);
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
		
		Week a;//合并同周次
		for(int i=0;i<arr.size();i++)
			{   String key="";
			if(hm.containsKey(key=(arr.get(i).getYear()+""+arr.get(i).getWeeknumber()))){
			   a=arr.get(i);a.setUpworks(hm.get(key)+"");
				array.add(a);
				hm.remove(key);
			}

			}
		System.out.println(array.size());
		return array;
	}
	
	public Manageruser getManageruser(String name,String pass,String id){
		statment();
		Manageruser man = null;
		String nam="",pas="",idd="";
		if(name!=null){
		nam=" and a.phone='"+name+"'";
		pas="  and a.password='"+pass+"'";}
		if(id!=null)idd=" and a.studentid="+id;
		String sql="select a.status,a.headimg,a.ex1,a.phone as aa,a.password,a.birthday,a.gender,a.parentname,a.account,a.studentid,a.studentname,b.schoolid,b.teachername,b.isTrain,b.phone,c.schoolname " +
				"from students as a left join teachers as b on a.teacherid=b.teacherid left join schools as c on b.schoolid=c.schoolid where a.status>0"+nam+pas+idd ;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		while(re.next()){
			man=new Manageruser();
			man.setStatus(re.getString("status"));
			man.setPhone(re.getString("aa"));
			man.setPass(re.getString("password"));
			man.setAccount(re.getString("account"));
			man.setId(re.getString("studentid"));
			man.setTname(re.getString("studentname"));
			man.setTeachername(re.getString("teachername"));
			man.setTeacherphone(re.getString("phone"));
			man.setIsTrain(re.getString("isTrain"));
			man.setStname(re.getString("schoolname"));
			man.setSchoolid(re.getString("schoolid"));
			String bir=re.getString("birthday").replace(".0", "");
			man.setBiryear(bir.split("-")[0]);
			man.setBirmonth(bir.split("-")[1]);
			man.setBirday(bir.split("-")[2]);
			man.setGender(re.getString("gender"));
			man.setParentsname(re.getString("parentname"));
			String headimg="images/photo.png";
			if(re.getString("headimg")!=null&&!re.getString("headimg").equals(""))headimg=re.getString("headimg");
			man.setHeadimg(headimg);
			man.setEx1(re.getString("ex1"));
		}
		
		if(man!=null){
			if(man.getIsTrain()!=null&&man.getIsTrain().equals("0")){
			sql="select * from trainingorgs where orgid="+man.getSchoolid();
			re=stat.executeQuery(sql);
			while(re.next())
				man.setStname(re.getString("orgname"));
			}
			//是否年会员
				man.setIsyearvip(isYearvip(man.getId()));
		}
		//可上传数
		if(man!=null){
				man.setUptimes(getUptimes(man.getId()));
			
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
		return man;
	}
	public String isYearvip(String id){
		statment();
		String time=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		ResultSet re;
		String sql="select  date_format(date_sub('"+time+"', interval 365 day),'%Y-%c-%d')";
		try {
			re=stat.executeQuery(sql);
		
		while(re.next())
			time=re.getString("date_format(date_sub('"+time+"', interval 365 day),'%Y-%c-%d')");
		
		sql="select * from productbill where productid=1 and status=1 and studentid="+id+" and timadd>'"+time+"'";
		re=stat.executeQuery(sql);
		while(re.next())
			return "1";
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
		return "0";
	}
	public String getUptimes(String id){
		String addtime="";
		String time0="";
		boolean flag0=false;
		ResultSet re;
		String sql="select  date_format(date_sub(now(), interval 365 day),'%Y-%c-%d %H:%i:%s')";
		try {
      int competeid=isCompetePaint();
      statment();
      if(competeid==0){
			re=stat.executeQuery(sql);
		while(re.next())
			time0=re.getString("date_format(date_sub(now(), interval 365 day),'%Y-%c-%d %H:%i:%s')");
		sql="select * from productbill where studentid="+id+" and productid=1 and status=1 and timadd>'"+time0+"'";
		re=stat.executeQuery(sql);
		while(re.next())
		{	
			addtime=re.getString("timadd");
			flag0=true;}
		
		if(flag0){//年会有效期内
			long a=0;
			sql="select numbers from virtualproducts where  productid=1";
			re=stat.executeQuery(sql);
			while(re.next())
				a=re.getInt("numbers");
			
			sql="select a.*,sum(b.numbers*a.pronumbers) as bb from productbill as a left join virtualproducts as b on a.productid=b.productid where a.studentid="+id+" and a.status=1 and a.productid!=1 and a.timadd>'"+addtime+"' group by a.studentid";
			re=stat.executeQuery(sql);
			while(re.next())//判断加油包  大于年会时间 
				a=a+re.getLong("bb");
			
			sql="select count(*) from works where status=1 and studentid="+id;
			re=stat.executeQuery(sql);
			while(re.next())
				a=a-re.getLong("count(*)");
			if(a<0)a=0;
		  return(a+"");
		}
		}
      else{//有正在进行画作比赛
			System.out.println("比赛中。。。");
			sql="select account-b.aa as bb from compete_paint a,(select count(*) as aa from works where studentid="+id+" and competeid="+competeid+"  and status=2) b  where a.id="+competeid;
			re=stat.executeQuery(sql);
			while(re.next())
				return re.getString("bb");
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
		return "0";
	}
	
	
//	public Manageruser getManageruser(int id){
//		statment();
//		Manageruser man = null;
//		String sql="select a.*,b.isTrain,b.phone as aa,b.schoolid as bb,b.teachername from students as a left join teachers as b on a.teacherid=b.teacherid where a.studentid="+id+" and a.status>0" ;
//		ResultSet re;
//		try {
//			re = stat.executeQuery(sql);
//		while(re.next()){
//			man=new Manageruser();
//			man.setId(re.getString("studentid"));
//			man.setTname(re.getString("studentname"));
//			man.setGender(re.getString("gender"));
//			man.setParentsname(re.getString("parentname"));
//			man.setTeachername(re.getString("teachername"));
//			man.setIsTrain(re.getString("isTrain"));
//			String birthday=re.getString("birthday").split(" ")[0];
//			man.setBiryear(birthday.split("-")[0]);
//			man.setBirmonth(birthday.split("-")[1]);
//			man.setBirday(birthday.split("-")[2]);
//			man.setTeacherphone(re.getString("aa"));
//			man.setSchoolid(re.getString("bb"));
//			String headimg="images/photo.png";
//			if(re.getString("headimg")!=null&&!re.getString("headimg").equals(""))headimg=re.getString("headimg");
//			man.setHeadimg(headimg);
//			man.setEx1(re.getString("ex1"));
//		}
//		if(man!=null){
//			if(man.getIsTrain()!=null){
//			if(man.getIsTrain().equals("1"))
//				sql="select * from schools where schoolid="+man.getSchoolid();
//			else sql="select * from trainingorgs where orgid="+man.getSchoolid();
//			  re=stat.executeQuery(sql);
//			  while(re.next())
//				  man.setStname(re.getString(2));
//			}
//			man.setUptimes(getUptimes(man.getId()));
//		}
//		} catch (SQLException e) {
//			
//			log.error("error:", e);log.info(sql);
//		}finally{
//			try {
//				stat.close();
//				conn.close();
//			} catch (SQLException e) {
//				
//				log.info(e);
//			}
//			
//		}
//		return man;
//	}
	
	public int isCompetePaint(){
		statment();
		String sql="select * from compete_paint where now()>begtim and now()<endtim and status=1";
		ResultSet re;
		try {
			re=stat.executeQuery(sql);
			while(re.next())
			{	
			Variable.vobj_competepaint=new Competepaint(re.getString("id"), re.getString("name"),
					re.getString("begtim").replace(".0", "").replace(" 00:00:00", "") ,re.getString("endtim").replace(".0", "").replace(" 00:00:00", "") );
				return Integer.parseInt(re.getString("id"));}
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
			  return 0;
	}
	
	public boolean addWorks(String title,String introduction,String remark,String picurl,String stuid){
		int competeid=isCompetePaint();
		statment();
		String sql="insert into works(worktitle,introduction,remark,url,studentid,createtime) values('"+title+"','"+introduction+"','"+remark+"','"+picurl+"',"+stuid+",now())";
		if(competeid!=0)
			   sql="insert into works(worktitle,introduction,remark,url,studentid,createtime,status,competeid) values('"+title+"','"+introduction+"','"+remark+"','"+picurl+"',"+stuid+",now(),2,'"+competeid+"')";
		try {
			//判断 是否有比赛---login同加这个判断  共享函数？
			stat.executeUpdate(sql);
			return true;
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
		return false;
	}
	
	public ArrayList<Workss> getWorksById(String id){
		statment();
		ArrayList arr=new ArrayList<Workss>();
		Workss w;
		String time;
		String sql="select a.workid,a.worktitle,a.url,a.createtime,a.isAppraised,b.score from works as a  inner join appraisedworks as b on a.workid=b.worksid and b.status=1 where a.studentid="+id+" and a.status=1";
	ResultSet re;
	try {
		re = stat.executeQuery(sql);
	while(re.next()){
		w=new Workss();
		w.setWorkid(re.getString("workid"));
		w.setIsA(re.getString("isAppraised"));
		w.setPicurl(re.getString("url"));
		w.setScore(re.getString("score"));
		w.setTitle(re.getString("worktitle"));
		w.setUptime(re.getString("createtime").replace(".0", ""));
		arr.add(w);
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
	return arr;
	}
	
	public ArrayList<Workss> getallWorksById(String id,String beg,String end){
		statment();
		ArrayList arr=new ArrayList<Workss>();
		Workss w;
		String time;
		String a="",b="";
		if(beg!=null&&end!=null&&!"".equals(beg)&&!"".equals(end)){
			a=" and a.createtime>'"+beg+"'";
			b=" and a.createtime<'"+end+"'";
		}
		String sql="select a.workid,a.worktitle,a.url,a.createtime,a.isAppraised,b.score from works as a  left join appraisedworks as b on a.workid=b.worksid and b.status=1 where a.studentid="+id+" and a.status>=1 "+a+b+" order by a.createtime desc";
	ResultSet re;
	try {
		re = stat.executeQuery(sql);
	while(re.next()){
		w=new Workss();
		w.setWorkid(re.getString("workid"));
		w.setIsA(re.getString("isAppraised"));
		w.setPicurl(re.getString("url"));
		w.setScore(re.getString("score"));
		w.setTitle(re.getString("worktitle"));
		w.setUptime(re.getString("createtime").replace(".0", ""));
		arr.add(w);
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
	return arr;
	}
	
	public ArrayList<School> getSchoolList(){
		statment();
		School s;
		ArrayList<School> arr=new ArrayList<School>();
		String sql="select * from schools";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		
		while(re.next()){
			s=new School();
			s.setId(re.getString("schoolid"));
		  s.setName(re.getString("schoolname"));
			arr.add(s);           
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
		return arr;
	}
	
	public ArrayList<Train> getTrainList(){
		statment();
		Train s;
		ArrayList<Train> arr=new ArrayList<Train>();
		String sql="select * from trainingorgs";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		
		while(re.next()){
			s=new Train();
			s.setId(re.getString("orgid"));
			s.setName(re.getString("orgname"));
			arr.add(s);
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
		return arr;
	}

	public int addStudents(String parentname,String studentname,String birthday,String gender,String password
			,String phone,int teacherid,String headimg,String st){
		statment();
		String sql0="select * from students where phone="+phone;
		String status="1";
		if(headimg!=null||st!=null)status="2";
		String sql="insert into students(studentname,parentname,birthday,password,gender,phone,timadd,teacherid,status,headimg,ex1) values('"
			+studentname+"','"+parentname+"','"+birthday+"','"+password+"','"+gender+"','"+phone+"',now(),"+teacherid+","+status+",'"+headimg+"','"+st+"')";
		ResultSet re;
		try {
	    	re=stat.executeQuery(sql0);//判断是否已有这电话
	    	while(re.next())
	    		return 0;
	    	
			stat.executeUpdate(sql);
			sql="select max(studentid) from students";
			 re=stat.executeQuery(sql);
			while(re.next())
				return re.getInt("max(studentid)");
		} catch (SQLException e) {
			
			log.error("error:", e);log.info(sql);log.info(sql);
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				
				log.info(e);
			}
			
		}
		return 0;
	}
	
	public int addTeachers(Regform0 reg){
		statment();
		String tim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime());
		String sql0="select * from teachers where phone="+reg.getPhone();//判断有无这个号码
		String sql="insert into teachers(teachername,qqnumber,email,weixin,phone,isTrain,schoolid,timadd) values('"+reg.getTeachername()+"','"+reg.getQq()+"','"+reg.getEmail()+"','"+reg.getWeixin()+"','"+reg.getPhone()+"','"+reg.getIsTrain()+"','"+reg.getSt()+"','"+tim+"')";
		ResultSet re;
		try {
	    	re=stat.executeQuery(sql0);
	    	while(re.next())
	    		return re.getInt("teacherid");
	    	
			stat.executeUpdate(sql);
		
	    sql="select max(teacherid) from teachers";
	     re=stat.executeQuery(sql);
	    while(re.next())
	    	return re.getInt("max(teacherid)");
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
	    return 0;
	}
	
	public Workss getDetailworkById(String workid){
		statment();
		Workss w = null;
		String sql="select c.studentname,c.gender,c.phone,a.worktitle,a.studentid,a.url,a.createtime,a.introduction,b.score,b.teachersay,b.comment  from works as a inner join appraisedworks as b on a.workid=b.worksid and a.workid='"+workid+"' and a.status>=1 left join students as c on a.studentid=c.studentid";
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		
		while(re.next()){
			w=new Workss();
			w.setStudentid(re.getString("studentid"));
			w.setGender(re.getString("gender"));
			w.setTname(re.getString("studentname"));
			w.setPhone(re.getString("phone"));
			w.setWorkid(workid);
			w.setTitle(re.getString("worktitle"));
			w.setPicurl(re.getString("url"));
			w.setUptime(re.getString("createtime").replace(".0", ""));
			w.setIntroduction(re.getString("introduction"));
			w.setScore(re.getString("score"));
			w.setTeachersay(re.getString("teachersay"));
			w.setComment(re.getString("comment"));
		}
		if(w!=null){
		sql="select max(workid),min(workid) from works where studentid="+w.getStudentid()+" and isAppraised=1 and status=1";
		re=stat.executeQuery(sql);
		while(re.next()){
			w.setEndid(re.getString("max(workid)"));
			w.setBegid(re.getString("min(workid)"));}
	                                     //前一个
		sql="select max(workid) from works where studentid="+w.getStudentid()+" and isAppraised=1 and status=1 and workid<"+workid;
		re=stat.executeQuery(sql);
		while(re.next())
			w.setBefid(re.getString("max(workid)"));     //后一个
		sql="select min(workid) from works where studentid="+w.getStudentid()+" and isAppraised=1 and status=1 and workid>"+workid;
		re=stat.executeQuery(sql);
		while(re.next())
			w.setAfrid(re.getString("min(workid)"));
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
		return w;
	}
	
	public ArrayList<Add> getAddList(String id){
		statment();
		ArrayList<Add> arr=new ArrayList<Add>();
		Add a;
		String sql="select a.orderid,a.orderdate,b.productname,a.quantity  from orders as a inner join virtualproducts as b on a.productid=b.productid and a.studentid="+id;
	ResultSet re;
	try {
		re = stat.executeQuery(sql);

	while(re.next()){
		a=new Add();
		a.setOrderdate(re.getString("orderdate"));
		a.setOrderid(re.getString("orderid"));
		a.setProductname(re.getString("productname"));
		a.setQuantity(re.getString("quantity"));
	//	a.setFee(fee)  费用
		arr.add(a);
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
	return arr;
	}
	
	public Teacher getTeacher(String phone){
		statment();
		Teacher t = null;
		String sql="select * from teachers where phone="+phone;
		ResultSet re;
		try {
			re = stat.executeQuery(sql);
		
		while(re.next()){
			t=new Teacher();
			t.setEmail(re.getString("email"));
			t.setId(re.getString("teacherid"));
			t.setName(re.getString("teachername"));
			t.setPhone(phone);
			t.setQq(re.getString("qqnumber"));
			t.setWeixin(re.getString("weixin"));
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
		return t;
	}
	
	public boolean updateSql(String sql){
		statment();
		try {
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			   
			   log.error("error:", e);log.info(sql);return false;
			  }finally{
			   try {
			    stat.close();
			  conn.close();
			   } catch (SQLException e) {
			    log.info(e);
			   }
			  }
			  return true;
	}
	
}
