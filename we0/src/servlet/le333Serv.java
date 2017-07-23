package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pubvari.Variable;

import model.Pubid;

import dbconnection.Dbconn;

public class le333Serv extends HttpServlet{
	Logger log = Logger.getLogger("logfile");
	public Variable vari=new Variable();
    public String project=vari.project;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	//	req.setCharacterEncoding("UTF-8");
		Dbconn db=new Dbconn();
		String pub=(String) req.getSession().getAttribute("pub");
		    String name=req.getParameter("name");//ยาย๋ฃฟ
//		    log.info("name0="+name);
			String curl=req.getParameter("url");
			String ctoken=req.getParameter("token");
			String username=req.getParameter("wxname");
			String pwd=req.getParameter("pass");
			String appid=req.getParameter("appid");
			String secret=req.getParameter("secret");
			Pubid pub0=new Pubid();
			pub0.setApiurl(curl);
			pub0.setAppid(appid!=null?"":appid);
			pub0.setAppsecret(secret!=null?"":secret);
			try {
				pub0.setName(db.getPubByWxname(username));
			} catch (SQLException e) {
				
				log.error(e);
			}
			pub0.setPass(pwd);
			pub0.setToken(ctoken);
			pub0.setWxname(username);
			req.getSession().setAttribute("cpub",pub0);
			resp.sendRedirect("/"+project+"/le333.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
     
}
