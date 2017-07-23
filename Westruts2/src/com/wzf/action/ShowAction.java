package com.wzf.action;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.wzf.method.ConvertTheOther;
import com.wzf.method.ReqResp;

public class ShowAction {
	
	public String showmap(){
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpServletResponse resp=ServletActionContext.getResponse();
		String length=req.getParameter("length");
		if(length!=null){
		String name[]=req.getParameterValues("name");
		String address[]=req.getParameterValues("address");
		String location_x[]=req.getParameterValues("location_x");
		String location_y[]=req.getParameterValues("location_y");
		String query=req.getParameter("query");
		String str="";
	if(name!=null){
		req.setAttribute("query",ConvertTheOther.EncodeUtf8(query));
		for(int i=0;i<name.length;i++)	
		{  
			str+="var point = new BMap.Point("+location_y[i]+","+location_x[i]+");\n"+
			"mp.centerAndZoom(point, 18);\n"+
			"var marker"+i+" = new BMap.Marker(point);\n"+
			"mp.addOverlay(marker"+i+");\n"+
			"var opts = {\n"+
			"width : 150, \n"+
			"height: 105,\n"+
			"title : \""+name[i]+"\" \n"+
			"}\n"+
			"var infoWindow"+i+" = new BMap.InfoWindow(\""+address[i]+"\", opts);\n"+
			"mp.openInfoWindow(infoWindow"+i+", mp.getCenter()); \n"+
			"marker"+i+".addEventListener('click',function(){ marker"+i+".openInfoWindow(infoWindow"+i+");}); \n"; 
		}
		req.setAttribute("str", ConvertTheOther.EncodeUtf8(str));
		 resp.setCharacterEncoding("utf-8");
		ReqResp.forward(req, resp,"/showmap.jsp");
		}
		}else ReqResp.redirect(resp,"http://wap.baidu.com/");
		return null;
	}

}
