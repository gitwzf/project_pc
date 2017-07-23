<?php
//歌词 geci_modify.php
require('config.php');
require('public.php');
require('inc/db.class.php');

if(!islogin()) err_quit3("请先登录！\n");
if((count_exp() < $message[7]) && !is_admin()) error_quit3("你的权限等级不够, 经验值必须超过<b>".$message[7]."</b>，".$message[6]);
if($song_id == '') error_quit3("非法操作!");

if(isset($submit)) //歌词为空
{
 if(strlen($geci)<200) error_quit3("你的修改好像有错喔~");
 $now = date("Y-m-d H:i:s");
 $geci = trim($geci);
 $geci .= "\n\n--\n※ ".$m_user[user_name]."于".$now."个改过本歌词";

 $db->query("UPDATE geci SET geci = '$geci', zuoqu = '$zuoqu', tianci = '$tianci' WHERE song_id = '$song_id'");

 html_head("修改歌词成功");
 print "
	 <br><br><br>
	 <p align=center><span class=okmsg>添加成功！</span> 感谢您的参与！</p>
	 <p align=center> $message[0] </p>
	 <script>setTimeout('window.close()', 2000);</script>
 ";
}
else{
$query = "SELECT S.song_name, G.* FROM song S, geci G WHERE S.song_id = '$song_id' AND G.song_id = '$song_id'";
$res = $db->query_first($query);

//把上次改过的资料去掉
$res[geci] = addslashes($res[geci]);
//$res[geci] = strtok($res[geci],'\n\n--');
//$res[geci] = $nouse;//eregi_replace('$nouse','',$res[geci]);

html_head("修改《".$res[song_name]."》的歌词");
print "
	<br>
	<table width=96% bgcolor='#000000' align=center cellPadding=2 cellSpacing=1 height=95%>
	<tr height=18 bgcolor=$message[2]><td>为".$res[song_name]."修改歌词</td></tr>
	<tr width=100%><td background='images/bg1.gif'>
		 <table border=0 align=center>
	      <tr><form action='$PHP_SELF' mothod=post><td align=left>
		  作词: <input type=text size=15 name=tianci value='$res[tianci]' class=input> 作曲: <input type=text name=zuoqu size=15 value='$res[zuoqu]' class=input><br>
	      <textarea cols=65 rows=20 class=input nowrap name=geci>".htmlspecialchars($res[geci])."</textarea><br><br>
	      <input type=hidden name=song_id value='$song_id'>
	      &nbsp;&nbsp;&nbsp;&nbsp;<input type=submit name=submit onclick=this.blur() value=确定 class=button>
		  &nbsp;&nbsp;<input type=reset value=重来 class=button onclick=this.blur()>
		 </td></form></tr>
         </table>
	 </td></tr>
	";
if($res[tigong])
	print "
	  <tr bgcolor=$message[2]><td>感谢网友<b>$res[tigong]</b>提供歌词.</td></tr>\n";
print "
	  <tr bgcolor=$message[3]><td>$message[1] $message[0]</td></tr>
	  </table>
	  ";
}

print "</body></html>";
$db->close();
exit;
?>