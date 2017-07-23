<?php
$chapters = array( 'ch01' => '第一章' , 'ch02' => '第二章' , 'ch03' => '第三章' , 'ch04' => '第四章');
//序列化
$sb = serialize($chapters);
setcookie("birds", urlencode($sb), time()+3600);
print_r( unserialize(urldecode($_COOKIE['birds'])));
?>