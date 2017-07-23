<?php
$to1 = "phpbook1@sohu.com";
$to2 = "phpbook2@sohu.com";
$to3 = "phpbook3@sohu.com";

$subject = "这是使用mail()函数发送邮件的测试示例";
$body = "您好,测试以下,嘿嘿.";
mail($to1.",".$to2.",".$to3, $subject, $body);
?>