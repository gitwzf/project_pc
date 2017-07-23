<?php
//FastTemplate模版使用示例
include "class.FastTemplate.php3";
$tpl = new FastTemplate("D:\work\web-apps\phpbook\ch09\fasttemplate");
$tpl->define(array(test1 => "test1.tpl", test2 => "test2.tpl"));
//处理test2
$tpl->assign(name, "phpbook");
$tpl->parse(content, "test2");
//处理test1
$tpl->assign(title, "FastTemplate 测试");
$tpl->parse(main, "test1");
//输出结果
$tpl->FastPrint(main);
?>