<?php
require('config.php');
require('public.php');
require('inc/db.class.php');

$pj = intval($pj);
if(!is_admin()) error_quit3("��û�в���Ȩ��!");
$db->query("UPDATE post SET flag = $pj WHERE post_id = '$post_id'");

html_head("���óɹ�");
print "
<br><br>
<div class=okmsg>�޸ĳɹ�</div> ��ˢ���б�<p align=center> $message[0] </p>
<script language=javascript>setTimeout('window.history.go(-1)', 10)</script>
</body></html>";

$db->close();
exit;
?>