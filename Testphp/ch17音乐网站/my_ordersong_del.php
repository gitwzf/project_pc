<?php
//my_ordersong_del.php  ɾ������¼
//order_id..ͬʱ�����ǲ����Լ�ɾ�������򲻵�ɾ��.
require('config.php');
require('public.php');
require('inc/db.class.php');

if(!$order_id)  error_quit3("�Ƿ�������");
if(!islogin()) error_quit3("����û�е�¼��$message[4] $message[6]"); //��¼��������ܴ������

$db->query("DELETE FROM ordersong WHERE order_id = '$order_id' AND sender = '$m_user[user_name]'");

print "<script>window.history.back();</script>";

$db->close();
exit;
?>