package com.wzf.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class Changeval extends HttpServlet{
        int a=3;
        
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PrintWriter pw=resp.getWriter();
		a=5;   
		System.out.println("get a="+a);
		pw.write((int)97);
		pw.flush();
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PrintWriter pw=resp.getWriter();
		System.out.println("post a="+a);
		pw.write(a+"");
		pw.flush();
		pw.close();
	}
	
	public void init(){
		Logger log=Logger.getLogger("logger");
		log.info("logger:"+log);
		
		Logger log0=Logger.getLogger("logfile");
		log0.info("logfile:"+log0);
	}

}
