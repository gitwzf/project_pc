<?php /* Smarty version 2.6.16, created on 2007-06-03 13:23:26
         compiled from example2.tpl */ ?>
<?php require_once(SMARTY_CORE_DIR . 'core.load_plugins.php');
smarty_core_load_plugins(array('plugins' => array(array('modifier', 'lower', 'example2.tpl', 4, false),array('modifier', 'capitalize', 'example2.tpl', 5, false),array('modifier', 'cat', 'example2.tpl', 6, false),array('modifier', 'replace', 'example2.tpl', 7, false),array('modifier', 'indent', 'example2.tpl', 8, false),array('modifier', 'date_format', 'example2.tpl', 9, false),array('modifier', 'default', 'example2.tpl', 10, false),)), $this); ?>
<html>
<head><title>smartyʾ��2</title></head>
<body>
1. ��һ���cHengWeitS@163.comȫ����ΪСд��<?php echo ((is_array($_tmp=$this->_tpl_vars['str1'])) ? $this->_run_mod_handler('lower', true, $_tmp) : smarty_modifier_lower($_tmp)); ?>
<br>
2. �ڶ�������ĸҪ��д��<?php echo ((is_array($_tmp=$this->_tpl_vars['str2'])) ? $this->_run_mod_handler('capitalize', true, $_tmp) : smarty_modifier_capitalize($_tmp)); ?>
<br>
3. ������ģ����� + ��ΰ��<?php echo ((is_array($_tmp=$this->_tpl_vars['str3'])) ? $this->_run_mod_handler('cat', true, $_tmp, "��ΰ") : smarty_modifier_cat($_tmp, "��ΰ")); ?>
<br>
4. ���ľ�ѱ����е�chengweits�滻�ɣ���ΰ��<?php echo ((is_array($_tmp=$this->_tpl_vars['str4'])) ? $this->_run_mod_handler('replace', true, $_tmp, 'chengweits', "��ΰ") : smarty_modifier_replace($_tmp, 'chengweits', "��ΰ")); ?>
<br>
5. �����Ҫ��������5���հ���ĸλ����ʹ��"*"ȡ����5���հ��ַ���<?php echo ((is_array($_tmp=$this->_tpl_vars['str5'])) ? $this->_run_mod_handler('indent', true, $_tmp, 5, "*") : smarty_modifier_indent($_tmp, 5, "*")); ?>
<br>
6. �����������ǰ���ڣ�<?php echo ((is_array($_tmp=((is_array($_tmp=$this->_tpl_vars['str6'])) ? $this->_run_mod_handler('date_format', true, $_tmp, "%Y-%m-%d") : smarty_modifier_date_format($_tmp, "%Y-%m-%d")))) ? $this->_run_mod_handler('cat', true, $_tmp, "����") : smarty_modifier_cat($_tmp, "����")); ?>
<br>
7. ���߾�.php�����в���������ʾĬ��ֵ��<?php echo ((is_array($_tmp=@$this->_tpl_vars['str8'])) ? $this->_run_mod_handler('default', true, $_tmp, "û��ֵ��") : smarty_modifier_default($_tmp, "û��ֵ��")); ?>

</body>
</html>