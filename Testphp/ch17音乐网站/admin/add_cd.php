<?php
//���ר��
require_once('../config.php');
require_once('../public.php');
require_once('../inc/db.class.php');
if(!is_admin())  error_quit3("���������ǹ���Ա��û�е�¼��");
function error_return($msg) {
	print "<script language=javascript> alert('".$msg."'); window.history.go(-1);</script>";
	exit;
}
html_head("���ר��");
if(isset($step2)) { //ִ����ӵĲ���
	if($singer_id == '') error_return('ϵͳ����������');
	if($cd_name == '') error_return('ר�����Ʊ�����д!');
	if($imgurl == 'http://') $imgurl = '';
	$add_date = date("Y-m-d H:i:s");
	$check = $db->query_first("SELECT count(*) FROM cd WHERE cd_name = '$cd_name'");
	if($check[0] > 0) {
		error_return('ר���Ѿ�����!');
	}
	$db->query("INSERT INTO cd (cd_name, imgurl, introduce, lang, pub_date, add_date, singer_id) VALUES ('$cd_name', '$imgurl', '$introduce', '$lang', '$pub_date', '$add_date', '$singer_id')");
	$db->query("UPDATE singer set cd_num=cd_num+1 WHERE singer_id = '$singer_id'");
	echo "<p><span class=okmsg>��ӳɹ�!</span> <a href=main.php>���˷���</a></p>";
}
else if(isset($step1)){//��д��ص�����ҳ��
	if(!$singer_name || !$singer_id) error_return("��������ʹ�÷�������");
	$singer_name = urldecode($singer_name);
	echo "<h3>���ר��(2) -- $singer_name -- ��д����</h3> ";
	echo "<form action=$PHP_SELF method=post><input type=hidden name=step2 value='1'><input type=hidden name=singer_id value='$singer_id'>ר�����ƣ� <input type=text name=cd_name maxlength=40 size=15> 	<br>�������ࣺ <input type=text name=lang size=15> (Ӣ��/����/̨��/Խ�� etc)<br>����ʱ�䣺 <input type=text name=pub_date size=15> (��: 2006-12-23) <br>ר�����棺 <input type=text name=imgurl maxlength=200 size=40 value='http://'>(û��������, ��<font color=red>http://</font>��ͷ) <br>ר����飺<textarea name=introduce rows=4 cols=45></textarea>(��֧��HTML)<br><p align=left><input type=submit name=submit value='�ύ'><input type=reset value='��д'></p></form>";
}else { //ѡ�����
	$sql = "SELECT singer_id,singer_name FROM singer ORDER BY alpha, singer_name";
	$singer = $db->query($sql);
	echo "<h3>���ר��(1) -- ѡ�����</h3>";
	echo "Ŀǰ��".$db->num_rows($singer)."λ����,���������ִ�����е����֡�(����ĸ˳��) <a href=add_singer.php>��Ҫ����?</a><hr size=1 noshade>";
	$i = 1;
	while($tmp = $db->fetch_array($singer)){
		echo "<a href='$PHP_SELF?step1=1&singer_id=$tmp[singer_id]&singer_name=".urlencode($tmp[singer_name])."'>$tmp[singer_name]</a>";
		if($i == 10) { $i = 1; echo "<br>\n"; }
		else echo "��";
		$i ++;
	}
}
echo "</body></html>";
$db->close();
exit;
?>