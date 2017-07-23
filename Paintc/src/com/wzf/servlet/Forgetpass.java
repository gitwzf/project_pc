package com.wzf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Forgetpass extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String phone=req.getParameter("phone");
		boolean flag=false;
		if(phone!=null){
			req.getSession().setAttribute("uss", phone);
			
			flag=true;
		}
		if(flag)req.getRequestDispatcher("/retrive_password1.jsp").forward(req, resp);
		else req.getRequestDispatcher("/retrive_password.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
       
}
