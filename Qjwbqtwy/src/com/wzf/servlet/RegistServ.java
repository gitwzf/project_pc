package com.wzf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;

public class RegistServ extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String khDlm=req.getParameter("khDlm");
		String khDlmm=req.getParameter("khDlmm");
		String back="{\"backCode\":\"-1\",\"name\":\"\",\"errorMess\":\"没有这个用户\"}";
		if(khDlm!=null&&khDlmm!=null)
		{
			Dbcon db=new Dbcon();
			if(db.isUser(khDlm,khDlmm)){
				back="{\"backCode\":\"0\",\"name\":\""+khDlm+"\"}";
				db.isAddOpenid("0",khDlm);
		       req.getSession().setAttribute("user", db.getUser(khDlm));
			}
		}
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter(); 
        out.print(back); 
        out.flush(); 
         out.close();
	}
	
	

}
