<?php
/* ʹ��qmail�����ʼ����� */
function send_check_mail($email, $subject,$uid,$buffer){
	$command =  "/var/qmail/bin/qmail ".$email; //qmail�����ַ��$email��Ҫ���͵ĵ�ַ
	$handle = popen($command, "w"); //�򿪹ܵ�
	if (!$handle) {
		return false;
	}

	$from = "admin@net9.org"; //������
	fwrite($handle, "From: ".$from."\n"); //���ܵ�д����
	fwrite($handle, "Return-Path: ".$from."\n");
	fwrite($handle, "To: ".$uid."\n");
	fwrite($handle, "Subject: ".$subject."\n");
	fwrite($handle, "Mime-Version: 1.0\n");
	fwrite($handle, "Content-Type: text/html; charset=\"gb2312\"\n\n");
	fwrite($handle, $buffer."\n");
	pclose($handle); //�رչܵ�

	return true;
}

//�����ʼ�ʾ��
$subject = "�����ʼ�";

$uid = $_POST['uid']; //from��Ϣ
$content = "<html><body>".$u_email." ���ã�<br><br>лл�����ʼ����ԣ�<br</body></html>"; //������Ϣ

$u_email = "cheng.vip@yahoo.com.cn"; //���͵�������
if (send_check_mail($u_email, $subject, $uid, $content)) {

	echo "��ϲ������ͶƱ�ʼ����������䣡<br><br>�����������䣺<font color=#CC0033>".$u_email." </font><br><br>". $close;
} else {
	echo "�ܲ��ң�����ͶƱ�ʼ�����������ʧ�ܣ������Ի�����ϵ������Ա��<br><br>". $close;
}
?>