package com.aowin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aowin.form.LogonForm;

public class LogonAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//		String username=request.getParameter("username");
//		String password=request.getParameter("password");
//		
//		System.out.println(username+"   "+password);
//		request.setAttribute("username", username);
		
		System.out.println(form);
		System.out.println(request.getSession().getAttribute("testForm"));
		
//		LogonForm logonForm=(LogonForm) form;
		
//		System.out.println(logonForm.getUsername()+"   "+logonForm.getPassword());
//		request.setAttribute("username", logonForm.getUsername());
//		request.getRequestDispatcher("welcome.jsp").forward(request, response);
//		response.sendRedirect("welcome.jsp");
		return mapping.findForward("success2");
	
//		return mapping.findForward("fail");
//		return new ActionForward("fail.jsp");
	}
	
	

}
