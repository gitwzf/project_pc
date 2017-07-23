<?php /* Smarty version 2.6.16, created on 2007-08-11 17:00:00
         compiled from example3.tpl */ ?>
<?php require_once(SMARTY_CORE_DIR . 'core.load_plugins.php');
smarty_core_load_plugins(array('plugins' => array(array('function', 'html_checkboxes', 'example3.tpl', 8, false),array('function', 'html_radios', 'example3.tpl', 10, false),array('function', 'html_select_date', 'example3.tpl', 12, false),)), $this); ?>
<html> 
 <head><title>模板中内定的一些函数</title></head> 
 <body> 
    
   <?php $this->assign('UserName', "张三"); ?> 
   这里将显示模板内部定义的一个变量:UserName=<?php echo $this->_tpl_vars['UserName']; ?>
<br>
   <font color="Blue">下面的这一行将显示3个checkBox:</font><br>
   <?php echo smarty_function_html_checkboxes(array('name' => 'id','options' => $this->_tpl_vars['cust_checkboxes'],'selected' => $this->_tpl_vars['customer_id'],'separator' => ' '), $this);?>
<br>
   <font color="red">下面在这一行将显示3个radio:</font><br>
   <?php echo smarty_function_html_radios(array('name' => 'rid','options' => $this->_tpl_vars['cust_radios'],'selected' => $this->_tpl_vars['radio_id'],'separator' => "<br/>"), $this);?>
<br>
   下面显示一个月,日, 年选择框:<br>
   <?php echo smarty_function_html_select_date(array(), $this);?>
 <br>
   <?php $_smarty_tpl_vars = $this->_tpl_vars;
$this->_smarty_include(array('smarty_include_tpl_file' => "footer.tpl", 'smarty_include_vars' => array()));
$this->_tpl_vars = $_smarty_tpl_vars;
unset($_smarty_tpl_vars);
 ?>