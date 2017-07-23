<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>我的课程</title>
		<link href="css/base.css" rel="stylesheet" type="text/css" />
		<link href="css/course.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div id="container">
			<div id="banner">
			<!--  <a href="javascript:" class="collect" onClick="jscript:window.external.AddFavorite(document.location.href,document.title);"></a>
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
			<div class="remind"><p><span>您已成功报名，以下是您的全部课程，请您按时参加，未能准时参加请提前电话联系我们的工作人员！</span></p></div>
			<div class="list">
				<ul>
					<li class="thead">
						<dd class="rowone">期数</dd>
						<dd class="rowtow">组别</dd>
						<dd class="rowthree">上课时间</dd>
						<dd class="rowfour">上课地点</dd>
						<dd class="rowfive">教员</dd>
						<dd class="rowsix">联系方式</dd>
						<dd class="rowseven">状态</dd>
					</li>
					<c:forEach items="${ruserlist}" var="ruser">
					<li class="tbody">
						<dd class="rowone"><c:if test="${ruser.period==1}">第一期</c:if><c:if test="${ruser.period==2}">第二期</c:if><c:if test="${ruser.period==3}">第三期</c:if></dd>
						<dd class="rowtow"><c:if test="${ruser.group==1}">少年组（8-14周岁）</c:if><c:if test="${ruser.group==2}">初中组（14-16周岁）</c:if><c:if test="${ruser.group==3}">成人组</c:if><c:if test="${ruser.group==4}">港北组</c:if></dd>
						<dd class="rowthree">${ruser.tim_beg}-${ruser.tim_end}</dd>
						<dd class="rowfour">&nbsp;${ruser.class_addr}&nbsp;</dd>
						<dd class="rowfive">&nbsp;${ruser.class_teacher}&nbsp;</dd>
						<dd class="rowsix">&nbsp;${ruser.tel_teacher}&nbsp;</dd>
						<dd class="rowseven"><c:if test="${ruser.status>2}"><a href="./course0.jsp" class="finish">已结束</c:if><c:if test="${ruser.status<=2}"><a href="./course0.jsp?classid=${ruser.classid}" class="notstart">取消报名</c:if></a></dd>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</body>
</html>