package com.wzf.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Manageruser;

public class Personalcenter extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Manageruser man=(Manageruser) req.getSession().getAttribute("manager");
		if(man!=null){
			Dbcon db=new Dbcon();
		//	req.getSession().setAttribute("addlist", db.getAddList(man.getId()));
		//	req.getSession().setAttribute("minuslist", db.getWorksById(man.getId()));
			req.getSession().setAttribute("weeklist", db.getWeeklist(man.getId()));
			ArrayList<String> array=new ArrayList<String>();
			String time=new SimpleDateFormat("yyyy").format(new Date());
			for(int i=2014;i<=Integer.parseInt(time);i++)
				  array.add(i+"");
			req.getSession().setAttribute("years", array);
			
			array=new ArrayList<String>();
			for(int i=1;i<=12;i++)
				  array.add(i+"");
			req.getSession().setAttribute("months", array);
			man.setIsyearvip(db.isYearvip(man.getId()));
			man.setUptimes(db.getUptimes(man.getId()));
			req.getSession().setAttribute("manager",man);
			req.getRequestDispatcher("/personalCenter.jsp").forward(req, resp);
		}
		else {
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
     
}
