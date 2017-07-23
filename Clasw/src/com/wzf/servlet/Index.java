package com.wzf.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.User;
import com.wzf.pubvari.Variable;

public class Index extends HttpServlet{
	public Variable vari=new Variable();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//重定向 或 转发 保持地址不变
		Logger log = Logger.getLogger("logfile");
		Dbcon db=new Dbcon();
		String username;
		String password;
		User user=(User) req.getSession().getAttribute("user");
		if(user==null){
		int ips=0;    //获取ip
		String ip=req.getRemoteAddr();
		String ip_headers[]={"x-forwarded-for","Proxy-Client-IP","WL-Proxy-Client-IP","HTTP_CLIENT_IP","HTTP_X_FORWARDED_FOR"};
		 while(ips<ip_headers.length&&(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)||"127.0.0.1".equals(ip))){
		   ip = ((HttpServletRequest) req).getHeader(ip_headers[ips++]); 
		    }
		log.info("来访者ip:"+ip);
		Cookie[] cookies=req.getCookies();
		if(cookies!=null)
		for(Cookie coo:cookies){
			String client=coo.getValue();
			if(client.split("\\|").length==2){
				username=client.split("\\|")[0].replace("'", "");
				password=client.split("\\|")[1].replace("'", "");
				username=URLDecoder.decode(username,"utf-8");
				System.out.println("username1="+username);
				if(db.isUser(username,password)){ 
					req.getSession().setMaxInactiveInterval(15*60);
					req.getSession().setAttribute("user",db.getUser(username));
					}
			}
			if("clientuser".equals(coo.getName()))
			req.getSession().setAttribute("clientuser", URLDecoder.decode(coo.getValue(),"utf-8"));
		}
		}
		
		User u=(User) req.getSession().getAttribute("user");
		req.getSession().setAttribute("annlist", db.getAnnList());
		req.getSession().setAttribute("classlist", db.getClassList(null,null,null,null,u));
		req.getRequestDispatcher("/indexx.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
     
}
