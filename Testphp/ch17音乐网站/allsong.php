<?php
//列出所有音乐 allsong.php
require('config.php');
require('public.php');
require('inc/db.class.php');

$singer_id=1;
$singer_name='Beyond';
$cate_name='乐队组合';
$area_name='港台';
$cate_id='3';
$area_id='1';

if($singer_id == '' && $singer_name == '') error_quit("非法操作!");

//编码字符串：
$cate_name2 = urlencode($cate_name);
$area_name2 = urlencode($area_name);
$singer_name2 = urlencode($singer_name);

$login = islogin();
if($login) 
 $usertab = "inc/havelogin.inc.php";
else
 $usertab = "inc/unlogin.inc.php";

/* 标题，位置处理 */
$title = "全部歌曲列表 - ".$singer_name;
$pos = "<a href=index.php>首页</a>";
if($cate_name != '' || $area_name != '') 
{
 $title .= " - ".$area_name.$cate_name;
 if($cate_name != '' && $area_name != '')
 $pos .= " 》<a href='cate2.php?cate_id=$cate_id&cate_name=$cate_name2&area_id=$area_id&area_name=$area_name2'>$area_name$cate_name</a>";
 else
  if($cate_name != '')
   $pos .= " 》<a href='".($cate_id != $message[11] ? 'cate.php' : 'movie-tv.php')."?cate_id=$cate_id&cate_name=$cate_name2'>$cate_name</a>";
 else
	 $pos .= " 》<a href='area.php?area_id=$area_id&area_name=$area_name2'>$area_name</a>";
}
$pos .= " 》<a href='".($cate_id != $message[11] ? 'singer.php' : 'movie-tv_detail.php')."?cate_id=$cate_id&cate_name=$cate_name2&area_id=$area_id&area_name=$area_name2&singer_id=$singer_id&singer_name=$singer_name2'>$singer_name</a> 》全部歌曲列表";
/* 处理完 */

html_head($title);
include('inc/head.inc.php');
now_pos($pos, '');
?>

