<?php
include "template.inc";
$t = new Template("D:\work\web-apps\phpbook\ch09\phplib");
$my_color = "red";//���ں���ʹ��
//������ͬ��һ������һ��
$t->set_file("MyFileHandle","MyTemplate.html");
$t->set_var("some_color",$my_color);
$t->parse("MyOutput","MyFileHandle");
//ע������û�е���p()����Ȼû������κζ���
//���ڷ����ڶ���ģ��
$t->set_file("WholeHandle","wholePage.html");
//wholePage.ihtml �� "{MyOutput}" ������
$t->parse("MyFinalOutput","WholeHandle"); // ���е� {MyOutput} ���滻��
$t->p("MyFinalOutput"); // ��� MyFinalOutput ��ֵ
?>