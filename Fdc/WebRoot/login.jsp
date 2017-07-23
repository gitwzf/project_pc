<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登陆</title>
    
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
<script type="text/javascript" src="js/jquery1.8.2.min.js"></script>
<script type="text/javascript">
 function isok(){
	   if($('#tel').val()==''||isNaN($('#tel').val())||$('#tel').val().length!=11){alert('请输入正确的手机号');return false;}
	   if($('#name').val()==''){alert('请输入姓名');return false;}
	   return true;
	   }
</script>
  </head>
  
  <body>
  <div class="container">
		<div class="wrapper">
			<form action="./login.do?type=login" method="post">
				<div class="enroll-form">
					<div class="item"><label>姓名：</label><input type="text" name="name"/></div>
					<div class="item"><label>手机：</label><input type="text" name="tel"/></div>
					<div class="btn"><input type="submit" value="登陆" onclick="return isok()"/></div>
					<div style="text-align: right"><a href="./register.jsp">点击注册</a></div>
				</div>
			</form>
		</div>
	</div>
  </body>
</html>
