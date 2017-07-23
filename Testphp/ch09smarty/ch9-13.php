<?php
include_once("Smarty.class.php"); //包含smarty类文件

$smarty = new Smarty(); //建立smarty实例对象$smarty
$smarty->template_dir = "./smarty/templates";//设置模板目录
$smarty->compile_dir = "./smarty/templates_c"; //设置编译目录
//----------------------------------------------------
//左右边界符，默认为{}，但实际应用当中容易与JavaScript
//相冲突，所以建议设成<{}>或其它。
//----------------------------------------------------
$smarty->left_delimiter = "<{"; 
$smarty->right_delimiter = "}>";

$smarty->assign("name", "程伟"); //进行模板变量替换

//编译并显示位于./templates下的ch9-10.tpl模板
$smarty->display("ch9-10.tpl"); 
?>