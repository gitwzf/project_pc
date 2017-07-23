package com.wzf.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Page;
import com.wzf.pubvari.Variable;

public class CompanyServ extends HttpServlet{
     Variable vari=new Variable();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String type=req.getParameter("type");
		String str="/companylist.jsp";
		Dbcon db=new Dbcon();
		req.getSession().setAttribute("ad", db.getAd());
		if(type!=null){
		if("detail".equals(type)){
			String companyid=req.getParameter("companyid");
			req.getSession().setAttribute("company", db.getCompany(companyid));
			req.getSession().setAttribute("positionlist", db.getPositionlist(companyid,vari.POSITION_TYPE_COMPANY));
			str="/positionlist.jsp";
		}else
			if("positiondetail".equals(type)){
				String positionid=req.getParameter("positionid");
				String hunterid=req.getParameter("hunterid");
				if(hunterid!=null){
					req.setAttribute("title",URLEncoder.encode("Î¯ÍÐ","utf-8"));
					req.setAttribute("hunterid", hunterid);
				}else{
					req.setAttribute("title",URLEncoder.encode("Í¶µÝ","utf-8"));
				}
				req.getSession().setAttribute("position", db.getPosition(positionid));
				str="/positiondetail.jsp";
			}
		}else{	
			String typeid=req.getParameter("typeid");
			String mtype=req.getParameter("mtype");
			req.setAttribute("typeid", typeid);
			req.setAttribute("all", URLEncoder.encode(db.getModeltypeName(typeid), "utf-8"));
			
			if(mtype!=null&&"1".equals(mtype)){
				req.getSession().setAttribute("company", db.getCompany(typeid));
				req.getSession().setAttribute("positionlist", db.getPositionlist(typeid,vari.POSITION_TYPE_COMPANY));
				
				str="/positionlist.jsp";
			}
			else
			{req.getSession().setAttribute(
						"compage",
						new Page("1", db.getCompanylist(-1, typeid)
								.size()
								/ vari.p_size
								+ (db.getCompanylist(-1, typeid).size()
										% vari.p_size == 0 ? 0 : 1) + ""));
			req.getSession().setAttribute("companylist", db.getCompanylist(1,typeid));}
		}
		
		req.getRequestDispatcher(str).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
        
}
