<?php
//�³�Աע��
require_once('config.php');
require_once('public.php');
if($passwd != $passwd1) error_quit3("��������������벻һ��!");
if($user_name == '' || $province == '' || $passwd == '' || $email == '' || $sex == ''){
	include_once('inc/reg_form.inc.php');
} else {
	if(eregi("[;[@|+\\\/?,!'\"{}~%#`*&\t^()]+", $user_name)) error_quit3("�û������Ƿ��ַ�!");
	if($err = checkword($user_name)) error_quit3($err);
	if(strlen($user_name) > 15) error_quit3('�û���̫������Ҫ������12���ַ�����');
	if(!emailcheck($email)) error_quit3("����ȷ��д����Email��ַ!");
	require_once('inc/db.class.php');
	$exits = $db->query_first("SELECT user_id FROM user WHERE user_name = '$user_name'");
	if($exits[0]) {
		error_quit3("�û��� $user_name �Ѿ�����!");
	}
	$firstlogin = date(U);
	$lastfrom = getenv("REMOTE_ADDR");
	$query = "INSERT INTO user (user_name, passwd, email, sex, oicq, province, addr, yearold, homepage, sign, firstlogin, lastfrom) VALUES ('$user_name', '$passwd', '$email', '$sex', '$oicq', '$province', '$addr', '$yearold', '$homepage', '$sign', '$firstlogin', '$lastfrom')";
	$db->query($query);
	//������ʾ��Ϣ
	html_head("��ϲ����ע��ɹ�");
	echo "<br><br><br>
		<p class=okmsg align=center>��ϲ����ע��ɹ���[�ʺţ�<font color=red>".$user_name."</font>]</p>
		<p align=center> $message[0] </p>
		<script>setTimeout('window.close()', 5000);</script>
		</body></html>";
	$db->close();
}
exit;
?>