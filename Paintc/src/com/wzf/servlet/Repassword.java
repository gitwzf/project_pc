package com.wzf.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;

public class Repassword extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String phone=req.getParameter("phone");
		String password1=req.getParameter("password1");
		String password2=req.getParameter("password2");
		boolean flag=false;
		if(phone!=null&&password1!=null){
			phone=phone.replace("'", "");
			password1=password1.replace("'", "");
			Dbcon db=new Dbcon();
			if(db.isUser(phone, password1))
				if(!"".equals(password1))
				    {db.addPassword(phone, password2);flag=true;}
			
		}
		PrintWriter out = resp.getWriter(); 
        if(flag)out.print("00"); 
        else out.print("11"); 
        out.flush(); 
         out.close();
	}
        
}
