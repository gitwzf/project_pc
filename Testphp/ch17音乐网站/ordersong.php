<?php
//�㲥����
define("USE_MAIL", 1);
require_once('config.php');
require_once('public.php');
if(!islogin()) error_quit3("����û�е�¼��$message[4] $message[6]"); //��¼��������ܴ������
if(!$song_id) error_quit3("�Ƿ�������");
if($to_email != '' && $receiver != '' && $sender != '' && $from_email != ''){
	if(!emailcheck($to_email) || !emailcheck($from_email)) error_quit3("����ȷ��дEmail��ַ!");
	$now = date("Y-m-d H:i:s");
	$receiver1 = "<a href=mailto:".$to_email.">".$receiver."</a>";
	//��ӵ����ݿ�
	require_once('inc/db.class.php');
	$db->query("INSERT INTO ordersong (receiver, sender, song_name, song_id, singer_name, singer_id, date, ssay, says, flag) VALUES ('$receiver1', '$sender', '$song_name', '$song_id', '$singer_name', '$singer_id', '$now', '$ssay', '$says', '$flag')");
	$db->close();
	//����
	include_once("phpmailer/class.phpmailer.php");
	/**
	 * �����ʼ�ģ��������Ϣ
	 */
	define("SMTP_HOST","smtp.126.com");    		// SMTP ����
	define("SMTP_MAIL","phpbook_ch17@126.com");		// SMTP �û�email
	define("SMTP_PASS","12345");					  	// SMTP �õ�����

	define("SERVICE_MAIL","phpbook_ch17@126.com"); // SMTP �û�email
	define("SERVICE_NAME","�㲥���ָ���"); // SMTP �õ�����

	/**
	 * ʹ��phpmailer���ʼ�ģ��
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

		$mail->IsSMTP();                    			// ����ʹ��SMTP
		$mail->Host = SMTP_HOST;  					// ����SMTP��������ַ
		$mail->SMTPAuth = true;     					// ��SMTPȨ����֤
		$mail->Username = SMTP_MAIL;  					// SMTP �û���
		$mail->Password = SMTP_PASS; 					// SMTP ����������

		$mail->From = SERVICE_MAIL;						// ���÷����ߵ�ַ
		$mail->FromName = SERVICE_NAME;					// ���÷���������
		$mail->AddAddress($email, $user);				// ��ӽ����ߵ�ַ
		$mail->AddReplyTo(SERVICE_MAIL, SERVICE_NAME); 	// ���ûظ���ַ

		$mail->WordWrap = 50;                // ������ʾ��ʽ
		$mail->IsHTML(true);                 // �����ʼ�֧��html
		$mail->Subject = $subject;
		$mail->Body    = $body;
		$mail->AltBody = "";				// �ı����͵��ʼ�

		if(!$mail->Send())
		{
			return $mail->ErrorInfo;
		}
		return true;
	}

	$mail_subject = "[181��ʹ]".$sender."��������".$ssay;
	$mail_content = $receiver.": ���ã�\r\n"
		."���ĺ��� $sender �� $site_name ( $site_addr ) ������衣\r\n"
		."�㲥���� ��".$song_name."��- �ݳ�: $singer_name \r\n"
		."��������ַ���ػ����� $song_url \r\n"
		."���ʱ�� $now \r\n\r\n"
		."��(��)�������������£�\r\n"
		."----------------------------------------------------------------\r\n"
		.$says."\r\n"
		."----------------------------------------------------------------\r\n"
		."*/.    .   .    *      .\r\n"
		.".\\*    .    []           *  __\r\n"
		."*/ .   ./\\~~~~~~~~~~~~'\\. |��\r\n"
		."\*   ,/,..,\\,...........,\\.��\r\n"
		."||  ..��#  ����  �� �� | ����\r\n"
		."||  &&��   ��       ��'|'�� o  ".$site_name." \r\n"
		."|| ##����������������������    ".$site_addr." \r\n"
		."��������������������������������������������������������������������\r\n";

	$headers .= "From: ".$sender." <".$from_email.">\r\n";
	$headers .= "Reply-To: ".$sender." <".$from_email.">\r\n";
	$headers .= "Content-Type: text/plain\r\n";
	$headers .= "Return-Path: ".$from_email."\r\n";
	$headers .= "X-Mailer: cheng.Mar\r\n";
	$headers .= "X-Priority: 1\r\n";

	if($smtp_mail == 0)
		mail($to_email, $mail_subject, $mail_content, $headers);
	else {

		/***** ����һ��ʹ�÷���һ��Ҫ��config�ļ��е��ʼ����Ͳ��֣�Ϊ�˷�ֹ�ͷ�������ͻ���Ѿ���ע�͡�
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
		//��������ֱ��ʹ�õ�15�½�����phpmailer�������
		$tomail 		= $to_email;
		$sendresult = sendMail($tomail, $sender, $mail_subject , $mail_content);
	}
	html_head("��ϲ�����ɹ���");
	echo "<p align=center><span class=okmsg>��ϲ�������ɹ���</span><br>\n"
		."���Ѿ��ɹ��Ľ���".$song_name."�����<br>��������".$receiver."&lt;".$to_email."&gt;</p>\n"
		."<p align=center> $message[0] </p>"
		."<script>setTimeout('window.close()', 1000000)</script>\n";
}else{
	html_head("�㲥����");
	echo "<table width=400 align=center cellPadding=2 cellSpacing=0 background='images/bg1.gif'>
		   <tr><td colspan=2><p class=p3>��������±�,ÿ����</p></td></tr>
           <tr height=18><td colspan=2 align=center background='images/bg3.gif'> <font color='$message[14]'>��</font>�㲥�ø��� -����ܰ���� <font color='$message[14]'>��</font> </td></tr>
	       <form action=$PHP_SELF method=post>
	       <tr><td>�������������</td><td>$song_name ($singer_name)</td></tr>
	       <tr><td>�������ѵĴ�����</td><td><input type=text name=receiver value='$receiver' class=input size=15></td></tr>
	       <tr><td>�������ѵ�Email��</td><td><input type=text name=to_email class=input value='$to_email' size=35></td></tr>
	       <tr><td>���Լ��Ĵ�����</td><td><input type=text name=sender value='$m_user[user_name]' class=input size=15 readonly=true></td></tr>
	       <tr><td>���Լ���E-mail��</td><td><input type=text name=from_email value='$m_user[email]' class=input size=35 readonly=true></td></tr>
		   <tr><td>ѡ��һ�����ף�</td><td>
			   <select name='ssay' size='1' class='input'>
			   <option value='���춼�к�����'>���춼�к�����</option>
			   <option value='�����³�'>�����³�</option>
			   <option value='�Һ�����'>�Һ�����</option>
			   <option value='�Ұ���'>�Ұ���</option>
			   <option value='ѧϰ����'>ѧϰ����</option>
			   </select>
		   </td></tr>
	       <tr><td>д����������ɣ�</td><td><textarea name=says class=input cols=40 rows=6 wrap=on>$says</textarea></td></tr>
	       <tr><td>��ʾ�ڵ���¼�</td><td><input type=radio name=flag value='1' checked>�� <input type=radio name=flag value='0'> ��</td></tr>
           <tr height=20 bgcolor=$message[2]><td colspan=2 align=center>
			   <input type=hidden name=song_id value='$song_id'>
			   <input type=hidden name=song_name value='$song_name'>
			   <input type=hidden name=song_url value='$song_url'>
			   <input type=hidden name=singer_name value='$singer_name'>
			   <input type=hidden name=singer_id value='$singer_id'>
			   <input type=submit name=submit value='ȷ��' class=button onclick=this.blur()>
			   <input type=reset value='����' class=button onclick=this.blur()>
		   </td></tr></form>
           <tr align=center><td colspan=2>$message[0]</td></tr></table>";
}
echo "</body></html>";
exit;
?>