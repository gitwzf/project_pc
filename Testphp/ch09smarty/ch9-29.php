<?php
//FastTemplateģ��ʹ��ʾ��
include "class.FastTemplate.php3";
$tpl = new FastTemplate("D:\work\web-apps\phpbook\ch09\fasttemplate");
$tpl->define(array(test1 => "test1.tpl", test2 => "test2.tpl"));
//����test2
$tpl->assign(name, "phpbook");
$tpl->parse(content, "test2");
//����test1
$tpl->assign(title, "FastTemplate ����");
$tpl->parse(main, "test1");
//������
$tpl->FastPrint(main);
?>