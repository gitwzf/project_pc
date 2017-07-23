<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width" />
    <title>编辑页</title>
	<script type="text/javascript" src="js/jquery1.8.2.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<style>
		
	</style>
</head>
	<body>
		<div style="overflow:hidden">
			<i class="new-btn"></i>
			<i class="delete-btn" style="float:left"></i>
			<input type="text"/ style="float:left"><i class="search-btn"></i>
		</div>
		<table border=1>
			 <tr>
				<c:forEach items="${titles}" var="title">
				<th>${title}</th>
				</c:forEach>
		    </tr>
		    <c:forEach items="${datalist}" var="data">
		    <tr class="line">
				<td>${data.id}</td>
				<td>${data.title}</td>
				<td>${data.titshlder}</td>
				<td>${data.titsec}</td>
				<td>${data.time}</td>
				<td>${data.source}</td>
				<td>${data.writer}</td>
				<td>${data.picUrl}</td>
				<td>${data.makerId}</td>
				<td>${data.timemake}</td>
				<td>${data.makeinfo}</td>
				<td>${data.checkrId}</td>
				<td>${data.timecheck}</td>
				<td>${data.checkres}</td>
				<td>${data.checkinfo}</td>
				<td>${data.puberId}</td>
				<td>${data.rankId}</td>
				<td>${data.timpub}</td>
				<td>${data.status}</td>
				<td><span class="edit-btn"></span></td>
				<td><i class="delete-btn"></i></td>
			</tr>
			</c:forEach>
		</table>
		<div class="edit-box">
			<form>
				<div class="item"><label>编号</label><input type="text" value="" name="" class=""/></div>
				<div class="item"><label>标题</label><input type="text" value="" name="" class=""/></div>
				<div class="item"><label>名称</label><input type="text" value="" name="" class=""/></div>
			</form>
			 <div class="upload-photo">
				<form id="photo" name="photo" action="" enctype="multipart/form-data" method="post">

					<label>对应图标</label>
					<div class="upload-box">
						<img id="pre" src="" />
					</div>
					<input id="" name="" class="file" type="file" />
					<input class="choose-btn" type="button" value="选择">
					<input class="upload-btn" type="button" value="保存">
				</form>
			</div>
			
			<input class="can-btn" type="button" value="取消">
			<input class="sav-btn" type="button" value="保存">
			<div class="cancel"></div>
		</div>
	</body>
</html>