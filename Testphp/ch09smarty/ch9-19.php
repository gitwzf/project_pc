<?php
require_once ("Smarty.class.php");
$smarty = new Smarty();
$smarty->template_dir = './smarty/templates/';
$smarty->compile_dir = './smarty/templates_c/';
$smarty->config_dir = './smarty/configs/';
$smarty->cache_dir = './smarty/cache/';
$smarty->caching = false;
for ($i = 1 ; $i <= 10 ; $i ++){
	$array[]= array("recordId"=>$i, "content"=>"第".$i."条访问记录");
}
$smarty->assign("record", $array);
$smarty->display("example4.tpl");
?>