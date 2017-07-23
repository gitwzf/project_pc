package com.wzf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.User;
import com.wzf.pubvari.Variable;

public class Course extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Dbcon db=new Dbcon();
		User user=(User) req.getSession().getAttribute("user");
		String classid=req.getParameter("classid");
		if(user!=null){
		   if(classid!=null){
			db.delRuserSign(user.getUsername(),classid);
		   }
		   System.out.println("de="+user.getUsername());
		   req.getSession().setAttribute("ruserlist", db.getRuserlist(user.getUsername()));
		}
		req.getRequestDispatcher("/course.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
        
}
