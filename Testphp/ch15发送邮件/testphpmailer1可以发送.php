<?php
include_once("phpmailer/class.phpmailer.php");
/**
 * �����ʼ�ģ��������Ϣ
 */
define("SMTP_HOST","mail.dudu-inc.com");    		// SMTP ����
define("SMTP_MAIL","phpbook_all@126.com");		// SMTP �û�email
define("SMTP_PASS","phpbooks");					  	// SMTP �õ�����

define("SERVICE_MAIL","phpbook_all@126.com"); // SMTP �û�email
define("SERVICE_NAME","PHPBOOK�ʼ�����"); // SMTP �õ�����

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

//��ʼ���Ͳ����ʼ�
$tomail 		= "phpbook_ch15@126.com";
$user 			= "Mr.Cheng";
$_mailSubject 	= "�ʼ�����ʾ��!"; // �����û����ʼ�����
$_mailBody		= "<a href='http://business.sina.com/' style='color:red'>������</a>"; // �ʼ�����
$sendresult = sendMail($tomail, $user, $_mailSubject , $_mailBody);
if($sendresult){
	//���ͳɹ�����ӡ�ɹ���Ϣ��ʾ
	echo "��Ϣ�Ѿ����ɹ����ͣ�";
}
?>