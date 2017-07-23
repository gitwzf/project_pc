package com.wzf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Mapmodel;
import com.wzf.model.User;
import com.wzf.pubvari.Variable;

public class Resume extends HttpServlet {
     Variable vari=new Variable();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User u = (User) req.getSession().getAttribute("user");
		if(u!=null){
		Map<String,String> mapyears=vari.map_years,mapdegree=vari.map_degree,mapsalary=vari.map_salary;
		ArrayList<Mapmodel> array1=new ArrayList<Mapmodel>(),array2=new ArrayList<Mapmodel>(),array3=new ArrayList<Mapmodel>();
		Mapmodel m=null;
		for(String key:mapyears.keySet()){
			m=new Mapmodel();
			m.setKey(key);
			m.setValue(mapyears.get(key));
			array1.add(m);
		}
		for(String key:mapdegree.keySet()){
			m=new Mapmodel();
			m.setKey(key);
			m.setValue(mapdegree.get(key));
			array2.add(m);
		}
		for(String key:mapsalary.keySet()){
			m=new Mapmodel();
			m.setKey(key);
			m.setValue(mapsalary.get(key));
			array3.add(m);
		}
		req.getSession().setAttribute("YEARS", array1);
		req.getSession().setAttribute("DEGREE", array2);
		req.getSession().setAttribute("SALARY", array3);
		
		Dbcon db=new Dbcon();
		req.getSession().setAttribute("myresu", db.getResume(u.getId()));
		}
		req.getRequestDispatcher("./myresume.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String real_nam = req.getParameter("real_nam");
		String gender = req.getParameter("gender");
		String birthday = req.getParameter("birthday");
		String salary = req.getParameter("salary");
		String expectsalary = req.getParameter("expectsalary");
		String school = req.getParameter("school");
		String gradu_tim = req.getParameter("gradu_tim");
		String years = req.getParameter("years");
		String degree = req.getParameter("degree");
		String major = req.getParameter("major");
		String address = req.getParameter("address");
		String self_evaluate = req.getParameter("self_evaluate");
		String telphone = req.getParameter("telphone");
		String email = req.getParameter("email");

//		if(real_nam!=null)real_nam=new String(real_nam.getBytes("iso-8859-1"),"utf-8");
//		if(school!=null)school=new String(school.getBytes("iso-8859-1"),"utf-8");
//		if(major!=null)major=new String(major.getBytes("iso-8859-1"),"utf-8");
//		if(address!=null)address=new String(address.getBytes("iso-8859-1"),"utf-8");
//		if(self_evaluate!=null)self_evaluate=new String(self_evaluate.getBytes("iso-8859-1"),"utf-8");
		
		User u = (User) req.getSession().getAttribute("user");
		// Ìí¼Ó
		Dbcon db = new Dbcon();
		boolean flag=false;
		if (u != null)//Ìí¼Ó¡¢ÐÞ¸Ä
			flag=db.addResume(u.getId(), u.getOpenid(), real_nam, birthday, gender,
					telphone, email, years, school, gradu_tim, degree, major,
					salary, expectsalary, address, self_evaluate);
          req.getSession().setAttribute("user", db.getUser(u.getOpenid()));
		PrintWriter pw=resp.getWriter();
		pw.write(flag?"11":"00");
		pw.flush();
		pw.close();
	}

}
