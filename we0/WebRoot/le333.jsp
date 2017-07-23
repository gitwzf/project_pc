<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
<link type="text/css" rel="stylesheet" href="./resource/style/common.css?v=1383814250" />
<script type="text/javascript" src="./resource/script/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./resource/script/bootstrap.js"></script>
<script type="text/javascript" src="./resource/script/common.js?v=1383814250"></script>
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="account.php?act=post&">添加公众号</a></li>
		<li><a href="account.php?act=display&">管理公众号</a></li>
	</ul>
	<div class="main">
		<form action="le3Serv" method="get" class="form-horizontal form">
		<input type="hidden" name="id" value="31" />
						<h4>设置公众号</h4>
						<table class="tb">
				<tr>
					<th><label for="">公众号名称</label></th>
					<td>
						<input type="text" name="name" class="span6" value="${cpub.name}" autocomplete="off">
						<span class="help-block">您可以给此公众号起一个名字, 方便下次修改和查看.</span>
					</td>
				</tr>
								<tr>
					<th style="color:red">接口地址</th>
					<td>
						<input type="text" name="apiurl" class="span6" value="${cpub.apiurl}"  autocomplete="off"/>
						<div class="help-block">设置“微信公众平台接口”配置信息中的接口地址</div>
					</td>
				</tr>
				<tr>
					<th style="color:red">微信Token</th>
					<td>
						<input type="text" name="token" class="span6" value="${cpub.token}"/> 
						<div class="help-block">与微信公众平台接入设置值一致，必须为英文或者数字，长度为3到32个字符. 请妥善保管, Token 泄露将可能被窃取或篡改微信平台的操作数据.</div>
					</td>
				</tr>
								<tr>
					<th>公众号AppId</th>
					<td>
						<input type="text" name="appid" class="span6" value="${cpub.appid}" autocomplete="off"/>
						<div class="help-block">请填写微信公众平台后台的AppId</div>
					</td>
				</tr>
				<tr>
					<th>公众号AppSecret</th>
					<td>
						<input type="text" name="appsecret" class="span6" value="${cpub.appsecret}" autocomplete="off"/>
						<div class="help-block">请填写微信公众平台后台的AppSecret, 只有填写这两项才能管理自定义菜单</div>
					</td>
				</tr>
				<tr>
					<th><label for="">微信号</label></th>
					<td>
						<input type="text" name="account" class="span6" value="" autocomplete="off" />
						<span class="help-block">您的微信帐号，本平台支持管理多个微信公众号</span>
					</td>
				</tr>
				<tr>
					<th><label for="">原始帐号</label></th>
					<td>
						<input type="text" name="original" class="span6" value="" autocomplete="off" />
						<span class="help-block">微信公众帐号的原ID串，<a href="index.php?act=help&amp;do=wx_uid" target="blank">怎么查看微信的原始帐号？</a></span>
					</td>
				</tr>
								<tr>
					<th>二维码</th>
					<td>
						<img src="http://demo.we7.cc/resource/attachment//qrcode_31.jpg?weid=" width="150" />
					</td>
				</tr>
												<tr>
					<th>头像</th>
					<td>
						<img src="http://demo.we7.cc/resource/attachment//headimg_31.jpg?weid=" width="85" />
					</td>
				</tr>
							</table>
			<h4>
			  设置微信号
			  <small>设置用户名密码后，程序会自动采集您的相关信息。还可以进行群发操作。</small>
			</h4>
			<table class="tb">
				<tr>
					<th><label for="">微信公众登录用户</label></th>
					<td>
						<input type="text" name="wxname" id="username" class="span6" value="${cpub.wxname}" autocomplete="off" />
						<span class="help-block">请输入你的微信公众平台用户名</span>
					</td>
				</tr>
				<tr>
					<th><label for="">微信公众登录密码</label></th>
					<td>
						<input type="password" name="wxpassword" class="span6" value="${cpub.pass}" autocomplete="off"  />
						<span class="help-block">请输入你的微信公众平台密码</span>
					</td>
				</tr>
				<tr>
					<th><label for="">登录验证码</label></td>
					<td>
						<input type="text" name="verify" class="txt grid-1 alpha pin" value="" autocomplete="off" onfocus="verifyGen()"  />
						<span class="help-inline"><img src="" id="imgverify"> <a href="javascript:;" onclick="verifyGen()">换一张</a></span>
					</td>
				</tr>
				<tr>
					<th></th>
					<td>
						<label class="checkbox inline"><input type="checkbox" name="islogin" value="1" checked="checked" /> 是否验证登录</label>
						<div class="help-inline">&nbsp;勾选此选项后，提交后将验证您的微信帐号。如果有任何异常信息，请取消此选项。</div>
					</td>
				</tr>
								<tr>
					<th></th>
					<td>
						<a href="account.php?act=sync&id=31">同步微信公众平台帐号信息</a>
						<div class="help-block">填写公众号帐号密码后，如果发现信息没有同步成功，请点击此选项进行手动同步。</div>
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
<!--
	function verifyGen() {
		if ($('#username').val()) {
			$('#imgverify').attr('src', 'https://mp.weixin.qq.com/cgi-bin/verifycode?username='+$('#username').val()+'&r='+Math.round(new Date().getTime()));
		} else {
			message('请先输入微信公众平台用户名');
		}
	}
//-->
</script>
	
	<div class="emotions" style="display:none;"></div>
</body>
</html>