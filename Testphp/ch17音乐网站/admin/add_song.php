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
if(isset($step3)) { //���
	if(!$singer_id || !$cd_id) error_return("��������ʹ�÷�������");
	if($song_name == '') error_return('����������д!');
	if($rm_url == 'http://' || $rm_url =='') error_return('������ַ������д!');
	//mp3 url����
	for($i=1; $i<6; $i++){
		$str = "mp3".$i;
		if($$str != 'http://' && $$str != '') $mp3_url .= $$str.'|';
	}
	$geci1 = 0;
	$song_id = $db->query_first("SELECT MAX(song_id) FROM song");
	$song_id = $song_id[0] + 1;
	if(!empty($geci)) {
		$geci1 = 1;
		$db->query("INSERT INTO geci VALUES('$song_id', '$zuoqu', '$tianci', '$tigong', '$geci')");
	}
	$db->query("INSERT INTO song (song_id, song_name, rm_url, mp3_url, geci, cd_id, singer_id) VALUES ('$song_id', '$song_name', '$rm_url', '$mp3_url', '$geci1', '$cd_id', '$singer_id')");
	$db->query("UPDATE cd set song_num=song_num+1 WHERE cd_id = '$cd_id'");
	$db->query("UPDATE singer set song_num=song_num+1 WHERE singer_id = '$singer_id'");
	echo "<p><span class=okmsg>��ӳɹ�!</span> <a href=main.php>���˷���</a></p>";
}else if(isset($step2)){ //��ʾ���ҳ��
	if(!$singer_name || !$singer_id || !$cd_name || !$cd_id) error_return("��������ʹ�÷�������");
	$singer_name = urldecode($singer_name);
	$cd_name = urldecode($cd_name);
	echo "<h3>����¸�(3) -- $singer_name -- ��д����</h3> ";
	echo "<h3>ר��: $cd_name </h3>";
	echo "<form action=$PHP_SELF method=post><input type=hidden name=step3 value='1'><input type=hidden name=singer_id value='$singer_id'><input type=hidden name=cd_id value='$cd_id'>�������ƣ� <input type=text name=song_name maxlength=40 size=25><br>������ַ�� <input type=text name=rm_url size=35 value='http://'> (֧��real/mp3��ʽ,http://��ͷ)<br>MP3����1�� <input type=text name=mp31 size=35 value='http://'> (http:// ��ͷ�) <br>MP3����2�� <input type=text name=mp32 size=35 value='http://'> (http:// ��ͷ�) <br>MP3����3�� <input type=text name=mp33 size=35 value='http://'> (http:// ��ͷ�) <br>MP3����4�� <input type=text name=mp34 size=35 value='http://'> (http:// ��ͷ�) <br>MP3����5�� <input type=text name=mp35 size=35 value='http://'> (http:// ��ͷ�) <br> �� �� �ߣ�<input type=text name=zuoqu size=20> <br>�� �� �ߣ� <input type=text name=tianci size=20> <br> ������ݣ�<textarea name=geci rows=6 cols=45></textarea>(��֧��HTML)<br><p align=left><input type=submit name=submit value='�ύ'><input type=reset value='��д'></p></form>";
}else if(isset($step1)){
	if(!$singer_name || !$singer_id) error_return("��������ʹ�÷�������");
	$cd = $db->query("SELECT cd_id,cd_name FROM cd WHERE singer_id = '$singer_id' ORDER BY pub_date DESC");
	$singer_name = urldecode($singer_name); //url��ԭ
	echo "<h3>����¸�(2) -- $singer_name -- ѡ��ר��</h3> ";
	echo "Ŀǰ��".$db->num_rows($cd)."��ר��,���������ִ�ר���е����֡�(������ʱ������) <a href='add_cd.php?singer_id=$singer_id&singer_name=".urlencode($singer_name)."'>��Ҫ����?</a><hr size=1 noshade>";
	while($tmp = $db->fetch_array($cd)){
		echo "<a href='$PHP_SELF?step2=1&singer_id=$singer_id&singer_name=".urlencode($singer_name)."&cd_name=".urlencode($tmp[cd_name])."&cd_id=$tmp[cd_id]'>$tmp[cd_name]</a><br>";
	}
}else {//ѡ�����
	$singer = $db->query("SELECT singer_id,singer_name FROM singer ORDER BY alpha");
	echo "<h3>����¸�(1) -- ѡ�����</h3> ";
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