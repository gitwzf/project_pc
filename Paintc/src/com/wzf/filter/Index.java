package com.wzf.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.xml.registry.infomodel.User;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Manageruser;


public class Index implements javax.servlet.Filter {

	public void destroy() {
		
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hsr=(HttpServletRequest) request;
		Manageruser u= (Manageruser) hsr.getSession().getAttribute("manager");
		System.out.println(hsr.getRequestURI());
		String username;
		String password;
		Dbcon db=new Dbcon();
		if(u==null){
			Cookie[] cookies=hsr.getCookies();
			if(cookies!=null)
			for(Cookie coo:cookies){
				String client=coo.getValue();
				if(client.split("\\|").length==2&&"client".equals(coo.getName())){
					username=client.split("\\|")[0].replace("'", "");
					password=client.split("\\|")[1].replace("'", "");
					u=db.getManageruser(username,password,null);
				}
				
			}
		}
		if(u==null){RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");dispatcher.forward(request, response);}
		else {RequestDispatcher dispatcher = request.getRequestDispatcher("/Login");dispatcher.forward(request, response);}
			
			// chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}

}
