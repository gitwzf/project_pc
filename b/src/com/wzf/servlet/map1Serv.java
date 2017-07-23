package com.wzf.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.wzf.method.Dbconn;
import com.wzf.pubvari.Variable;

public class map1Serv extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doGet(req, resp);
	}
	
	public void init(){
		Dbconn db=new Dbconn();
		db.setDatabase("qjwb_qtwy");
		Variable.array_province=db.getProvinceList(); 
		Variable.str_province=toJsonArray(Variable.array_province);
	}
	
	public String toJsonArray(ArrayList array){
		return  JSONArray.fromObject(array).toString();
	}

}
