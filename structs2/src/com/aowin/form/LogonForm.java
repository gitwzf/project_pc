package com.aowin.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class LogonForm extends ActionForm{
	
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors=new ActionErrors();
		String username=request.getParameter("username");
		System.out.println("parameter  username:"+username);
		System.out.println("form  username:"+this.username);
		if("".equals(username)||username==null){
			errors.add("usernameerror", new ActionMessage("username is invalidate"));
			
		}
		return errors;
		
	}
	
	

}
