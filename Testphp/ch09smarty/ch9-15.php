<?php
include_once("Smarty.class.php"); //����smarty���ļ�

$smarty = new Smarty(); //����smartyʵ������$smarty
$smarty->template_dir = "./smarty/templates";//����ģ��Ŀ¼
$smarty->compile_dir = "./smarty/templates_c"; //���ñ���Ŀ¼
//���ұ߽����Ĭ��Ϊ{}����ʵ��Ӧ�õ���������JavaScript���ͻ�����Խ������<{}>��������
$smarty->left_delimiter = "<{"; 
$smarty->right_delimiter = "}>";

$smarty->assign("str1", "cHengWeitS@163.com"); //�����chengweits@163.com
$smarty->assign("str2", "my name is wei, cheng."); //�����My Name Is Wei, Cheng.
$smarty->assign("str3", "�ҵ����ֽУ�"); //������ҵ����ֽ�:��ΰ
$smarty->assign("str4", "this is chengweits"); //�����this is ��ΰ
$smarty->assign("str5", "ǰ��5��*"); //�����*****ǰ��5��*
$smarty->assign("str6", "ʱ��"); //�����ǰʱ��
//$smarty->assign("str7", ""); //������ʱ����ʾĬ��ֵ�����ʹ��ǰ����һ�����滻Ϊ""
//���벢��ʾexample2.tplģ��
$smarty->display("example2.tpl");
?>