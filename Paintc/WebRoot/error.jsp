<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.net.URLDecoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta name="viewport" content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" />
    <script src="js/jquery-1.7.2.min.js"></script>
    <link href="css/css.css" rel="stylesheet" />

    <script src="js/jslib.js"></script>
    <script src="js/js.js"></script>
    <script type="text/javascript">
      $(function(){
        $('.showpage').click(function(){
        var i=$(this).attr("i");
        $.post("PageServ",{type:'p_college',i:i},function(text){
        if(text.trim()==''){
        $('#hasMore').remove();
        TouClick.mask(1).float('已全部加载');
        
        }
          else
              $('#apage').append(text);  
        
        });
        });
      
      });
    </script>
</head>
<body>
   登陆页面超时，请重新访问。。。
</body>
</html>
