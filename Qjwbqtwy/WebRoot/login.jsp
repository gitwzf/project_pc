<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="css/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<title>生活帮实习基地</title>
<script type="text/javascript">
	var openId = "";
	var nickname = "";
	var headimgurl = "";
</script>
</head>
  
  <body>
    <div id="main">
		<div id="shhead">
	<!-- 		<a href="javascript:history.back(-1)" class="float_left"><img src="/jsp/mobile/images/back_holo_light.png" width="30px;" height="30px;"> </a>
	-->
			<div class="title">用户绑定</div>
			<a href="http://115.236.173.81/mobileInPage!register.htm"><span class="float_right">注册</span>
			</a>
		</div>
		<form id="registForm" method="post">
			<div id="login">
				<div id="login_swapper">
					<div id="login_name">
						<img src="images/user_off.png" width="20px;" height="20px;"> 
						<input type="text" id="khDlm" class="itext notnull" placeholder="用户名/会员名/邮箱">
					</div>
					<div id="login_id">
						<img src="images/id.png" width="20px;" height="20px;">
						<input type="password" id="khDlmm" class="itext notnull" placeholder="请输入密码">
					</div>
				</div>
			</div>
			<br>
			<div id="errorMess" name="errorMess"></div>
			<div class="margin_auto90">
				<button id="login_push" type="button" onclick="onRegBtn()">用户绑定</button>
			</div>
		</form>
		<div id="forget">如果你还不是我们的会员，<a href="http://115.236.173.81/mobileInPage!register.htm">点击这里进行注册</a></div> 
	</div>
  </body>
</html>
