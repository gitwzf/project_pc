<?php
//����ظ�
require('public.php');
require('inc/db.class.php');
require('config.php');

if(!islogin()) error_quit3("����û�е�¼��$message[4] $message[6]"); //��¼��������ܴ������
if(!$post_id) error_quit3('�Ƿ�����');

$tmp = $db->query_first("SELECT author FROM post WHERE post_id = '$post_id'");
if(($tmp[0] != $m_user[user_name]) && !is_admin()) error_quit3("��û�в���Ȩ��!");
$db->query("DELETE FROM post WHERE post_id = '$post_id'");
$db->query("UPDATE user SET numpost = numpost-1 WHERE user_name = '$tmp[0]'");

html_head("ɾ���ɹ�");
print "
<br><br>
<div class=okmsg>ɾ���ɹ�</div> ��ˢ���б�<p align=center> $message[0] </p>
<script language=javascript>setTimeout('window.history.go(-1)', 10)</script>
</body></html>";

$db->close();
exit;
?>