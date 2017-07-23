<?php
include_once("phpmailer/class.phpmailer.php");
/**
 * 定义邮件模块配制信息
 */
define("SMTP_HOST","mail.dudu-inc.com");    		// SMTP 主机
define("SMTP_MAIL","phpbook_all@126.com");		// SMTP 用户email
define("SMTP_PASS","phpbooks");					  	// SMTP 用的名字

define("SERVICE_MAIL","phpbook_all@126.com"); // SMTP 用户email
define("SERVICE_NAME","PHPBOOK邮件测试"); // SMTP 用的名字

/**
	 * 使用phpmailer发邮件模块
	 *
	 * @param string $email
	 * @param string $user
	 * @param string $subject
	 * @param string $body
	 * @return bool
	 */
function sendMail($email,$user,$subject,$body)
{
	$mail = new PHPMailer();

	$mail->IsSMTP();                    			// 设置使用SMTP
	$mail->Host = SMTP_HOST;  					// 设置SMTP服务器地址
	$mail->SMTPAuth = true;     					// 打开SMTP权限验证
	$mail->Username = SMTP_MAIL;  					// SMTP 用户名
	$mail->Password = SMTP_PASS; 					// SMTP 服务器密码

	$mail->From = SERVICE_MAIL;						// 设置发送者地址
	$mail->FromName = SERVICE_NAME;					// 设置发送者名字
	$mail->AddAddress($email, $user);				// 添加接收者地址
	$mail->AddReplyTo(SERVICE_MAIL, SERVICE_NAME); 	// 设置回复地址

	$mail->WordWrap = 50;                // 设置显示格式
	$mail->IsHTML(true);                 // 设置邮件支持html
	$mail->Subject = $subject;
	$mail->Body    = $body;
	$mail->AltBody = "";				// 文本类型的邮件

	if(!$mail->Send())
	{
		return $mail->ErrorInfo;
	}
	return true;
}

//开始发送测试邮件
$tomail 		= "phpbook_ch15@126.com";
$user 			= "Mr.Cheng";
$_mailSubject 	= "邮件测试示例!"; // 发给用户的邮件标题
$_mailBody		= "<a href='http://business.sina.com/' style='color:red'>新浪网</a>"; // 邮件内容
$sendresult = sendMail($tomail, $user, $_mailSubject , $_mailBody);
if($sendresult){
	//发送成功，打印成功信息提示
	echo "信息已经被成功发送！";
}
?>