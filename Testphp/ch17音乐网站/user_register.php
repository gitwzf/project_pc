<?php
//新成员注册
require_once('config.php');
require_once('public.php');
if($passwd != $passwd1) error_quit3("您两次输入的密码不一致!");
if($user_name == '' || $province == '' || $passwd == '' || $email == '' || $sex == ''){
	include_once('inc/reg_form.inc.php');
} else {
	if(eregi("[;[@|+\\\/?,!'\"{}~%#`*&\t^()]+", $user_name)) error_quit3("用户名含非法字符!");
	if($err = checkword($user_name)) error_quit3($err);
	if(strlen($user_name) > 15) error_quit3('用户名太长，需要设置在12个字符以内');
	if(!emailcheck($email)) error_quit3("请正确填写您的Email地址!");
	require_once('inc/db.class.php');
	$exits = $db->query_first("SELECT user_id FROM user WHERE user_name = '$user_name'");
	if($exits[0]) {
		error_quit3("用户名 $user_name 已经存在!");
	}
	$firstlogin = date(U);
	$lastfrom = getenv("REMOTE_ADDR");
	$query = "INSERT INTO user (user_name, passwd, email, sex, oicq, province, addr, yearold, homepage, sign, firstlogin, lastfrom) VALUES ('$user_name', '$passwd', '$email', '$sex', '$oicq', '$province', '$addr', '$yearold', '$homepage', '$sign', '$firstlogin', '$lastfrom')";
	$db->query($query);
	//给出提示信息
	html_head("恭喜您！注册成功");
	echo "<br><br><br>
		<p class=okmsg align=center>恭喜您！注册成功！[帐号：<font color=red>".$user_name."</font>]</p>
		<p align=center> $message[0] </p>
		<script>setTimeout('window.close()', 5000);</script>
		</body></html>";
	$db->close();
}
exit;
?>