<?php
//my_ordersong_del.php  删除点歌记录
//order_id..同时算算是不是自己删除，否则不得删除.
require('config.php');
require('public.php');
require('inc/db.class.php');

if(!$order_id)  error_quit3("非法操作！");
if(!islogin()) error_quit3("您还没有登录！$message[4] $message[6]"); //登录后才能享受此项服务。

$db->query("DELETE FROM ordersong WHERE order_id = '$order_id' AND sender = '$m_user[user_name]'");

print "<script>window.history.back();</script>";

$db->close();
exit;
?>