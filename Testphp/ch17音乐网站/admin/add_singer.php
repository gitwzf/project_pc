<?php
//��Ӹ���
require_once('../config.php');
require_once('../public.php');
require_once('../inc/db.class.php');
if(!is_admin())  error_quit3("���������ǹ���Ա��û�е�¼��");
function error_return($msg) {
	echo "<script language=javascript> alert('".$msg."'); window.history.go(-1);</script>";
	exit;
}
html_head("��Ӹ���");
if(isset($submit)) {
	if($alpha < 'a' || $alpah > 'z') error_return('����ĸ����,����д');
	if($singer_name == '') error_return('��������������д!');
	if($imgurl == 'http://') $imgurl = '';
	$check = $db->query_first("SELECT count(*) FROM singer WHERE singer_name = '$singer_name'");
	if($check[0] > 0) {
		error_return('�����Ѿ�����!');
	}
	$db->query("INSERT INTO singer (alpha, singer_name, imgurl, introduce, area_id, cate_id) VALUES ('$alpha', '$singer_name', '$imgurl', '$introduce', '$area_id', '$cate_id')");
	echo "<p><span class=okmsg>��ӳɹ�!</span> <a href=main.php>���˷���</a></p>";
}else{
	$cate = $db->query("SELECT * FROM cate");
	$area = $db->query("SELECT * FROM area");
	echo "<h3>��Ӹ���</h3>";
	echo "<form action=$PHP_SELF method=post>ƴ�����֣� <select name=alpha size=1>";
	$a = "a";
	for(;;){
		$a = strval($a);
		echo "<option value='$a'>$a</option>\n";
		if($a == "z") break;
		$a++;
	}
	echo "</select> (��ѡ�񣬼�Ϊ���ֵ�ƴ������ĸ����������) <br>���������� <input type=text name=singer_name maxlength=40 size=15><br>������� <select name=cate_id size=1> ";
	while($tmp = $db->fetch_array($cate))
		echo "<option value='$tmp[cate_id]'> $tmp[cate_name] </option>\n";
	echo "</select>(��ѡ��)<br>���ڵ���	: <select name=area_id size=1>";
	while($tmp = $db->fetch_array($area))
		echo "<option value='$tmp[area_id]'> $tmp[area_name] </option>\n";
	echo "</select>(��ѡ��)<br>ͷ�����ӣ� <input type=text name=imgurl maxlength=200 size=40 value='http://'>(û��������, ��<font color=red>http://</font>��ͷ) <br>���ּ�飺<textarea name=introduce rows=10 cols=45></textarea>(��֧��HTML)<br><p align=left><input type=submit name=submit value='�ύ'><input type=reset value='��д'></p></form>";
}
echo "</body></html>";
$db->close();
exit;
?>