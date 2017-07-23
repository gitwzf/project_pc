<?php /* Smarty version 2.6.16, created on 2007-06-03 14:46:08
         compiled from example5.tpl */ ?>
<html>
<head>
<title>一行输出多条记录</title>
</head>
<body>
<table>
<tr>
<?php unset($this->_sections['loop']);
$this->_sections['loop']['name'] = 'loop';
$this->_sections['loop']['loop'] = is_array($_loop=$this->_tpl_vars['record']) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
$this->_sections['loop']['step'] = ((int)1) == 0 ? 1 : (int)1;
$this->_sections['loop']['show'] = true;
$this->_sections['loop']['max'] = $this->_sections['loop']['loop'];
$this->_sections['loop']['start'] = $this->_sections['loop']['step'] > 0 ? 0 : $this->_sections['loop']['loop']-1;
if ($this->_sections['loop']['show']) {
    $this->_sections['loop']['total'] = min(ceil(($this->_sections['loop']['step'] > 0 ? $this->_sections['loop']['loop'] - $this->_sections['loop']['start'] : $this->_sections['loop']['start']+1)/abs($this->_sections['loop']['step'])), $this->_sections['loop']['max']);
    if ($this->_sections['loop']['total'] == 0)
        $this->_sections['loop']['show'] = false;
} else
    $this->_sections['loop']['total'] = 0;
if ($this->_sections['loop']['show']):

            for ($this->_sections['loop']['index'] = $this->_sections['loop']['start'], $this->_sections['loop']['iteration'] = 1;
                 $this->_sections['loop']['iteration'] <= $this->_sections['loop']['total'];
                 $this->_sections['loop']['index'] += $this->_sections['loop']['step'], $this->_sections['loop']['iteration']++):
$this->_sections['loop']['rownum'] = $this->_sections['loop']['iteration'];
$this->_sections['loop']['index_prev'] = $this->_sections['loop']['index'] - $this->_sections['loop']['step'];
$this->_sections['loop']['index_next'] = $this->_sections['loop']['index'] + $this->_sections['loop']['step'];
$this->_sections['loop']['first']      = ($this->_sections['loop']['iteration'] == 1);
$this->_sections['loop']['last']       = ($this->_sections['loop']['iteration'] == $this->_sections['loop']['total']);
?>
<?php if ($this->_sections['loop']['index'] % 5 == 0): ?>
</tr>
<tr>
<?php endif; ?>
<td><font size="2"><?php echo $this->_tpl_vars['record'][$this->_sections['loop']['index']]['recordID']; ?>
</font></td>
<td><font size="2"><?php echo $this->_tpl_vars['record'][$this->_sections['loop']['index']]['content']; ?>
</font></td>
<?php endfor; endif; ?>
</tr>
</table>
</body>
</html>