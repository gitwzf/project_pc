<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'logon.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	
	function check(){
		var username=document.logonform.username.value;
		var password=document.logonform.password.value;
		if(username!="" && password!="")
			return true;
		else{
			alert('用户名或密码不能为空');
			return false;
		}
			
	}
	
	</script>

  </head>
  
  <body>
    	<form action="logon.do" method="post" name="logonform">
    		username:<input type="text" name="username"><br/>
    		password:<input type="password" name="password"><br/>
    		<!--  
    		<input type="submit" value="登录" onclick="return check()"><br/>
    		-->
    		<input type="submit" value="登录" ><br/>
    	</form>
  </body>
</html>
