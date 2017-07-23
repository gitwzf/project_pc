<?php
//用户首页
require_once('config.php');
require_once('public.php');
require_once('inc/db.class.php');
//判断用户是否登录
$login = islogin();
if($login)
	$usertab = "inc/havelogin.inc.php";
else
	$usertab = "inc/unlogin.inc.php";
//显示标题和页头部分
html_head('欢迎光临-在线听歌-点播歌曲');
$index = 1;
//包含页头部分,页头显示LOGO和一些基本的统计数据信息
include('inc/head.inc.php');
//显示当前位置
now_pos('首页','');
//页面代码开始
?>
<table align=center border=0 cellpadding=0 cellspacing=0 width=760 background='images/bg1.gif'>
<tr>
<td width=160 valign=top align=left>
<!-- 左栏 -->
<?php include_once('inc/left.inc.php');?>
</td>
<td width=22></td>
<td width=578 valign=top align=left>
<!-- 主要部分 -->
 <table align=center border=0 cellpadding=0 cellspacing=0 width=100% height=100%>
  <!-- 歌手分类 -->
  <tr><td width=100% valign=top height=30><?php include_once('inc/cate2.inc.php'); ?></td></tr>
  <!-- 最新专辑列表 -->
  <tr><td width=100% valign=top height=150><?php include_once('inc/newcd.inc.php');?></td></tr>
  <!-- 推荐专辑列表 -->
  <tr><td width=100% valign=top height=130><?php include_once('inc/recommend.inc.php');?></td></tr>
  <!-- 明星会员列表 -->
  <tr><td width=100% valign=top height=60><?php include_once('inc/popuser.inc.php'); ?></td></tr>
  <!-- 最新论坛发帖 -->
  <tr><td width=100% valign=top><?php include_once('inc/news.inc.php');?></td></tr>
  <!-- 点播歌曲列表 -->
  <tr><td width=100% valign=top><?php include_once('inc/ordersong.inc.php');?></td></tr>
  <!-- 友情连接列表 -->
  <tr><td width=100% valign=top><?php include_once('inc/links.inc.php');?></td></tr>
 </table>
</td>
</tr>
</table>
<?php
//页尾部分
include_once('inc/foot.inc.php');
exit;
?>