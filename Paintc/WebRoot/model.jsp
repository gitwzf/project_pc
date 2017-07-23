<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>上传作品</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="css/nav.css"/>
<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
</head>

<body>
<div class="container">
    <div class="content">  
      <section class="model-list">
      <a href="detailwork.html">
            <img src="images/model01.jpg" class="left photo" style="width:25%;margin-right:10px;"/>
            <ul class="left" style="width:38%;">
                <li style="height:30px;">杨同学</li>
                <li style="height:30px;"><dd class="left">性别：</dd><dt>女</dt></li>
                <li style="height:30px;"><dd class="left">身高：</dd><dt class="left">156cm</dt></li>
            </ul>
            <ul class="left" style="width:33%;">
                <li style="height:30px;"><dd class="left">年龄：</dd><dt>9岁</dt></li>
                <li style="height:30px;"><dd class="left">体重：</dd><dt class="left">42kg</dt></li>
                <div class="button btn9"><input type="button" id="username"name="" value="投票" /></div>
            </ul>
            <div style="clear:both;"></div>
        </a>
        </section>	
        <section class="model-list">
      <a href="detailwork.html">
            <img src="images/model01.jpg" class="left photo" style="width:25%;margin-right:10px;"/>
            <ul class="left" style="width:38%;">
                <li style="height:30px;">杨同学</li>
                <li style="height:30px;"><dd class="left">性别：</dd><dt>女</dt></li>
                <li style="height:30px;"><dd class="left">身高：</dd><dt class="left">156cm</dt></li>
            </ul>
            <ul class="left" style="width:33%;">
                <li style="height:30px;"><dd class="left">年龄：</dd><dt>9岁</dt></li>
                <li style="height:30px;"><dd class="left">体重：</dd><dt class="left">42kg</dt></li>
                <div class="button btn9"><input type="button" id="username"name="" value="投票" /></div>
            </ul>
            <div style="clear:both;"></div>
        </a>
        </section>	
    </div>
</div>
</body>
</html>
