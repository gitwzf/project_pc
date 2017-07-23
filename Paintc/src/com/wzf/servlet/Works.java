package com.wzf.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Manageruser;
import com.wzf.pubvari.Variable;

public class Works extends HttpServlet{
	public Variable vari=new Variable();//¿ÕµÄ£¿£¡
    public String project=vari.project;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Dbcon db=new Dbcon();
		String title=req.getParameter("title");
		String introduction=req.getParameter("introduction");
		String remark=req.getParameter("remark");
		String picurl=(String) req.getSession().getAttribute("picurl");
		Manageruser mana= (Manageruser) req.getSession().getAttribute("manager");
		if(picurl!=null&&mana!=null){
			if(title==null||"".equals(title))title=""+new Date().getTime();
			title=new String(title.getBytes("iso-8859-1"),"utf-8");
			if(introduction!=null)
			introduction=new String(introduction.getBytes("iso-8859-1"),"utf-8");
			if(remark!=null)
			  remark=new String(remark.getBytes("iso-8859-1"),"utf-8");
		if(db.addWorks(title,introduction,remark,picurl,mana.getId())){
			mana=db.getManageruser(mana.getPhone(), mana.getPass(),null);
			req.getSession().setAttribute("manager", mana);
		}
		
		
		req.getSession().setAttribute("apworks", db.getallWorksById(mana.getId(),null,null));
		req.getRequestDispatcher("/worklist.jsp").forward(req, resp);
		}else
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		
		
	}

	
}
