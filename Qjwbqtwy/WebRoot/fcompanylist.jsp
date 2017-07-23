<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
        <div class="form-wapper">
            <h4 class="form-title">${college.name }</h4>
            <span class="form-item">
                <span class="item-key">时间</span>
                <span>${college.beg_day } 至 ${college.end_day }</span>
            </span>
            <c:if test="${!empty college.info }">
            <span class="form-item">
                <span class="item-key">简介</span>
                <p class="form-itme-p">${college.info }</p>
            </span>
            </c:if>
        </div>
        <a class="title" href="javascript:void(0)">到场企业</a>
        
        <c:forEach items="${fcompanylist}" var="fcompany">
        <a href="./Fair?type=fcompanydetail&fcompanyid=${fcompany.id }">
        <section class="list1">
            <img class="list-img-left" src="${fcompany.pic_url }" />
            <div class="list-wapper">
                <h3>${fcompany.name }</h3>
                <p class="list-content">${fcompany.info }</p>
            </div>
        </section>
</c:forEach>
<c:if test="${empty fcompanylist}">列表为空！</c:if>

    </div>
    <mask style="display:none"></mask>
    <float style="display:none">
        <p>请完善您的简历</p>
        <a class="but">确定</a>
    </float>
    <jsp:include page="_footer.jsp"/>
</body>
</html>
