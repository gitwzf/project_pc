<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wzf.pubvari.*"%>
<%@ page import="com.wzf.model.Infopage" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <%Infopage ipage=Variable.map_pageinfo.get("3"); %>
    <title><%=ipage==null?"无标题":ipage.getTitle() %></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1" /><!-- 控制宽度 -->
	<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
<link rel="stylesheet" href="css/nav.css"/>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/windows.js"></script>
  </head>
  
  <body>
   <div style="padding:0 15px;background:#fff">

  <%=ipage==null?"无内容":ipage.getContent() %>

</div>
<br/>
<br/>
<br/>
 <div class="container">
 <c:if test="${!empty manager}">
<jsp:include page="menu.jsp" flush="true"/>  
</c:if>
</div>
<script type="text/javascript" src="js/jquery.min.js"></script>
 
	<script type="text/javascript" src="js/jr.js?y=7"></script> 
  </body>
</html>
