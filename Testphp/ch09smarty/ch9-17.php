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
 //����html_checkboxes
 $smarty->assign('cust_checkboxes',array(1=>'��һ��',2=>'�ڶ���',3=>'������'));
 $smarty->assign('customer_id',3);
 //����html_radioes 
 $smarty->assign('cust_radios',array(299=>'����',300=>'����',301=>'ʨ��'));
 $smarty->assign('radio_id',299);
 //{html_select_date}���ô�����Զ���� 
 $smarty->display("example3.tpl");
?>