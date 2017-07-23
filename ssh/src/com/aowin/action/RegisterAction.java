package com.aowin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.aowin.form.RegisterForm;
import com.aowin.model.User;
import com.aowin.service.IUserService;
import com.aowin.service.UserService;

public class RegisterAction extends DispatchAction{
	
	private IUserService userService0;
	public IUserService getUserService0() {
		return userService0;
	}
	public void setUserService0(IUserService userService0) {
		this.userService0 = userService0;
	}


//	public ActionForward execute(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		RegisterForm registerForm=(RegisterForm) form;
//		userService0.register(registerForm.getEmail(),registerForm.getPass(),registerForm.getName(),registerForm.getTelphone());
//	//	request.getSession().setAttribute("username", registerForm.getUsername());
//		return mapping.findForward("success");
//	}
	//µÇÂ½
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RegisterForm registerForm=(RegisterForm) form;
		User u=userService0.login(registerForm.getEmail(), registerForm.getPass());
	//	request.getSession().setAttribute("username", registerForm.getUsername());
		if(u!=null)
		return mapping.findForward("success");
		else
		return mapping.findForward("error");
	}
	//×¢²á
	public ActionForward register(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RegisterForm registerForm=(RegisterForm) form;
		userService0.register(registerForm.getEmail(),registerForm.getPass(),registerForm.getName(),registerForm.getTelphone());
	//	request.getSession().setAttribute("username", registerForm.getUsername());
		return mapping.findForward("success");
	}
	
	

}
