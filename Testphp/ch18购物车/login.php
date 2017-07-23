<?php
session_start();
//实际应用中，还需要判断用户输入的信息是否正确，过滤相关的关键字等处理
$conn = mysql_connect("localhost","root", "");
mysql_select_db("phpbook_ch18") or die("连接数据库失败!");
$str = "select userpass, userid from user where username='".$username."'";
$result = mysql_query($str);
$isExist = false;
if (!isset($prepage)) {
	session_register("prepage");
}
$prepage = "usershow.php";
while ($res = mysql_fetch_object($result)) {
	$isExist = true;
	$userid = $res->userid;
	$pass = $res->userpass;
}
//用户名存在
if($isExist){
	//验证密码是否正确
	if(md5($password) == $pass){
		$_SESSION["userid"] = $userid;
		if (isset($cart)){
			$cart->setOwner($userid);
		}
		header("Location:usershow.php?userid=".$userid);
		exit();
	}
	$returnpage = "Location:".$prepage."?errmsg=".urlencode("密码错误!");
	header($returnpage);
	exit();
}else{
	$returnpage = "Location:".$prepage."?errmsg=".urlencode("用户名不存在!");
	header($returnpage);
	exit();
}
?>