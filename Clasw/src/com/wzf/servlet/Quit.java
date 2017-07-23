package com.wzf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;

public class Quit extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Dbcon db=new Dbcon();
		req.getSession().removeAttribute("user");
		req.getSession().setAttribute("annlist", db.getAnnList());
		req.getSession().setAttribute("classlist", db.getClassList(null,null,null,null,null));
		req.getRequestDispatcher("/indexx.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
     
}
