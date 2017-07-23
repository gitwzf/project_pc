<?php
include "template.inc";
$t = new Template("D:\work\web-apps\phpbook\ch09\phplib");
$t->set_file(array("first" => "first.html", "second" =>"second.html"));
$fruit = array ("月季", "玫瑰", "樱桃", "西红柿", "黄瓜", "茄子");
reset($fruit);
while (list($name, $value) =each($fruit)) {
	//设置'value'和'name'为每一个元素的值和名字
	$t->set_var("name",$name); $t->set_var("value",$value);
	//追加each_element的拷贝
	$t->parse("array_elements","second",true);
}
$t->pparse("output","first");
?>