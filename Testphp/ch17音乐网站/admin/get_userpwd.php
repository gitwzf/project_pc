<?php
//��ȡ�û�����
require('../config.php');
require('../public.php');
require('../inc/db.class.php');

if(!is_admin())  error_quit3("���������ǹ���Ա��û�е�¼��");

function error_return($msg) { //����ʱ����javascript����
	global $db;
	$db->close();
	print "<script language=javascript> alert('".$msg."'); window.history.go(-1);</script>";
	exit;
}

html_head("ǿ���һ�����");
print "<br><h3>ǿ���һ�����</h3>\n";

if($do == "getpw") {
	if(!$tl) error_return("�������ʺţ�");
	$chk = $db->query_first("SELECT passwd FROM user WHERE user_name = '$tl'");
	$chk = $chk[0];
	if(empty($chk)) error_return("����Ĵ��ţ�".$tl);

	echo "ϵͳ�Ѳ��<font color=red>".$tl."</font>������Ϊ��<font color=red>".$chk."</font><br>\n"
	."<a href=".$PHP_SELF.">���˷��ء���</a>\n";

} else { // give Form

print <<<__EOF__
	<form action="$PHP_SELF" method="post">
	��Ҫ�һ�������ʺţ�<input type="text" size="20"  name="tl"> (����վ���û�����)��
	<input type="hidden" name="do" value="getpw">
	<input type="submit" value="ȷ��"> 
	</form>
__EOF__;
}

print "</body></html>\n";
$db->close();
exit;

?>