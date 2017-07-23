package com.wzf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Model;
import com.wzf.model.Page;
import com.wzf.pubvari.Variable;

public class Showpage extends HttpServlet{
	Variable vari=new Variable();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Page page=(Page) req.getSession().getAttribute("page");
		String p=req.getParameter("p");
		String i=req.getParameter("i");
		Dbcon db=new Dbcon();
		if(p!=null&&i!=null){
//			ArrayList<Model> array=db.getModellist(Integer.parseInt(p),5);//批次  和  条目数
			req.getSession().setAttribute("modelss", db.getModellist(Long.parseLong(p),vari.items,null));
			req.getSession().setAttribute("page",new Page(p,vari.items+"",db.getModellist(0l,0,null).size()/vari.items+(db.getModellist(Long.parseLong(p),vari.items,null).size()%vari.items==0?0:1)+""));
            req.setAttribute("i", i);
			req.getRequestDispatcher("./rankingjsp.jsp").forward(req, resp);
			//			String o="";
//			Model m=new Model();
//			for(int i=0;i<array.size();i++){
//				m=array.get(i);
//				o=o+m.getPhone()+";"+m.getCompeteid()+";"+m.getPicurl()+";"+m.getStudentname()+";"+m.getGender()+";"+m.getVote()+" ";
//			}
//			PrintWriter out = resp.getWriter(); 
//	         out.print(o); 
//	         out.flush(); 
//	          out.close();
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
     
}
