<?php
//�ҵĵ���¼,����ɾ���ġ�
require_once('config.php');
require_once('public.php');
require_once('inc/db.class.php');
if(!islogin()) error_quit3("����û�е�¼��$message[4] $message[6]"); //��¼��֤
$usertab = "inc/havelogin.inc.php";
html_head("�ҵĵ���¼");
include_once('inc/head.inc.php');
now_pos('<a href=index.php>��ҳ</a> ���ҵĵ���¼', '');
?>
<table align=center border=0 cellpadding=0 cellspacing=0 width=760 background='images/bg1.gif'>
<tr>
<td width=160 valign=top align=left>
<!-- ���� -->
<?php include_once('inc/left.inc.php');?>
</td>
<td width=22 height=100%></td>
<td width=578 valign=top align=left>
<!-- ��Ҫ���� -->
 <table align=center border=0 cellpadding=0 cellspacing=0 width=100% height=100%>
  <tr>
   <td height=18 background='images/bg3.gif' valign=bottom>
   <font color="<?php echo $message[14]; ?>">&nbsp;��</font>
   <font color=#ffffff> �� �� �� �� �� ¼ </font>
   <font color="<?php echo $message[14]; ?>">��</font>
   </td>
  </tr>
  <tr><td width=100% valign=top>
<!-- ��ʼ��ʾ -->
   <table width=100% border=1 cellspacing=0 bordercolordark="#ffffff" bordercolorlight="<? echo $message[2];?>">
    <tr bgcolor="<?php echo $message[2];?>" align=center height=18>
	<td align=left>������</td><td width=15%>���˭��</td><td width=6%>���</td><td width=6%>״̬</td>
	<td width=22%>���ʱ��</td><td width=6%>��ϸ</td><td width=4%>ɾ</td></tr>
<?php
if(!isset($page) || ($page <= 0)) $page = 1;
$total = $db->query_first("select count(*) from ordersong where sender = '$m_user[user_name]'");
$total = $total[0];
$start = ($page - 1) * $perpage;
if($start > $total) $start = $total;
$other_page = "��ҳ: ";
require_once('set_page.php');
$other_page .= set_page($php_self."?page=", $total, $page, $perpage) . "����¼";
$res = $db->query("select order_id,receiver,song_name,song_id,date,flag from ordersong where sender = '$m_user[user_name]' order by date desc limit $start, $perpage");
while($tmp = $db->fetch_array($res)){
	if(!$tmp[flag]) $tmp[flag] = "����";
	else $tmp[flag] ="����";
	echo "<tr height=18 align=center><td align=left><a href='listen/listen_id.php?song_id=$tmp[song_id]' onclick='return listen(this.href);'>$tmp[song_name]</a></td><td>$tmp[receiver]</td><td><a href='geci.php?song_id=$tmp[song_id]' onclick='return geci(this.href);'>�鿴</td><td>$tmp[flag]</td><td>$tmp[date]</td><td><a href='order_read.php?order_id=$tmp[order_id]' onclick='return readorder(this.href);'>����</a></td><td><a href='my_ordersong_del.php?order_id=$tmp[order_id]'><font color=red>ɾ</font></a></td></tr>";
}
echo "<tr bgcolor='$message[2]'><td colspan=7 height=18>$other_page</td></tr>";
?>
   </table>
<!-- ���� -->  
  </td></tr>
 </table>
</td>
</tr>
</table>
<?php
include('inc/foot.inc.php');
exit;
?>