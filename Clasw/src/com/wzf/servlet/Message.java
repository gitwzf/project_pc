package com.wzf.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Tans;
import com.wzf.model.User;
import com.wzf.pubvari.Variable;

public class Message extends HttpServlet{
	public Variable vari=new Variable();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Dbcon db=new Dbcon();
		boolean flag=true;
		String page=req.getParameter("page");
		if(page==null)page="1";
		else if("0".equals(page))flag=false;
		String pagenum=(String) req.getSession().getAttribute("pagenum");
		if(pagenum==null)pagenum="1";
		if(Integer.parseInt(page)>Integer.parseInt(pagenum))flag=false;
		if(flag){
		req.getSession().setAttribute("qusanslist", db.getQusanslist(page));
	//	System.out.println("q_title is null?="+db.getQusanslist(page).get(0).getTans().get(0));
		req.getSession().setAttribute("page",page);
		int num=db.getPagenum();
		req.getSession().setAttribute("pagenum",""+(num%5==0?num/5:(num/5+1)));
		System.out.println("page="+page+" pagenum="+(num%5==0?num/5:(num/5+1)));
		}
		req.getRequestDispatcher("/message.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String area=req.getParameter("area");
		System.out.println("area="+area);
		User user=(User)req.getSession().getAttribute("user");
		PrintWriter out=resp.getWriter();
		Dbcon db;
		String re="";
		if(user==null)re="44";
		else if(area==null||"".equals(area))re="33";
		else{
			db=new Dbcon();
			if(db.addTqus(user,area))re="00";
			else
				re="11";
		}
		out.write(re);
		System.out.println("re="+re);
		out.flush();
		out.close();
		
	}
       
}
