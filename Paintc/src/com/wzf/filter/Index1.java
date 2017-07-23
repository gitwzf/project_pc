package com.wzf.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.registry.infomodel.User;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Manageruser;


public class Index1 implements javax.servlet.Filter {

	public void destroy() {
		
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hsr=(HttpServletRequest) request;
		HttpServletResponse hsre=(HttpServletResponse) response;
	//	Manageruser u= (Manageruser) hsr.getSession().getAttribute("manager");
		System.out.println(hsr.getRequestURI());
		String username;
		String phone;
	//	boolean flag=false;
		Dbcon db=new Dbcon();
			Cookie[] cookies=hsr.getCookies();
			if(cookies!=null)
			for(Cookie coo:cookies){
				String client=coo.getValue();
				if(client.split("&").length==2&&"gameuser".equals(coo.getName())){
					username=client.split("&")[0].replace("'", "");
					username=URLDecoder.decode(username, "utf-8");
					phone=client.split("&")[1].replace("'", "");
					hsr.getSession().setAttribute("user", db.getUser(username,phone));
					if(db.getUser(username,phone)==null)coo.setMaxAge(0);
				}
				
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/GameServ");
			dispatcher.forward(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}

}
