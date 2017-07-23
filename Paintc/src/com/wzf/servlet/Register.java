package com.wzf.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Manageruser;
import com.wzf.model.Regform0;

public class Register extends HttpServlet{
	Logger log=Logger.getLogger("logfile");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Dbcon db=new Dbcon();
		String phone=req.getParameter("phone");
		String forgetp=req.getParameter("forgetp");//找回密码
		log.info("Register phone="+phone+" forgetp="+forgetp);
		boolean flag=false;
			String content=(int)(Math.random()*89998+10001)+"";
		PrintWriter out = resp.getWriter();       
//		if(forgetp==null)
//		{   if(req.getSession().getAttribute("regform0")!=null)//非注册用户，这里可以判断regform0是否存在
//			{
//			System.out.println("regform="+req.getSession().getAttribute("regform0"));
//			flag=!db.isUser(phone,null);}}
//		else
//		flag=db.isUser(phone,null);
//		if(flag&&db.isYzm(phone)&&getYzm(phone,content)){out.print(content); 
//		db.updateYzmTim(phone);//保护注册用户             加验证码是王道，先验证后发送短信
//		}
//		else
//        out.print("11"); //发送失败
		flag=db.isUser(phone, null);
		if(flag)out.write("11");
		else out.write("00");
        out.flush(); 
         out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		boolean flag=false;
		String parentname=req.getParameter("parentname");
		String studentname=req.getParameter("studentname");
		String biryear=req.getParameter("biryear");
		String birmonth=req.getParameter("birmonth");
		String birday=req.getParameter("birday");
		String birthday=biryear+"-"+birmonth+"-"+birday;
		String gender=req.getParameter("gender");
		String password=req.getParameter("password");
		String phone=req.getParameter("phone");
		if(parentname!=null&&studentname!=null){
			parentname=new String(parentname.getBytes("iso-8859-1"),"utf-8");
			studentname=new String(studentname.getBytes("iso-8859-1"),"utf-8");
			Dbcon db=new Dbcon();
			//获取添加注册0数据
			Regform0 reg=(Regform0) req.getSession().getAttribute("regform0");
			
			String headimg=(String) req.getSession().getAttribute("headurl");
			String st=(String) req.getSession().getAttribute("st");
			if(headimg==null)headimg=""; if(st==null)st="";
			
			int tid=0;
			if(reg!=null)
			{tid=db.addTeachers(reg);flag=false;
			}
			int id=db.addStudents(parentname,studentname,birthday,gender,password,phone,tid,headimg,st);
			if(id==0)flag=true;
			req.getSession().setAttribute("manager", db.getManageruser(null,null,""+id));
			
		}
		if(flag){req.getSession().setAttribute("info", "注册失败！"); req.getRequestDispatcher("/login.jsp").forward(req, resp);}
		else{req.getRequestDispatcher("/loading.jsp").forward(req, resp);}
		
	}
	//短信api
	public boolean getYzm(String phone,String content){
		String create_url = "http://115.236.173.85/sendMsg/?type=1&mp={0}&bn=cgx&nr={1}&fix={2}";  
		try {
	    String newsurl = create_url.replace("{0}", phone).replace("{1}",content).replace("{2}",URLEncoder.encode("书画社（如非本人操作，请勿理睬）","utf-8"));     
		log.info(httpRequest2(newsurl, "GET",""));
		} catch (IOException e) {
			
			e.printStackTrace();return false;
		} 
		return true;
	}
	 public  StringBuffer httpRequest2(String requestUrl, String requestMethod, String outputStr) throws IOException{  
	        StringBuffer buffer = new StringBuffer();  
URL getUrl = new URL(requestUrl);
HttpURLConnection connection = (HttpURLConnection) getUrl
        .openConnection();
connection.connect();
BufferedReader reader = new BufferedReader(new InputStreamReader(
        connection.getInputStream(),"gb2312"));
String lines;
while ((lines = reader.readLine()) != null) {
    buffer.append(lines);
}
reader.close();
connection.disconnect();
	return buffer;
	    } 
      
}
