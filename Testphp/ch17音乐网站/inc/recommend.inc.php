<?php
//推荐
include_once("admin/recommend.no.php");
$q = $db->query("select c.*, s.singer_name from cd c, singer s where c.cd_id = $recom_id and s.singer_id = c.singer_id");
if($r = $db->fetch_array($q)) {
	$href = "cd.php?cd_id=$r[cd_id]&cd_name=".urlencode($r[cd_name])
		."&singer_id=$r[singer_id]&singer_name=".urlencode($r[singer_name]);
	if($r[imgurl] == '' || $r[imgurl] == 'http://') $r[imgurl] = $default_cd_cover;
	if(strncasecmp($r[imgurl], "http://", 7))
	$r[imgurl] = $data_url.$r[imgurl];
	if(strlen($r[introduce]) > 300)
	$r[introduce] = substr($r[introduce], 0, 300)." ... ... ";
	echo "<table border=0 cellpadding=2 cellspacing=1 width=100% height=130>        
		<tr>
		  <td height=18 colspan=2 background='images/bg3.gif' valign=bottom>
		   <font color=\"".$message[14]."\">&nbsp;■</font>
		   <font color=#ffffff> 推 荐 专 辑 </font>
		   <font color=\"".$message[14]."\">■</font>
		  </td>
	     </tr>
		 <tr><td width=22%>
		  <a href=\"".$href."\"><img src='$r[imgurl]' width=110 height=110 border=0 alt='封面图' align='left'></a>
		 </td><td valign='top' width=78%>
		  <a href=\"singer.php?singer_id=".$r[singer_id]."&singer_name=".urlencode($r[singer_name])."\">".$r[singer_name]."</a>: <a href=\"".$href."\">".$r[cd_name]."</a> [".$r[lang]."]<br><br>
		  <u>专辑简介</u>: $r[introduce] <br>
		  <u>相关连接</u>: <a href='board.php?brd_id=$r[singer_id]&singer_name=$r[singer_name]'>$r[singer_name]的讨论区</a>
		  <a href='singer_introduce.php?singer_id=$r[singer_id]' onclick='return querysinger(this.href)'>$r[singer_name]简介</a>
		  <a href='listen/listen_cd.php?cd_id=$r[cd_id]' onclick='return listen(this.href)'>播放本专辑</a>
		 </td></tr>
		</table>";
	unset($r);
	unset($q);
	$db->free_result();
}
?>