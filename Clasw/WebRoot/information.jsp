<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>资讯信息</title>
		<link href="css/base.css" rel="stylesheet" type="text/css" />
		<link href="css/information.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div id="container">
			<div id="banner">
		<!--  	<a href="javascript:" class="collect" onClick="jscript:window.external.AddFavorite(document.location.href,document.title);"></a>
		-->	</div>
			<div id="nav">
				<ul>
					<li><a href="./index.jsp">首页</a></li>
					<li><a href="./course0.jsp">我的课程</a></li>
					<li><a href="./information1.jsp">课程介绍</a></li>
					<li><a href="./information2.jsp">报名须知</a></li>
					<li><a href="./message0.jsp">留言板</a></li>
				</ul>
			</div>
			<div class="title">
				<h3>${annouce.title}</h3>
				<span>最后编辑：${annouce.timPub}</span>
			</div>
			<div class="clear"></div>
			<div class="text">
				<p>${annouce.content}</p>
			</div>
		</div>
	</body>
</html>