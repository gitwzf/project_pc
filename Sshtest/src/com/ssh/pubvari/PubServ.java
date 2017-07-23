package com.ssh.pubvari;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.Application;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class PubServ extends HttpServlet{
   public void init() throws ServletException{
	   Map<String,String> map_users=new HashMap<String, String>();
	   Dbconn db=new Dbconn();
	  com.ssh.pubvari.Variable.map_users=db.getUsers();
	   System.out.println("用户名全部加载成功。。。"+com.ssh.pubvari.Variable.map_users.size());
	   
   }


}
