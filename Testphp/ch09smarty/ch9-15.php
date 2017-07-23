<?php
include_once("Smarty.class.php"); //包含smarty类文件

$smarty = new Smarty(); //建立smarty实例对象$smarty
$smarty->template_dir = "./smarty/templates";//设置模板目录
$smarty->compile_dir = "./smarty/templates_c"; //设置编译目录
//左右边界符，默认为{}，但实际应用当中容易与JavaScript相冲突，所以建议设成<{}>或其它。
$smarty->left_delimiter = "<{"; 
$smarty->right_delimiter = "}>";

$smarty->assign("str1", "cHengWeitS@163.com"); //输出：chengweits@163.com
$smarty->assign("str2", "my name is wei, cheng."); //输出：My Name Is Wei, Cheng.
$smarty->assign("str3", "我的名字叫："); //输出：我的名字叫:程伟
$smarty->assign("str4", "this is chengweits"); //输出：this is 程伟
$smarty->assign("str5", "前边5个*"); //输出：*****前边5个*
$smarty->assign("str6", "时间"); //输出当前时间
//$smarty->assign("str7", ""); //不处理时会显示默认值，如果使用前面这一句则替换为""
//编译并显示example2.tpl模板
$smarty->display("example2.tpl");
?>