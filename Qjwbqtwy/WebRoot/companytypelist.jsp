<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>行业列表</title>
    <meta name="viewport" content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" />
    <script src="js/jquery-1.9.1.min.js"></script>
    <link href="css/css.css?r=4" rel="stylesheet" />

    <script src="js/jslib.js"></script>
    <script src="js/js.js"></script>
</head>
<body>
    <div class="contain">
        <a href="${ad.url }" class="clicktimes" adid="${ad.id}"><img class="head_img" src="${ad.pic_url }" /></a>
        
        <div class="category-wapper">
        <%int i=1; %>
        
        <c:forEach items="${modeltypesupcompanylist}" var="modelcompanyt">
          <div class="flex-box">
            <c:forEach items="${pageScope.modelcompanyt}" var="modelcompany">
                <a class="flex2 flex f-c-<%=i++%7 %>" href="./CompanyServ?typeid=${modelcompany.id}&mtype=${modelcompany.type}"><img class="flex-img" src="${modelcompany.pic_url}" style="width:90px"/></a>
              </c:forEach>
             </div>
            </c:forEach>
          
           
        </div>
    </div>
     <jsp:include page="_footer.jsp"/>
</body>
</html>
