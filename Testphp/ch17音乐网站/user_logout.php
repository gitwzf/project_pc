<?php
session_start(); //初始session
//离线处理
$m_online_tag = "";
$m_user = array();

setcookie("m_user_id", "", time()-3156000);
unset($m_user_id);

session_unregister("m_user");
session_unregister("m_online_tag"); //删除已经注册变量

//unset the global varible

session_destroy();

//重定向到上页
print "<script>window.history.go(-1);</script>";
exit;
?>