<?php
ob_start();
echo "Hello World!";
$content = ob_get_contents();//取得php页面输出的全部内容
$fp = fopen("archives/2006/12/19/0001.html", "w");
fwrite($fp, $content);
fclose($fp);
?>