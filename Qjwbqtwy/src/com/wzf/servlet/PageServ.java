package com.wzf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Page;
import com.wzf.pubvari.Variable;

public class PageServ extends HttpServlet {
	Variable vari = new Variable();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// String p=req.getParameter("p");
		String type = req.getParameter("type");
		boolean flag=false;
		Dbcon db = new Dbcon();
		if (type != null) {
			if ("p_hunter".equals(type)) {
				Page pa = (Page) req.getSession().getAttribute("hpage");
				System.out.println(pa.getP()+"  "+pa.getMaxp());
				int p = Integer.parseInt(pa.getP()) + 1;System.out.println("maxp="+pa.getMaxp()+" p="+p);
				if (pa != null && Integer.parseInt(pa.getMaxp()) >= p) {
					req.setAttribute("p_hunterlist", db.getHunterlist(p));
					req.getSession().setAttribute(
							"hpage",
							new Page(p + "", db.getHunterlist(-1).size()
									/ vari.p_size
									+ (db.getHunterlist(-1).size()
											% vari.p_size == 0 ? 0 : 1) + ""));
					flag=true;
				}
			} else if ("p_college".equals(type)) {
				String i = req.getParameter("i");
				Page pa = (Page) req.getSession().getAttribute("cpage");
				int p = Integer.parseInt(pa.getP()) + 1;
				if (pa != null && Integer.parseInt(pa.getMaxp()) >= p) {
					req.setAttribute("p_collegelist", db.getCollegelist(p, i));// page·¶Î§
					req.getSession().setAttribute(
							"cpage",
							new Page(p + "", db.getCollegelist(-1, i).size()
									/ vari.p_size
									+ (db.getCollegelist(-1, i).size()
											% vari.p_size == 0 ? 0 : 1) + ""));
					flag=true;
				}
			} else if ("p_company".equals(type)) {
				String typeid = req.getParameter("typeid");
				Page pa = (Page) req.getSession().getAttribute("compage");
				int p = Integer.parseInt(pa.getP()) + 1;System.out.println("maxp="+pa.getMaxp()+" p="+p+" typeoneid="+typeid);
				if (pa != null && Integer.parseInt(pa.getMaxp()) >= p) {
					req.setAttribute("p_companylist", db.getCompanylist(p,
							typeid));
					req.getSession().setAttribute(
							"compage",
							new Page(p + "", db.getCompanylist(-1, typeid)
									.size()
									/ vari.p_size
									+ (db.getCompanylist(-1, typeid).size()
											% vari.p_size == 0 ? 0 : 1) + ""));
					flag=true;
				}
			}
            if(flag)
			req.getRequestDispatcher("/" + type + ".jsp").forward(req, resp);
		}
	}

}
