<?php
	if(!isset($_SERVER['PHP_AUTH_USER']))
	{
	//���$PHP_AUTH_USER����������
		header("WWW-Authenticate:Basic realm='�û���֤'");
		header("HTTP/1.0 401 Unauthorized");
		echo "����������ȡ�����ͻ���ʾ��仰��\r\n";
		exit;
	}else{
		echo "��½�ɹ�����ӭ".$_SERVER['PHP_AUTH_USER']."�ĵ�����";
		echo "���ղ����������Ϊ".$_SERVER['PHP_AUTH_PW']."�������μ���������";
	}
?>