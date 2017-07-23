<?php
//版面回复
require('public.php');
require('inc/db.class.php');
require('config.php');

if(!islogin()) error_quit3("您还没有登录！$message[4] $message[6]"); //登录后才能享受此项服务。
if(!$post_id) error_quit3('非法操作');

$tmp = $db->query_first("SELECT author FROM post WHERE post_id = '$post_id'");
if(($tmp[0] != $m_user[user_name]) && !is_admin()) error_quit3("您没有操作权限!");
$db->query("DELETE FROM post WHERE post_id = '$post_id'");
$db->query("UPDATE user SET numpost = numpost-1 WHERE user_name = '$tmp[0]'");

html_head("删除成功");
print "
<br><br>
<div class=okmsg>删除成功</div> 请刷新列表。<p align=center> $message[0] </p>
<script language=javascript>setTimeout('window.history.go(-1)', 10)</script>
</body></html>";

$db->close();
exit;
?>