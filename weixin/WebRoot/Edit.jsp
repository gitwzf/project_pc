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
	<!--<link href="css/style.css" rel="stylesheet" type="text/css">-->
  <script language="javascript" type="text/javascript">
        function onDO(obj){
          var line=obj.parentNode.parentNode;
          var squ=line.cells;
          var squto1=document.getElementById("id");
          var squto2=document.getElementById("标题");
          var squto3=document.getElementById("说明");
          var squto4=document.getElementById("图片地址");
          var squto5=document.getElementById("链接");
          squto1.value=squ[1].innerHTML;
          squto2.value=squ[2].innerHTML;
          squto3.value=squ[3].innerHTML;
          squto4.value=squ[4].innerHTML;
          squto5.value=squ[5].innerHTML;
        }
        
        function onDel(obj){
         var line=obj.parentNode.parentNode;
         var squ=line.cells;
         var sq=squ[0].innerHTML;
         location.href="http://localhost:80/weixin/EdDo?delId="+sq;
        }
        
        function numCheck(){
        var squto2=document.getElementById("类型");
        if(isNaN(squto2.value)){
        alert("Not a num!");squto2.value="";}
        }
        function nullCheck(obj){
        if(obj.value==""){
        alert("not null!");squto1.value="1";}
        }
        
  
  </script>
  </head>
  
  <body>
    <table border=1>
       <tr>
         <td>id</td><td>标题</td><td>说明</td><td>图片地址</td><td>链接</td>
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
   i d：<input type="text" id="id" name="id" onchange="nullCheck(this)"/>
        标题：<input type="text" id="标题" name="title" onchange="nullCheck(this)"/>
        说  明：<input type="text" id="说明" name="main" /><br/>
        图 址：<input type="text" id="图片地址" name="picurl" size="100"  /><br/>
        链 接：<input type="text" id="链接" name="link" size="100" onchange="nullCheck(this)"/><br/>
        <input type="submit" value="提交" >
    </form>
  </body>
</html>
