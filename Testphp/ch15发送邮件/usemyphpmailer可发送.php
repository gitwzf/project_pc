<?php
require("myphpmailer.php");

//��ʼ����չ��
$mail = new my_phpmailer;

//���ڿ�ʼ�����Ҫ�����ʼ��Ļ�����Ϣ�������ʼ���ַ�����⡢���ݺ͸����ȡ�
$mail->AddAddress("phpbook_ch15@126.com", "Mr.Cheng");
$mail->Subject = "���ǲ����ʼ����͵����⣡";
$mail->Body    = "�����ʼ�����Ϣ�壡";
$mail->AddAttachment("c:/phpbook.zip", "phpbook.zip");  //���Ӹ���
//��ʼ�����ʼ�
if(!$mail->Send())
{
   //����ʧ�ܣ���ӡ������Ϣ��ʾ
   echo "���͹����з���һ����������ϸ���ģ�";
   exit;
}
//���ͳɹ�����ӡ�ɹ���Ϣ��ʾ
echo "��Ϣ�Ѿ����ɹ����ͣ�";
?>