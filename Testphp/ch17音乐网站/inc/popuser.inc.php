<table border=0 cellpadding=0 cellspacing=0 width=100%>
<tr>
  <td height=18 colspan=7 background='images/bg3.gif' valign=bottom>
   <font color="<?php echo $message[14]; ?>">&nbsp;■</font>
   <font color=#ffffff> 明 星 会 员 </font>
   <font color="<?php echo $message[14]; ?>">■</font>
  </td>
</tr>
<tr>
<!-- 从数据库里读取数据 -->
<?php
$sql = "select user_name, face, sex, numlisten/(numlogin+1) as bf from user order by bf desc limit 0, 5";
$popresult = $db->query($sql);
while($poprow = $db->fetch_array($popresult)){
	$face = get_face($poprow[face], $poprow[sex]);
	echo "\t<td align=left width=14%><img src=\"images/face/".$face.".jpg\"><br>"
		."<a href='user_query.php?user_name=".urlencode($poprow[user_name])
		."' onclick='return query(this.href)'>$poprow[user_name]</a></td>\n";
}
?>
</tr>
<!-- 结束 -->
</table>