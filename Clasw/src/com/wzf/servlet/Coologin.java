package com.wzf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.protocol.Protocol;

import com.wzf.model.User;
import com.wzf.pubvari.MySSLProtocolSocketFactory;
import com.wzf.pubvari.Variable;

public class Coologin extends HttpServlet{
     public Variable vari=new Variable();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String code=req.getParameter("code");
		//获取access_token
		String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id="+vari.appid+"&client_secret="+vari.appkey+"&code="+code+"&redirect_uri="+vari.redirect_uri;
		HttpClient client = new HttpClient();
		Protocol myhttps = new Protocol("https",
				new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		GetMethod getMethod = new GetMethod(url);
		client.executeMethod(getMethod);
		String returnStr = getMethod.getResponseBodyAsString();
		String access_token=returnStr.split("access_token=")[1].split("&expires_in")[0];
		
		//获取用户openid   相当于微信openid
		url = "https://graph.qq.com/oauth2.0/me?access_token="+access_token;
		client = new HttpClient();
		myhttps = new Protocol("https",
				new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		 getMethod = new GetMethod(url);
		client.executeMethod(getMethod);
		 returnStr = getMethod.getResponseBodyAsString();
		 String openid=returnStr.split("openid\":\"")[1].split("\"}")[0];
		 
		 //获取3个信息
		 url = "https://graph.qq.com/user/get_user_info?oauth_consumer_key="+vari.appid+"&access_token="+access_token+"&openid="+openid+"&format=json";
			client = new HttpClient();
			myhttps = new Protocol("https",
					new MySSLProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", myhttps);
			 getMethod = new GetMethod(url);
			client.executeMethod(getMethod);
			 returnStr = getMethod.getResponseBodyAsString();
			 JSONObject json=JSONObject.fromObject(returnStr);
			 User u=new User();
			 u.setUsername(json.getString("nickname"));
			 u.setSex(json.getString("gender"));
			 u.setPicurl(json.getString("figureurl"));
			req.getSession().setAttribute("user", u);
			req.getRequestDispatcher("./index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
           
}
