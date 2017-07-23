package com.wzf.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import com.wzf.dbconn.Dbcon;
import com.wzf.model.Manageruser;
import com.wzf.pubvari.Variable;


public class Login extends HttpServlet{
	Logger log = Logger.getLogger("logfile");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	Manageruser man=(Manageruser) req.getSession().getAttribute("manager");
		String action=req.getParameter("action");
		String username;
		String password;
		Dbcon db=new Dbcon();
		if(man==null){
			Cookie[] cookies=req.getCookies();
			if(cookies!=null)
			for(Cookie coo:cookies){
				String client=coo.getValue();
				if(client.split("\\|").length==2){
					username=client.split("\\|")[0].replace("'", "");
					password=client.split("\\|")[1].replace("'", "");
					man=db.getManageruser(username,password,null);
				}
				
			}
		}
		
		if(man==null||action!=null){
			Cookie[] cookies=req.getCookies();
			if(cookies!=null)
			for(Cookie coo:cookies){
				String client=coo.getValue();
				if(client.split("\\|").length==2&&"client".equals(coo.getName())){
					coo.setMaxAge(0);
					resp.addCookie(coo);
					req.getSession().removeAttribute("manager");
				}
				
			}
			req.getRequestDispatcher("/login.jsp").forward(req, resp);}
		else{
			req.getSession().removeAttribute("picurl");
			man.setUptimes(db.getUptimes(man.getId()));
			req.getSession().setAttribute("manager",man);
			req.getRequestDispatcher("/adeclarative.jsp").forward(req, resp);}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		boolean flag=false;
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String username2=(String) req.getSession().getAttribute("uss");
		String password2=req.getParameter("password2");
		if(username!=null){
			Dbcon db=new Dbcon();
			username=username.replace("'", "");
			password=password.replace("'", "");
			if(password2!=null&&username2!=null){//修改密码
				username2=username2.replace("'", "");
				password2=password2.replace("'", "");
				db.upManager(username2,password2, null, null, null, null, null,null);
				username=username2;
				password=password2;
			}
			
			
			Manageruser man=db.getManageruser(username,password,null);
			if(man!=null){
				Cookie cook=new Cookie("client", username+"|"+password);//放入cookie
				cook.setMaxAge(365*24*3600);
				resp.addCookie(cook);
				
				req.getSession().removeAttribute("info");
				req.getSession().removeAttribute("us");
			req.getSession().setAttribute("manager",man);flag=true;
			}else{
				req.getSession().setAttribute("info","无该账号或密码错误！");
				req.getSession().setAttribute("us",username);
			}
		}
		if(flag){
			req.getSession().removeAttribute("picurl");
			req.getRequestDispatcher("/adeclarative.jsp").forward(req, resp);}
		else
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	
	public void init(){
		Timer t=new Timer();
		t.schedule(new Time(), 1000,60*1000);
		
	}
	 class Time extends java.util.TimerTask{

			@Override
			public void run() {
				
				Dbcon db=new Dbcon();
				//更新数据
				if(Variable.array_competepaint.size()!=0)
				db.updateCompetepaint(Variable.array_competepaint);
				db.updateAllVoteUser(Variable.map_actvoteuser);
			//	log.info("上周期画作投票用户数："+Variable.map_actvoteuser.size()+"  上周期画作投票数："+Variable.number_vote);
				//缓存设初始值
				Variable.number_vote=0l;
				Variable.vobj_competepaint=null;
				Variable.map_actvoteuser.clear();
				
				Variable.array_competepaint=db.getPainterlist();
				Variable.map_compete_user=db.updateCompete();
				log.info("加载画家比赛作品数："+Variable.array_competepaint.size());
				//说明页
				Variable.map_pageinfo=db.getPage();
//				Variable.map_voteuser=db.getAllVoteUser();
//				log.info("加载投票用户数："+Variable.map_voteuser.size());
			}
			  
			  
		  }
	
    
}
