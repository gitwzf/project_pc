package com.wzf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.User;

public class Sendposition extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User u=(User) req.getSession().getAttribute("user");
		String type=req.getParameter("type");
		String str="/sendpositionlist.jsp";
		Dbcon db=new Dbcon();
		req.getSession().setAttribute("ad", db.getAd());
		if(u!=null){
			if(type!=null&&"detail".equals(type)){
				String positionid=req.getParameter("positionid");
				req.getSession().setAttribute("sposition", db.getPosition(positionid));
				String companyid=db.getCompanyId(positionid);
				req.getSession().setAttribute("scompany", db.getCompany(companyid));
				str="/spositiondetail.jsp";
			}
			
			req.getSession().setAttribute("sendplist", db.getSendPlist(u.getId()));
			
		}
		req.getRequestDispatcher(str).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
  
}
