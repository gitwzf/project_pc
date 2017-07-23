<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微擎 - 微信公众平台自助引擎 -  Powered by WE7.CC</title>
<meta name="keywords" content="微擎,微信,微信公众平台" />
<meta name="description" content="微信公众平台自助引擎，简称微擎，微擎是一款免费开源的微信公众平台管理系统。" />
<link type="text/css" rel="stylesheet" href="./resource/style/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="./resource/style/font-awesome.css" />
<link type="text/css" rel="stylesheet" href="./resource/style/common.css?v=1384760416" />
<script type="text/javascript" src="./resource/script/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./resource/script/bootstrap.js"></script>
<script type="text/javascript" src="./resource/script/common.js?v=1384760416"></script>
<script type="text/javascript" src="./resource/script/emotions.js"></script>

<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript" src="resource/js/windows.js"></script>
<script type="text/javascript">
cookie.prefix = 'f1c8_';
</script>
<!--[if IE 7]>
<link rel="stylesheet" href="./resource/style/font-awesome-ie7.min.css">
<![endif]-->
<!--[if lte IE 6]>
<link rel="stylesheet" type="text/css" href="./resource/style/bootstrap-ie6.min.css">
<link rel="stylesheet" type="text/css" href="./resource/style/ie.css">
<![endif]-->
</head>
<body >
<script type="text/javascript">
	function checkname(){
	     var a=document.getElementById("user");
	     var user= $('#user').val();
	     jQuery.ajax({
	     	   type:"POST",
	           url:"le4Serv",
	           data:({"username":user}),
	       	   dataType:"text",
	       	   cache:true,
	       	   async:false,
	       	   contentType:"application/x-www-form-urlencoded;charset=utf-8",
	       	   success:function(result){
	       	       if(result=="00"){
	       	       alert("已有该用户名，将视为修改");
	       	          document.getElementById("user").style.background='red';}
	       	   else if(result=="11"){ alert("这个名字可用");  document.getElementById("user").style.background='green';}
	       	   }    
	     });
	
	}
	function check1() {
		if($.trim($(':text[name="user"]').val()) == '') {
			alert('没有输入姓名.');
			return false;
		}
		if($('#pass').val() == '') {
			alert('没有输入密码.');
			return false;
		}
		if($('#pass').val() != $('#repass').val()) {
			alert('两次输入的密码不一致.');
			return false;
		}
		return true;
	}
	function check2() {
		if($.trim($(':text[name="telephone"]').val()) == '') {
			alert('没有输入电话.');
			return false;
		}
		return true;
	}
	function check3() {
		if($.trim($(':text[name="identity"]').val()) == '') {
			alert('没有输入身份证号.');
			return false;
		}
		return true;
	}
</script>
<div class="main">
	<form action="le4Serv" class="form-horizontal form" onsubmit="return check();" method="post">
		<h4>学员信息</h4>
		<table class="tb">
			<tr>
				<th><label for="">姓名</label></th>
				<td>
					<div><input type="text" id="user" name="user" class="span6" value="" onchange="check1()"><span class="help-block">请输入真实姓名</span></div>
					
				</td>
			</tr>
			<tr>
				<th><label for="">年龄</label></th>
				<td>
					<div><input type="text" id="age" name="age" class="span6" value="" onchange="check2()"><span class="help-block">请输入周岁年龄</span></div>
					
				</td>
			</tr>
			<tr>
				<th><label for="">电话</label></th>
				<td>
					<div><input type="text" id="telephone" name="telephone" class="span6" value="" onchange="check2()"><span class="help-block">请输入联系方式，手机或电话均可</span></div>
					
				</td>
			</tr>
			<tr>
				<th><label for="">身份证号</label></th>
				<td>
					<div><input type="text" id="identity" name="identity" class="span6" value="" onchange="check3()"><span class="help-block">请输入 15 或18个数字</span></div>
					
				</td>
			</tr>
			<tr>
				<th><label for="">备注</label></th>
				<td>
					<textarea id="" name="instruction" style="height:80px;" class="span6"></textarea>
					<span class="help-block">方便注明此学员的特殊情况</span>
				</td>
			</tr>
			<tr>
				<th></th>
				<td>
					<input type="submit" class="btn btn-primary span2" name="submit" value="提交" />
				</td>
			</tr>
		</table>
	</form>
</div>
	<div class="emotions" style="display:none;"></div>
</body>
</html>