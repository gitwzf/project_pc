<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery1.8.2.min.js"></script>
	<script type="text/javascript" src="js/main.js?f=3"></script> 
	<script type="text/javascript">
	$(function(){
	$('.add').click(function(){
	    $('.edit').css("display",'block');
	});
	
	$('.upd').click(function(){
	    $('.edit').css("display",'block');
	    var item=$(this).parent().parent().children();
	    $('.id').val(item[0].innerHTML);
	    $('.loginname').val(item[1].innerHTML);
	    $('.name').val(item[2].innerHTML);
	    
	});
	
	$('.save').click(function(){
	 $.post("UserServ",{id:$('.id').val(),loginname:$('.loginname').val(),name:$('.name').val(),type:"save"});
	 window.location.href=window.location.href;
	 });
	$('.del').click(function(){
	$.post("UserServ",{id:$(this).parent().parent().children()[0].innerHTML,type:"del"});
	window.location.href=window.location.href;
	 });
	})
	</script>
	
  </head>
  
  <body>
    <form method="get" action="./UserServ">
    <input type="button" value="添加" class="add">
    id:<input type="text" name="id">登录名:<input type="text" name="loginname"><input type="submit" value="查询">
      <br/> <table>
          <tr><tb>id</tb><tb>loginname</tb><tb>name</tb><tb>操作</tb></tr>
          <c:forEach items="${userlist}" var="user">
             <tr><td>${user.id }</td>
          	<td>${user.loginname }</td>
          <td>${user.name }</td>
          <td><input type="button" value="修改" class="upd"><input type="button" value="删除" class="del"></td></tr>
          </c:forEach>
       
       </table>
    
    </form>
    <div class='edit' style="display:none;">用户
    <input type="text" class="id">
    <input type="text" class="loginname">
    <input type="text" class="name">
         <input type="button" value="保存" class="save">
    </div>
  </body>
</html>
