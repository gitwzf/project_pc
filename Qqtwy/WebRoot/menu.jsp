<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.net.URLDecoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'menu.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	   window.onload=function(){
	      var info='<%=(request.getAttribute("info")==null?"":URLDecoder.decode((String)request.getAttribute("info"),"utf-8"))%>';
	      if(info!='')alert(info);
	   }
	
	</script>
    <style type="text/css">
    input{
   width:70%;
    
    }
    
    </style>
  </head>
  
  <body>
    <form action="menuServ" method="post">
    <div>
    <c:forEach items="${menu}" var="me" varStatus="status">
      <div style="float:left;padding-right:5px;width:25%"> 
            菜单${status.index+1}：<input type="text" name="name" value="${name[status.index]}"><br>
         <div style="padding-bottom:20px"><select name="type">
         <c:if test="${me[0].type=='view'}">
         <option value="view" selected="selected">链接</option><option value="click">事件</option>
         </select><input type="text" name="name0" value="${me[0].name }"><input type="text" name="key" value="${me[0].url }">
         </c:if>
          <c:if test="${me[0].type=='click'}">
         <option value="view">链接</option><option value="click" selected="selected">事件</option>
         </select><input type="text" name="name0" value="${me[0].name }"><input type="text" name="key" value="${me[0].key }">
         </c:if>
         </div>
         
     	 <div style="padding-bottom:20px"><select name="type">
         <c:if test="${me[1].type=='view'}">
         <option value="view" selected="selected">链接</option><option value="click">事件</option>
         </select><input type="text" name="name0" value="${me[1].name }"><input type="text" name="key" value="${me[1].url }">
         </c:if>
          <c:if test="${me[1].type=='click'}">
         <option value="view">链接</option><option value="click" selected="selected">事件</option>
         </select><input type="text" name="name0" value="${me[1].name }"><input type="text" name="key" value="${me[1].key }">
         </c:if>
         </div>
         
         <div style="padding-bottom:20px"><select name="type">
         <c:if test="${me[2].type=='view'}">
         <option value="view" selected="selected">链接</option><option value="click">事件</option>
         </select><input type="text" name="name0" value="${me[2].name }"><input type="text" name="key" value="${me[2].url }">
         </c:if>
          <c:if test="${me[2].type=='click'}">
         <option value="view">链接</option><option value="click" selected="selected">事件</option>
         </select><input type="text" name="name0" value="${me[2].name }"><input type="text" name="key" value="${me[2].key }">
         </c:if>
         </div>
         
         <div style="padding-bottom:20px"><select name="type">
         <c:if test="${me[3].type=='view'}">
         <option value="view" selected="selected">链接</option><option value="click">事件</option>
         </select><input type="text" name="name0" value="${me[3].name }"><input type="text" name="key" value="${me[3].url }">
         </c:if>
          <c:if test="${me[3].type=='click'}">
         <option value="view">链接</option><option value="click" selected="selected">事件</option>
         </select><input type="text" name="name0" value="${me[3].name }"><input type="text" name="key" value="${me[3].key }">
         </c:if>
         </div>
         
         <div style="padding-bottom:20px"><select name="type">
         <c:if test="${me[4].type=='view'}">
         <option value="view" selected="selected">链接</option><option value="click">事件</option>
         </select><input type="text" name="name0" value="${me[4].name }"><input type="text" name="key" value="${me[4].url }">
         </c:if>
          <c:if test="${me[4].type=='click'}">
         <option value="view">链接</option><option value="click" selected="selected">事件</option>
         </select><input type="text" name="name0" value="${me[4].name }"><input type="text" name="key" value="${me[4].key }">
         </c:if>
         </div>
         
      </div>
   </c:forEach>
    </div>
    <div  style="width:50%"><input type="submit" value="保存" style="width:10%"/></div>
    </form>
  </body>
</html>
