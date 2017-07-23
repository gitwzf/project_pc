<?php
//添加歌词,任何人可以添加.geci_add.php
//放在一个临时的数据库tmpgeci里等管理者确认。 
require('config.php');
require('public.php');
require('inc/db.class.php');


if(!$song_id) error_quit3("非法操作!", 0);

if(isset($submit)) {

 if(strlen($geci)<100) error_quit3("你的歌词也太短了吧~");
 if(!$tigong) $tigong = 'guest';

 if(is_admin()) { //管理员直接加到歌里
   $db->query("INSERT INTO geci VALUES('$song_id', '$zuoqu', '$tianci', '$tigong', '$geci')");
   $db->query("UPDATE song SET geci = '1' WHERE song_id = '$song_id'");
 } else {
   $db->query("INSERT INTO tmpgeci (zuoqu, tianci, tigong, geci, song_name, song_id) VALUES ('$zuoqu', '$tianci', '$tigong', '$geci', '$song_name', '$song_id')");
 }
 html_head("添加歌词成功");
 print "
	 <br><br><br>
	 <p align=center><span class=okmsg>添加成功！</span> 感谢您的参与！</p>
	 <p align=center> $message[0] </p>
	 <script>setTimeout('window.close()', 2000);</script>
 ";
} else {
	
	session_start();

	html_head("添置《".$song_name."》的歌词");
	print "
	<br>
	<table width=96% bgcolor='#000000' align=center cellPadding=2 cellSpacing=1 height=95%>
	<tr height=20 bgcolor=$message[2]><td>为".$song_name."添加歌词</td></tr>
	<tr width=100%><td background='images/bg1.gif'>
		<table align=center>
		  <tr><form action=\"".$PHP_SELF."\" method=\"post\"><td align=left>
		  大名: <input type=\"text\" size=\"15\" name=\"tigong\" value=\"".$m_user[user_name]."\" class=\"input\"><br>
		  作词: <input type=\"text\" size=\"15\" name=\"tianci\" class=\"input\">
		  作曲: <input type=\"text\" name=\"zuoqu\" size=\"15\" class=\"input\"><br>
	      <input type=\"hidden\" name=\"song_id\" value=\"".$song_id."\">
		  <input type=\"hidden\" name=\"song_name\" value=\"".$song_name."\">
	      <textarea cols=\"55\" rows=\"18\" class=\"input\" name=\"geci\"></textarea><br><br>
	      &nbsp;&nbsp;&nbsp;&nbsp;<input type=\"submit\" name=\"submit\" value=\"确定\" class=\"button\" onclick=\"this.blur()\">
		  &nbsp;&nbsp;<input type=\"reset\" value=\"重来\" class=\"button\" onclick=\"this.blur()\">
		  (歌词编辑支持Enter键和BBcode)</td></form></tr>
	     </table>
	 </td></tr>
	 <tr bgcolor=$message[3] align=center><td>$message[1] $message[0]</td></tr>
	 </table>
	";
}

print "</body></html>";
$db->close();
exit;
?>