<?php
$chapters = array( 'ch01' => '��һ��' , 'ch02' => '�ڶ���' , 'ch03' => '������' , 'ch04' => '������');
//���л�
$sb = serialize($chapters);
setcookie("birds", urlencode($sb), time()+3600);
print_r( unserialize(urldecode($_COOKIE['birds'])));
?>