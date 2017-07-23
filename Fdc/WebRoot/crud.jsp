<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width" />
    <title>编辑页</title>
	<script type="text/javascript" src="js/jquery1.8.2.min.js"></script>
	<script type="text/javascript" src="js/main.js?f=3"></script>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
	<body>
		<div style="overflow:hidden">
			<i class="new-btn"></i>
		<!-- 	<i class="delete-btn" style="float:left"></i>   -->
			<form id="searchform" method="get">
			<input type="text" style="float:left" name="svalue"><i class="search-btn"></i>
			<select name="field" id="field" data-role='none' class="salary">
                               <option value="">请选择查询字段</option>
                               <c:forEach items="${titles}" var="year">
                                <option value="${year.name }">${year.comment }</option>
                               </c:forEach>
                            </select>
                            </form>
		</div>
		<table border=1>
			 <tr>
				<c:forEach items="${titles}" var="title">
				<th>${title.comment}</th>
				</c:forEach>
				<th>修改</th>
				<th>删除</th>
		    </tr>
		    <c:forEach items="${datalist}" var="data">
		    <tr class="line">
				 <c:forEach items="${pageScope.data}" var="da">
				<td>${da}</td>
				</c:forEach>
				<th><span class="edit-btn"></span></th>
				<th>aa</th>
			</tr>
			</c:forEach>
		</table>
		<div class="edit-box">
			<form id="editorform" method="post">
			<c:forEach items="${titles}" var="title">
				<div class="item"><label>${title.comment }</label><input type="text"  name="editoritem" class="editoritem" length="" type0=""/></div>
			</c:forEach>
			</form>
		<!-- 	 <div class="upload-photo">
				<form id="photo" name="photo" action="" enctype="multipart/form-data" method="post">

					<label>对应图标</label>
					<div class="upload-box">
						<img id="pre" src="" />
					</div> 
					<input id="" name="" class="file" type="file" /> 
					<input class="choose-btn" type="button" value="选择">
					<input class="upload-btn" type="button" value="保存">
				</form>   
			</div>    -->
			
			<input class="can-btn" type="button" value="取消">
			<input class="sav-btn" type="button" value="保存">
			<div class="cancel"></div>
		</div>
		
	</body>
</html>