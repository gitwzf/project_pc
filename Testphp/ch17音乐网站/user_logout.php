<?php
session_start(); //��ʼsession
//���ߴ���
$m_online_tag = "";
$m_user = array();

setcookie("m_user_id", "", time()-3156000);
unset($m_user_id);

session_unregister("m_user");
session_unregister("m_online_tag"); //ɾ���Ѿ�ע�����

//unset the global varible

session_destroy();

//�ض�����ҳ
print "<script>window.history.go(-1);</script>";
exit;
?>