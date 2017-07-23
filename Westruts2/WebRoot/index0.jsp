<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    	<form action="test/user_logon.action" method="post">
    		username:<input type="text" name="logonForm.username"><br/>
    		age:<input type="text" name="logonForm.age"><br/>
    		password:<input type="password" name="logonForm.password"><br/>
    		<input type="submit" value="logon">
    	</form>
    	
    	<a href="user_add.action">add</a><br/>
    	<a href="user_delete.action">delete</a><br/>
    	<a href="user_select.action">select</a><br/>
 <hr/>
    	<a href="user2!add.action">add</a><br/>
    	<a href="user2!delete.action">delete</a><br/>
    	<a href="user2!select.action">select</a><br/>
  </body>
</html>
