package com.wzf.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.User;

public class UpdatesqlServ extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User manager=(User) req.getSession().getAttribute("user");
		String str="00";
		if(manager!=null){
			String sql=req.getParameter("sql");
			Dbcon db=new Dbcon();
			str=db.updateSql(sql);
		}
		PrintWriter pw=resp.getWriter();
		pw.write(str);
		pw.flush();
		pw.close();
	}

}
