<?php 
//����,�����ı�����tmpgeci����
require_once('../config.php');
require_once('../public.php');
require_once('../inc/db.class.php');
if(!is_admin()) error_quit3("���������ǹ���Ա��û�е�¼��");
//����ʱͨ��js����
function error_return($msg) {
	echo "<script language=javascript> alert('".$msg."'); window.history.go(-1);</script>";
	exit;
}
if(isset($step2)) { //�ύ����
	if(!$id || !$song_id) error_return('ϵͳ����������');
	if($geci == '') error_return('��ʱ�����д!');
	if($do != 'del' && $do != 'add') error_return('ϵͳ����������');
	$check = $db->query_first("SELECT song_id FROM geci WHERE song_id = '$song_id'");
	if(($do != 'del') && ($song_id == $check[0])) {
		$do = 'del';
		echo "<p> Warning: ����Ѿ���������ɾ������</p>\n";
	}
	if($do == 'add'){
		$geci = addslashes($geci);
		$db->query("INSERT INTO geci VALUES('$song_id', '$zuoqu', '$tianci', '$tigong', '$geci')");
		$db->query("UPDATE user SET numpost=numpost+1 WHERE user_name = '$tigong'"); //�Ӿ���
		$db->query("UPDATE song SET geci = '1' WHERE song_id = '$song_id'");
	}
	$db->query("DELETE FROM tmpgeci WHERE id = '$id'");
	$db->close();
	@header("Location: $PHP_SELF");
	echo "<a href='$PHP_SELF'> ���� </a>";
	exit;
}else if(isset($step1)) { //������
	html_head("������");
	if($id == '') error_return('ϵͳ����������');
	$tmp = $db->query_first("SELECT * FROM tmpgeci WHERE id = '$id'");
	echo "<h3>������(2)</h3> ";
	echo "<p>$tmp[tigong] �ṩ�� $tmp[song_name] �ĸ������: </p>";
	echo "<form method=post action='".$_SERVER['PHP_SELF']."'>
   	<input type=hidden name=id value='$tmp[id]'>
   	<input type=hidden name=song_id value='$tmp[song_id]'>
   	<input type=hidden name=tigong value='$tmp[tigong]'>
   	����: <input type=text value='$tmp[zuoqu]' size=15> ����: <input type=text value='$tmp[tianci]' size=15> <br><br>
   ���: <textarea cols=55 rows=12 name=geci>$tmp[geci]</textarea> <br>
   ����: <input type=radio name=do value='add' checked>ͨ�� <input type=radio name=do value='del'>ɾ��
   <input type=submit name=step2 value='ȷ��'>
   <input type=reset  value='����'>
   </form>";
}else { //ѡ�����
	html_head("������");
	$tmpgeci = $db->query("SELECT id, tigong, song_name FROM tmpgeci");
	echo "<h3>������(1)</h3> ";
	echo "Ŀǰ��".$db->num_rows($tmpgeci)."ƪ, ���������ִ�����ʱ�����д���<hr size=1 noshade>";
	while($tmp = $db->fetch_array($tmpgeci)) {
		echo "�� <a href='$PHP_SELF?step1=1&id=$tmp[id]'>$tmp[song_name]</a> (by $tmp[tigong]) <br>";
	}
}
echo "</body></html>";
$db->close();
exit;
?>