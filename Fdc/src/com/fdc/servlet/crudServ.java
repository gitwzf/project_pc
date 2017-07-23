package com.fdc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdc.dbconn.Dbcon;
import com.fdc.model.Tabletitle;
import com.fdc.pubvari.Variable;


public class crudServ extends HttpServlet{
	static String  table="activity";
	static int[]  ex=new int[]{};
	static ArrayList<Tabletitle> titles;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Dbcon db=new Dbcon();
		String field=req.getParameter("field");//搜索  null或“”则不执行
		String svalue=req.getParameter("svalue");
		
		req.getSession().setAttribute("titles", titles=db.getTabTitles(table,ex));//可以加类型，图片、标题、段落  对应不同处理方式
		req.getSession().setAttribute("datalist", db.getDatalist(table,field,svalue));
		req.getRequestDispatcher("/crud.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String first=req.getParameter("first");
		String[] editoritem=req.getParameterValues("editoritem");
		Dbcon db=new Dbcon();
		if(first==null){
		db.AddOrUpdate(editoritem,table,titles);
		req.getSession().setAttribute("datalist", db.getDatalist(table,null,null));
		req.getRequestDispatcher("/crud.jsp").forward(req, resp);
		}else
		{
			boolean flag=db.delByFirst(first,table,titles.get(0).getName());
			PrintWriter pw=resp.getWriter();
			pw.write(flag==true?"11":"00");
			pw.flush();
			pw.close();
		}
		Variable.map_activ=db.getAllActiv();//更新活动缓存
	}
         
}
