<?php
include_once("Smarty.class.php"); //����smarty���ļ�

$smarty = new Smarty(); //����smartyʵ������$smarty
$smarty->template_dir = "./smarty/templates";//����ģ��Ŀ¼
$smarty->compile_dir = "./smarty/templates_c"; //���ñ���Ŀ¼
//----------------------------------------------------
//���ұ߽����Ĭ��Ϊ{}����ʵ��Ӧ�õ���������JavaScript
//���ͻ�����Խ������<{}>��������
//----------------------------------------------------
$smarty->left_delimiter = "<{"; 
$smarty->right_delimiter = "}>";

$smarty->assign("name", "��ΰ"); //����ģ������滻

//���벢��ʾλ��./templates�µ�ch9-10.tplģ��
$smarty->display("ch9-10.tpl"); 
?>