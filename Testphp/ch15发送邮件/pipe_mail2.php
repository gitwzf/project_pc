<?php
$senders_email = "admin@sina.com.cn";
$comments = "�ܵ������ʼ�ע��";
$pp = popen("/usr/sbin/sendmail -t", "w") or die("����sendmailӦ�ó���ʧ�ܣ�");
fputs($pp, "To: wei.cheng@sina.com.cn\r\n");
fputs($pp, "Reply-to: $senders_email\r\n");
fputs($pp, "From: $senders_email\r\n");
fputs($pp, "Subject �������⣡\r\n\r\n");
fputs($pp, "$senders_email ��������ע��:\r\n");
fputs($pp, $comments);
pclose($pp) or die("���ܹرյ�sendmail�Ĺܵ����ӣ�");
?>