package com.wzf.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Regform0;
import com.wzf.model.Teacher;

public class Register0 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Dbcon db=new Dbcon();
		String phone=req.getParameter("phone");
		if(phone==null){
		req.getSession().setAttribute("schoollist", db.getSchoolList());
		req.getSession().setAttribute("trainlist", db.getTrainList());
		req.getRequestDispatcher("/register0.jsp").forward(req, resp);}
		else{ 
			Teacher t=db.getTeacher(phone);
			PrintWriter out = resp.getWriter(); 
	         if(t==null)out.print("11"); 
	         else out.print(t.getEmail()+";"+t.getWeixin()+";"+t.getQq()+";"+new String(t.getName().getBytes("utf-8"),"iso-8859-1"));
	         out.flush(); 
	          out.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		Dbcon db=new Dbcon();
		
		String school=req.getParameter("school");
		String train=req.getParameter("train");
		String othera=req.getParameter("othera");
		String otherb=req.getParameter("otherb");
		
		String s="2";String isTrain="1";
		System.out.println("Re0  school="+school+" train="+train+" othera="+othera+" otherb="+otherb);
		
		if(otherb!=null&&!otherb.equals("")){
			s=db.addSchool(otherb,"0");
			isTrain="0";
		}
		else if(othera!=null&&!othera.equals("")){
			s=db.addSchool(othera,"1");
			isTrain="1";
		}
	
		String phone=req.getParameter("phone");
		String teachername=req.getParameter("teachername");
		String qq=req.getParameter("qq");
		String weixin=req.getParameter("weixin");
		String email=req.getParameter("email");
		
		String st=req.getParameter("st");
		if(phone!=null&&teachername!=null){
			req.getSession().setAttribute("regform0", new Regform0(isTrain,s,phone,teachername,qq,weixin,email));
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
		}else if(st!=null){
			req.getSession().setAttribute("st", st);
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
		}
		else{
			resp.sendRedirect("/register0.jsp");
		}
	}
     
}
