<?php
//点播歌曲
define("USE_MAIL", 1);
require_once('config.php');
require_once('public.php');
if(!islogin()) error_quit3("您还没有登录！$message[4] $message[6]"); //登录后才能享受此项服务。
if(!$song_id) error_quit3("非法操作！");
if($to_email != '' && $receiver != '' && $sender != '' && $from_email != ''){
	if(!emailcheck($to_email) || !emailcheck($from_email)) error_quit3("请正确填写Email地址!");
	$now = date("Y-m-d H:i:s");
	$receiver1 = "<a href=mailto:".$to_email.">".$receiver."</a>";
	//添加到数据库
	require_once('inc/db.class.php');
	$db->query("INSERT INTO ordersong (receiver, sender, song_name, song_id, singer_name, singer_id, date, ssay, says, flag) VALUES ('$receiver1', '$sender', '$song_name', '$song_id', '$singer_name', '$singer_id', '$now', '$ssay', '$says', '$flag')");
	$db->close();
	//发送
	include_once("phpmailer/class.phpmailer.php");
	/**
	 * 定义邮件模块配制信息
	 */
	define("SMTP_HOST","smtp.126.com");    		// SMTP 主机
	define("SMTP_MAIL","phpbook_ch17@126.com");		// SMTP 用户email
	define("SMTP_PASS","12345");					  	// SMTP 用的名字

	define("SERVICE_MAIL","phpbook_ch17@126.com"); // SMTP 用户email
	define("SERVICE_NAME","点播音乐歌曲"); // SMTP 用的名字

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

	$mail_subject = "[181信使]".$sender."点歌给您：".$ssay;
	$mail_content = $receiver.": 您好！\r\n"
		."您的好友 $sender 在 $site_name ( $site_addr ) 给您点歌。\r\n"
		."点播歌曲 《".$song_name."》- 演唱: $singer_name \r\n"
		."点击这个地址下载或收听 $song_url \r\n"
		."点歌时间 $now \r\n\r\n"
		."他(她)还给您留言如下：\r\n"
		."----------------------------------------------------------------\r\n"
		.$says."\r\n"
		."----------------------------------------------------------------\r\n"
		."*/.    .   .    *      .\r\n"
		.".\\*    .    []           *  __\r\n"
		."*/ .   ./\\~~~~~~~~~~~~'\\. |◆\r\n"
		."\*   ,/,..,\\,...........,\\.◆\r\n"
		."||  ..▎#  ▎田  田 ▎ | ▎◆\r\n"
		."||  &&▎   ▎       ▎'|'▎ o  ".$site_name." \r\n"
		."|| ##■■■■■■■■■■〓    ".$site_addr." \r\n"
		."…………………………………………………………………………………………\r\n";

	$headers .= "From: ".$sender." <".$from_email.">\r\n";
	$headers .= "Reply-To: ".$sender." <".$from_email.">\r\n";
	$headers .= "Content-Type: text/plain\r\n";
	$headers .= "Return-Path: ".$from_email."\r\n";
	$headers .= "X-Mailer: cheng.Mar\r\n";
	$headers .= "X-Priority: 1\r\n";

	if($smtp_mail == 0)
		mail($to_email, $mail_subject, $mail_content, $headers);
	else {

		/***** 方法一，使用方法一需要打开config文件中的邮件发送部分，为了防止和方法二冲突，已经被注释。
			$send_params = array();
			$send_params['headers'] = "Subject: ".$mail_subject."\r\n".$headers;
			unset($headers);
			$send_params['recipients'] = $to_email;
			$send_params['from'] = $sender." <".$from_email.">";
			$send_params['body'] = $mail_content;
			unset($mail_content);
			$smtp = smtp::connect($params);
			$smtp->send($send_params);
			unset($send_params);
		*/
		//方法二，直接使用第15章讲述的phpmailer组件发送
		$tomail 		= $to_email;
		$sendresult = sendMail($tomail, $sender, $mail_subject , $mail_content);
	}
	html_head("恭喜，点歌成功！");
	echo "<p align=center><span class=okmsg>恭喜您，点歌成功！</span><br>\n"
		."您已经成功的将《".$song_name."》点给<br>您的朋友".$receiver."&lt;".$to_email."&gt;</p>\n"
		."<p align=center> $message[0] </p>"
		."<script>setTimeout('window.close()', 1000000)</script>\n";
}else{
	html_head("点播歌曲");
	echo "<table width=400 align=center cellPadding=2 cellSpacing=0 background='images/bg1.gif'>
		   <tr><td colspan=2><p class=p3>请完成以下表单,每格必填！</p></td></tr>
           <tr height=18><td colspan=2 align=center background='images/bg3.gif'> <font color='$message[14]'>■</font>点播该歌曲 -传温馨情意 <font color='$message[14]'>■</font> </td></tr>
	       <form action=$PHP_SELF method=post>
	       <tr><td>即将点出歌曲：</td><td>$song_name ($singer_name)</td></tr>
	       <tr><td>您的朋友的大名：</td><td><input type=text name=receiver value='$receiver' class=input size=15></td></tr>
	       <tr><td>您的朋友的Email：</td><td><input type=text name=to_email class=input value='$to_email' size=35></td></tr>
	       <tr><td>您自己的大名：</td><td><input type=text name=sender value='$m_user[user_name]' class=input size=15 readonly=true></td></tr>
	       <tr><td>您自己的E-mail：</td><td><input type=text name=from_email value='$m_user[email]' class=input size=35 readonly=true></td></tr>
		   <tr><td>选择一个简短祝语：</td><td>
			   <select name='ssay' size='1' class='input'>
			   <option value='天天都有好心情'>天天都有好心情</option>
			   <option value='心想事成'>心想事成</option>
			   <option value='我好想你'>我好想你</option>
			   <option value='我爱你'>我爱你</option>
			   <option value='学习进步'>学习进步</option>
			   </select>
		   </td></tr>
	       <tr><td>写下甜言蜜语吧：</td><td><textarea name=says class=input cols=40 rows=6 wrap=on>$says</textarea></td></tr>
	       <tr><td>显示在点歌记录里？</td><td><input type=radio name=flag value='1' checked>是 <input type=radio name=flag value='0'> 否</td></tr>
           <tr height=20 bgcolor=$message[2]><td colspan=2 align=center>
			   <input type=hidden name=song_id value='$song_id'>
			   <input type=hidden name=song_name value='$song_name'>
			   <input type=hidden name=song_url value='$song_url'>
			   <input type=hidden name=singer_name value='$singer_name'>
			   <input type=hidden name=singer_id value='$singer_id'>
			   <input type=submit name=submit value='确定' class=button onclick=this.blur()>
			   <input type=reset value='重来' class=button onclick=this.blur()>
		   </td></tr></form>
           <tr align=center><td colspan=2>$message[0]</td></tr></table>";
}
echo "</body></html>";
exit;
?>