<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>快乐学艺书画排行榜</title>
<script src="js/jquery-1.7.2.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="css/nav.css"/>
<link rel="stylesheet" type="text/css" href="css/index.css" media="all"/>
<script type="text/javascript">
   function addwinners(obj,obj0){
  // alert(obj);
     $.post("HonorRoll",{choosecompete:obj,competename:obj0},function(data){
           $('#winners').html(data);
      });
   
   }

  $(function(){
  
  });

</script>

<style>
.rank-list{
	position:relative;
}
.rank-list .rank-sapn{
	display:block;
	text-align:left;
	font-size:16px;
	height:40px;
	line-height:45px;
	padding-left:20px;
	
	color:#fff;
}
.rank-list ul{
	display:none;
	
	paddint-top:10px;
}
.rank-list ul li{
	text-align:left;
	padding-left:20px;
	height:40px;
	line-height:40px;
	font-size:14px;
}
.rank-list ul li a{
	display:block;
	font-size:14px;
}
.rank-list ul li:nth-child(even){
	background:#eee;
}
.rank-list{
	position:relative;
}
.r-icon{
	position:absolute;
	right:20px;
	top:7px;
	font-size:18px;
	font-weight:bolder;
	color:#fff;
}
.wrap{
	background:#fff;
}
</style>
</head>
<body style="position:relative">
	<div class="wrap">
		<div class="rank-list">
			<span class="rank-sapn" style="background-color:#00c3d9;">周榜单</span><span class="r-icon">+</span>
			<ul class="rank-ul">	
				<c:forEach items="${weekcompetelist}" var="weekcompete">
                 <li><a href="javascript:addwinners(${weekcompete.id },'${weekcompete.name }');">${weekcompete.name }</a><span class="t-icon"><span></li>
                </c:forEach>
                <c:if test="${empty weekcompetelist}"><li>周榜单还没公布！</li></c:if>
			<ul>
		</div>	
		<div class="rank-list">
			<span class="rank-sapn" style="background-color:#00d48f;">月榜单</span><span class="r-icon">+</span>
			<ul class="rank-ul">	
				<c:forEach items="${monthcompetelist}" var="monthcompete">
                 <li><a href="javascript:addwinners(${monthcompete.id },'${monthcompete.name }');">${monthcompete.name }</a></li>
                </c:forEach>
                <c:if test="${empty monthcompetelist}"><li>月榜单还没公布！</li></c:if>
			<ul>
		</div>	
		<div class="rank-list">
			<span class="rank-sapn" style="background-color:#008884;">季榜单</span><span class="r-icon">+</span>
			<ul class="rank-ul">	
				<ul class="rank-ul">	
				<c:forEach items="${seasoncompetelist}" var="seasoncompete">
                 <li><a href="javascript:addwinners(${seasoncompete.id },'${seasoncompete.name }');">${seasoncompete.name }</a></li>
                </c:forEach>
                <c:if test="${empty seasoncompetelist}"><li>季榜单还没公布！</li></c:if>
			<ul>
			<ul>
		</div>
		<div class="rank-list">
			<span class="rank-sapn"  style="background-color:#f3c300;">年榜单</span><span class="r-icon">+</span>
			<ul class="rank-ul">	
				<c:forEach items="${yearcompetelist}" var="yearcompete">
                 <li><a href="javascript:addwinners(${yearcompete.id },'${yearcompete.name }');">${yearcompete.name }</a></li>
                </c:forEach>
                <c:if test="${empty yearcompetelist}"><li >年榜单还没公布！</li></c:if>
			<ul>
		</div>
	</div>
	<div id="winners" style="font-size: 1.6em;padding-bottom: 70px;"></div>
<a href="./register00.jsp" style="position: absolute;right: 15px;bottom: 50px;font-size:14px;">我也要参加</a>
	<jsp:include page="menuall.jsp" flush="true"/>
</body>

<script>
    $(document).ready(function () {
		$('.rank-list').on('click',function(){
			$('.rank-ul',this).toggle();
			$('.rank-ul',$(this).siblings()).css('display','none');
			
		})

    });
</script>
</html>
