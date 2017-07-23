package com.wzf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
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
		
		String phone=req.getParameter("phone");
		User u=(User) req.getSession().getAttribute("user");
		Dbcon db=new Dbcon();
		if(u!=null){//用户
		if(phone!=null){
		String str="00";
	//	map.put("0", "2");//无奖     用sql查询
		if(db.isGamePhone(phone,u.getId())){
			str=vari.map_ranktype.get(db.getIndex(phone,u.getId()));//返回了id
		}
		else{
			String str0=vari.map_ranktype.get(db.getIndexId(u.getId()));
			str+=";"+(str0==null?"3":str0);
		}
		log.info("奖号："+str);
		req.getSession().setAttribute("user", db.getUser(u.getOpenid()));
		PrintWriter pw=resp.getWriter();
		pw.write(str==null?"3":str);
		pw.flush();
		pw.close();
		}
		else{
			req.getSession().setAttribute("gamerule", db.getGamerule());
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}
		}else{//错误页面
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User u=(User) req.getSession().getAttribute("user");//null
		if(u!=null){
		String phone=req.getParameter("phone");
		String name=req.getParameter("name");
		Dbcon db=new Dbcon();
		String str=db.addGameUser(phone,name,u.getId());
		str=URLEncoder.encode(str, "utf-8");
		log.info(str);
		req.getSession().setAttribute("user", db.getUser(u.getOpenid()));
		PrintWriter pw=resp.getWriter();
		pw.write(str);
		pw.flush();
		pw.close();
		}
	}
	

}
