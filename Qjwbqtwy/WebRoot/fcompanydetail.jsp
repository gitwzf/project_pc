<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.net.URLDecoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
        <div class="uc-top">
            <img src="${fcompany.pic_url}" class="avatar" />
            <h3 class="uc-name">${fcompany.name }</h3>
        </div>
        <div class="uc-top uc-content">
            <h3 class="uc-name">企业介绍</h3>
            <p>${fcompany.info }</p>
        </div>
        <a class="but" href="./Fair?fairid=${FAIRID }">返回</a>
    </div>
     <jsp:include page="_footer.jsp"/>
</body>
</html>
