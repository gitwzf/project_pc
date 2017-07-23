package servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

public class Index2 implements javax.servlet.Filter{

	public void destroy() {
		
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		boolean flag=false;
		HttpServletRequest hsr=(HttpServletRequest) request;
		com.wzf.model.User user=(com.wzf.model.User) hsr.getSession().getAttribute("user");
		String pub=hsr.getParameter("pub");
		String privlg=hsr.getParameter("privlg");
		String mid=hsr.getParameter("mid");
		String mid0=hsr.getParameter("mid0");
		String json_type=hsr.getParameter("json_type");
		String pageid=HTMLencode(hsr.getParameter("PAGEID"));
	//	System.out.println("pageid0="+hsr.getParameter("PAGEID"));
	//	System.out.println("ss="+hsr.getParameter("ss"));
	//	System.out.println("pub="+hsr.getParameter("pub"));
		if(pub!=null&&user!=null)
		{  System.out.println("pub0="+new String(hsr.getParameter("pub").getBytes("iso8859-1"),"utf-8"));
		hsr.getSession().setAttribute("pub", pub);
		if(mid!=null)
			hsr.getSession().setAttribute("mid", mid);
		if(mid0!=null)
			hsr.getSession().setAttribute("mid0", mid0);
		if(json_type!=null){
			Manager u=(Manager)JSONObject.toBean(JSONObject.fromObject(json_type), Manager.class);
			System.out.println("user.tname="+new String(u.getTname().getBytes("iso-8859-1"),"utf-8"));
			u.setTname(new String(u.getTname().getBytes("iso-8859-1"),"utf-8"));
			hsr.getSession().setAttribute("user", u);
			privlg=u.getPrivlg();
		
		}
		
		flag=true;
		
		//按访问的page页面判断权限
//		if("manager".equals(pageid)) 
//		if(privlg==null||privlg.equals("0"))
//		flag=false;
//		if("ptuser".equals(pageid)) 
//			if(privlg==null||Integer.parseInt(privlg)!=-1)
//			    flag=false;
//		if("ptapart".equals(pageid)) 
//			if(privlg==null||Integer.parseInt(privlg)<1)
//			    flag=false;
//		if("ptadmin".equals(pageid)) 
//			if(privlg==null||Integer.parseInt(privlg)<2)
//			    flag=false;
		}
		
		
		else{
	    	if(hsr.getSession().getAttribute("pub")!=null)flag=true;
		}
		System.out.println("publist="+hsr.getSession().getAttribute("pubidlist"));
		if(flag){
		 //   chain.doFilter(request, response);
		    request.getRequestDispatcher("/ShowReport.wx?PAGEID="+pageid).forward(request, response);
		}
		else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response); 
			return;
			}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}
	public String HTMLencode(String pageid){
		if(pageid==null)return null;
		return pageid.replaceAll("/'|(\\|)|(&)|(;)|($)|(%)|(@)|(\\')|(\")|(\\\")|(')|(\\<)|(\\>)|(\\()|(\\))|(\\+)|(\\,)|(\\\\)|'/ix", "");
		
	}

	
      
}
