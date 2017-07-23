package com.wzf.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Manageruser;

public class HonorRoll extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String action=req.getParameter("action");
		Dbcon db=new Dbcon();
		if(action==null)action="week";
		Map<String,String> m=new HashMap();
		m.put("week", "3");
		m.put("month", "2");
		m.put("season", "1");
		m.put("year", "0");
		Manageruser man=(Manageruser) req.getSession().getAttribute("manager");
		System.out.println(man);
		req.getSession().setAttribute("action", action);
	//	req.getSession().setAttribute("competelist", db.getCompetelist(m.get(action)));
		req.getSession().setAttribute("weekcompetelist", db.getCompetelist("3"));
		req.getSession().setAttribute("monthcompetelist", db.getCompetelist("2"));
		req.getSession().setAttribute("seasoncompetelist", db.getCompetelist("1"));
		req.getSession().setAttribute("yearcompetelist", db.getCompetelist("0"));
		
		req.getSession().setAttribute("winnerlist", db.getWinnerList(m.get(action)));
		req.getRequestDispatcher("/honorRoll0.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String choosecompete=req.getParameter("choosecompete");
		String competename=req.getParameter("competename");
		//String action=(String) req.getSession().getAttribute("action");
		if(choosecompete!=null){
			Dbcon db=new Dbcon();
			String a=db.getAction(choosecompete);
			Map m=new HashMap<String, String>();
			m.put("0", "year");
			m.put("1", "season");
			m.put("2", "month");
			m.put("3", "week");
//			req.getSession().setAttribute("action", m.get(a));
//			req.getSession().setAttribute("competelist", db.getCompetelist(a));
			req.getSession().setAttribute("winnerlist", db.getWinnerList0(choosecompete));
			req.setAttribute("competename", URLEncoder.encode(competename, "utf-8"));
			req.getRequestDispatcher("/p_winner.jsp").forward(req, resp);	
			System.out.println("winnerÊý£º"+db.getWinnerList0(choosecompete).size());
		}
			
	}
    
}
