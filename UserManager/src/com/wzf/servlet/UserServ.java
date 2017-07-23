package com.wzf.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.compara.UserCom;
import com.wzf.model.UserInfo;
import com.wzf.pub.PubVari;

public class UserServ extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id=req.getParameter("id");
		String loginname=req.getParameter("loginname");
		if(id==null)
			id="";
		if(loginname==null)
			loginname="";
		ArrayList<UserInfo> newUserList=getAletUsers(id,loginname);
		Collections.sort(newUserList, new UserCom());
		req.getSession().setAttribute("userlist",newUserList);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id=req.getParameter("id");
		String loginname=req.getParameter("loginname");
		String name=req.getParameter("name");
		String type=req.getParameter("type");
		if(id==null)
			id="";
		if(loginname==null)
			loginname="";
		if(name==null)
			name="";
		if("save".equals(type))
		saveOrUpdate(id,loginname,name);
		else if("del".equals(type))
			del(id);
		req.getSession().setAttribute("userlist",PubVari.userList);
	//	resp.sendRedirect("./index.jsp");
	}
	
	protected void del(String id){
		if(id.isEmpty())
			return;
		long idd=Long.parseLong(id);
		UserInfo user=null;
		for(int i=PubVari.userList.size()-1;i>=0;i--){
			user=PubVari.userList.get(i);
			 if(user.getId()==idd){
				 PubVari.userList.remove(i);
			 }
		}
	}
	
	protected void saveOrUpdate(String id,String loginname,String name){
		if(id.isEmpty())
			return;
		long idd=Long.parseLong(id);
		boolean flag=false;
		for(UserInfo user :PubVari.userList){
			 if(user.getId()==idd){
				 user.setLoginname(loginname);
				 user.setName(name);
				 flag=true;
			 }
		}
		if(!flag){
			UserInfo user=new UserInfo(idd, loginname, name);
			PubVari.userList.add(user);
		}
		
		
	}
	
	protected ArrayList<UserInfo> getAletUsers(String id,String loginname){
		ArrayList<UserInfo> array=new ArrayList<UserInfo>();
		for(UserInfo user : PubVari.userList){
		   if(!id.isEmpty() && loginname.isEmpty()){
			   long idd=Long.parseLong(id);
			   
				   if(user.getId()==idd)
					   array.add(user);
		   }else
		   if(id.isEmpty() && !loginname.isEmpty()){
				   if(user.getLoginname().indexOf(loginname)>=0)
					   array.add(user);
		   }else
			if(!id.isEmpty() && !loginname.isEmpty()){ 
				   long idd=Long.parseLong(id);
				   
				   if(user.getId()==idd && user.getLoginname().indexOf(loginname)>=0)
					   array.add(user);
			   }else{
				   array.add(user);
			   }
			   }
		return array;
		
	}

}
