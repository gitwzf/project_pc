
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
	function check() {
		if($.trim($(':text[name="user"]').val()) == '') {
			alert('没有输入用户名.');
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
</script>
<ul class="nav nav-tabs">
	<li class="active"><a href="le44.jsp">添加用户</a></li>
	<li><a href="le4Serv">用户列表</a></li>
</ul>
<div class="main">
	<form action="le4Serv" class="form-horizontal form" onsubmit="return check();" method="post">
		<h4>添加（修改）用户</h4>
		<table class="tb">
			<tr>
				<th><label for="">用户名</label></th>
				<td>
					<div><input type="text" id="user" name="user" class="span6" value="" onchange="checkname()"><span class="help-block">请输入用户名，用户名为 3 到 15 个字符组成，包括汉字，大小写字母（不区分大小写）</span></div>
					
				</td>
			</tr>
			<tr>
				<th><label for="">密码</label></th>
				<td>
					<input id="pass" name="pass" type="password" class="span6" value="" />
					<span class="help-block">请填写密码，最小长度为 8 个字符</span>
				</td>
			</tr>
			<tr>
				<th><label for="">确认密码</label></th>
				<td>
					<input id="repass" type="password" class="span6" value="" />
					<span class="help-block">重复输入密码，确认正确输入</span>
				</td>
			</tr>
			<tr>
				<th><label for="">备注</label></th>
				<td>
					<textarea id="" name="instruction" style="height:80px;" class="span6"></textarea>
					<span class="help-block">方便注明此用户的身份</span>
				</td>
			</tr>
			<tr>
				<th></th>
				<td>
					<input type="submit" class="btn btn-primary span2" name="submit" value="确认注册" /><input type="hidden" name="token" value="0f1b5942" />
				</td>
			</tr>
		</table>
	</form>
</div>
	<div class="emotions" style="display:none;"></div>
</body>
</html>