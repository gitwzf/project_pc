<?php
//��ϸ�Ķ�����¼��order_read.php
//����: order_id;
require('config.php');
require('public.php');
require('inc/db.class.php');

if($order_id == '') error_quit3("�Ƿ�����!");

$res = $db->query_first("SELECT * FROM ordersong WHERE order_id = '$order_id'");

html_head("�������¼");

print "
<br>
<table width=95% bgcolor=#000000 align=center cellPadding=5 cellSpacing=1 height=90%>
<tr height=18 bgcolor=$message[2]><td align=left><a href='user_query.php?user_name=".urlencode($res[sender])."'>$res[sender]</a> ��� $res[receiver] ˵: $res[ssay] </td></tr>";

$res[says] = bbcode($res[says]);
print "
<tr bgcolor=$message[3]><td valign=top>
<table align=center background='images/bg1.gif' width=95% cellPadding=2 cellSpacing=0 height=100%>
	<tr height=15><td width=100%>����:</b> <a href='listen/listen_id.php?song_id=$res[song_id]' onclick='return Listen(this.href);'>$res[song_name]</a> </td></tr>
	<tr height=15><td>�ݳ�: <a href='singer.php?singer_id=$res[singer_id]&singer_name=".urlencode($res[singer_name])."' target=_blank>$res[singer_name]</a>  <a href='geci.php?song_id=$res[song_id]' onclick='return Geci(this.href)'>�鿴���</a></td></tr>
	<tr height=15><td align=left width=100%>ʱ��: $res[date] </td></tr>
	<tr><td align=left width=100% height=1 bgcolor=#000000></td></tr>
	<tr><td align=left valign=top width=100%>����: <br><br>$res[says]</td></tr>
</table>
</td></tr>";

print "<tr bgcolor=$message[2] height=18><td align=center>$message[0]</td></tr>";
print "</table>";
print "</body></html>";

$db->close();
exit;
?>