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
				<h3>夏季游泳培训班电脑摇号招生通告</h3>
				<span></span>
			</div>
			<div class="clear"></div>
			<div class="text">
				<p>为满足全县广大群众的实际需求，提高自身身体素质、增强免疫力，并在活动中学到一技之长，我中心现决定组织开办夏季游泳培训班。
为体现公平、公开、公正的原则，消除往年排队报名的安全隐患，今年培训报名采取电脑摇号方式。报名分三个阶段：报名登记阶段（5月20日8:30&mdash;6月15日17:30）；电脑摇号公示阶段（6月16日）；报名阶段（6月17日&mdash;6月19日）。请携带本人身份证或户口簿于体育中心办公室预报名，或发送姓名、联系电话、身份证号码至yhtyzx@sina.cn网上报名。网上报名截至2013年6月12日。一证一号，学员出生年月截至2005年6月30日前。<strong>注：港北片学员请于港北游泳池报名处登记。身高1.5米以上请报晚班。初中组、成人组学习时间均安排在晚上。
</strong></p>
			</div>
		</div>
	</body>
</html>