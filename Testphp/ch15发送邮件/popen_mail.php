<?php
/* 使用qmail发送邮件函数 */
function send_check_mail($email, $subject,$uid,$buffer){
	$command =  "/var/qmail/bin/qmail ".$email; //qmail程序地址和$email是要发送的地址
	$handle = popen($command, "w"); //打开管道
	if (!$handle) {
		return false;
	}

	$from = "admin@net9.org"; //发件人
	fwrite($handle, "From: ".$from."\n"); //往管道写数据
	fwrite($handle, "Return-Path: ".$from."\n");
	fwrite($handle, "To: ".$uid."\n");
	fwrite($handle, "Subject: ".$subject."\n");
	fwrite($handle, "Mime-Version: 1.0\n");
	fwrite($handle, "Content-Type: text/html; charset=\"gb2312\"\n\n");
	fwrite($handle, $buffer."\n");
	pclose($handle); //关闭管道

	return true;
}

//发送邮件示例
$subject = "测试邮件";

$uid = $_POST['uid']; //from信息
$content = "<html><body>".$u_email." 您好！<br><br>谢谢，本邮件测试！<br</body></html>"; //内容信息

$u_email = "cheng.vip@yahoo.com.cn"; //发送到的邮箱
if (send_check_mail($u_email, $subject, $uid, $content)) {

	echo "恭喜！发送投票邮件到您的邮箱！<br><br>请检查您的邮箱：<font color=#CC0033>".$u_email." </font><br><br>". $close;
} else {
	echo "很不幸，发送投票邮件到您的邮箱失败，请重试或者联系开发人员。<br><br>". $close;
}
?>