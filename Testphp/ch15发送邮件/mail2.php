<?php
$to1 = "phpbook1@sohu.com";
$to2 = "phpbook2@sohu.com";
$to3 = "phpbook3@sohu.com";

$subject = "����ʹ��mail()���������ʼ��Ĳ���ʾ��";
$body = "����,��������,�ٺ�.";
mail($to1.",".$to2.",".$to3, $subject, $body);
?>