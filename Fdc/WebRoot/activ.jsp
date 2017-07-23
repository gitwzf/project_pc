<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.fdc.pubvari.*" %>
<%@ page import="com.fdc.model.*" %>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>活动列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/base.css" media="all">
<link rel="stylesheet" type="text/css" href="css/enroll.css" media="all">
  </head>

  <body>
    <%
   {
		List<Map.Entry<String, Activity>> infoIds =
	        new ArrayList<Map.Entry<String, Activity>>(Variable.map_activ.entrySet());
	    Collections.sort(infoIds, new Comparator<Map.Entry<String, Activity>>() {
	        public int compare(Map.Entry<String, Activity> o1, Map.Entry<String, Activity> o2) {
	            return (o1.getValue()).compareTo(o2.getValue());
	        }
	    }); 
	    
	    out.print("<div class=container><div class=wrapper>");
	    for(Map.Entry<String, Activity>map:infoIds){
	    	Activity a=map.getValue();
	    	out.print("<div style=margin-bottom:10px>");
	    	out.print("活动"+a.getId()+"："+a.getTitle()+"<br>");
	    	//out.print("<img src='http://xiaowangzi.touclick.com/file/image/painting/shijiale.jpg'/><br>");
	    	out.print("报名时间：<br>"+a.getBegtim()+"——"+a.getEndtim()+"<br>");
	    	out.print("详情："+a.getConcate()+"<br>");
	    	
	    	Date d1 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).parse(a.getBegtim());
	        Date d2 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).parse(a.getEndtim());
	    	if(d1.before(new Date())&&d2.after(new Date()))//活动中
	    	{
	    	if(!Variable.map_apply.containsKey(((User)request.getSession().getAttribute("user")).getId()+";"+a.getId()))
	    	out.print("<a href=./login.do?type=apply&action=yes&actid="+a.getId()+"><div class=btn><input type=button value=报名></div></a>");
	    	else
	    	out.print("<a href=./login.do?type=apply&action=no&actid="+a.getId()+"><div class=btn><input type=button value=取消报名></div></a>");
	    	}
	    	else if(d1.after(new Date()))//未开始
	    	   out.print("<center>——报名未开始——</center>");
	    	   else if(d2.before(new Date()))//已结束
	    	   out.print("<center>——报名已结束——</center>");
	    	
	    	out.print("</div>");
	    }
	    out.print("</div></div>");
	}%>
  </body>
</html>