<script language="javascript" src="song.js"></script>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 width=760 background='images/bg1.gif'>
<TR>
<TD width=160 valign=top align=left>
<!-- 左栏 -->
<?php include('inc/left.inc.php');?>
</TD>
<TD width=22></TD>
<TD width=578 valign=top align=left>
<!-- 主要部分 -->
 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 width=100%>

 <?php
	$db->query("UPDATE singer SET click=click+1 WHERE singer_id = '$singer_id'");
	$song_num = $db->query_first("SELECT COUNT(*) FROM song WHERE singer_id = '$singer_id'");
	$song_num = $song_num[0];
	$query = "SELECT * FROM song WHERE singer_id = '$singer_id' ORDER BY click DESC";

	if($song_num > $perpage) {
		if(!$page) $page = 1;

		$start = ($page-1) * $perpage;
		if($start > $song_num) $start = $song_num;

		$query .= " LIMIT $start, $perpage";

		require('set_page.php');
		$burl = $PHP_SELF."?cate_id=".$cate_id."&cate_name=".$cate_name2."&area_id=".$area_id."&area_name="
		.$area_name2."&singer_id=".$singer_id."&singer_name=".$singer_name2."&page=";
		$other_page = set_page($burl, $song_num, $page, $perpage);
	}
	
	$result = $db->query($query);

	print "
	 <TR>
	  <TD height=18 background='images/bg3.gif' valign=bottom>
	   <font color=\"".$message[14]."\">&nbsp;■</font>
	   <font color=#FFFFFF>".$singer_name."专辑列表 -- 共".$song_num."首</font>
	   <font color=\"".$message[14]."\">■</font>
	   ...<a href=\"board.php?brd_id=".$singer_id."&singer_name=".$singer_name2."&cate_id=".$cate_id  
	   ."&cate_name=".$cate_name2."&area_id=".$area_id."&area_name=".$area_name2
	   ."\" target=_blank><font color=#FFFFFF>".$singer_name."讨论区</font></a>
	   ...<a href=\"singer_introduce.php?singer_id=".$singer_id
	   ."\" onclick=\"return QuerySinger(this.href)\"><font color=#FFFFFF>".$singer_name."简介</font></a>
	   ...... 
	   </TD>
	  </TR>
	  <TR><TD width=100% valign=top>
	";
    
	$tmp = $db->query_first("SELECT imgurl, LEFT(introduce, 300) as intro FROM singer WHERE singer_id = '$singer_id'");
	if(!$tmp['imgurl'] || $tmp['imgurl'] == 'http://') $tmp['imgurl'] = $default_singer;
	
	if(strncasecmp($tmp['imgurl'], "http://", 7))
		$tmp['imgurl'] = $data_url.$tmp['imgurl'];

	print "
		<Table border=0>
		  <TR><TD width=100% valign=top>
		  <img src=".$tmp['imgurl']."' alt='$singer_name' align='left'>\n<font size=+1> <b>$singer_name</b></font><br><br>
		  ".$tmp['intro']."
		  ... <a href='singer_introduce.php?singer_id=$singer_id' onclick='return QuerySinger(this.href)'>详请点击 &gt;&gt; 		...</a>
		  </td></TR>
		</Table>
	   <TABLE width=100% border=0 cellSpacing=1 bgcolor=\"".$message[14]."\">
		<TR bgcolor=\"".$message[2]."\" height=18 align=center>
		<td widtd=3%>选</td><td widtd=45%> 歌 曲 标 题 </td><td widtd=6%>试听</td><td widtd=12%><font color=red>右键下载</font></td>
		<td widtd=6%>歌词</td><td widtd=6%>点歌</td><td widtd=10%>人气</td><td widtd=6%>评论</td><td widtd=6%>收藏</td>
		</TR>
		<form name='SelectSongs'>
		";

		$k = rand(0, 100);
		$k = intval($k%10); //这样几率大些
		$listen = "listen/listen".$k.".php";

		while($tmp = $db->fetch_array($result)) {

			unset($download); //先清空
			$mp3_url = explode('\|',$tmp['mp3_url'],10);
			$i = 1;
			$tmpurl = $mp3_url[0];
			$flag=Array('','①','②','③','④','⑤','⑥','⑦','⑧','⑨','⑩');
			while($tmpurl) {
				if($login) 
					@$download .= "<a href='$tmpurl'>$flag[$i]</a>";
				else
					@$download .= $flag[$i];
				$tmpurl = next($mp3_url);
				$i++;
			}
		
			$geci = ($tmp['geci'] == 1 ? '查看' : '提供');
		
		print "
			<TR align=center>
			<td background='images/bg1.gif'><input type=checkbox name='SelectSongsID' value='".$tmp['song_id']."' onclick=this.blur()></td>
			<td align=left background='images/bg1.gif'>
			<a href='$listen?song_url=".$tmp['rm_url']."' onclick='return Listen(this.href);'>".$tmp['song_name']."</a>
			</td>
			<td background='images/bg1.gif'><a href='$listen?song_url=".$tmp['rm_url']."' onclick='return Listen(this.href);'>收听</a></td>
			<td background='images/bg1.gif'>$download</td>
			<td background='images/bg1.gif'><a href='geci.php?song_id=".$tmp['song_id']."' onclick='return Geci(this.href);'>$geci</a></td>
			<td background='images/bg1.gif'><a href='ordersong.php?song_id=".$tmp['song_id']."&song_name=".urlencode($tmp['song_name'])."&song_url=".$tmp['rm_url']."&singer_id=$singer_id&singer_name=$singer_name2' onclick='return OrderSong(this.href);'>点歌</a></td>
			<td background='images/bg1.gif'>".$tmp['click']."</td>
			<td background='images/bg1.gif'><a href='board_add.php?singer_name=$singer_name2&brd_id=$singer_id&title=".urlencode('评论「'.$tmp['song_name'].'」')."' onclick='return Post(this.href);'>评论</td>
			<td background='images/bg1.gif'><a href='favorite.php?type=song&song_id=".$tmp['song_id']."' onclick='return Favorite(this.href);'>收藏</a></td>
			</TR>
			";
	}
    print" <TR height=18>
	<td colspan=9 background='images/bg1.gif'>
	※ <a href='listen/listen_cd.php?cd_id=".@$cd_id."' onclick='return Listen(this.href);'>连续播全部歌曲</a> &nbsp;&nbsp;
	</td>
	</TR>";
?>
    <TR bgcolor=<?php echo $message[2];?>><td colspan=9>
	选取歌曲处理: 
	<input type=button class=button onclick='do_select(true);this.blur()' value='全选'>
	<input type=button class=button onclick='do_select(false);this.blur()' value='反选'>
	<input type=button class=button onclick='play_song();this.blur()' value='播放'>
	<input type=button class=button onclick='collect_song();this.blur()' value='收藏'>
	</td></form></TR>
    <TR bgcolor=<?php echo $message[2];?>><td colspan=9>
		<?php echo @$other_page; ?>
	</td></TR>
   </TABLE>
<!-- 结束 --> 
  </TD></TR>
 </TABLE>
</TD>
</TR>
</TABLE>
<!-- footer --> 
<?php
include('inc/foot.inc.php');
exit;
?>