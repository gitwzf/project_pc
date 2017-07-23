<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Edit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/style.css" rel="stylesheet" type="text/css">
  <script language="javascript" type="text/javascript">
        function onDO(obj){
          var line=obj.parentNode.parentNode;
          var squ=line.cells;
          var squto1=document.getElementById("消息");
          var squto2=document.getElementById("类型");
          var squto3=document.getElementById("内容");
          squto1.value=squ[1].innerHTML;
          squto2.value=squ[2].innerHTML;
          squto3.value=squ[3].innerHTML;
        }
        
        function onDel(obj){
         var line=obj.parentNode.parentNode;
         var squ=line.cells;
         var sq=squ[1].innerHTML;
         location.href="http://localhost:80/weixin/EdDo?delRep="+sq;
        }
        
        function numCheck(){
        var squto2=document.getElementById("类型");
        if(isNaN(squto2.value)){
        alert("Not a num!");squto2.value="";}
        }
        function nullCheck(){
        var squto1=document.getElementById("消息");
        if(squto1.value==""){
        alert("not null!");squto1.value="...";}
        }
        
  
  </script>
  </head>
  
  <body>
    <table border=1>
       <tr>
         <td>操作</td><td>收到的消息</td><td>回复类型</td><td>回复内容</td>
       </tr>
       <c:forEach items="${sessionScope.repArr}" var="rp">
           <tr><td><input type="button" value="编辑" onclick="onDO(this)"><input type="button" value="删除" onclick="onDel(this)"></td>
              <c:forEach items="${pageScope.rp}" var="r">
                  <td>${r}</td>
              </c:forEach>
           </tr>
       </c:forEach>
    </table>
    <form action="EdDo">
        收到的消息：<input type="text" id="消息" name="rep" onchange="nullCheck()"/>&nbsp;&nbsp;&nbsp;&nbsp;回复的类型：<input type="text" id="类型" name="rep_style" onchange="numCheck()"/><br/>
        回复的内容：<input type="text" id="内容" size="60" name="con" /><br/>
        <input type="submit" value="提交" >
    </form>
  </body>
</html>
