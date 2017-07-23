<?php
$to = "phpbook@sohu.com";
$subject = "这是使用mail()函数发送邮件的测试示例";
$body = "您好,测试以下,嘿嘿.";
mail($to, $subject, $body);
?>