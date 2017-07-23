<?php /* Smarty version 2.6.16, created on 2007-06-03 13:23:26
         compiled from example2.tpl */ ?>
<?php require_once(SMARTY_CORE_DIR . 'core.load_plugins.php');
smarty_core_load_plugins(array('plugins' => array(array('modifier', 'lower', 'example2.tpl', 4, false),array('modifier', 'capitalize', 'example2.tpl', 5, false),array('modifier', 'cat', 'example2.tpl', 6, false),array('modifier', 'replace', 'example2.tpl', 7, false),array('modifier', 'indent', 'example2.tpl', 8, false),array('modifier', 'date_format', 'example2.tpl', 9, false),array('modifier', 'default', 'example2.tpl', 10, false),)), $this); ?>
<html>
<head><title>smarty示例2</title></head>
<body>
1. 第一句把cHengWeitS@163.com全部变为小写：<?php echo ((is_array($_tmp=$this->_tpl_vars['str1'])) ? $this->_run_mod_handler('lower', true, $_tmp) : smarty_modifier_lower($_tmp)); ?>
<br>
2. 第二句首字母要大写：<?php echo ((is_array($_tmp=$this->_tpl_vars['str2'])) ? $this->_run_mod_handler('capitalize', true, $_tmp) : smarty_modifier_capitalize($_tmp)); ?>
<br>
3. 第三句模板变量 + 程伟：<?php echo ((is_array($_tmp=$this->_tpl_vars['str3'])) ? $this->_run_mod_handler('cat', true, $_tmp, "程伟") : smarty_modifier_cat($_tmp, "程伟")); ?>
<br>
4. 第四句把变量中的chengweits替换成：程伟：<?php echo ((is_array($_tmp=$this->_tpl_vars['str4'])) ? $this->_run_mod_handler('replace', true, $_tmp, 'chengweits', "程伟") : smarty_modifier_replace($_tmp, 'chengweits', "程伟")); ?>
<br>
5. 第五句要让它缩进5个空白字母位，并使用"*"取替这5个空白字符：<?php echo ((is_array($_tmp=$this->_tpl_vars['str5'])) ? $this->_run_mod_handler('indent', true, $_tmp, 5, "*") : smarty_modifier_indent($_tmp, 5, "*")); ?>
<br>
6. 第六句输出当前日期：<?php echo ((is_array($_tmp=((is_array($_tmp=$this->_tpl_vars['str6'])) ? $this->_run_mod_handler('date_format', true, $_tmp, "%Y-%m-%d") : smarty_modifier_date_format($_tmp, "%Y-%m-%d")))) ? $this->_run_mod_handler('cat', true, $_tmp, "日期") : smarty_modifier_cat($_tmp, "日期")); ?>
<br>
7. 第七句.php程序中不处理，它显示默认值：<?php echo ((is_array($_tmp=@$this->_tpl_vars['str8'])) ? $this->_run_mod_handler('default', true, $_tmp, "没有值！") : smarty_modifier_default($_tmp, "没有值！")); ?>

</body>
</html>