<?php
//�û���ҳ
require_once('config.php');
require_once('public.php');
require_once('inc/db.class.php');
//�ж��û��Ƿ��¼
$login = islogin();
if($login)
	$usertab = "inc/havelogin.inc.php";
else
	$usertab = "inc/unlogin.inc.php";
//��ʾ�����ҳͷ����
html_head('��ӭ����-��������-�㲥����');
$index = 1;
//����ҳͷ����,ҳͷ��ʾLOGO��һЩ������ͳ��������Ϣ
include('inc/head.inc.php');
//��ʾ��ǰλ��
now_pos('��ҳ','');
//ҳ����뿪ʼ
?>
<table align=center border=0 cellpadding=0 cellspacing=0 width=760 background='images/bg1.gif'>
<tr>
<td width=160 valign=top align=left>
<!-- ���� -->
<?php include_once('inc/left.inc.php');?>
</td>
<td width=22></td>
<td width=578 valign=top align=left>
<!-- ��Ҫ���� -->
 <table align=center border=0 cellpadding=0 cellspacing=0 width=100% height=100%>
  <!-- ���ַ��� -->
  <tr><td width=100% valign=top height=30><?php include_once('inc/cate2.inc.php'); ?></td></tr>
  <!-- ����ר���б� -->
  <tr><td width=100% valign=top height=150><?php include_once('inc/newcd.inc.php');?></td></tr>
  <!-- �Ƽ�ר���б� -->
  <tr><td width=100% valign=top height=130><?php include_once('inc/recommend.inc.php');?></td></tr>
  <!-- ���ǻ�Ա�б� -->
  <tr><td width=100% valign=top height=60><?php include_once('inc/popuser.inc.php'); ?></td></tr>
  <!-- ������̳���� -->
  <tr><td width=100% valign=top><?php include_once('inc/news.inc.php');?></td></tr>
  <!-- �㲥�����б� -->
  <tr><td width=100% valign=top><?php include_once('inc/ordersong.inc.php');?></td></tr>
  <!-- ���������б� -->
  <tr><td width=100% valign=top><?php include_once('inc/links.inc.php');?></td></tr>
 </table>
</td>
</tr>
</table>
<?php
//ҳβ����
include_once('inc/foot.inc.php');
exit;
?>