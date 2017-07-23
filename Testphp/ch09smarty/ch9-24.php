<?php
include "template.inc";
$t = new Template("D:\work\web-apps\phpbook\ch09\phplib");
$my_color = "red";//将在后面使用
//这三行同第一个例子一样
$t->set_file("MyFileHandle","MyTemplate.html");
$t->set_var("some_color",$my_color);
$t->parse("MyOutput","MyFileHandle");
//注意我们没有调用p()，仍然没有输出任何东西
//现在分析第二个模板
$t->set_file("WholeHandle","wholePage.html");
//wholePage.ihtml 有 "{MyOutput}" 在里面
$t->parse("MyFinalOutput","WholeHandle"); // 所有的 {MyOutput} 被替换了
$t->p("MyFinalOutput"); // 输出 MyFinalOutput 的值
?>