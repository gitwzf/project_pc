package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pubvari.Variable;

import model.Manageruser;

import dbconnection.Dbconn;

public class le4Serv extends HttpServlet{
	Logger log = Logger.getLogger("logfile");
	public Variable vari=new Variable();
    public String project=vari.project;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Dbconn db=new Dbconn();
		String action=req.getParameter("action");
		Manageruser mana=(Manageruser) req.getSession().getAttribute("manager");
		String user=req.getParameter("user");
		  if(user!=null)user=new String(user.getBytes("iso8859-1"),"utf-8");
		  if(mana==null)resp.sendRedirect("/"+project+"/login.jsp");
		  else
		try{
		if("delete".equals(action)){
				db.delManager(user);
		}
		if("disable".equals(action)){
				String value=req.getParameter("value");		
				db.setManagerDisable(user,value);		
		}
		if("edit".equals(action)){
			String power=req.getParameter("power"); 
			if(Integer.parseInt(power)<=Integer.parseInt(mana.getPower()))
				db.setManagerPower(user,power,mana.getUser());	
		}
			req.getSession().setAttribute("managerarray", db.getManagerarray(mana));
			resp.sendRedirect("/"+project+"/le4.jsp");
		} catch (SQLException e) {
			
			log.error(e);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		log.info("111");
		Dbconn db=new Dbconn();
		String user=req.getParameter("user");
		String username=req.getParameter("username");
		if(user!=null){user=new String(user.getBytes("iso8859-1"),"utf-8")
			;
		String pass=req.getParameter("pass");
		String instruction=req.getParameter("instruction");
		try {
			instruction=new String(instruction.getBytes("iso8859-1"),"utf-8");
			db.addManageruser(user,pass,instruction);
		} catch (SQLException e) {
			
			log.error(e);
		}
		resp.sendRedirect("/"+project+"/le44.jsp");
		}
		
		if(username!=null){
			boolean flag=false;
			try {
				log.info("n_username="+username);
				 flag=db.isHaveUser(username);
			} catch (SQLException e) {
				
				log.error(e);
			}	
			PrintWriter out = resp.getWriter();
			if(flag)
	        out.print("00");
			else  out.print("11");
	        out.flush();
			out.close();
			
		}
	}
     
}
