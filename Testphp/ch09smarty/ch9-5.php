<?php
require('Smarty.class.php');
$t = new Smarty;
$t->assign("title","Hello World!");
$content = $t->fetch("templates/index.htm");
//����� fetch() ���ǻ�ȡ������ݵĺ���,����$content��������,����Ҫ��ʾ��������
$fp = fopen("archives/2006/12/19/0001.html", "w");
fwrite($fp, $content);
fclose($fp);
?>