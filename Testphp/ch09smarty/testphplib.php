<?php
include "template.inc";
$my_color = "red";//���ں���ʹ��
$t = new Template("D:\work\web-apps\phpbook\ch09\phplib");//����һ����Ϊ$t��ģ����� 
$t->set_file("MyFileHandle","MyTemplate.html"); //���� MyFileHandleΪ���ǵ�ģ���ļ� 
$t->set_var("some_color",$my_color); //����ģ����� some_color = $my_colorֵ 
$t->parse("MyOutput","MyFileHandle"); //����ģ����� MyOutput Ϊ��������ļ�
$t->p("MyOutput");  //��� MyOutput ��ֵ(���ǵķ����������)
//pparse�ȼ���p+parse
//$t->pparse("MyOutput","MyFileHandle");
?>