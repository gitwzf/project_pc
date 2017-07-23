package com.wzf.action;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.marker.weixin.MySecurity;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wzf.form.LogonForm;
import com.wzf.form.SignForm;
import com.wzf.form.User;

import dbconnection.Dbconn;

public class LogonAction extends ActionSupport{
	
	private LogonForm logonForm;
	private String hello;
	private String errorMessage;
	private Date date;
	
	public Date getDate() {
		return date;
	}

	public void setDate() {
		this.date = new Date();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	
	public void validateLogon0() {
		if("".equals(logonForm.getUsername())){
			this.setErrorMessage("用户名不能为空");
		}
			
	}

	public String getHello() {
		return hello;
	}

	public void setHello() {
		this.hello ="hello world!";
	}

	public LogonForm getLogonForm() {
		return logonForm;
	}

	public void setLogonForm(LogonForm logonForm) {
		this.logonForm = logonForm;
	}

	public String execute(){
		System.out.println("execute.....");
		return "success"; 
	}
	
	
	
	public String Logon(){
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpServletResponse resp=ServletActionContext.getResponse();
		String username=req.getParameter("username");
		if(username!=null){
			Dbconn db=new Dbconn();
			String password=req.getParameter("password");
			String rember=req.getParameter("rember");
			String saveTime=req.getParameter("saveTime");
			try {
				username=username.replace("'", "");//防sql注入
				password=password.replace("'", "");
			if(db.isManageruser(username,password)){
				Cookie cook=new Cookie("client", username+"|"+password);
				cook.setMaxAge(Integer.parseInt(saveTime)*24*3600);
				resp.addCookie(cook);
				if(rember!=null){Cookie cook1=new Cookie("clientuser", username);
				cook1.setMaxAge(365*24*3600);
				resp.addCookie(cook1);}
			//	req.getSession().setMaxInactiveInterval(15*60);
			    req.getSession().setAttribute("manager",db.getManager(username));}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "index";//重定向  没cookie不能登录
		}
		else
		return "login";
	}

	
}
