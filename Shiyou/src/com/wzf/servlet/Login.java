package com.wzf.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wzf.dbconn.Dbcon;
import com.wzf.pubvari.Variable;

public class Login extends HttpServlet{
	Logger log = Logger.getLogger("logger");
	public Variable vari=new Variable();//¿ÕµÄ£¿£¡
//    public String project=vari.project;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
//		req.setCharacterEncoding("iso-8859-1");
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String username=req.getParameter("username");
//		log.info(username);
		if(username!=null){
			username=username.replaceAll("/'|(\\')|(\\-\\-)|(%27)|(%23)|(#)|'/ix", "");
			Dbcon db=new Dbcon();
			String password=req.getParameter("password");
			if(password!=null)password=password.replaceAll("/'|(\\')|(\\-\\-)|(%27)|(%23)|(#)|'/ix", "");
			String rember=req.getParameter("rember");
			String saveTime=req.getParameter("saveTime");
			log.info("µÇÂ½      username="+username+" password="+password+" rember="+rember+" saveTime="+saveTime);
		if(db.isManageruser(username,password)){
				Cookie cook=new Cookie("client", username+"|"+password);
				cook.setMaxAge(Integer.parseInt(saveTime)*24*3600);
				cook.setSecure(true);
				resp.addCookie(cook);
				if(rember!=null){Cookie cook1=new Cookie("clientuser", username);
				cook1.setMaxAge(365*24*3600);
				resp.addCookie(cook1);}
			//	req.getSession().setMaxInactiveInterval(15*60);
			    req.getSession().setAttribute("user",db.getManageruser(username));
			req.getRequestDispatcher("/index.jsp").forward(req, resp);}
		else{
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		}
		else
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
     
}
