package com.wzf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.pubvari.Variable;

public class Info extends HttpServlet{
	public Variable vari=new Variable();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String id=req.getParameter("id");
		Dbcon db=new Dbcon();
		if(id!=null)
		req.getSession().setAttribute("annouce", db.getAnnById(Integer.parseInt(id)));
		
		req.getRequestDispatcher("/information.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
     
}
