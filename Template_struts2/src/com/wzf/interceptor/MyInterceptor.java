package com.wzf.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class MyInterceptor implements Interceptor{

	public void destroy() {
		
		
	}

	public void init() {
		
		
	}

	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("befor......");
		String result=arg0.invoke();
		System.out.println("after......");
		return result;
	}

}
