<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript">
$(function(){  
   if('${info}'!='')alert('${info}');
   $('.btn5').click(function(){
      //location.href="./Register0";
	   location.href="./register00.jsp";
   });  
   });
</script>
</head>

<body>
    <div class="container">
        <form id="loginForm" class="login-form" action="Login" method="post">
        <div  style="margin:20px auto 10px;width:150px;">
    		<img src="images/photo1.png" style="width:120px;margin:auto;"/>
    	</div>
            <div class="input-box">
            	<label>手机号码</label>:
            	<input type="text" required id="username" name="username" placeholder="手机号" value="${us}"/>
            </div>
            <div class="input-box">
            	<label style="letter-spacing:15px";>密码</label>:
            	<input type="password" required id="password"name="password" placeholder="密码" />
            </div>
            <div class="button btn5">
            	<input type="button" name="" value="注册" /> 
            </div>
            <div class="button btn4 right">
            	<input type="submit" id="username" name="" value="登录" />
            </div>
            <br/>
        <!--   <a href="./retrieve_password.jsp" class="right">忘记密码？</a>  
            <a href="./aboutsignup.jsp" class="right " style="margin-right: 20px;">报名须知</a> -->  
            <a href="./aboutsignup.jsp" class="right">比赛说明</a> 
        </form>
    </div>
</body>
</html>
