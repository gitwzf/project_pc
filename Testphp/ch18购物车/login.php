<?php
session_start();
//ʵ��Ӧ���У�����Ҫ�ж��û��������Ϣ�Ƿ���ȷ��������صĹؼ��ֵȴ���
$conn = mysql_connect("localhost","root", "");
mysql_select_db("phpbook_ch18") or die("�������ݿ�ʧ��!");
$str = "select userpass, userid from user where username='".$username."'";
$result = mysql_query($str);
$isExist = false;
if (!isset($prepage)) {
	session_register("prepage");
}
$prepage = "usershow.php";
while ($res = mysql_fetch_object($result)) {
	$isExist = true;
	$userid = $res->userid;
	$pass = $res->userpass;
}
//�û�������
if($isExist){
	//��֤�����Ƿ���ȷ
	if(md5($password) == $pass){
		$_SESSION["userid"] = $userid;
		if (isset($cart)){
			$cart->setOwner($userid);
		}
		header("Location:usershow.php?userid=".$userid);
		exit();
	}
	$returnpage = "Location:".$prepage."?errmsg=".urlencode("�������!");
	header($returnpage);
	exit();
}else{
	$returnpage = "Location:".$prepage."?errmsg=".urlencode("�û���������!");
	header($returnpage);
	exit();
}
?>