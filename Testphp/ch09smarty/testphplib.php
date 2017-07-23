<?php
include "template.inc";
$my_color = "red";//将在后面使用
$t = new Template("D:\work\web-apps\phpbook\ch09\phplib");//创建一个名为$t的模板对象 
$t->set_file("MyFileHandle","MyTemplate.html"); //设置 MyFileHandle为我们的模板文件 
$t->set_var("some_color",$my_color); //设置模板变量 some_color = $my_color值 
$t->parse("MyOutput","MyFileHandle"); //设置模板变量 MyOutput 为分析后的文件
$t->p("MyOutput");  //输出 MyOutput 的值(我们的分析后的数据)
//pparse等价与p+parse
//$t->pparse("MyOutput","MyFileHandle");
?>