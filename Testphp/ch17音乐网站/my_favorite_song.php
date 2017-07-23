<?php
//我的最爱
require_once('config.php');
require_once('public.php');
require_once('inc/db.class.php');
if(!islogin()) error_quit3("您还没有登录！$message[4] $message[6]"); //登录验证
$usertab = "inc/havelogin.inc.php";
html_head("我的音乐收藏");
include('inc/head.inc.php');
now_pos('<a href=index.php>首页</a> 》我的音乐收藏', '');
?>
<script language="javascript" src="song.js"></script>
<table align=center border=0 cellpadding=0 cellspacing=0 width=760 background='images/bg1.gif'>
<tr>
<td width=160 valign=top align=left>
<!-- 左栏 -->
<?php include('inc/left.inc.php');?>
</td>
<td width=22 height=100%></td>
<td width=578 valign=top align=left>
<!-- 主要部分 -->
 <table align=center border=0 cellpadding=0 cellspacing=0 width=100% height=100%>
  <tr>
   <td height=18 background='images/bg3.gif' valign=bottom>
   <font color="<? echo $message[14]; ?>">&nbsp;■</font>
   <font color=#ffffff> 我 的 音 乐 收 藏 </font>
   <font color="<? echo $message[14]; ?>">■</font>
   </td>
  </tr>
  <tr><td width=100% valign=top>
   <!-- 开始显示 -->
   <table width=100% border=1 cellspacing=0 bordercolordark="#ffffff" bordercolorlight="<? echo $message[2];?>">
    <form name='SelectSongs'>	
<?php
	$k = rand(0, 100);
	$k = intval($k%10);
	$listen = "listen/listen".$k.".php";
	$songs = split('\|',$m_user[favorite_song], $message[8]);
	$total = count($songs) - 2;
	if($total < 1)
		echo "<tr><td colspan=8><h3>您没有收藏任何歌曲！</h3></td></tr>";
	else{
		if(!isset($page) || ($page <= 0)) $page = 1;
		$start = ($page - 1) * $perpage + 1;
		$end = $start + $perpage;
		if($start > $total) $start = $total;
		if($end > $total) $end = $total;
		$expr = $songs[$start];
		$start += 1;
		for($i=$start; $i<=$end; $i++) {
			if(!empty($songs[$i]))
				$expr .= ",".$songs[$i];
		}
		$other_page = "分页: ";
		require('set_page.php');
		$other_page .= set_page($php_self."?page=", $total, $page, $perpage);
		$other_page .= "条记录";
		echo "<tr bgcolor='$message[2]'><td colspan=9>$other_page</td></tr><tr bgcolor='$message[2]' align=center height=18><td width=3%>选</td><td>歌名</td><td>专辑</td><td>歌手</td><td width=6%>试听</td><td width=6%>点歌</td><td width=6%>歌词</td><td width=6%>删除</td></tr>";
		$sql = "select s.song_id, s.song_name, s.rm_url, c.cd_id, c.cd_name, g.singer_id, g.singer_name from song s, cd c, singer g where s.song_id in (".$expr.") and c.cd_id = s.cd_id and g.singer_id = c.singer_id";
		$res = $db->query($sql);
		while($tmp = $db->fetch_array($res)){
			echo "<tr align=center><td><input type=checkbox name='SelectSongsID' value='$tmp[song_id]' onclick=this.blur()></td><td align=left><a href='$listen?song_url=$tmp[rm_url]' onclick='return listen(this.href);'>$tmp[song_name]</a></td><td><a href='cd.php?cd_id=$tmp[cd_id]&cd_name=".urlencode($tmp[cd_name])."&singer_id=$tmp[singer_id]&singer_name=$tmp[singer_name]'>《".$tmp[cd_name]."》</a></td><td><a href='singer.php?singer_id=$tmp[singer_id]&singer_name=$tmp[singer_name]'>$tmp[singer_name]</a></td><td><a href='listen/listen.php?song_url=$tmp[rm_url]' onclick='return listen(this.href);'>试听</a></td><td><a href='ordersong.php?song_id=$tmp[song_id]&song_name=".urlencode($tmp[song_name])."&song_url=$tmp[rm_url]&singer_id=$tmp[singer_id]&singer_name=".urlencode($tmp[singer_name])."' onclick='return ordersong(this.href);'>点歌</a></td><td><a href='geci.php?song_id=$tmp[song_id]' onclick='return geci(this.href);'>查看</a></td><td><a href='my_favorite_del.php?type=song&id=$tmp[song_id]'><font color=red>删除</font></a></td></tr>";
		}
	}
?>
    <tr bgcolor=<?echo $message[2];?>><td colspan=9>选取歌曲处理: 
		<input type=button class=button onclick='do_select(true);this.blur()' value='全选'>
		<input type=button class=button onclick='do_select(false);this.blur()' value='反选'>
		<input type=button class=button onclick='play_song();this.blur()' value='播放'>
		<input type=button class=button onclick='del_song();this.blur()' value='删除'>
		&nbsp;&nbsp;&nbsp;&nbsp;<a href='my_favorite_del.php?type=song&id=all'>清空收藏列表</a>
	</td></form></tr>
    <tr bgcolor=<?echo $message[2];?>><td colspan=9>
	<?php echo $other_page;?>
	</td></tr>
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