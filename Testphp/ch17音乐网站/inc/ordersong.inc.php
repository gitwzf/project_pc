<!-- ���µ�10������¼! -->
<table border=0 cellpadding=0 cellspacing=0 width=100%>        
<tr>
  <td height=18 colspan=6 background='images/bg3.gif' valign=bottom>
   <font color="<?php echo $message[14]; ?>">&nbsp;��</font>
   <font color=#ffffff> �� �� ף �� </font>
   <font color="<?php echo $message[14]; ?>">��</font>
  </td>
</tr>
<tr height=18 bgcolor="<?php echo $message[2];?>">
  <td>�����</td>
  <td>�ݳ���</td>
  <td>������</td>
  <td width=8%>���</td>
  <td>�ո���</td>
  <td width=20%>���ף����</td>
</tr>
<?php
$ossql = "select order_id, receiver, sender, song_name, song_id, singer_name, singer_id, ssay from ordersong where flag = 1 order by date desc limit 0, 10";
$res = $db->query($ossql);
while($tmp = $db->fetch_array($res)){
	echo "<tr height=18>
		  <td><a href='user_query.php?user_name=$tmp[sender]' onclick='return query(this.href)'>$tmp[sender]</a></td>
		  <td><a href='singer.php?singer_id=$tmp[singer_id]&singer_name=$tmp[singer_name]'>$tmp[singer_name]</a></td>
		  <td><a href='listen/listen_id.php?song_id=$tmp[song_id]' onclick='return listen(this.href);'>$tmp[song_name]</a></td>
		  <td><a href='geci.php?song_id=$tmp[song_id]' onclick='return geci(this.href)'>�鿴</a></td>
		  <td>$tmp[receiver]</td>
		  <td><a href='order_read.php?order_id=$tmp[order_id]' onclick='return readorder(this.href)'>$tmp[ssay] ... </a></td>
	    </tr>
		<tr width=100%><td colspan=6><img border=0 src='images/line.gif'></td></tr>";
}
unset($res);
unset($tmp);
$db->free_result();
?>
<tr width=100%><td colspan=6 align=right><a href='ordersong_log.php'>�������¼ &gt;&gt;..</a></td></tr>
</table>