package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pubvari.Variable;

import common.WxService;

import dbconnection.Dbconn;

public class le3Serv extends HttpServlet{
	Logger log = Logger.getLogger("logfile");
	public Variable vari=new Variable();
    public String project=vari.project;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		Dbconn db=new Dbconn();
		String name=req.getParameter("name");
//		log.info("name2="+name);
		if(name!=null){
			String curl=req.getParameter("apiurl");
			String ctoken=req.getParameter("token");
			String username=req.getParameter("wxname");
			String pwd=req.getParameter("wxpassword");
			log.info("curl="+curl);
			log.info("token="+ctoken);
			WxService wx=new WxService();
			if(wx.login(username, pwd))
				if(wx.setApiToken(curl, ctoken)){
					try {
						db.updatePub(username,curl,ctoken);
					} catch (SQLException e) {
						
						log.error(e);
					}
					log.info("设置成功1！");}
				else 
					log.info("设置失败1！");
			
		}
		try {
			req.getSession().setAttribute("pubidarray", db.getPubId());
		} catch (SQLException e) {
			
			log.error(e);
		}
		resp.sendRedirect("/"+project+"/le3.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Dbconn db=new Dbconn();
		String pub=(String) req.getSession().getAttribute("pub");
		req.setCharacterEncoding("utf-8");
		String user=req.getParameter("wxusername");
		String pass=req.getParameter("wxpassword");
		String name=req.getParameter("name");
		String wx_id=req.getParameter("account");
		String appid=req.getParameter("key");
		String secret=req.getParameter("secret");
		WxService wx=new WxService();
		String api,token;
		log.info("name="+name);
		if(wx.login(user, pass))
		{
			String[] a=wx.getApiToken();
			api=a[0];
			token=a[1];
//			log.info("api="+api+" token="+token);
			try {
				db.addPubId(name,wx_id,user,pass,api,token,appid,secret);
			} catch (SQLException e) {
				
				log.error(e);
			}
		}
		else req.getSession().setAttribute("state", "error!");
		
		
		resp.sendRedirect("/"+project+"/le33.jsp");
	}
       
}
