package com.wzf.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.User;

public class Register extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String tname=req.getParameter("tname");
		tname=new String(tname.getBytes("iso-8859-1"),"utf-8");
	//	System.out.println(tname);
		Dbcon db=new Dbcon();
		PrintWriter out = resp.getWriter();
		if(db.isHaveUser(tname))
             out.print("00");
		else out.print("11");
        out.flush();
         out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException { 
		
		Dbcon db=new Dbcon();
		String tiny_name=req.getParameter("tiny_name");
		String age=req.getParameter("age");
		String ID=req.getParameter("ID");
		String height=req.getParameter("height");
		String sex=req.getParameter("sex");
		String name=req.getParameter("name");
		String tel=req.getParameter("tel");
		String password=req.getParameter("password");
		if(tiny_name!=null){

			tiny_name=new String(tiny_name.getBytes("iso-8859-1"),"utf-8");
			name=new String(name.getBytes("iso-8859-1"),"utf-8");
			db.addTuserr(tiny_name,name,age,ID,height,sex,tel,password);
			
		}
		User u=new User();
		req.getSession().setAttribute("user", u=db.getUser(tiny_name));
		req.getSession().setAttribute("annlist", db.getAnnList());
		req.getSession().setAttribute("classlist", db.getClassList(null,null,null,null,u));
		req.getRequestDispatcher("/indexx.jsp").forward(req, resp);
	}
          
}
