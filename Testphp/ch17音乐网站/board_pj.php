<?php
require('config.php');
require('public.php');
require('inc/db.class.php');

$pj = intval($pj);
if(!is_admin()) error_quit3("您没有操作权限!");
$db->query("UPDATE post SET flag = $pj WHERE post_id = '$post_id'");

html_head("设置成功");
print "
<br><br>
<div class=okmsg>修改成功</div> 请刷新列表。<p align=center> $message[0] </p>
<script language=javascript>setTimeout('window.history.go(-1)', 10)</script>
</body></html>";

$db->close();
exit;
?>