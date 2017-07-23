package com.wzf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;

public class Fair extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String type=req.getParameter("type");
		String str="./fcompanylist.jsp";
		Dbcon db=new Dbcon();
		req.getSession().setAttribute("ad", db.getAd());
		if(type!=null&&"fcompanydetail".equals(type)){
			// ∆Û“µœÍœ∏
			String fcompanyid=req.getParameter("fcompanyid");
			req.getSession().setAttribute("fcompany", db.getFcompany(fcompanyid));
			str="./fcompanydetail.jsp";
		}else{
		String fairid=req.getParameter("fairid");
		req.getSession().setAttribute("FAIRID",fairid);
		req.getSession().setAttribute("college", db.getCollege(fairid));
		req.getSession().setAttribute("fcompanylist", db.getFcompanylist(fairid));
		}
		req.getRequestDispatcher(str).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}

}
