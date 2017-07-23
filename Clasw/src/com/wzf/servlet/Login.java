package com.wzf.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.User;
import com.wzf.pubvari.Variable;

public class Login extends HttpServlet{
	public Variable vari=new Variable();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String period=req.getParameter("period");
		String group=req.getParameter("group");
		String time=req.getParameter("time");
		String class_addr=req.getParameter("class_addr");
		User user=(User) req.getSession().getAttribute("user");
		if(class_addr!=null)class_addr=new String(class_addr.getBytes("iso-8859-1"),"utf-8");
		
		Dbcon db=new Dbcon();
		req.getSession().setAttribute("classlist", db.getClassList(group,period,time,class_addr,user));
		req.getSession().setAttribute("lookfor", new Lookfor(period,group,time,class_addr));
		req.getRequestDispatcher("/indexx.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String username=req.getParameter("username");
		String rember=req.getParameter("rember");
		String saveTime=req.getParameter("saveTime");
		System.out.println("rember="+rember);
		System.out.println("saveTime0="+saveTime);
//		log.info(username);
		if(username!=null){
			username=new String(username.getBytes("iso-8859-1"),"utf-8"); 
			Dbcon db=new Dbcon();
			String password=req.getParameter("password");
			username=username.replace("'", "\'");//·Àsql×¢Èë
			password=password.replace("'", "\'");
if(db.isUser(username,password)){
	req.getSession().setAttribute("user",db.getUser(username));
	   username=URLEncoder.encode(username,"utf-8");
				if(saveTime!=null){
				Cookie cook=new Cookie("client", username+"|"+password);
				cook.setMaxAge(365*24*3600);
				resp.addCookie(cook);}
				if(rember!=null){Cookie cook1=new Cookie("clientuser", username);
				cook1.setMaxAge(365*24*3600);
				resp.addCookie(cook1);}
//	req.getSession().setMaxInactiveInterval(15*60);
	System.out.println("111");
			
			}
//				for(Pubid p: db.getPubId())
//					log.info("p.name="+p.name);
		//	resp.sendRedirect("/"+project+"/index.jsp");
		}
//		else
//			req.getSession().setAttribute("user",null);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
		
	}

}
