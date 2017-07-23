package com.wzf.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Manageruser;
import com.wzf.pubvari.Variable;

public class Worklist extends HttpServlet{
	public Variable vari=new Variable();//空的？！
    public String project=vari.project;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
Dbcon db=new Dbcon();
String type=req.getParameter("type");
Manageruser mana= (Manageruser) req.getSession().getAttribute("manager");
if(mana!=null){
	String beg=req.getParameter("beg");
	String end=req.getParameter("end");
req.getSession().setAttribute("apworks", db.getallWorksById(mana.getId(),beg,end));
if(type==null||(!"right".equals(type)&&!"left".equals(type)))type="right";
req.setAttribute("type", type);
//System.out.println("workslen="+db.getallWorksById(mana.getId(),beg,end).size());
req.getRequestDispatcher("/worklist.jsp").forward(req, resp);}
else req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		   唯一手机号
//		String phone=req.getParameter("phone");
//		if(phone!=null){
//			Dbcon db=new Dbcon();
//			PrintWriter out = resp.getWriter(); 
//			if(db.isHavephone(phone))out.print("00");
//			else out.print("11");
//				 out.flush(); 
//	          out.close();
//		}
		super.doPost(req, resp);
	}
     
}
