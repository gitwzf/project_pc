package com.wzf.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Page;
import com.wzf.pubvari.Variable;

public class Allwork extends HttpServlet{
	Variable vari=new Variable();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String page=req.getParameter("pageid");
		String name=req.getParameter("modelname");
		Dbcon db=new Dbcon();
		if(page==null||page=="0"||page=="")page="1";
		long p=Long.parseLong(page);
		if(name!=null){name=new String(name.getBytes("iso-8859-1"),"utf-8");
		req.setAttribute("modelname", URLEncoder.encode(name,"utf-8"));
		}
		System.out.println(name+" "+page);
		
		req.getSession().setAttribute("painterlist", db.getPainterlist(p,name));
		req.getSession().setAttribute("page",new Page(p+"",vari.items_p+"",db.getPainterlist(0l,name).size()/vari.items_p+(db.getPainterlist(0l,name).size()%vari.items_p==0?0:1)+""));
		req.getRequestDispatcher("/allwork.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
       
}
