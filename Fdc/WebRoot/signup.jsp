<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>签到</title>
    
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
  <c:if test="${apply.issign==0}">
  <a href="./login.do?type=signup"><div class="btn"><input type="button" value="点击这里签到"/></div></a></c:if>
  <c:if test="${apply.issign!=0}">
    <label>签到成功.${user.name }</label>
    </c:if>
     <br>
  </body>
</html>
