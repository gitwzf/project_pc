<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>留言板</title>
		<link href="css/base.css" rel="stylesheet" type="text/css" />
		<link href="css/message.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.7.2.min.js" ></script>
	<script type="text/javascript" src="js/windows.js"></script>
		<script type="text/javascript">
		$(function(){
		      $('.smessage').click(function(){
               var area=$('.tarea').val();
             //  window.location.Reload(); 添加刷新
             $.post("message0.jsp", { area: area},function(data){
             if(data=="00"){
                 alert("提交成功！");$('.tarea').attr("value","");
             }
             else if(data=="11")alert("添加失败！");
             else if(data=="33")alert("请输入内容");
             else if(data=="44")alert("请登录！");
             else
                 alert("访问失败！"+data);
             });

});
         $('.choosepage').click(function(){
			 location.href="./message0.jsp?page="+$('.pagenum').attr("value");
         });
		      
		});
		</script>
	</head>
	<body>
		<div id="container">
			<div id="banner">
		<!--  		<a href="javascript:" class="collect" onClick="jscript:window.external.AddFavorite(document.location.href,document.title);"></a>
		-->	</div>
			<div id="nav">
				<ul>
					<li><a href="./index.jsp">首页</a></li>
					<li><a href="./course0.jsp">我的课程</a></li>
					<li><a href="./information1.jsp">课程介绍</a></li>
					<li><a href="./information2.jsp">报名须知</a></li>
					<li><a href="./message0.jsp">留言板</a></li>
				</ul>
			</div>
			<div class="release">
				<div class="write">
					<textarea placeholder="欢迎提问，我们的工作人员会在2-3个工作日内回复您..." class="tarea"></textarea>
				</div>
				<div class="enter">
					<button class="smessage">发布</button>
				</div>
			</div>
			<div class="content">
			  <c:forEach items="${qusanslist}" varStatus="status" var="qusans">
				<div class="question">
					<p><span>${qusans.usertname}(${qusans.q_time}):</span>${qusans.q_info}</p>
				</div>
				     <c:if test="${qusans.isAnswer==1}">
				     
				       <c:forEach items="${qusans.tans}" var="ans">
				<div class="answer">
					<p><span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="blur">${ans.admname}(${ans.a_time}):</font></span>${ans.answer }</p>
				</div>
				       </c:forEach>
				     </c:if>  
				</c:forEach>
			</div>
			<div class="page">
				<ul >
					<li class="last"><a href="./message0.jsp?page=${page-1}">上一页</a></li>
				<li class="current0"  style="display:inline;width:150px"><button class="choosepage">GO</button><input type="text" value="${page}" class="pagenum" style="width:30px">页 共${pagenum}页</li>
				<li class="last"><a href="./message0.jsp?page=${page+1}">下一页</a></li>
				</ul>
			</div>
			
		</div>
	</body>
</html>