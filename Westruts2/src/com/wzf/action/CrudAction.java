package com.wzf.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Tabletitle;

import org.apache.struts2.ServletActionContext;

import com.wzf.method.ReqResp;

import dbconnection.Dbconn;

public class CrudAction {
	static String  table="re_keyword";
	static int[]  ex=new int[]{};
	static ArrayList<Tabletitle> titles;
	public String crud(){
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpServletResponse resp=ServletActionContext.getResponse();
		if(req.getMethod().equals("GET"))
			init(req,resp);
		else 
		    edit(req,resp);
		
		return null;
	}
	
	public void init(HttpServletRequest req,HttpServletResponse resp){
		Dbconn db=new Dbconn();
		String field=req.getParameter("field");//搜索  null或“”则不执行
		String svalue=req.getParameter("svalue");
		req.getSession().setAttribute("titles", titles=db.getTabTitles(table,ex));//可以加类型，图片、标题、段落  对应不同处理方式
		req.getSession().setAttribute("datalist", db.getDatalist(table,field,svalue));
		ReqResp.forward(req, resp, "/crud.jsp");
	}
	public void edit(HttpServletRequest req,HttpServletResponse resp){
		ReqResp.CEncode(req,"utf-8");
		String first=req.getParameter("first");
		String[] editoritem=req.getParameterValues("editoritem");
		Dbconn db=new Dbconn();
		if(first==null){
		db.AddOrUpdate(editoritem,table,titles);
		req.getSession().setAttribute("datalist", db.getDatalist(table,null,null));
		ReqResp.forward(req, resp,"/crud.jsp");
		}else
		{
			boolean flag=db.delByFirst(first,table,titles.get(0).getName());
			ReqResp.writer(resp, flag==true?"11":"00");
		}
	}
	

}
