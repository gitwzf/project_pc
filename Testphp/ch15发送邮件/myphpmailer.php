<?php
require("phpmailer/class.phpmailer.php");

class my_phpmailer extends phpmailer {
    //Ϊ�Լ��ĳ���������ص�Ĭ��ֵ
    var $From     = "wei.cheng@dudu-inc.com";
	var $Username = "wei.cheng@dudu-inc.com";
	var $Password = "1234567890";
    var $FromName = "Mr.Cheng";
    var $Host     = "mail.dudu-inc.com";
    var $Mailer   = "smtp";        //Ҳ����ʹ��IsSMTP()������mailerΪsmtp��������
    var $WordWrap = 75;var $SMTPAuth = true; 

    //��չĬ�ϵĴ��������error_handler
    function error_handler($msg) {
        print("�����ʼ�����");
        print("������Ϣ��������:");
        printf("%s", $msg);
        exit;
    }

    //�����Լ���˽�з����������Ӧ�Ĺ���
    function do_something($something) {
        //����Ӧ���е���ع���
    }
}
?>