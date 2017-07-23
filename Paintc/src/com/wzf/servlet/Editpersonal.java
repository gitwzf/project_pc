package com.wzf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Manageruser;

public class Editpersonal extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Dbcon db=new Dbcon();
		String school=req.getParameter("school");
		String train=req.getParameter("train");
		String phone=req.getParameter("phone");
		if(phone==null){
		req.getSession().setAttribute("schoollist", db.getSchoolList());
		req.getSession().setAttribute("trainlist", db.getTrainList());
		req.getRequestDispatcher("/editPersonalInfo.jsp").forward(req, resp);}
		else{System.out.println("phone="+phone);
			boolean flag=false;
			if(school!=null){
				flag=db.isTeacher(phone,school,"1");
			}else if(train!=null)flag=db.isTeacher(phone,train, "0");
			
			PrintWriter out = resp.getWriter(); 
			if(flag)out.print("11");
			else out.print("00"); 
	         out.flush(); 
	          out.close();
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String teacherphone=req.getParameter("teacherphone");
		String parentname=req.getParameter("parentname");
		String studentname=req.getParameter("studentname");
		String gender=req.getParameter("gender");
		String biryear=req.getParameter("biryear");
		String birmonth=req.getParameter("birmonth");
		String birday=req.getParameter("birday");
		String ex1=req.getParameter("stnam");
		String birthday = null;
		String type=req.getParameter("type");
		Manageruser man=(Manageruser) req.getSession().getAttribute("manager");
		Dbcon db=new Dbcon();
		if(man!=null)
		if(type!=null){
			if(type.equals("info")){
		if(biryear!=null&&birmonth!=null&&birday!=null){
		   	if(biryear.matches("^[0-9]{4}")&&birmonth.matches("^[0-9]{1,2}")&&birday.matches("^[0-9]{1,2}"))
	     	birthday=biryear+"-"+birmonth+"-"+birday;
		}
		db.upManager(man.getPhone(), null,teacherphone,parentname,studentname,gender,birthday,ex1);
		}
			else if(type.equals("head")){
				db.uphead(man.getPhone(),(String) req.getSession().getAttribute("headurl"));
			}
		}
		man=db.getManageruser(man.getPhone(),man.getPass(),null);
		if(man!=null)
		req.getSession().setAttribute("manager",man);
		req.getRequestDispatcher("/personalCenter.jsp").forward(req, resp);
	}
        
}
