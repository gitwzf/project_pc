package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pubvari.Variable;

import dbconnection.Dbconn;

public class le111Serv extends HttpServlet{
	static Logger log = Logger.getLogger("logfile");
       public Variable vari=new Variable();
       public String project=vari.project;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Dbconn db=new Dbconn();

		String name = null;
		String act=req.getParameter("act");
		String word=req.getParameter("word");
		if(req.getParameter("name")!=null)
		   name=new String(req.getParameter("name").getBytes("iso8859-1"),"utf-8");
		 if(word!=null)
			 word=new String(req.getParameter("word").getBytes("iso8859-1"),"utf-8");
		if("delete".equals(act))
			try {
				
				log.info("delname="+name);
				db.delKeyword(name);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			else if("setwelcome".equals(act)){
				try {
					db.setWelcome(name);
				} catch (SQLException e) {
					
					log.error(e);
				}
				req.getSession().setAttribute("info","设置欢迎消息成功！");
			}
			else if("setdefault".equals(act)){
				try {
					db.setDefault(name);
				} catch (SQLException e) {
					
					log.error(e);
				}
				req.getSession().setAttribute("info","设置默认消息成功！");
				}
			else if("delwelcome".equals(act)){
				try {
					db.setWelcome("");
				} catch (SQLException e) {
					
					log.error(e);
				}
				req.getSession().setAttribute("info","取消欢迎消息成功！");
			}
			else if("deldefault".equals(act)){
				try {
					db.setDefault("");
				} catch (SQLException e) {
					
					log.error(e);
				}
				req.getSession().setAttribute("info","取消默认消息成功！");
				}
		String kind=req.getParameter("kind");
		log.info("le111Serv  act:"+act+" word:"+word+" name:"+name+" kind:"+kind);
		req.getSession().setAttribute("kinds",kind);
			
			
			
		try {
			req.getSession().setAttribute("rearray",db.getRearray());
			if(word!=null)req.getSession().setAttribute("rearray",db.getRearray(word));
//			log.info("txtarray:"+db.getTxtarray());
		} catch (SQLException e) {
			
			log.error(e);
		}
		resp.sendRedirect("/"+project+"/le111.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		log.info("post");
		resp.sendRedirect("/"+project+"/le111.jsp");
	}
	
	public static String URLDecodeUTF8(String str){
		if(str==null);
		else
			try {
				return URLDecoder.decode(str,"utf-8");
			} catch (UnsupportedEncodingException e) {
				
				log.error("le111 urldecode:"+e);
			}
		return str;
	}

}
