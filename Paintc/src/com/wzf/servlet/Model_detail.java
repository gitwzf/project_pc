package com.wzf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Model;

public class Model_detail extends HttpServlet{
	Logger log=Logger.getLogger("logfile");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String phone=req.getParameter("phone");
		String competeid=req.getParameter("competeid");
		String i0=req.getParameter("i0");
		Dbcon db=new Dbcon();
		Model m;
		m=db.getModel(phone,competeid);
		req.getSession().setAttribute("model",m);
		req.setAttribute("i0",i0);
		req.getRequestDispatcher("/model_detail.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
      
}
