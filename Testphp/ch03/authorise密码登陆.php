<?php
	if(!isset($_SERVER['PHP_AUTH_USER']))
	{
	//如果$PHP_AUTH_USER变量不存在
		header("WWW-Authenticate:Basic realm='用户认证'");
		header("HTTP/1.0 401 Unauthorized");
		echo "如果您点击了取消，就会显示这句话。\r\n";
		exit;
	}else{
		echo "登陆成功，欢迎".$_SERVER['PHP_AUTH_USER']."的到来！";
		echo "您刚才输入的密码为".$_SERVER['PHP_AUTH_PW']."，请您牢记您的密码";
	}
?>