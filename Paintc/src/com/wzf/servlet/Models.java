package com.wzf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Manageruser;
import com.wzf.model.Model;
import com.wzf.model.Page;
import com.wzf.model.Voteuser;
import com.wzf.pubvari.Variable;

public class Models extends HttpServlet{
	Variable vari=new Variable();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String phone=req.getParameter("phone");
		req.getSession().removeAttribute("noprivlg");
		req.getSession().removeAttribute("isvote");
		Dbcon db=new Dbcon();
		String username;
		String userkey;
		boolean flag=false;
		Cookie[] cookies=req.getCookies();
		if(cookies!=null)
		for(Cookie coo:cookies){
			String client=coo.getValue();
			if(client.split("&").length==2&&"clientopenid".equals(coo.getName())){
				username=client.split("&")[0].replace("'", "");
				userkey=client.split("&")[1].replace("'", "");
				req.getSession().setAttribute("username", username);
				req.getSession().setAttribute("userkey", userkey);
				if(db.isDoneuser(username,userkey))
					req.getSession().setAttribute("noprivlg", "0");

				Voteuser v=Variable.map_actvoteuser.get(username+";"+userkey);
				if(v==null)v=db.getVoteuser(username, userkey);
					if(v!=null){flag=true;
					Variable.map_actvoteuser.put(username+";"+userkey, v);
					req.getSession().setAttribute("voteuser", v);
					}
			}
			
		}
		if(!flag){System.out.println("加cookie...");
			username=new Date().getTime()+"";
			userkey=(long)(Math.random()*89998)+10001+"";
			int ips=0;    //获取ip
			String ip=req.getRemoteAddr();
			String ip_headers[]={"x-forwarded-for","Proxy-Client-IP","WL-Proxy-Client-IP","HTTP_CLIENT_IP","HTTP_X_FORWARDED_FOR"};
			 while(ips<ip_headers.length&&(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)||"127.0.0.1".equals(ip))){
			   ip = req.getHeader(ip_headers[ips++]); 
			    }
			 Variable.map_actvoteuser.put(username+";"+userkey, db.addVoteuser(username,userkey,ip));
			Cookie cook=new Cookie("clientopenid", username+"&"+userkey);//放入cookie
			cook.setMaxAge(365*24*3600);
			resp.addCookie(cook);
			
		}
		
		
		
		
		if(phone!=null){
			PrintWriter out = resp.getWriter(); 
			String str="11";
			if(db.getCompetetion()!=null){
		Model mo=db.getModel(phone,null);
		String data;
		if(mo!=null){
		String sname=new String(mo.getStudentname().getBytes("utf-8"),"iso-8859-1");
		String pname=new String(mo.getParentname().getBytes("utf-8"),"iso-8859-1");
		 data=pname+";"+sname+";"+mo.getGender()+";"+mo.getAge()+";"+mo.getHeight()+";"+mo.getWeight()+";"
+mo.getRelationship()+";"+mo.getQq()+";"+mo.getWeixin()+";"+mo.getEmail();
		 str=data;
		}
		}else
			str=new String("现在没有要报名的比赛".getBytes("utf-8"),"iso-8859-1");
				
			out.print(str);
	        out.flush(); 
	         out.close();
		}
		else{
			req.getSession().setAttribute("models", db.getModellist(1l,vari.items,null));//批次  和  条目数
			req.getSession().setAttribute("page",new Page("1",vari.items+"",db.getModellist(0l,0,null).size()/vari.items+(db.getModellist(0l,0,null).size()%vari.items==0?0:1)+""));
			//System.out.println(db.getModellist(1,5).size());
			req.getRequestDispatcher("/modellist.jsp").forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Dbcon db=new Dbcon();
		String phone=req.getParameter("phone");
		String parentname=req.getParameter("parentname");
		String studentname=req.getParameter("studentname");
		String gender=req.getParameter("gender");
		String relationship=req.getParameter("relationship");
		String age=req.getParameter("age");
		String height=req.getParameter("height");
		String weight=req.getParameter("weight");
		String qq=req.getParameter("qq");
		String weixin=req.getParameter("weixin");
		String email=req.getParameter("email");
		
		String picurl=(String) req.getSession().getAttribute("picurl");
	//	Manageruser mana= (Manageruser) req.getSession().getAttribute("manager");
		if(picurl!=null){
			if(parentname!=null)parentname=new String(parentname.getBytes("iso-8859-1"),"utf-8").replace("'", "\\'");
			if(studentname!=null)studentname=new String(studentname.getBytes("iso-8859-1"),"utf-8").replace("'", "\\'");
		db.addModels(phone,parentname,studentname,gender,relationship,age,height,weight,qq,weixin,email,picurl,db.getCompetetion());
		
		}
		resp.sendRedirect("/"+vari.project+"/modellist.jsp");
		
		
		
	}
	

}
