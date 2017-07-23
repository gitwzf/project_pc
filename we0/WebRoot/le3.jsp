<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> 微信公众平台自助引擎 -  Powered by WE7.CC</title>
<meta name="keywords" content="微信,微信公众平台" />
<meta name="description" content="微信公众平台管理系统。" />
<link type="text/css" rel="stylesheet" href="./resource/style/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="./resource/style/font-awesome.css" />
<link type="text/css" rel="stylesheet" href="./resource/style/common.css?v=1382944677" />
<script type="text/javascript" src="./resource/script/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./resource/script/bootstrap.js"></script>
<script type="text/javascript" src="./resource/script/common.js?v=1382944677"></script>
<script type="text/javascript" src="./resource/script/emotions.js"></script>
<script type="text/javascript">
cookie.prefix = 'f1c8_';
</script>
<!--[if IE 7]>
<link rel="stylesheet" href="./resource/style/font-awesome-ie7.min.css">
<![endif]-->
<!--[if lte IE 6]>
<link rel="stylesheet" type="text/css" href="./resource/style/bootstrap-ie6.min.css">
<link rel="stylesheet" type="text/css" href="./resource/style/ie.css">
<![endif]-->
</head>
<body >
	<script type="text/javascript" src="./resource/script/jquery.zclip.min.js"></script>
	<ul class="nav nav-tabs">
		<!--ul class="pull-right unstyled">
			<li><a href="account.php?act=post&">添加公众号</a></li>
			<li class="active"><a href="account.php?act=display&">管理公众号</a></li>
		</ul-->
		<li><a href="le33.jsp">添加公众号</a></li>
		<li class="active"><a href="le3Serv">管理公众号</a></li>
	</ul>
	<div class="main">
		<div class="account">
		<c:forEach items="${sessionScope.pubidarray}" var="pubid">
			<div class="navbar-inner thead">
				<h4>
					<span class="pull-right"><a onclick="return confirm('删除帐号将同时删除全部规则及回复，确认吗？');return false;" href="account.php?act=delete&id=7">删除</a><a href="le333Serv?name=${pubid.name}&url=${pubid.apiurl}&token=${pubid.token}&wxname=${pubid.wxname}&pass=${pubid.pass}&appid=${pubid.appid}&secret=${pubid.appsecret}">编辑</a><a href="account.php?act=switch&id=7">切换</a></span>
					<span class="pull-left">${pubid.name} <small>（微信号：${pubid.wxname}）</small></span>
				</h4>
			</div>
			<div class="tbody">
				<div class="con">
					<div class="name pull-left">API地址</div>
					<div class="input-append pull-left" id="api_7">
						<input id="" type="text" value="${pubid.apiurl}">
						<button class="btn" type="button">复制</button>
					</div>
				</div>
				<div class="con">
					<div class="name pull-left">Token</div>
					<div class="input-append pull-left" id="token_7">
						<input id="" type="text" value="${pubid.token}">
						<button class="btn" type="button">复制</button>
					</div>
				</div>
			</div>
			</c:forEach>
			<script>
				$(function() {
					$("#api_7 button").zclip({
						path:'./resource/script/ZeroClipboard.swf',
						copy:$('#api_7 input').val()
					});
					$("#token_7 button").zclip({
						path:'./resource/script/ZeroClipboard.swf',
						copy:$('#token_7 input').val()
					});
				});
			</script>
					</div>
    </div>
	
	<div class="emotions" style="display:none;"></div>
</body>
</html>