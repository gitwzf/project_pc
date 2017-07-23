<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.net.URLDecoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <meta name="viewport" content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" />
    <script src="js/jquery-1.9.1.min.js"></script>
    <link href="css/css.css" rel="stylesheet" />

    <script src="js/jslib.js"></script>
    <script src="js/js.js"></script>
</head>
<body>
    <div class="contain">
       <a href="${ad.url }" class="clicktimes" adid="${ad.id}"><img class="head_img" src="${ad.pic_url }" /></a>
        <a class="title" href="javascript:void(0)">我的应聘</a>
        <div class="speart"><!--空白分割--></div>
        <c:forEach items="${sendplist}" var="sendp">
        <a href="./Sendposition?type=detail&positionid=${sendp.id}">
            <section class="list1 list2 list3 list4">
                <div class="list-wapper">
                    <h3>${sendp.position_name }<span class="send-time">(投递时间：${sendp.add_tim })</span></h3>
                    <p class="list-item">企业名称：${sendp.companyname }</p>
                    <p class="list-item">地点：${sendp.address }</p>
                    <p class="list-item">截止：${sendp.end_tim}</p>
                    <p class="list-item">薪资：<span class="color-red">${sendp.salary }</span></p>
                </div>
            </section>
        </a>
        </c:forEach>
        <c:if test="${empty sendplist}">无应聘过的职位！</c:if>
    </div>
     <jsp:include page="_footer.jsp"/>
</body>
</html>
