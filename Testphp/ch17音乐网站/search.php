<?php
//���ּ���
require_once('config.php');
require_once('public.php');
require_once('inc/db.class.php');
if($type != 'cd' && $type !='song' && $type !='singer') error_quit("�Ƿ�����!");
$keyword2 = urlencode($keyword);
if(!$page) $page = 1;
$login = islogin();
if($login)
	$usertab = "inc/havelogin.inc.php";
else
	$usertab = "inc/unlogin.inc.php";
$cd = 'ר��';
$singer = '����';
$song = '����';
$title = 'ȫվ'.$$type.'���� ��'.$$type.'�ؼ���: <font color=red><b>'.$keyword.'</b></font>';
html_head(strip_tags($title));
include_once('inc/head.inc.php');
now_pos('<a href=index.php>��ҳ</a> ��'.$title, '');
?>
<table align=center border=0 cellpadding=0 cellspacing=0 width=760 background='images/bg1.gif'>
<tr>
<td width=160 valign=top align=left>
<!-- ���� -->
<?php include('inc/left.inc.php');?>
</td>
<td width=22 height=100%></td>
<td width=578 valign=top align=left>
<!-- ��Ҫ���� -->
 <table align=center border=0 cellpadding=0 cellspacing=0 width=100% height=100%>
  <tr>
   <td height=18 background='images/bg3.gif' valign=bottom>
   <font color="<? echo $message[14]; ?>">&nbsp;��</font>
   <font color=#ffffff> �� �� �� �� �� -- ��ѡ��������<? echo $$type; ?> </font>
   <font color="<? echo $message[14]; ?>">��</font>
   </td>
  </tr>
  <tr><td width=100% valign=top>
   <table width=100% border=1 cellspacing=0 bordercolordark="#ffffff" bordercolorlight="<? echo $message[2];?>">
