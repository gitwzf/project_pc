package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import common.WxService;

import dbconnection.Dbconn;

public class Information extends HttpServlet{
	Logger log = Logger.getLogger("logfile");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html");
		WxService wxS = new WxService();
			//	String fackId = request.getParameter("fackId");
		Dbconn db=new Dbconn();
		String pub=(String) req.getSession().getAttribute("pub");
		log.info("pub="+pub);
		String[] s = null;
		try {
			s = db.getPubUserPassName(pub);
		} catch (SQLException e) {
			
			log.error(e);
		}
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		String str;
		if(wxS.login(s[0], s[1]))
		if(!"".equals(str=wxS.getImageItem()))
			out.println(str);
		else
			out.println("0");
		else
			out.println("0");
		out.flush();
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String action=req.getParameter("action");
		log.info(action);
		if("freshfans".equals(action)){
			WxService wxS=new WxService();
			Dbconn db=new Dbconn();
			String pub=(String) req.getSession().getAttribute("pub");
			log.info("pub="+pub);
			String[] s = null;
			try {
				s = db.getPubUserPassName(pub);
			} catch (SQLException e) {
				
				log.error(e);
			}
			wxS.login(s[0], s[1]);
			boolean flag=wxS.getFanList(s[2]);
			PrintWriter out = resp.getWriter();
			if(flag)
				out.write("1");
			else out.write("00");
			out.flush();
			out.close();
		}
	}
       
}
