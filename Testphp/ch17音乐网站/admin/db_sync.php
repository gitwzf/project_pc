<?php
//��������
require('../config.php');
require('../public.php');
require('../inc/db.class.php');
if(!is_admin()) error_quit3("���������ǹ���Ա��û�е�¼��");
//����ʱ����js����
function error_return($msg) {
	global $db;
	$db->close();
	print "<script language=javascript> alert('".$msg."'); window.history.go(-1);</script>";
	exit;
}
html_head("��վ��������");
echo "<br><h3>��������</h3>\n";
//��ʼ�ʺ�����
if($do == "acct_sync") {
	if(!$tl) $tl = 120;
	$res = $db->query("SELECT user_name FROM user WHERE (TO_DAYS(NOW()) - TO_DAYS(lastlogin)) > $tl");
	$tol = $db->num_rows();
	while($tmp = $db->fetch_array($res)) {
		$tmp[0] = addslashes($tmp[0]);
		$db->query("DELETE FROM mail WHERE '$tmp[0]' IN (sender, receiver)");
		$db->query("DELETE FROM ordersong WHERE '$tmp[0]' IN (sender, receiver)");
		$db->query("DELETE FROM user WHERE user_name = '$tmp[0]'");
	}
	echo "ϵͳ���������<font color=red>".$tl."</font>����ʺ�<font color=red>"
		.$tol."</font>��(ע��δ�������)��<br>\n<a href=".$PHP_SELF.">���˷��ء���</a>\n";
} else if($do == "acct_del") { //ɾ���ʺ�
	if(!$tl) error_return("�������ʺţ�");
	$chk = $db->query_first("SELECT user_id FROM user WHERE user_name = '$tl'");
	$chk = $chk[0];
	if(empty($chk)) error_return("����Ĵ��ţ�".$tl);
	$db->query("DELETE FROM mail WHERE '$tl' IN (sender, receiver)");
	$db->query("DELETE FROM ordersong WHERE '$tl' IN (sender, receiver)");
	$db->query("DELETE FROM post WHERE author = '$tl'");
	$db->query("DELETE FROM user WHERE user_id = '$chk'");
	echo "ϵͳ�����<font color=red>".$tl."</font>���������¡�����¼�����Ļ�����<br>\n"
		."<a href=".$PHP_SELF.">���˷��ء���</a>\n";
} else if($do == "post_sync") { //����������
	$tl2 = intval($tl / 2);
	//������Ϣ
	$cnt2 = $db->query_first("SELECT COUNT(*) FROM post WHERE brd_id = 0 AND flag != 1 AND (TO_DAYS(NOW()) - TO_DAYS(date)) > $tl2");
	$cnt2 = $cnt2[0];
	$db->query("DELETE FROM post WHERE brd_id = 0 AND flag != 1 AND (TO_DAYS(NOW()) - TO_DAYS(date)) > $tl2");
	//������Ϣ
	$cnt = $db->query_first("SELECT COUNT(*) FROM post WHERE flag != 1 AND (TO_DAYS(NOW()) - TO_DAYS(date)) > $tl");
	$cnt = $cnt[0];
	$db->query("DELETE FROM post WHERE flag != 1 AND (TO_DAYS(NOW()) - TO_DAYS(date)) > $tl");
	echo "ϵͳ�����<font color=red>".$tl."</font>��ǰ����������<font color=red>".$cnt."</font>����"
	."<font color=red>".$tl2."</font>��ǰ����������<font color=red>".$cnt2."</font>�� ����<br>\n"
	."<a href=".$PHP_SELF.">���˷��ء���</a>\n";
} else if($do == "mail_sync") { //�������Ļ�
	if(!$tl) $tl = 60;
	$cnt = $db->query_first("SELECT COUNT(*) FROM mail WHERE flag != '*' AND (TO_DAYS(NOW()) - TO_DAYS(date)) > $tl");
	$cnt = $cnt[0];
	$db->query("DELETE FROM mail WHERE flag != '*' AND (TO_DAYS(NOW()) - TO_DAYS(date)) > $tl");
	echo "ϵͳ�����<font color=red>".$tl."</font>��ǰ�ĸ������Ļ����˴ι������<font color=red>".$cnt."</font>����¼����<br>\n"."<a href=".$PHP_SELF.">���˷��ء���</a>\n";
} else if($do == "os_sync") { //����¼
	if(!$tl) $tl = 60;
	$cnt = $db->query_first("SELECT COUNT(*) FROM ordersong WHERE (TO_DAYS(NOW()) - TO_DAYS(date)) > $tl");
	$cnt = $cnt[0];
	$db->query("DELETE FROM ordersong WHERE (TO_DAYS(NOW()) - TO_DAYS(date)) > $tl");
		echo "ϵͳ�����<font color=red>".$tl."</font>��ǰ�ĵ���¼���˴ι������<font color=red>".$cnt."</font>����¼����<br>\n"	."<a href=".$_SERVER['PHP_SELF'].">���˷��ء���</a>\n";
} else if($do == "z_numli") { //�������
	$db->query("UPDATE user SET numlisten = 0 WHERE user_name LIKE '$tl'");
	if($tl == '%%') $tl = "ȫ���û�";
	echo "ϵͳ�ѽ� <font color=red>".$tl."</font>������������㡣<br>\n"
		."<a href=".$_SERVER['PHP_SELF'].">���˷��ء���</a>\n";
} else if($do == "z_numlo") { //��վ����
	$db->query("UPDATE user SET numlogin = 0 WHERE user_name LIKE '$tl'");
	if($tl == '%%') $tl = "ȫ���û�";
	echo "ϵͳ�ѽ� <font color=red>".$tl."</font>����վ�������㡣<br>\n"
		."<a href=".$_SERVER['PHP_SELF'].">���˷��ء���</a>\n";
} else if($do == "z_numpo") { //���Ĵ���
	$db->query("UPDATE user SET numpost = 0 WHERE user_name LIKE '$tl'");
	if($tl == '%%') $tl = "ȫ���û�";
	echo "ϵͳ�ѽ� <font color=red>".$tl."</font>�ķ��Ĵ������㡣<br>\n"
		."<a href=".$_SERVER['PHP_SELF'].">���˷��ء���</a>\n";
} else { //��ʾ�������
print <<<__EOF__
<ol type="1">
	<li>
		<form action="$PHP_SELF" method="post">
		ɾ�����г���<input type="text" size="6" value="60" name="tl">��(Ĭ��Ϊ60��)�ĵ���¼��
		<input type="hidden" name="do" value="os_sync">
		<input type="submit" value="ȷ��"> 
		</form>
	</li>
	<li>
		<form action="$PHP_SELF" method="post">
		ɾ�����г���<input type="text" size="6" value="60" name="tl">��(Ĭ��Ϊ60��)���û����Ļ���
		<input type="hidden" name="do" value="mail_sync">
		<input type="submit" value="ȷ��"> 
		</li>
		</form>
	<li>
		<form action="$PHP_SELF" method="post">
		ɾ�����г���<input type="text" size="6" value="120" name="tl">��(Ĭ��Ϊ120��, ����������ȡ����)���������ۣ�
		<input type="hidden" name="do" value="post_sync">
		<input type="submit" value="ȷ��"> 
		</li>
		</form>
	<li>
		<form action="$PHP_SELF" method="post">
		ɾ�����г���<input type="text" size="6" value="120" name="tl">��(Ĭ��Ϊ120��)û���Ϲ�վ�Ļ�Ա��
		<input type="hidden" name="do" value="acct_sync">
		<input type="submit" value="ȷ��"> 
		</form>
	</li>
	<li>
		<form action="$PHP_SELF" method="post">
		ɾ��Υ���ʺ�<input type="text" size="20"  name="tl"> (����վ���û�����)��
		<input type="hidden" name="do" value="acct_del">
		<input type="submit" value="ȷ��"> 
		</form>
	</li>
	<li>
		<form action="$PHP_SELF" method="post">
		�����������<input type="text" size="20"  name="tl"> (����վ���û�����, ���� %% ��ʾȫ���û�)��
		<input type="hidden" name="do" value="z_numli">
		<input type="submit" value="ȷ��"> 
		</form>
	</li>
	<li>
		<form action="$PHP_SELF" method="post">
		�����������<input type="text" size="20"  name="tl"> (����վ���û�����, ���� %% ��ʾȫ���û�)��
		<input type="hidden" name="do" value="z_numpo">
		<input type="submit" value="ȷ��"> 
		</form>
	</li>
	<li>
		<form action="$PHP_SELF" method="post">
		��վ��������<input type="text" size="20"  name="tl"> (����վ���û�����, ���� %% ��ʾȫ���û�)��
		<input type="hidden" name="do" value="z_numlo">
		<input type="submit" value="ȷ��"> 
		</form>
	</li>
</ol>
__EOF__;
}
echo "</body></html>\n";
$db->close();
exit;
?>