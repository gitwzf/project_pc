package com.wzf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.User;
import com.wzf.pubvari.Variable;

public class GameServ extends HttpServlet{
	Logger log=Logger.getLogger("logfile");
	Variable vari=new Variable();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//账户放入cookie
		boolean flag=false;
		String phone=req.getParameter("phone");
		User u=(User) req.getSession().getAttribute("user");
		Dbcon db=new Dbcon();
		if(u!=null)
		   if(phone!=null){
			   System.out.println(11);
	//		String phone=u.getCjphone();
		String str="00";
	//	map.put("0", "2");//无奖     用sql查询
		if(db.isGamePhone(phone)){
			System.out.println("01");
			str=vari.map_ranktype.get(db.getIndex(phone));//返回了id
		}
		else{System.out.println("02");
			String str0=vari.map_ranktype.get(db.getIndexId(phone));
			str+=";"+(str0==null?"3":str0);
		}
		log.info("奖号："+str);
		flag=true;
		req.getSession().setAttribute("user", db.getUser(u.getCjname(),u.getCjphone()));
		PrintWriter pw=resp.getWriter();
		pw.write(str==null?"3":str);
		pw.flush();
		pw.close();
		   }  
		   if(!flag){
			req.getSession().setAttribute("gamerule", db.getGamerule());
		req.getRequestDispatcher("/game0.jsp").forward(req, resp);
		   }
		   
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	//	User u=(User) req.getSession().getAttribute("user");//null
		//if(u!=null){
		String phone=req.getParameter("phone");
		String name=req.getParameter("name");
		Dbcon db=new Dbcon();
		String str=db.addGameUser(name,phone);
		req.getSession().setAttribute("user", db.getUser(name,phone));
		if(!"00".equals(str)){
			//加cookie
			name=URLEncoder.encode(name, "utf-8");
			Cookie cook=new Cookie("gameuser", name+"&"+phone);//放入cookie
			cook.setMaxAge(365*24*3600);
			resp.addCookie(cook);
		}
		
		str=URLEncoder.encode(str, "utf-8");
		log.info(str);
		
		PrintWriter pw=resp.getWriter();
		pw.write(str);
		pw.flush();
		pw.close();
		//}
	}
	

}
