<?php
session_start();
//�ж��û����벿��,��
//�û�������ȷ,û�в��淶��������
$conn = mysql_connect("localhost","root", "");
mysql_select_db("phpbook_ch18") or die("�������ݿ�ʧ��!");
$str = "select userpass, userid from user where username='".$username."'";
//�û�������
if($result = mysql_query($str))
{
	$res = mysql_fetch_object($result);
	$userid = $res->userid;
	$pass = $res->userpass;
	//��֤�����Ƿ���ȷ
	if(md5($password) == $pass)
	{
		$_SESSION["userid"] = $userid;
		$_SESSION["manager"]  = 'true';
		echo $username."���,��ӭ����!<br>";
		echo "<hr>";
		echo "<p><a href='usermanager.php' target='_blank'>�û�����</a></p>";
		echo "<p><a href='productmanage.php' target='_blank'>��Ʒ����</a></p>";
		echo "<p><a href='ordermanage.php' target='_blank'>�û���������</a></p>";
		exit();
	}
}
?>