<?php
if($keyword == 'all') $keyword = '';
$keyword = str_replace("%", "\%", $keyword);
$keyword = str_replace("_", "\_", $keyword);
require_once('set_page.php');
if($type == 'song') {
	$total = $db->query_first("SELECT COUNT(*) FROM song WHERE song_name LIKE '%$keyword%'");
	$total = $total[0];
	$start = ($page-1) * $perpage;
	if($start > $total) $start = $total;
	$burl = $PHP_SELF."?type=".$type."&keyword=".$keyword2."&page=";
	$other_page = "��ҳ��";
	$other_page .= set_page($burl, $total, $page, $perpage);
	$other_page .="����¼.&nbsp;&nbsp;������<input type=text class=input size=1 name=page maxlength=5>ҳ<input type=hidden name=type value='$type'><input type=hidden name=keyword value='$keyword'><input type=submit value='Go' class=button onclick=this.blur()>";
	echo "<TR bgcolor=$message[2] height=18 align=center>"
		."<td align=left>����</td><td>�ݳ�</td><td width=6%>����</td><td width=12%>Mp3����</td>"
		."<td width=6%>���</td><td width=6%>���</td><td width=8%>����</td><td width=6%>�ղ�</th></TR>";
	$res = $db->query("select s.song_id, s.song_name, s.mp3_url, s.geci, s.click, s.singer_id, s2.singer_name  from song s, singer s2 where s.song_name like '%$keyword%' and s2.singer_id = s.singer_id order by s.click desc limit $start, $perpage");
	while($tmp = $db->fetch_array($res))	{
		unset($download); //�����
		$mp3_url = split('\|',$tmp[mp3_url],10);
		$i = 1;
		$tmpurl = $mp3_url[0];
		$flag=Array('','��','��','��','��','��','��','��','��','��','��');
		while($tmpurl) {
			if($login)
				$download .= "<a href='$tmpurl'>$flag[$i]</a>";
			else
				$download .= $flag[$i];
			$tmpurl = next($mp3_url);
			$i++;
		}
		$geci = ($tmp[geci] == 1 ? '�鿴' : '�ṩ');
		echo "<TR align=center height=18><td align=left><img src=images/dot2.gif> <a href='listen/listen_id.php?song_id=$tmp[song_id]' onclick='return Listen(this.href)'>$tmp[song_name]</a></td><td><a href='singer.php?singer_id=$tmp[singer_id]&singer_name=".urlencode($tmp[singer_name])."'>$tmp[singer_name]</a></td><td><a href='listen/listen_id.php?song_id=$tmp[song_id]' onclick='return Listen(this.href)'>����</a></td><td>$download</td><td><a href='geci.php?song_id=$tmp[song_id]' onclick='return Geci(this.href);'>$geci</a></td><td><a href='ordersong.php?song_id=$tmp[song_id]&song_name=".urlencode($tmp[song_name])."&song_url=$tmp[rm_url]&singer_id=$tmp[singer_id]&singer_name=".urlencode($tmp[singer_name])."' onclick='return OrderSong(this.href);'>���</a></td><td>$tmp[click]</td><td><a href='favorite.php?type=song&song_id=$tmp[song_id]' onclick='return Favorite(this.href);'>�ղ�</a></td></tr>";
	}
	echo "<tr bgcolor=$message[2] height=18><form method=post action=$php_self><td colspan=8>
		$other_page	</td></form></tr>";
}else if($type == 'singer'){
	$total = $db->query_first("select count(*) from singer where singer_name like '%$keyword%'");
	$total = $total[0];
	$start = ($page-1) * $perpage;
	if($start > $total) $start = $total;
	$burl = $PHP_SELF."?type=".$type."&keyword=".$keyword2."&page=";
	$other_page = "��ҳ��";
	$other_page .= set_page($burl, $total, $page, $perpage);
	$other_page .="����¼.&nbsp;&nbsp;������<input type=text class=input size=1 name=page maxlength=5>ҳ<input type=hidden name=type value='$type'><input type=hidden name=keyword value='$keyword'><input type=submit value='Go' class=button onclick=this.blur()>";
	echo "<tr bgcolor=$message[2] align=center><td>����</td><td width=8%>ר����</td><td width=8%>������</td><td width=15%>�����</td><td width=8%>���</td><td width=10%>������</td></tr>";
	$res = $db->query("select singer_id, singer_name, cd_num, song_num, click from singer where singer_name like '%$keyword%' order by alpha limit $start, $perpage");
	while($tmp = $db->fetch_array($res)){
		echo "<tr align=center height=18><td align=left><img src=images/dot2.gif><a href='singer.php?singer_id=$tmp[singer_id]&singer_name=$tmp[singer_name]'>$tmp[singer_name]</a></td><td>$tmp[cd_num]</td><td>$tmp[song_num]</td><td>$tmp[click]</td><td><a href='singer_introduce.php?singer_id=$tmp[singer_id]' onclick='return querysinger(this.href)'>�鿴</a></td><td><a href='board.php?brd_id=$tmp[singer_id]&singer_name=$tmp[singer_name]'>����</a></td></tr>";
	}
	echo "<tr bgcolor=$message[2] height=18><form method=post action=$php_self><td colspan=6 align=right>$other_page	</td></form></tr>";	
} else if($type == 'cd') {
	$total = $db->query_first("SELECT COUNT(*) FROM cd WHERE cd_name LIKE '%$keyword%'");
	$total = $total[0];
	$start = ($page-1) * $perpage;
	if($start > $total) $start = $total;
	$burl = $PHP_SELF."?type=".$type."&keyword=".$keyword2."&page=";
	$other_page = "��ҳ��";
	$other_page .= set_page($burl, $total, $page, $perpage);
	$other_page .="����¼.&nbsp;&nbsp;������<input type=text class=input size=1 name=page maxlength=5>ҳ
		<input type=hidden name=type value='$type'><input type=hidden name=keyword value='$keyword'>
		<input type=submit value='Go' class=button onclick=this.blur()>";
	echo "<tr bgcolor=$message[2] height=18 align=center><td>ר������</td><td width=8%>������</td><td width=12%>����</td><td width=10%>�����</td><td width=16%>����ʱ��</td><td width=10%>���</th></tr>";
	$res = $db->query("select c.cd_id, c.cd_name, c.song_num, c.lang, c.pub_date, c.click, c.singer_id, s.singer_name from cd c, singer s where cd_name like '%$keyword%' and s.singer_id = c.singer_id order by click desc limit $start, $perpage");
	while($tmp = $db->fetch_array($res)){
		echo "<TR height=18 align=center><td align=left><img src=images/dot2.gif><a href='cd.php?cd_id=$tmp[cd_id]&cd_name=$tmp[cd_name]&singer_id=$tmp[singer_id]&singer_name=$tmp[singer_name]'>��".$tmp[cd_name]."��</a></td><td>$tmp[song_num]</td><td>$tmp[lang]</td><td>$tmp[click]</td><td>$tmp[pub_date]</td><td><a href='cd_introduce.php?cd_id=$tmp[cd_id]' onclick='return QuerySinger(this.href)'>�鿴</a></td></tr>";
	}
	echo "<tr bgcolor=$message[2] height=18><form method=post action=$php_self><td colspan=6 align=right>$other_page</td></form></tr>";	
}
?>
   </table>
<!-- ���� -->  
  </td></tr>
 </table>
</td>
</tr>
</table>
<?php
include_once('inc/foot.inc.php');
exit;
?>