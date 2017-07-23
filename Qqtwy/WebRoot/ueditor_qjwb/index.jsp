<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<script type="text/javascript"  src="ueditor.config.js"></script>
      <script type="text/javascript"  src="ueditor.all.js"></script>
      <script type="text/javascript" src="ueditor.all.min.js"></script>
      <link type="text/css" href="themes/default/css/ueditor.css"/>
      <script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/windows.js"></script>
     <script type="text/javascript">
      // $(function(){
	//alert(document.getElementById("edui1"));
//})
     </script>
  </head>
  
  <body>
    <div id="editor"></div>
    <script type="text/javascript" charset="utf-8">
          var editor = new baidu.editor.ui.Editor();
          editor.render('editor');
      </script>    
  </body>
</html>
