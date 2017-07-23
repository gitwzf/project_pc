package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pubvari.Variable;

import dbconnection.Dbconn;

public class le15Serv extends HttpServlet{
	Logger log = Logger.getLogger("logfile");
	public Variable vari=new Variable();
    public String project=vari.project;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Dbconn db=new Dbconn(); 
		String pub=(String) req.getSession().getAttribute("pub");
		if(pub!=null){  //过滤器
		String[] s = null;
		try {
			s = db.getPubUserPassName(pub);
			db.setDatabase(s[2]);
		} catch (SQLException e) {
			
			log.error(e);
		}
		String str1 = "error";
		String id=req.getParameter("showid");
		try {
		if("groupid".equals(id))
			str1=db.getGroupname();
		if("place".equals(id))
			str1= db.getP1();
		if("place2".equals(id)){
			String p1=new String(req.getParameter("place1").getBytes("ISO-8859-1"),"utf-8");
		    str1=db.getP2(p1);}
		if("place3".equals(id)){
			String p2=new String(req.getParameter("place2").getBytes("ISO-8859-1"),"utf-8");
			str1=db.getP3(p2);}
		} catch (SQLException e) {
			
			log.error(e);
		}
		log.info(str1);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
        out.print(str1);
        out.flush();
		out.close();}
		else resp.sendRedirect("/"+project+"/login.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Dbconn db=new Dbconn(); 
		String pub=(String) req.getSession().getAttribute("pub");
		String[] s = null;
		try {
			s = db.getPubUserPassName(pub);
			db.setDatabase(s[2]);
		} catch (SQLException e) {
			
			log.error(e);
		}
		req.setCharacterEncoding("utf-8");
		String start=req.getParameter("start");
		String end=req.getParameter("end");
		String groupid=req.getParameter("select[0]");
		String gender=req.getParameter("select[1]");
		String place=req.getParameter("select[2]");
		try {
			String s1 = null,s2=null,s3[] = null;
			if(groupid!=null&&(s1=req.getParameter("groupid"))!=null);
			if(gender!=null&&(s2=req.getParameter("gender"))!=null)
				s2=s2.replace("女","2").replace("男","1").replace("全部","0");
			if(place!=null&&(s3=req.getParameterValues("place"))!=null){
			s3[0]=s3[0].replace("全部","");
			s3[1]=s3[1].replace("全部","");
			s3[2]=s3[2].replace("全部","");
			}
			log.info("s1="+s1);
			req.getSession().setAttribute("fansarray", db.getFans(start,end,s1,s2,s3));
		} catch (Exception e) {
			
			log.error(e);
		}
        resp.sendRedirect("/"+project+"/le15.jsp");
	}
	
}