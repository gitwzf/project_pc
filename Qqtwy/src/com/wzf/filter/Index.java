package com.wzf.filter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import sun.rmi.server.Dispatcher;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import com.wzf.dbconn.Dbcon;
import com.wzf.model.Manageruser;
import com.wzf.pubvari.Variable;

public class Index implements javax.servlet.Filter {
	Logger log = Logger.getLogger("logfile");
	Variable vari = new Variable();

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String username;
		String password;
		int ips = 0; // 获取ip
		String ip = request.getRemoteAddr();
		String ip_headers[] = { "x-forwarded-for", "Proxy-Client-IP",
				"WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR" };
		while (ips < ip_headers.length
				&& (ip == null || ip.length() == 0
						|| "unknown".equalsIgnoreCase(ip) || "127.0.0.1"
						.equals(ip))) {
			ip = ((HttpServletRequest) request).getHeader(ip_headers[ips++]);
		}
		log.info("来访者ip:" + ip);
		boolean flag = true;
		Dbcon db = new Dbcon();
		HttpServletRequest hsr = (HttpServletRequest) request;
		HttpServletResponse hsre = (HttpServletResponse) response;
		// Manageruser mana=(Manageruser)
		// hsr.getSession().getAttribute("manager");
		Cookie[] cookies = hsr.getCookies();
		if (cookies != null)
			for (Cookie coo : cookies) {
				String client = coo.getValue();
				if (client.split("\\|").length == 2
						&& coo.getName().equals(vari.COOKIE_ACCOUNT)) {
					username = client.split("\\|")[0].replace("'", "");
					username = URLDecoder.decode(username, "utf-8");
					password = client.split("\\|")[1].replace("'", "");
					if (db.isManageruser(username, password)) {
						hsr.getSession().setMaxInactiveInterval(15 * 60);
						hsr.getSession().setAttribute("manager",
								db.getManager(username));
						flag = false;
					}
				}
				if (vari.COOKIE_NAME.equals(coo.getName()))
					hsr.getSession().setAttribute(vari.COOKIE_NAME,
							URLDecoder.decode(coo.getValue(), "utf-8"));
			}
		if (flag) {
			hsr.getRequestDispatcher("/login.jsp").forward(hsr, hsre);
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Properties p=new Properties();
		try {
			p.load(new InputStreamReader(this.getClass().getResourceAsStream("../../../vari.properties")));
			Variable.IP=p.getProperty("IP");
			Variable.database=p.getProperty("database");
			Variable.port=p.getProperty("port");
			Variable.username=p.getProperty("username");
			Variable.password=p.getProperty("password");
			Variable.str_local_dir=p.getProperty("webapps");
			Variable.path_pic_upload=p.getProperty("picpath");
//			Variable.pic_path=p.getProperty("filepicpath");
//			Variable.audio_path=p.getProperty("fileaudpath");
			log.info("vari.properties配置信息：IP="+Variable.IP+" database="+Variable.database+" port="+Variable.port+
					" username="+Variable.username+" password="+Variable.password+"\n weapps目录="+Variable.str_local_dir+" 富文本图片目录="+Variable.path_pic_upload);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("初始化异常："+e);
		}
	}

}
