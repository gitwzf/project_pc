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
			<!-- 	<a href="javascript:" class="collect" onClick="jscript:window.external.AddFavorite(document.location.href,document.title);"></a>
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
				<h3>报名须知</h3>
				<span></span>
			</div>
			<div class="clear"></div>
			<div class="text">
<p>学习班次：<br/> 玉环游泳池少年组（8:00&mdash;9:10）；（9:30&mdash;10:40）； 
                               （13:00&mdash;14:10）；（14:30&mdash;15:40）；  <br/>
初中组（18:30&mdash;19:40）       <br/>
成人（初中组）（20:00&mdash;21:00）  <br/>
港北游泳池组（8:30&mdash;9:40）（9:50&mdash;11:00） <br/>
 
&nbsp;&nbsp;&nbsp;&nbsp;学习期次及人次：<br/> 少年组第一期（400人）少年组第二期（400人） 
少年组第三期（400人） <br/>
      初中组第一期（80人）初中组第二期（80人） 
初中组第三期（80人） <br/>
成人组（300人含初中） <br/>
港北组第一期（200人）港北组第二期（200人） 
港北组第三期（200人） <br/> <br/>
   
预报名地点： 县体育馆内办公室 
             港北游泳池  <br/>
开课地点：  玉环游泳池（玉城组） 
            港北游泳池（港北组）玉环中学（提高班） 
 <br/>
咨询电话：87211692  87212692 联系人：陈老师 </p>
			</div>
		</div>
	</body>
</html>