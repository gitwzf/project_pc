
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微信公众平台自助引擎 -  Powered by WE7.CC</title>
<meta name="keywords" content="微信,微信公众平台" />
<meta name="description" content="微信公众平台管理系统。" />
<link type="text/css" rel="stylesheet" href="./resource/style/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="./resource/style/font-awesome.css" />
<link type="text/css" rel="stylesheet" href="./resource/style/common.css?v=1382944729" />
<script type="text/javascript" src="./resource/script/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./resource/script/bootstrap.js"></script>
<script type="text/javascript" src="./resource/script/common.js?v=1382944729"></script>
<script type="text/javascript" src="./resource/script/emotions.js"></script>
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
	<div class="main">
		<form action="" method="post" class="form-horizontal form" onsubmit="return formcheck(this)">
			<h4>管理员信息修改</h4>
			<table class="tb">
				<tr>
					<th><label for="">管理员帐号</label></th>
					<td>
						<input type="text" name="name" class="span6" value="admin" readonly />
						<div class="help-block">只能用'0-9'、'a-z'、'A-Z'、'.'、'@'、'_'、'-'、'!'以内范围的字符</div>
					</td>
				</tr>
				<tr>
					<th><label for="">管理员密码</label></th>
					<td>
						<input type="password" name="pw" class="span6" value="" />
					</td>
				</tr>
				<tr>
					<th style="color:red;">新密码</th>
					<td>
						<input type="password" name="pw2" class="span6" value="" />
					</td>
				</tr>
				<tr>
					<th style="color:red;">确认密码</th>
					<td>
						<input type="password" name="pw3" class="span6" value="" />
					</td>
				</tr>
				<tr>
					<th></th>
					<td>
						<input name="submit" type="submit" value="提交" class="btn btn-primary span3" />
						<input type="hidden" name="token" value="da083f61" />
					</td>
				</tr>
			</table>
		</form>
    </div>
	<script type="text/javascript">
	function formcheck(form) {
		if (!form['name'].value) {
			alert('请填写管理员帐号！');
			form['name'].focus();
			return false;
		}
		if (!form['pw'].value) {
			alert('请填写管理员密码！');
			form['pw'].focus();
			return false;
		}
		if (!form['pw2'].value) {
			alert('请填写新密码！');
			form['pw2'].focus();
			return false;
		}
		if (form['pw'].value == form['pw2'].value) {
			alert('新密码与原密码一致，请检查！');
			form['pw'].focus();
			return false;
		}
		if (form['pw2'].value.length < 6 ) {
			alert('管理员密码不得小于6个字符！');
			form['pw2'].focus();
			return false;
		}
		if (form['pw2'].value != form['pw3'].value) {
			alert('两次输入的新密码不一致，请重新输入！');
			form['pw2'].focus();
			return false;
		}
	}
	</script>
	
	<div class="emotions" style="display:none;"></div>
</body>
</html>