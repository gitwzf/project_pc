<?php
//��PHP 4.1.0�汾������ķ�������ʹ��
   echo $_POST['username'];
   echo $_REQUEST['username'];
   import_request_variables('p', 'p_');
   echo $p_username;
//��PHP 3��ʼHTTP_POST_VARS�������Ա�ʹ��
   echo $HTTP_POST_VARS['username'];
//���PHP�����ļ��е�register_globals����������Ϊon ʱ������ֱ��ʹ�ñ���ǩ�����ơ�ע�⣬��PHP 4.2.0�汾��ʼĬ��ֵΪregister_globals Ϊoff�����Բ��Ƽ�ʹ�ô��ַ�����
   echo $username;
?>
