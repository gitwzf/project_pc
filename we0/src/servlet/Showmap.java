package servlet;

import java.io.IOException;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Smap;

public class Showmap extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String length=req.getParameter("length");
		System.out.println("111");
		if(length!=null){
			System.out.println("222");
		String name[]=req.getParameterValues("name");
		String address[]=req.getParameterValues("address");
		String location_x[]=req.getParameterValues("location_x");
		String location_y[]=req.getParameterValues("location_y");
		String query=req.getParameter("query");
		if(query!=null)query=new String(query.getBytes("iso8859-1"),"utf-8");
		String str="";
	if(name!=null){
		for(int i=0;i<name.length;i++)	
		{  
//			System.out.println("name="+new String(name[i].getBytes("iso8859-1"),"utf-8"));
//			System.out.println("address="+new String(address[i].getBytes("iso8859-1"),"utf-8"));
//			System.out.println("x="+location_x[i]);
//		 System.out.println("y="+location_y[i]);
			str+="var point = new BMap.Point("+location_y[i]+","+location_x[i]+");\n"+
			"mp.centerAndZoom(point, 18);\n"+
			"var marker"+i+" = new BMap.Marker(point);\n"+
			"mp.addOverlay(marker"+i+");\n"+
			"var opts = {\n"+
			"width : 150, \n"+
			"height: 105,\n"+
			"title : \""+new String(name[i].getBytes("iso8859-1"),"utf-8")
			+"\" \n"+
			"}\n"+
			"var infoWindow"+i+" = new BMap.InfoWindow(\""+new String(address[i].getBytes("iso8859-1"),"utf-8")
			+"\", opts);\n"+
			"mp.openInfoWindow(infoWindow"+i+", mp.getCenter()); \n"+
			"marker"+i+".addEventListener('click',function(){ marker"+i+".openInfoWindow(infoWindow"+i+");}); \n"; 
		}
		 resp.setCharacterEncoding("utf-8");
		Writer out = resp.getWriter();
       out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n"+
"<html>\n"+
"<head>\n"+
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"+
"<meta name=\"viewport\" content=\"initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no\">\n"+
"<title>"+query+"</title>\n"+
"<style type=\"text/css\">/*<![CDATA[*/\n"+
"body{margin:0;padding:0;font-family:Times New Roman, serif}\n"+
"p{margin:0;padding:0}\n"+
"html,body{\n"+
 "   width:100%;\n"+
  "  height:100%;\n"+
"}\n"+
"#map_container{height:100%; border: 1px solid #999;height:100%;}\n"+
"@media print{\n"+
 " #notes{display:none}\n"+
 " #map_container{margin:0}\n"+
"}\n"+
".BMap_bubble_content {\n"+
 "   font-size: 16px;\n"+
  "  line-height: 16px;\n"+
"}\n"+
"/*]]>*/</style>\n"+
"<script type=\"text/javascript\" src=\"http://api.map.baidu.com/api?v=1.4\"></script>\n"+
"<script type=\"text/javascript\" src=\"http://api.map.baidu.com/getscript?v=1.4&ak=&services=&t=20130607025020\"></script>\n"+
"</head>\n"+
"<body>\n"+
"<div style=\"overflow: hidden; position: relative; z-index: 0; background-color: rgb(243, 241, 236); color: rgb(0, 0, 0); text-align: left;\" id=\"map_container\">\n"+
"</div>\n"+
"<script type=\"text/javascript\">\n"+
"/*<![CDATA[*/\n"+
"var mp = new BMap.Map(\"map_container\",{\n"+
"enableHighResolution: true \n"+
"});\n"+
 str+
"mp.enableInertialDragging();\n"+
"mp.enableScrollWheelZoom(); \n"+
"</script>\n"+
"</body></html>");
       out.flush();
		out.close();
		
		}
		}else resp.sendRedirect("http://wap.baidu.com/");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
      
}
