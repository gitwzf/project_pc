<?php
ob_start();
echo "Hello World!";
$content = ob_get_contents();//ȡ��phpҳ�������ȫ������
$fp = fopen("archives/2006/12/19/0001.html", "w");
fwrite($fp, $content);
fclose($fp);
?>