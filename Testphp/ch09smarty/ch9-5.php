<?php
require('Smarty.class.php');
$t = new Smarty;
$t->assign("title","Hello World!");
$content = $t->fetch("templates/index.htm");
//这里的 fetch() 就是获取输出内容的函数,现在$content变量里面,就是要显示的内容了
$fp = fopen("archives/2006/12/19/0001.html", "w");
fwrite($fp, $content);
fclose($fp);
?>