package com.wzf.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wzf.dbconn.Dbcon;
import com.wzf.pubvari.Variable;

public class Login extends HttpServlet {
	Logger log = Logger.getLogger("logfile");
	public Variable vari = new Variable();// ø’µƒ£ø£°

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getSession().removeAttribute("manager");
		System.out.println("back...sc");
		// resp.sendRedirect("/"+project+"/index.jsp");
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String str="/login.jsp";
		String username = req.getParameter("username");
		if (username != null) {
			Dbcon db = new Dbcon();
			String password = req.getParameter("password");
			String rember = req.getParameter("rember");
			String saveTime = req.getParameter("saveTime");
			log.info("rember=" + rember);
			log.info("saveTime0=" + saveTime);
			if(username.indexOf("'")>0||password.indexOf("'")>0||username.indexOf("\"")>0||password.indexOf("\"")>0){
				int ips = 0; // ªÒ»°ip
				String ip = req.getRemoteAddr();
				String ip_headers[] = { "x-forwarded-for", "Proxy-Client-IP",
						"WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR" };
				while (ips < ip_headers.length
						&& (ip == null || ip.length() == 0
								|| "unknown".equalsIgnoreCase(ip) || "127.0.0.1"
								.equals(ip))) {
					ip = (req).getHeader(ip_headers[ips++]);
				}
				log.info("…Ê∫⁄ip:"+ip);}
			log.info("’À∫≈£∫"+new String(username.getBytes("iso-8859-1"), "utf-8")+" √‹¬Î£∫"+password);
			username = new String(username.getBytes("iso-8859-1"), "utf-8")
					.replace("'", "");
			password = password.replace("'", "");
			if (db.isManageruser(username, password)) {
				req.getSession().setAttribute("manager",
						db.getManager(username));
				username = URLEncoder.encode(username, "utf-8");
				Cookie cook = new Cookie(vari.COOKIE_ACCOUNT, username + "|"
						+ password);
				if (saveTime != null)
					cook.setMaxAge(365 * 24 * 3600);
				resp.addCookie(cook);
				if (rember != null) {
					Cookie cook1 = new Cookie(vari.COOKIE_NAME, username);
					cook1.setMaxAge(365 * 24 * 3600);
					resp.addCookie(cook1);
				}
				str="/index.jsp";
			}
		}
		req.getRequestDispatcher(str).forward(req, resp);
	}

}
