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
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="css/nav.css"/>
<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/windows.js"></script>
<script type="text/javascript">
   $(function(){
   $('.active').removeClass("active");
        if('${action}'==''||'${action}'=='week'){
            $('.week').addClass("active");
        }
         else  if('${action}'=='month'){
         $('.month').addClass("active");}
   		else  if('${action}'=='season'){
   		$('.season').addClass("active");}
   		else  if('${action}'=='year'){
   		$('.year').addClass("active");}
   
   $('.week').click(function(){
     location.href="./HonorRoll?action=week";
   });
   $('.season').click(function(){
     location.href="./HonorRoll?action=season";
   });
   $('.month').click(function(){
     location.href="./HonorRoll?action=month";
   });
   $('.year').click(function(){
     location.href="./HonorRoll?action=year";
   });
   
   $('.choosecompete').click(function(){
        if($(this).val()!="")$('#competeform').submit();
   }).blur(function(){
        if($(this).val()!="")$('#competeform').submit();
   });
   
   });
   </script>
<!--<link rel="stylesheet" type="text/css" href="css/ipad.css" media="only screen and (max-device-width:1024px)">-->
</head>

<body>
<ul class="honornav">
    <li class="week active">周榜单</li>
    <li class="month">月榜单</li>
    <li class="season">季榜单</li>
    <li class="year">年榜单</li>
</ul>
<div class="container">
    <div class="content">
 <c:if test="${empty winnerlist}"><c:if test="${!empty action}"><c:if test="${action=='week'}">周</c:if><c:if test="${action=='month'}">月</c:if><c:if test="${action=='season'}">季</c:if><c:if test="${action=='year'}">年</c:if></c:if>榜单还未公布！</c:if>
    <c:if test="${!empty winnerlist}">
       <c:forEach items="${winnerlist}" var="winner">
    	<section>
    	<a href="Detailwork?workid=${winner.picid}&competename=${winner.competename}&place=${winner.place}">
        	<img src="${winner.url}" class="img1"/>
        	<ul class="left"><li class="title">《${winner.title}》</li><li class="author ccc">${winner.name}</li></ul>
            <ul class="right"><li class="award"><c:if test="${winner.degree==1}">优胜奖</c:if><c:if test="${winner.degree==2}">入围奖</c:if></li><li class="score">排名：${winner.place}</li><li class="score ccc">${winner.score}分</li></ul>
       </a>
        </section>
        </c:forEach>
       </c:if> 
            <nav class="top">
                <span  style="display: block;margin: auto;width: 80px;">
                <form action="HonorRoll" method="post" id="competeform">
                <select class="input-box choosecompete" name="choosecompete" style="border:none;width:90px;margin-left:20px;margin:auto; background:none;height:25px; text-align:right;">
                <option selected="selected" value=""><c:if test="${action=='week'}">周</c:if><c:if test="${action=='month'}">月</c:if><c:if test="${action=='season'}">季</c:if><c:if test="${action=='year'}">年</c:if>榜</option>
                <c:forEach items="${competelist}" var="compete">
                <option value="${compete.id }">${compete.name }</option>
                </c:forEach>
            </select>
            </form>
            
            </span>
       </nav>
       
    </div>
   <!--  <jsp:include page="menu.jsp" flush="true"/>   -->
</body>
</html>
