package com.wzf.method;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReqResp {
	/*��ת*/
	public static void forward(HttpServletRequest req,HttpServletResponse resp,String path){
		try {
			req.getRequestDispatcher(path).forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*�ض���*/
	public static void redirect(HttpServletResponse resp,String path){
		try {
			resp.sendRedirect(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*д��*/
	public static void writer(HttpServletResponse resp,String str){
		Writer out;
		try {
			out = resp.getWriter();
			 out.write(str);
		       out.flush();
				out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   //img �� ��  100%
	      
	}
	/*���ñ���*/
	public static void CEncode(HttpServletRequest req,String type){
		try {
			req.setCharacterEncoding(type);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
