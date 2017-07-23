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
        <a class="title" href="javascript:void(0)">我的应聘</a>
        <a href="${ad.url }" class="clicktimes" adid="${ad.id}"><img class="head_img" src="${ad.pic_url }" /></a>
        <div class="form-wapper">
         <c:if test="${!empty sposition.position_name}">
            <span class="form-item">
                <span class="item-key">岗位名称:</span>
                <span>${sposition.position_name }</span>
            </span>
            </c:if>
            <c:if test="${!empty sposition.peoples  }">
            <span class="form-item">
                <span class="item-key">招聘人数:</span>
                <span>${sposition.peoples }人</span>
            </span>
            </c:if>
            
            <span class="form-item">
                <span class="item-key">任职要求:</span>
                <div class="form-item-content">
                    <div>
                    <c:if test="${!empty sposition.degree }">
                        <p>学历：${sposition.degree }</p>
                        </c:if>
                        <c:if test="${!empty sposition.experience }">
                        <p>工作经验：${sposition.experience}</p>
                        </c:if>
                    </div>
                    <div>
                    <c:if test="${!empty sposition.posi_type }">
                        <p>类别：${sposition.posi_type }</p>
                        </c:if>
                        <c:if test="${!empty sposition.sex }">
                        <p>性别要求：<c:if test="${sposition.sex==0}">女</c:if><c:if test="${sposition.sex==1}">男</c:if><c:if test="${sposition.sex==2}">无</c:if></p>
                 </c:if>
                    </div>
                </div>
            </span>
            <c:if test="${!empty sposition.info }">
            <span class="form-item">
                <span class="item-key">岗位职责:</span>
                <p class="form-item-p">${sposition.info }</p>
             </span>
             </c:if>
             <c:if test="${!empty scompany.name}">
            <span class="form-item">
                <span class="item-key">企业名称:</span>
                <span>${scompany.name }</span>
            </span>
</c:if>
<c:if test="${!empty scompany.address  }">
            <span class="form-item">
                <span class="item-key">所在地区:</span>
                <span>${scompany.address }</span>
            </span>
            </c:if>
            <c:if test="${!empty scompany.properties }">
            <span class="form-item">
                <span class="item-key">公司性质:</span>
                <span>${scompany.properties }</span>
            </span>
            </c:if>
            <c:if test="${!empty scompany.typeoneid }">
            <span class="form-item">
                <span class="item-key">所属行业:</span>
                <span>${scompany.typeoneid }</span>
            </span>
            </c:if>
            <c:if test="${!empty scompany.scale }">
            <span class="form-item">
                <span class="item-key">规模:</span>
                <span>${scompany.scale }</span>
            </span>
            </c:if>
            <c:if test="${!empty scompany.info }">
            <span class="form-item">
                <span class="item-key">公司简介:</span>
                <p class="form-item-p">${scompany.info }</p>
            </span>
            </c:if>
        </div>
    </div>
<jsp:include page="_footer.jsp"/>
</body>
</html>
