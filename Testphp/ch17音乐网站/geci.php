<?php
//歌词 geci.php
require('config.php');
require('public.php');
require('inc/db.class.php');

if($song_id == '') error_quit3("非法操作!");

$res = $db->query_first("SELECT * FROM geci WHERE song_id = '$song_id'");
$res1 = $db->query_first("SELECT song_name FROM song WHERE song_id = '$song_id'");

html_head("《".$res1[song_name]."》的歌词");

print "
<br>
<table width=96% bgcolor=#000000 align=center cellPadding=3 cellSpacing=1 height=95%>
<tr height=18 bgcolor=$message[2]><td> $res1[song_name] 的歌词</td></tr>
";

if(empty($res[geci])) //歌词为空
{
  print "
	<tr>
	<td align=center background='images/bg1.gif'><br><span class=p2>此歌尚未添加</span><br><br><a href=geci_add.php?song_id=$song_id&song_name=".urlencode($res1[song_name]).">我来添加歌词</a></td></tr>
	<tr align=center height=18><td background='images/bg1.gif'>$message[0]</td></tr>
	  ";
}
else
{
  $res[geci] = bbcode($res[geci]);
  print "
        <tr width=100%><td background='images/bg1.gif' valign=top>
         <table border=0 align=center width=98%>    
	      <tr><td align=left>词: $res[tianci] 曲: $res[zuoqu]<br><br>$res[geci]</td></tr>
         </table>
        </td></tr>";
  if($res[tigong])
	  print "
	  <tr bgcolor=$message[2] height=18>
	  <td>感谢网友<a href='user_query.php?user_name=$res[tigong]' onclick='return Query(this.href)'><b>$res[tigong]</b></a>提供歌词.</td></tr>
		  ";
  print "
	  <tr bgcolor=$message[3] height=18>
	  <td><a href=geci_modify.php?song_id=$song_id>【歌词有误，我来修改】</a> 【推荐该页给朋友】 $message[0]</td></tr>
	 ";
}
print "
</table>
</body></html>
";

$db->close();
exit;
?>