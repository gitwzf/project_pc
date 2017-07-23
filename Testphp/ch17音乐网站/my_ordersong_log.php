<?php
//我的点歌记录,可以删除的。
require_once('config.php');
require_once('public.php');
require_once('inc/db.class.php');
if(!islogin()) error_quit3("您还没有登录！$message[4] $message[6]"); //登录验证
$usertab = "inc/havelogin.inc.php";
html_head("我的点歌记录");
include_once('inc/head.inc.php');
now_pos('<a href=index.php>首页</a> 》我的点歌记录', '');
?>
<table align=center border=0 cellpadding=0 cellspacing=0 width=760 background='images/bg1.gif'>
<tr>
<td width=160 valign=top align=left>
<!-- 左栏 -->
<?php include_once('inc/left.inc.php');?>
</td>
<td width=22 height=100%></td>
<td width=578 valign=top align=left>
<!-- 主要部分 -->
 <table align=center border=0 cellpadding=0 cellspacing=0 width=100% height=100%>
  <tr>
   <td height=18 background='images/bg3.gif' valign=bottom>
   <font color="<?php echo $message[14]; ?>">&nbsp;■</font>
   <font color=#ffffff> 我 的 点 歌 记 录 </font>
   <font color="<?php echo $message[14]; ?>">■</font>
   </td>
  </tr>
  <tr><td width=100% valign=top>
<!-- 开始显示 -->
   <table width=100% border=1 cellspacing=0 bordercolordark="#ffffff" bordercolorlight="<? echo $message[2];?>">
    <tr bgcolor="<?php echo $message[2];?>" align=center height=18>
	<td align=left>歌曲名</td><td width=15%>点给谁的</td><td width=6%>歌词</td><td width=6%>状态</td>
	<td width=22%>点歌时间</td><td width=6%>详细</td><td width=4%>删</td></tr>
<?php
if(!isset($page) || ($page <= 0)) $page = 1;
$total = $db->query_first("select count(*) from ordersong where sender = '$m_user[user_name]'");
$total = $total[0];
$start = ($page - 1) * $perpage;
if($start > $total) $start = $total;
$other_page = "分页: ";
require_once('set_page.php');
$other_page .= set_page($php_self."?page=", $total, $page, $perpage) . "条记录";
$res = $db->query("select order_id,receiver,song_name,song_id,date,flag from ordersong where sender = '$m_user[user_name]' order by date desc limit $start, $perpage");
while($tmp = $db->fetch_array($res)){
	if(!$tmp[flag]) $tmp[flag] = "隐藏";
	else $tmp[flag] ="公开";
	echo "<tr height=18 align=center><td align=left><a href='listen/listen_id.php?song_id=$tmp[song_id]' onclick='return listen(this.href);'>$tmp[song_name]</a></td><td>$tmp[receiver]</td><td><a href='geci.php?song_id=$tmp[song_id]' onclick='return geci(this.href);'>查看</td><td>$tmp[flag]</td><td>$tmp[date]</td><td><a href='order_read.php?order_id=$tmp[order_id]' onclick='return readorder(this.href);'>详情</a></td><td><a href='my_ordersong_del.php?order_id=$tmp[order_id]'><font color=red>删</font></a></td></tr>";
}
echo "<tr bgcolor='$message[2]'><td colspan=7 height=18>$other_page</td></tr>";
?>
   </table>
<!-- 结束 -->  
  </td></tr>
 </table>
</td>
</tr>
</table>
<?php
include('inc/foot.inc.php');
exit;
?>