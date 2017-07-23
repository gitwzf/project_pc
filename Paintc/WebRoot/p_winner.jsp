<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.net.URLDecoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div style="text-align:center;padding: 10px 0;"><%=request.getAttribute("competename")==null?"":URLDecoder.decode((String)request.getAttribute("competename"),"utf-8")%></div>
       <c:forEach items="${winnerlist}" var="winner">
    	<section>
    	<a href="Detailwork?workid=${winner.picid}&competename=${winner.competename}&place=${winner.place}">
        	<img src="${winner.url}" class="img1"/>
        	<ul class="left"><li class="title">《${winner.title}》</li><li class="author ccc">${winner.name}</li></ul>
            <ul class="right"><li class="award"><c:if test="${winner.degree==1}">优胜奖</c:if><c:if test="${winner.degree==2}">入围奖</c:if></li><li class="score">排名：${winner.place}</li><li class="score ccc">${winner.score}分</li></ul>
       </a>
        </section>
        </c:forEach>
   