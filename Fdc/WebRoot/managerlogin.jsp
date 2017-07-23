<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员登陆</title>
    
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
  <div class="container">
		<div class="wrapper">
			<form action="./login.do?type=managerlogin" method="post">
				<div class="enroll-form">
					<div class="item"><label>姓名：</label><input type="text" name="name"/></div>
					<div class="item"><label>手机：</label><input type="text" name="tel"/></div>
					<div class="btn"><input type="submit" value="登陆"/></div>
				</div>
			</form>
		</div>
	</div>
  </body>
</html>
