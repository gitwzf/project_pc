package com.wzf.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.User;
import com.wzf.pubvari.Variable;

public class Workexperi extends HttpServlet{
    Variable vari=new Variable();
    Logger log=Logger.getLogger("logfile");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User u=(User) req.getSession().getAttribute("user");
		String delid=req.getParameter("deleteid");
		Dbcon db=new Dbcon();
		if(u!=null){
			System.out.println(delid);
			if(delid!=null)db.delWorkexperi(delid);
			req.getSession().setAttribute("worklist", db.getWorklist(u.getId()));
			req.getRequestDispatcher("/workexperi.jsp").forward(req, resp);
		}else
		req.getRequestDispatcher("/error.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String[] companyname=req.getParameterValues("companyname");
		String[] department=req.getParameterValues("department");
		String[] beg_tim=req.getParameterValues("beg_tim");
		String[] end_tim=req.getParameterValues("end_tim");
		String[] position=req.getParameterValues("position");
		String[] salary=req.getParameterValues("salary");
		String[] info=req.getParameterValues("info");
		User u=(User) req.getSession().getAttribute("user");
		Dbcon db=new Dbcon();
		boolean flag=true;
		String str="11";
		PrintWriter pw=resp.getWriter();
		if(u!=null){
			for(int i=0;i<companyname.length;i++){
//				companyname[i]=new String(companyname[i].getBytes("iso-8859-1"),"utf-8");
//				department[i]=new String(department[i].getBytes("iso-8859-1"),"utf-8");
//				position[i]=new String(position[i].getBytes("iso-8859-1"),"utf-8");
//				info[i]=new String(info[i].getBytes("iso-8859-1"),"utf-8");
				
				flag=db.addWorkexperi(u.getId(),companyname[i],department[i],beg_tim[i],end_tim[i],position[i],salary[i],info[i]);
			    if(!flag)
			    	{str="00";break;}//异步 数组提交
			}
//			resp.sendRedirect("/"+vari.project+"/Workexperi");
		}
		log.info("work_str="+str);
		pw.write(str);
		pw.flush();
		pw.close();
	}

}
