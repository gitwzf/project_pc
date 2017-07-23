<?php
//载入Smarty库，如果在php.ini设置了include_path，那么可以直接用include("Smarty.class.php");
//否则使用绝对路径，比如require('d:\mysmarty\Smarty.class.php');
//另外不设置include_path，可以直接把Smarty.class.php拷到网站目录，就不用加绝对路径了。
require('Smarty.class.php');

$smarty = new Smarty;

//下面的"网站目录"用绝对路径，可以用相对路径"./templates"
$smarty->template_dir='D:\work\web-apps\phpbook\ch09\smarty\templates';
$smarty->config_dir='D:\work\web-apps\phpbook\ch09\smarty\configs';
$smarty->cache_dir='D:\work\web-apps\phpbook\ch09\smarty\cache';
$smarty->compile_dir='D:\work\web-apps\phpbook\ch09\smarty\templates_c';
//上面四行为使用Smarty前的必要参数配置

$smarty->assign('name','请问今天是星期天吗？');
$smarty->display('index.tpl');
?>