<?php 
include ("./sendmail.class.php");
$mail = new sendmail();
$email = "���ã�����һ�������ʼ���";
$sendmail = $mail->send_mail("smtp.263.net", "PHPBOOK", true); //��ʾ��ʾ��Ϣ
if($mail->send("chengwei@263.net", "lyh.hui@263.net", "����SOCKET�ʼ�", $email)) {
	echo "���ͳɹ���<br>";
}else{
	echo "����ʧ�ܣ�<br>";
}
?>