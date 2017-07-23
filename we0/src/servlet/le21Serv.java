package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pubvari.Variable;

import dbconnection.Dbconn;

public class le21Serv extends HttpServlet{
	Logger log = Logger.getLogger("logfile");
	public Variable vari=new Variable();
    public String project=vari.project;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
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
		try {
			req.getSession().setAttribute("chatrecord", db.getAllChatRecord());
		} catch (SQLException e) {
			
			log.error(e);
		}
		resp.sendRedirect("/"+project+"/le21.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
      
}
