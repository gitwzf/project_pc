package com.wzf.filter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Properties;

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


import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import com.wzf.dbconn.Dbcon;
import com.wzf.model.User;
import com.wzf.pubvari.Variable;


public class Index implements javax.servlet.Filter{
	Logger log = Logger.getLogger("logger");
	public void destroy() {
		
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		String username;
		String password;
		boolean flag=true;
		Dbcon db=new Dbcon();
		HttpServletRequest hsr=(HttpServletRequest) request;
		Cookie[] cookies=hsr.getCookies();
		if(cookies!=null)
		for(Cookie coo:cookies){
			String client=coo.getValue();
			if(client.split("\\|").length==2){
				username=client.split("\\|")[0];
				password=client.split("\\|")[1];
				if(db.isManageruser(username,password)){ 
					hsr.getSession().setMaxInactiveInterval(15*60);
					hsr.getSession().setAttribute("user",db.getManageruser(username));
					flag=false;
					log.info("自动登陆：username="+username+" password="+password);
					chain.doFilter(request, response);}
			}
			if("clientuser".equals(coo.getName()))
			hsr.getSession().setAttribute("clientuser", coo.getValue());
		}
		User user=(User) hsr.getSession().getAttribute("user");
		if(flag){
		if(user==null){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response); 
		return;
		}
		else{
		    chain.doFilter(request, response);}
	}
	}
	public void init(FilterConfig filterConfig) throws ServletException {
		
		Properties p=new Properties();
		try {
			p.load(new InputStreamReader(this.getClass().getResourceAsStream("../../../vari.properties")));
			Variable.IP=p.getProperty("IP");
			Variable.database=p.getProperty("database");
			Variable.port=p.getProperty("port");
			Variable.username=p.getProperty("username");
			Variable.password=p.getProperty("password");
			Variable.path_webapps=p.getProperty("webapps");
			Variable.path_pic_upload=p.getProperty("picpath");
			log.info("vari.properties配置信息：IP="+Variable.IP+" database="+Variable.database+" port="+Variable.port+
					" username="+Variable.username+" password="+Variable.password+"\n weapps目录="+Variable.path_webapps+" 富文本图片目录="+Variable.path_pic_upload);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	
      
}
