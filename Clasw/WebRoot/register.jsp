<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>注册</title>
		<link href="css/base.css" rel="stylesheet" type="text/css" />
		<link href="css/register.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.7.2.min.js" ></script>
	<script type="text/javascript" src="js/windows.js"></script>
		<script type="text/javascript">
		 
	function checkname(){
	var tname= $('#tiny_name').val();
	$.get("Register",{ tname: tname},function(data){  if(data=="00"){
	       	       alert("已有该用户名!  "+tname);
	       	          $('#tiny_name').attr("value","");}
	       	  });
	}
	function checkpaswd() {
		if($('#paswd').val() != $('#paswd0').val()) {
			alert('两次输入的密码不一致.');
			$('#paswd0').attr("value","");
			return false;
		}
		return true;
	}
	function isnum(obj){
	    if(obj.value!=null&&obj.value!="")
	        if(isNaN(obj.value)){
	        alert("请输入数字！");
	        obj.value="";
	        }
	}
	function isnnull(){
	if($('#tiny_name').val()==''){alert("昵称不能为空！");return false;}
	else  if($('#tiny_name').val().length>32){alert("昵称已超出最大长度");return false;}
	if($('#paswd').val()==''){alert("密码不能为空！");return false;}
	else  if($('#paswd').val().length>32){alert("密码已超出最大长度");return false;}
	if($('#paswd0').val()==''){alert("确认密码不能为空！");return false;}
	if($('#name').val()==''){alert("姓名不能为空！");return false;}
	else  if($('#name').val().length>16){alert("姓名已超出最大长度");return false;}
	if($('#tel').val()==''){alert("电话不能为空！");return false;}
	   else  if($('#tel').val().length>16){alert("电话已超出最大长度");return false;}
	if($('#age').val()==''){alert("年龄不能为空！");return false;}
	return true;
	}
		</script>
	</head>
	<body>
		<div id="container">
			<div id="banner">
			<!--  	<a href="javascript:" class="collect" onClick="jscript:window.external.AddFavorite(document.location.href,document.title);"></a>
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
			<div class="forms">
			   <form action="./Register" method="POST">
				<ul>
					<li>
						<label><span>*</span>昵称:</label>
						<c:if test="${!empty user}"><input type="text" name="tiny_name" id="tiny_name" class="box" value="${user.username}" readOnly="true"/></c:if>
						<c:if test="${empty user}"><input type="text" name="tiny_name" id="tiny_name" class="box" onblur="checkname()"/></c:if>
					</li>
					<li>
						<label><span>*</span>密码:</label>
						<input type="password" name="password" id="paswd" class="box" value="${user.password}" />
					</li>
					<li>
						<label><span>*</span>确认密码:</label>
						<input type="password" name="password0" id="paswd0" class="boxone"  value="${user.password}" onblur="checkpaswd()"/>
					</li>
					<li>
						<label><span>*</span>真实姓名:</label>
						<input type="text" name="name"  id="name" class="boxone"  value="${user.name}"/>
					</li>
					<li>
						<label><span>*</span>联系电话:</label>
						<input type="text" name="tel" id="tel" class="boxone"  value="${user.tel}" onblur="isnum(this)"/>
					</li>
					<li>
						<label><span></span> 身份证 号:</label>
						<input type="text" name="ID"  class="boxone"  value="${user.ID}" onblur="isnum(this)"/>
					</li>
					<li>
						<label><span> </span>性 别:</label>
						男<input type="radio" name="sex" value="0" />女<input type="radio" name="sex"  value="1"/>
					</li>
					<li>
						<label><span>*</span>年龄:</label>
						<input type="text" name="age" id="age" class="box" value="${user.age}" onblur="isnum(this)" />
					</li>
					<li>
						<label><span></span>身 高:</label>
						<input type="text" name="height"  class="box"  value="${user.height}" onblur="isnum(this)"/>(cm)
					</li>
					<li>
						<input type="submit" value="提交" class="confirm" onclick="return isnnull()"/>
					</li>
				</ul>
				</form>
			</div>
		</div>
	</body>
</html>