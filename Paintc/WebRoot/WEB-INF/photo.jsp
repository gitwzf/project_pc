<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE HTML>
<html lang="en">
	<head>
		<meta charset="utf-8">
        <title>拍照</title>
		<meta name="author" content="terry - gbin1.com">
		<meta name="description" content="HTML5 webcam demo">
		<meta name="keywords" content="HTML5,webcam,gbin1.com, gbin1, gbtags">
		<link href='http://fonts.googleapis.com/css?family=Revalia' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="css/jquery.qtip.css" type="text/css" media="screen">
		<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="js/photobooth_min.js"></script>
		<script type="text/javascript" src="js/jquery.qtip.min.js"></script>
		<script type="text/javascript" src="js/gbin1.js"></script>
		<style>
			body{
				font-size:12px;
				background: #333;
				font-family: 'Revalia', cursive, arial;
			}
			
			div#pictures,div#webcam{
				margin: 10px 0 0;
			}
			
			h1{
				margin: 20px 0;
			}
			
			div#from{
				margin:20px 0px;
			}
			
			div#from a{
				color: #FFF;
				font-size:12px;
				background: #1557C3;
				margin: 20px 0;
				border-radius: 5px;
				padding:10px;
				line-height: 25px;
			}
			
			h1 a{
				background: #333;
				border-radius: 5px;
				padding: 5px;
				float:right;
				cusor: hand;
				color: #FFF;
				text-decoration: none;
				margin-left: 20px;
			}
			
			#main{
				width: 800px;
				margin: 10px auto;
				background: #FFF;
				color: #333;
				border: 2px solid #FFF;
				box-shadow: 0px 0px 10px #CCC;
				padding:20px;
				border-radius: 5px;
			}
			
			#main article{
				margin-bottom: 50px;
				background: #F8F8F8;
				border-radius: 5px;
				padding:20px;
				border: 1px solid #bbb;
			}
			
			#main #webcam{
				height: 400px;
				width: 100%;
			}
			
			#main #plist{
				margin: 20px 0;
				font-weight: bold;
				border-radius: 5px;
				background: #CCC;
				padding:10px;
			}
			
			#main img{
				margin-bottom: 50px;
				background: #F8F8F8;
				border-radius: 10px;
				box-shadow: 0px 0px 5px #888;
			}			
			
			.clear{
				clear:both;
			}
			
			#main ul{
				list-style:none;
				margin:0;
				padding:0;
			}
			
			#main .photobooth{
				border: 1px solid #ccc;
				border-radius: 5px;
			}
		
		</style>
	</head> 
	<body>
		
		<section id="main">
			<article>
			<div id="webcam"></div>
			<div id="plist">照片列表</div>
			<div id="pictures"><div class="nopic">无照片</div>
			<input type="button" class="button right btn2 btn4 btn5" value="上传"/>
			</div>
			 
			<div class="clear"></div>
			</article>
		</section>
	</body>	
</html>