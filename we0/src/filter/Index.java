package filter;

import java.io.IOException;
import java.io.InputStreamReader;
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

import model.Manageruser;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.org.apache.commons.digester.rss.Channel;

import pubvari.Variable;
import wxtry.Dbcon;

import dbconnection.Dbconn;

public class Index implements javax.servlet.Filter{
	Logger log = Logger.getLogger("logfile");
	public void destroy() {
		
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		String username;
		String password;
		int ips=0;    //获取ip
		String ip=request.getRemoteAddr();
		String ip_headers[]={"x-forwarded-for","Proxy-Client-IP","WL-Proxy-Client-IP","HTTP_CLIENT_IP","HTTP_X_FORWARDED_FOR"};
		 while(ips<ip_headers.length&&(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)||"127.0.0.1".equals(ip))){
		   ip = ((HttpServletRequest) request).getHeader(ip_headers[ips++]); 
		    }
		log.info("来访者ip:"+ip);
		boolean flag=true;
		Dbconn db=new Dbconn();
		HttpServletRequest hsr=(HttpServletRequest) request;
		String pub=(String) hsr.getSession().getAttribute("pub");
		Manageruser mana=(Manageruser) hsr.getSession().getAttribute("manager");
		Cookie[] cookies=hsr.getCookies();
		if(cookies!=null)
		for(Cookie coo:cookies){
			String client=coo.getValue();
			if(client.split("\\|").length==2){
				username=client.split("\\|")[0].replace("'", "");
				password=client.split("\\|")[1].replace("'", "");
				try {
					System.out.println("username1="+username);
					if(db.isManageruser(username,password)){ 
						hsr.getSession().setMaxInactiveInterval(15*60);
						hsr.getSession().setAttribute("manager",db.getManager(username));
//						hsr.getSession().setAttribute("pubidlist", db.getPubId());
//						hsr.getSession().setAttribute("pub",db.getPubId().get(0).getName());
						flag=false;
						chain.doFilter(request, response);}
				} catch (SQLException e) {
					
					log.error(e);
				}
			}
			if("clientuser".equals(coo.getName()))
			hsr.getSession().setAttribute("clientuser", coo.getValue());
		}
		if(flag){
	if(mana!=null)System.out.println("manauser="+mana.getUser());
		if(pub==null||mana==null){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		System.out.println("pub===null");
		dispatcher.forward(request, response); 
		return;
		}
		else
		    chain.doFilter(request, response);
	}
	}
	public void init(FilterConfig filterConfig) throws ServletException {
		
		Properties p=new Properties();
		
		try {
			p.load(new InputStreamReader(this.getClass().getResourceAsStream("../vari.properties")));
			Variable.URL=p.getProperty("URL");
			Variable.URL0=p.getProperty("URL0");
			Variable.database=p.getProperty("database");
			Variable.port=p.getProperty("port");
			Variable.host=p.getProperty("host");
			Variable.username=p.getProperty("username");
			Variable.password=p.getProperty("password");
			Variable.up_audio_path=p.getProperty("up_audio_path");
			Variable.up_pic_path=p.getProperty("up_pic_path");
			Variable.wa_pic_path=p.getProperty("wa_pic_path");
			Variable.weixin_name=p.getProperty("weixin_name");
			Variable.url=p.getProperty("url");
			Variable.news_item=p.getProperty("news_item");
//			String str=Variable.weixin_name;
//			System.out.println("str0="+new String(str.getBytes(),"utf-8"));
//			System.out.println("str0="+new String(str.getBytes(),"gbk"));
//			System.out.println("str3="+new String(str.getBytes("iso8859-1"),"gbk"));
//			System.out.println("str4="+new String(str.getBytes("iso8859-1"),"utf-8"));
//			System.out.println("str5="+new String(str.getBytes("gbk"),"utf-8"));
			
			
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
		Dbconn db=new Dbconn();
		String[] app=db.getApp();
		Variable.appid=app[0];
		Variable.appsecret=app[1];
		
		log.info("vari.properties配置信息：weixin_name="+Variable.weixin_name+"  URL="+Variable.URL+" database="+Variable.database+" port="+Variable.port+
				" username="+Variable.username+" password="+Variable.password+"\n 上传目录="+Variable.up_pic_path+" appid="+Variable.appid);
	}

	
      
}
