package com.wzf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.User;
import com.wzf.pubvari.Variable;

public class Enroll extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User user=(User)req.getSession().getAttribute("user");
		if(user!=null){
			String classid=req.getParameter("classid");
			Dbcon db=new Dbcon();
			if(classid!=null)
			db.addEnlist(user,classid);
			req.getRequestDispatcher("/course0.jsp").forward(req, resp);
		}
		else
			req.getRequestDispatcher("/indexx.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
   
}
