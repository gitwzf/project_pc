<?php
require("phpmailer/class.phpmailer.php");

class my_phpmailer extends phpmailer {
    //为自己的程序，设置相关的默认值
    var $From     = "wei.cheng@dudu-inc.com";
	var $Username = "wei.cheng@dudu-inc.com";
	var $Password = "1234567890";
    var $FromName = "Mr.Cheng";
    var $Host     = "mail.dudu-inc.com";
    var $Mailer   = "smtp";        //也可以使用IsSMTP()来设置mailer为smtp方法发送
    var $WordWrap = 75;var $SMTPAuth = true; 

    //扩展默认的错误处理机制error_handler
    function error_handler($msg) {
        print("发送邮件出错：");
        print("错误信息描述如下:");
        printf("%s", $msg);
        exit;
    }

    //创建自己的私有方法，完成相应的功能
    function do_something($something) {
        //具体应用中的相关功能
    }
}
?>