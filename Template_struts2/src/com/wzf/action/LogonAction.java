package com.wzf.action;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.wzf.form.LogonForm;
import com.wzf.form.User;

public class LogonAction extends ActionSupport{
	
	private LogonForm logonForm;
	private String hello;
	private String errorMessage;
	private Date date;
	

	
//	private String username;
//	private String password;
//	private int age;
//	
//	
//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

	
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
	
	public String aa(){
		System.out.println("aa...");
		return "success";
	}
	
	public String Logon(){
		System.out.println("logon....");
//		System.out.println(this.username);
//		System.out.println(this.password);
//		System.out.println(this.age);
		
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpSession session=request.getSession();
		ServletContext application=ServletActionContext.getServletContext();
		
		System.out.println(logonForm);
		setHello();
		
		ArrayList<User> list=new ArrayList<User>();
		list.add(new User("zhangsan","123",22));
		list.add(new User("lisi","123",22));
		list.add(new User("wangwu","123",22));
		session.setAttribute("userList", list);
		setDate();
		
//		String s=null;
//		System.out.println(s.length());
		
		
//		request.setAttribute("username", logonForm.getUsername());
//
//		System.out.println(logonForm.getUsername());
//		System.out.println(logonForm.getPassword());
//		System.out.println(logonForm.getAge());
		return "success";
	}
}
