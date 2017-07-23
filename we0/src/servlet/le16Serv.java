package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.WxService;

import dbconnection.Dbconn;

public class le16Serv extends HttpServlet{

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
			
			e.printStackTrace();
		}
		WxService wx=new WxService();
		if(wx.login(s[0], s[1]))	
		{   System.out.println("begin......");
			if(wx.getFanList(s[2]))
			{
		try {
			req.getSession().setAttribute("fanlist", db.getFans());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		resp.sendRedirect("/weEngine/fanList.jsp");
			}
		}
		else resp.sendRedirect("/weEngine/main.html");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
      
}
