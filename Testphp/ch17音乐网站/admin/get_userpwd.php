<?php
//获取用户密码
require('../config.php');
require('../public.php');
require('../inc/db.class.php');

if(!is_admin())  error_quit3("错误，您不是管理员或还没有登录！");

function error_return($msg) { //错误时借助javascript返回
	global $db;
	$db->close();
	print "<script language=javascript> alert('".$msg."'); window.history.go(-1);</script>";
	exit;
}

html_head("强行找回密码");
print "<br><h3>强行找回密码</h3>\n";

if($do == "getpw") {
	if(!$tl) error_return("请输入帐号！");
	$chk = $db->query_first("SELECT passwd FROM user WHERE user_name = '$tl'");
	$chk = $chk[0];
	if(empty($chk)) error_return("错误的代号：".$tl);

	echo "系统已查获<font color=red>".$tl."</font>的密码为：<font color=red>".$chk."</font><br>\n"
	."<a href=".$PHP_SELF.">按此返回……</a>\n";

} else { // give Form

print <<<__EOF__
	<form action="$PHP_SELF" method="post">
	需要找回密码的帐号：<input type="text" size="20"  name="tl"> (输入站内用户代号)！
	<input type="hidden" name="do" value="getpw">
	<input type="submit" value="确认"> 
	</form>
__EOF__;
}

print "</body></html>\n";
$db->close();
exit;

?>