<?php
$senders_email = "admin@sina.com.cn";
$comments = "管道发送邮件注释";
$pp = popen("/usr/sbin/sendmail -t", "w") or die("调用sendmail应用程序失败！");
fputs($pp, "To: wei.cheng@sina.com.cn\r\n");
fputs($pp, "Reply-to: $senders_email\r\n");
fputs($pp, "From: $senders_email\r\n");
fputs($pp, "Subject 测试主题！\r\n\r\n");
fputs($pp, "$senders_email 发送以下注释:\r\n");
fputs($pp, $comments);
pclose($pp) or die("不能关闭到sendmail的管道连接！");
?>