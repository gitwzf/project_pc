package com.wzf.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import sun.rmi.server.Dispatcher;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import com.wzf.dbconn.Dbcon;
import com.wzf.model.Manageruser;
import com.wzf.model.User;
import com.wzf.pubvari.Variable;
import com.wzf.security.CryptoTools;

public class Index implements javax.servlet.Filter {
	Logger log = Logger.getLogger("logfile");
	Variable vari = new Variable();

	public void destroy() {
		

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hsr = (HttpServletRequest) request;
		HttpServletResponse hsre = (HttpServletResponse) response;
			Dbcon db = new Dbcon();
			String str = "/login.jsp";
			CryptoTools tools = null;
		Map<String, String> m = new HashMap();
		m.put("01", "GameServ");//游戏
		m.put("11", "HunterServ");//猎头
		m.put("12", "ModeltypeServ");//类型
		m.put("21", "CollegeServ");//校招
		m.put("22", "SpecialServ");
		m.put("31", "Resume");
		m.put("32", "Workexperi");
		m.put("33", "Sendposition");
		
		String openid = hsr.getParameter("openid");
		String pid = hsr.getParameter("pid");
		String id = hsr.getParameter("id");
		log.info("pid="+pid+"  ");
//		if (openid != null)
//			try {
//				tools = new CryptoTools();
//				openid = tools.decode(openid);
//			} catch (Exception e) {
//				
//				log.info(e);
//			}
			if(openid!=null){
		if(openid.matches("\\d+"))	{
			db.isAddOpenid(id,openid);
		if (pid != null) {
			str = m.get(pid);
       hsr.getSession().setAttribute("user", db.getUser(openid));
		}
	}else{
		log.info("访问失败："+openid+"  "+pid);
	}
			}
		User u=(User) hsr.getSession().getAttribute("user");
		
		if(u==null){
			hsr.getSession().setAttribute("ad", db.getAd());
	//	if(openid!=null){
			hsr.getRequestDispatcher("companytypelist.jsp").forward(hsr, hsre);}
	//	else{log.info(hsr.getRequestURI());
	//		chain.doFilter(hsr, hsre);}
	//	}
		else 
		hsre.sendRedirect("/"+vari.project+"/"+str);
		

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		

	}

}
