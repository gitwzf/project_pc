package com.wzf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wzf.model.Workss;
import com.wzf.pubvari.Variable;

public class Paintdetail extends HttpServlet{
   Logger log=Logger.getLogger("logfile");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String workid=req.getParameter("workid");
		String i0=req.getParameter("i0");
		log.info("paintdetail  workid="+workid+" i0="+i0);
		
		if(workid!=null)
		req.getSession().setAttribute("paint", getPaint(workid));
		req.setAttribute("i0", i0);
		req.getRequestDispatcher("/paint_detail.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
	
	public Workss getPaint(String workid){
		Workss work=new Workss();
		for(Workss w:Variable.array_competepaint)
			if(w.getWorkid().equals(workid))
				{
				work=w;break;
				}
		return work;
	}
         
}
