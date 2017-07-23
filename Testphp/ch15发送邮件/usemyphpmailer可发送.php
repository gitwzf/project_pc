<?php
require("myphpmailer.php");

//初始化扩展类
$mail = new my_phpmailer;

//现在开始添加需要发送邮件的基本信息，比如邮件地址、标题、内容和附件等。
$mail->AddAddress("phpbook_ch15@126.com", "Mr.Cheng");
$mail->Subject = "这是测试邮件发送的主题！";
$mail->Body    = "这是邮件的消息体！";
$mail->AddAttachment("c:/phpbook.zip", "phpbook.zip");  //增加附件
//开始发送邮件
if(!$mail->Send())
{
   //发送失败，打印出错信息提示
   echo "发送过程中发现一个错误，请仔细查阅！";
   exit;
}
//发送成功，打印成功信息提示
echo "信息已经被成功发送！";
?>