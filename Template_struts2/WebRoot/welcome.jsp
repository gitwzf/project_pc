<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcome.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body> 
    pahello,${logonForm.username }<br/>
    hello-----:${hello }<br/>
    <hr>
    hello:<s:property value="#request.hello"/><br/>
    username:<s:property value="#request.logonForm.username"/><br/>
    
    <s:form action="" method="">
    	username:<s:textfield name="username" ></s:textfield><br/>
    	password:<s:password name="password"></s:password><br/>
    	<s:submit value="logon"></s:submit>
    </s:form>
    
    <s:if test="#request.logonForm.age<18">
    	未成年
    </s:if>
    <s:if test="#request.logonForm.age>18">
    	成年
    </s:if>
    <br/>
    <s:iterator value="#session.userList" var="user" >
       
    <s:property value="#user.username"/>------
    <s:property value="#user.password"/>-----
    <s:property value="#user.age"/>-----<br/>
    </s:iterator>
    
    <br/>
    ${date }
    <s:date name="#request.date" format="yyyy-MM-dd hh:mm:ss"/>
    
  </body>
</html>
