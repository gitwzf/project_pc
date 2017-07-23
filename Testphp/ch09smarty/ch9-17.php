<?php
 require_once ("Smarty.class.php");
 $smarty=new Smarty();
 $smarty->template_dir="./smarty/templates/";
 $smarty->compile_dir="./smarty/templates_c/";
 $smarty->config_dir="./smarty/configs/";
 $smarty->cache_dir="./smarty/cache/";
 $smarty->caching= false;
 $smarty->left_delimiter="<{";
 $smarty->right_delimiter="}>";
 $smarty->compile_check = true;
 $smarty->debugging = false;
 //处理html_checkboxes
 $smarty->assign('cust_checkboxes',array(1=>'第一章',2=>'第二章',3=>'第三章'));
 $smarty->assign('customer_id',3);
 //处理html_radioes 
 $smarty->assign('cust_radios',array(299=>'羚羊',300=>'袋鼠',301=>'狮子'));
 $smarty->assign('radio_id',299);
 //{html_select_date}不用处理会自动输出 
 $smarty->display("example3.tpl");
?>