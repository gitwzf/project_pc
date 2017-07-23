<?php
session_start();
//判断用户输入部分,略
//用户输入正确,没有不规范输入的情况
$conn = mysql_connect("localhost","root", "");
mysql_select_db("phpbook_ch18") or die("连接数据库失败!");
$str = "select userpass, userid from user where username='".$username."'";
//用户名存在
if($result = mysql_query($str))
{
	$res = mysql_fetch_object($result);
	$userid = $res->userid;
	$pass = $res->userpass;
	//验证密码是否正确
	if(md5($password) == $pass)
	{
		$_SESSION["userid"] = $userid;
		$_SESSION["manager"]  = 'true';
		echo $username."你好,欢迎回来!<br>";
		echo "<hr>";
		echo "<p><a href='usermanager.php' target='_blank'>用户管理</a></p>";
		echo "<p><a href='productmanage.php' target='_blank'>商品管理</a></p>";
		echo "<p><a href='ordermanage.php' target='_blank'>用户订单管理</a></p>";
		exit();
	}
}
?>