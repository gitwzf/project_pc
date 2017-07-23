package com.wzf.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Model;
import com.wzf.model.Page;
import com.wzf.pubvari.Variable;

public class Modelpage extends HttpServlet{
     Variable vari=new Variable();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String modelsid=req.getParameter("modelsid");
		String pageid=req.getParameter("pageid");
		String modelname=req.getParameter("modelname");
		req.getSession().removeAttribute("page");
		String url="./modellist.jsp";
		Dbcon db=new Dbcon();
		ArrayList<Model> array = null;
		if(modelsid!=null&&!"".equals(modelsid)){
			req.setAttribute("i0",modelsid);
			array=db.getModellist(Long.parseLong(modelsid), 1,null);
		}
		if(pageid!=null&&!"".equals(pageid)){
			 array=db.getModellist(Long.parseLong(pageid),vari.items,null);
			 req.setAttribute("i0", (Long.parseLong(pageid)-1)*vari.items+"");
			req.getSession().setAttribute("page",new Page(pageid,vari.items+"",db.getModellist(0l,0,null).size()/vari.items+(db.getModellist(0l,0,null).size()%vari.items==0?0:1)+""));
		}
		if(modelname!=null&&!"".equals(modelname)){
			modelname=new String(modelname.getBytes("iso-8859-1"),"utf-8");
			array=db.getModellist(0l,0,modelname);
		}
		if(array!=null)
		if(array.size()==1){
			req.getSession().setAttribute("model", array.get(0));
			url="./model_detail.jsp";
		}else{
			req.getSession().setAttribute("models", array);
		}
		
		req.getRequestDispatcher(url).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
    
}
