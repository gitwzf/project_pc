<?php
include "template.inc";
$t = new Template("D:\work\web-apps\phpbook\ch09\phplib");
$t->set_file(array("first" => "first.html", "second" =>"second.html"));
$fruit = array ("�¼�", "õ��", "ӣ��", "������", "�ƹ�", "����");
reset($fruit);
while (list($name, $value) =each($fruit)) {
	//����'value'��'name'Ϊÿһ��Ԫ�ص�ֵ������
	$t->set_var("name",$name); $t->set_var("value",$value);
	//׷��each_element�Ŀ���
	$t->parse("array_elements","second",true);
}
$t->pparse("output","first");
?>