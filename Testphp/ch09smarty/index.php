<?php
//����Smarty�⣬�����php.ini������include_path����ô����ֱ����include("Smarty.class.php");
//����ʹ�þ���·��������require('d:\mysmarty\Smarty.class.php');
//���ⲻ����include_path������ֱ�Ӱ�Smarty.class.php������վĿ¼���Ͳ��üӾ���·���ˡ�
require('Smarty.class.php');

$smarty = new Smarty;

//�����"��վĿ¼"�þ���·�������������·��"./templates"
$smarty->template_dir='D:\work\web-apps\phpbook\ch09\smarty\templates';
$smarty->config_dir='D:\work\web-apps\phpbook\ch09\smarty\configs';
$smarty->cache_dir='D:\work\web-apps\phpbook\ch09\smarty\cache';
$smarty->compile_dir='D:\work\web-apps\phpbook\ch09\smarty\templates_c';
//��������Ϊʹ��Smartyǰ�ı�Ҫ��������

$smarty->assign('name','���ʽ�������������');
$smarty->display('index.tpl');
?>