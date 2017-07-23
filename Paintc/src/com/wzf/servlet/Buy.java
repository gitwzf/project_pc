package com.wzf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Indexform;
import com.wzf.model.Manageruser;
import com.wzf.model.Vip;

public class Buy extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//年会+加油包
		Dbcon db=new Dbcon();
		req.getSession().setAttribute("vip", db.getVip());
		Manageruser man=(Manageruser) req.getSession().getAttribute("manager");
		if(man!=null)man.setIsyearvip(db.isYearvip(man.getId()));
		req.getSession().setAttribute("manager", man);
		req.getRequestDispatcher("/buy.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String vid=req.getParameter("vid");
		String aa="00";
		Manageruser man=(Manageruser) req.getSession().getAttribute("manager");
		if(vid!=null&&man!=null){ //执行购买
			Dbcon db=new Dbcon();
			Vip v=db.getVip(vid);
			String buyid=getBuyid();
			boolean flag=db.addBasebill(buyid,"mdfhjd@gmail.com",vid,man.getId(),v.getName(),"1",v.getCharge());
			if(flag){req.getSession().setAttribute("indexform", new Indexform("mdfhjd@gmail.com",buyid,v.getName(),v.getCharge()));
			req.getRequestDispatcher("/index.jsp").forward(req, resp);}
			else{//初步购买数据插入出错
				req.getSession().setAttribute("vip", db.getVip());
				req.getRequestDispatcher("/buy.jsp").forward(req, resp);
			}
			
		}
		
		
	}
	
	public String getBuyid(){
		String id=new SimpleDateFormat("yyMMdd").format(new Date().getTime());
		String idid[]={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		int j=0;
		for(int i=0;i<12;i++){
			 j=(int) (Math.random()*36);
			id=id+idid[j];
		}
		return id;
	}
          
}
