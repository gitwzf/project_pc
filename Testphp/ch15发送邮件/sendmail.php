<?php 
include ("./sendmail.class.php");
$mail = new sendmail();
$email = "您好，这是一个测试邮件！";
$sendmail = $mail->send_mail("smtp.263.net", "PHPBOOK", true); //显示调示信息
if($mail->send("chengwei@263.net", "lyh.hui@263.net", "测试SOCKET邮件", $email)) {
	echo "发送成功！<br>";
}else{
	echo "发送失败！<br>";
}
?>