package com.wzf.filter;

import java.io.IOException;
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
import com.wzf.model.Voteuser;
import com.wzf.pubvari.Variable;


public class Index0 implements javax.servlet.Filter {

	public void destroy() {
		
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hsr=(HttpServletRequest) request;
		HttpServletResponse hsre=(HttpServletResponse) response;
	//	Manageruser u= (Manageruser) hsr.getSession().getAttribute("manager");
		String username;
		String userkey;
	//	boolean flag=false;
		Dbcon db=new Dbcon();
			Cookie[] cookies=hsr.getCookies();
			if(cookies!=null)
			for(Cookie coo:cookies){
				String client=coo.getValue();
			//	System.out.println("cooname="+coo.getName());
				if(client.split("&").length==2&&"clientopenid".equals(coo.getName())){
				//	flag=true;//ÓÐÕâ¸öcookie
					username=client.split("&")[0].replace("'", "");
					userkey=client.split("&")[1].replace("'", "");
					Voteuser v=Variable.map_actvoteuser.get(username+";"+userkey);
					if(v==null)v=db.getVoteuser(username, userkey);
					if(v!=null){
						Variable.map_actvoteuser.put(username+";"+userkey, v);
					hsr.getSession().setAttribute("voteuser",v );}
				}
				
			}
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Models");
			dispatcher.forward(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}

}
