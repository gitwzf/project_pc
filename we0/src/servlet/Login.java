package servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pubvari.Variable;

import model.Manageruser;
import model.Pubid;

import dbconnection.Dbconn;

public class Login extends HttpServlet{
	Logger log = Logger.getLogger("logfile");
	public Variable vari=new Variable();//¿ÕµÄ£¿£¡
    public String project=vari.project;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
//		req.setCharacterEncoding("iso-8859-1");
		req.getSession().removeAttribute("manager");
		System.out.println("back...dk");
		//resp.sendRedirect("/"+project+"/index.jsp");
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String username=req.getParameter("username");
//		log.info(username);
		if(username!=null){
			Dbconn db=new Dbconn();
			String password=req.getParameter("password");
			String rember=req.getParameter("rember");
			String saveTime=req.getParameter("saveTime");
			log.info("rember="+rember);
			log.info("saveTime0="+saveTime);
		//	log.info(Login.class.getClassLoader().getResource("").getPath().split("webapps/")[1].split("/WEB-INF")[0]);
			try {
				username=username.replaceAll("/'|(\\')|(\\-\\-)|(%27)|(%23)|(#)|'/ix","");//·Àsql×¢Èë
				password=password.replaceAll("/'|(\\')|(\\-\\-)|(%27)|(%23)|(#)|'/ix","");
			if(db.isManageruser(username,password)){
				Cookie cook=new Cookie("client", username+"|"+password);
				cook.setMaxAge(Integer.parseInt(saveTime)*24*3600);
				resp.addCookie(cook);
				if(rember!=null){Cookie cook1=new Cookie("clientuser", username);
				cook1.setMaxAge(365*24*3600);
				resp.addCookie(cook1);}
			//	req.getSession().setMaxInactiveInterval(15*60);
			    req.getSession().setAttribute("manager",db.getManager(username));
//				req.getSession().setAttribute("pubidlist", db.getPubId());
//				req.getSession().setAttribute("pub",db.getPubId().get(0).getName());
			    req.getRequestDispatcher("/index.jsp").forward(req, resp);
			}
			else
			resp.sendRedirect("/"+project+"/login.jsp");
//				for(Pubid p: db.getPubId())
//					log.info("p.name="+p.name);
			} catch (SQLException e) {
				
				log.error(e);
			}
		//	req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}
		else
		resp.sendRedirect("/"+project+"/login.jsp");
	}
     
}
