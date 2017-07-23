<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>招聘职位-列表</title>
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
            <h4 class="form-title">${company.name }</h4>
            <c:if test="${!empty company.properties }">
            <span class="form-item">
                <span class="item-key">企业性质：</span>
                <span>${company.properties }</span>
            </span>
            </c:if>
            <c:if test="${!empty company.typeoneid }">
            <span class="form-item">
                <span class="item-key">所属行业：</span>
                <span>${company.typeoneid }</span>
            </span>
            </c:if>
            <c:if test="${!empty company.scale }">
            <span class="form-item">
                <span class="item-key">规模：</span>
                <span>${company.scale }</span>
            </span>
         </c:if>
         <c:if test="${!empty company.connector  }">
           <span class="form-item">
                <span class="item-key">联系人：</span>
                <span>${company.connector }</span>
            </span>
            </c:if>
            <c:if test="${!empty company.email }">
            <span class="form-item">
                <span class="item-key">电子邮箱：</span>
                <span>${company.email }</span>
            </span>
            </c:if>
            <c:if test="${!empty company.prof_url }">
            <span class="form-item">
                <span class="item-key">公司网址：</span>
                <span>${company.prof_url }</span>
            </span>
            </c:if>
            <c:if test="${!empty company.tel }">
            <span class="form-item">
                <span class="item-key">固定电话：</span>
                <span>${company.tel }</span>
            </span>
            </c:if>
            <c:if test="${!empty company.address }">
            <span class="form-item">
                <span class="item-key">所在地区：</span>
                <span>${company.address }</span>
            </span>
            </c:if>
            <c:if test="${!empty company.info }">
            <span class="form-item">
                <span class="item-key">公司简介</span>
                <p class="form-itme-p">${company.info }</p>
            </span>
            </c:if>
        </div>
        <a class="title" href="javascript:void(0)">招聘职位</a>
        <c:forEach items="${positionlist}" var="position">
        <a href="./CompanyServ?type=positiondetail&positionid=${position.id}">
            <section class="list1 list2 list3 list4" >
                <div class="list-wapper">
                    <h3>${position.position_name }</h3>
                    <c:if test="${!empty position.add_tim}">
                    <p class="list-item">时间：${position.add_tim }</p>
                    </c:if>
                    <c:if test="${!empty position.peoples }">
                    <p class="list-item left">招聘人数：${position.peoples }人</p>
					</c:if>
					<c:if test="${!empty position.salary }">
                    <p class="list-item right">年薪：<span class="color-red">${position.salary }</span></p>
              </c:if>
                </div>
            </section>
        </a>
       </c:forEach>
       <c:if test="${empty positionlist}">该公司暂无招聘信息</c:if>
    </div>
    <mask style="display:none"></mask>
    <float style="display:none">
        <p>请完善您的简历</p>
        <a class="but">确定</a>
    </float>
    <jsp:include page="_footer.jsp"/>
</body>
</html>
