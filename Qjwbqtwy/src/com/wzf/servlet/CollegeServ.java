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

public class CollegeServ extends HttpServlet{
    Variable vari=new Variable();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setAttribute("title",URLEncoder.encode("¸ßÐ£ÕÐÆ¸","utf-8"));
		req.setAttribute("i","1");
		Dbcon db=new Dbcon(); 
		req.getSession().setAttribute("ad", db.getAd());
		req.setAttribute("collegelist", db.getCollegelist(1,"1"));
		req.getSession().setAttribute("cpage", new Page("1",db.getCollegelist(-1,"1").size()/vari.p_size+(db.getHunterlist(-1).size()%vari.p_size==0?0:1)+""));
		req.getRequestDispatcher("./collegelist.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
  
}
