<?php
//�г��������� allsong.php
require('config.php');
require('public.php');
require('inc/db.class.php');

$singer_id=1;
$singer_name='Beyond';
$cate_name='�ֶ����';
$area_name='��̨';
$cate_id='3';
$area_id='1';

if($singer_id == '' && $singer_name == '') error_quit("�Ƿ�����!");

//�����ַ�����
$cate_name2 = urlencode($cate_name);
$area_name2 = urlencode($area_name);
$singer_name2 = urlencode($singer_name);

$login = islogin();
if($login) 
 $usertab = "inc/havelogin.inc.php";
else
 $usertab = "inc/unlogin.inc.php";

/* ���⣬λ�ô��� */
$title = "ȫ�������б� - ".$singer_name;
$pos = "<a href=index.php>��ҳ</a>";
if($cate_name != '' || $area_name != '') 
{
 $title .= " - ".$area_name.$cate_name;
 if($cate_name != '' && $area_name != '')
 $pos .= " ��<a href='cate2.php?cate_id=$cate_id&cate_name=$cate_name2&area_id=$area_id&area_name=$area_name2'>$area_name$cate_name</a>";
 else
  if($cate_name != '')
   $pos .= " ��<a href='".($cate_id != $message[11] ? 'cate.php' : 'movie-tv.php')."?cate_id=$cate_id&cate_name=$cate_name2'>$cate_name</a>";
 else
	 $pos .= " ��<a href='area.php?area_id=$area_id&area_name=$area_name2'>$area_name</a>";
}
$pos .= " ��<a href='".($cate_id != $message[11] ? 'singer.php' : 'movie-tv_detail.php')."?cate_id=$cate_id&cate_name=$cate_name2&area_id=$area_id&area_name=$area_name2&singer_id=$singer_id&singer_name=$singer_name2'>$singer_name</a> ��ȫ�������б�";
/* ������ */

html_head($title);
include('inc/head.inc.php');
now_pos($pos, '');
?>

<script language="javascript" src="song.js"></script>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 width=760 background='images/bg1.gif'>
<TR>
<TD width=160 valign=top align=left>
<!-- ���� -->
<?php include('inc/left.inc.php');?>
</TD>
<TD width=22></TD>
<TD width=578 valign=top align=left>
<!-- ��Ҫ���� -->
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
	   <font color=\"".$message[14]."\">&nbsp;��</font>
	   <font color=#FFFFFF>".$singer_name."ר���б� -- ��".$song_num."��</font>
	   <font color=\"".$message[14]."\">��</font>
	   ...<a href=\"board.php?brd_id=".$singer_id."&singer_name=".$singer_name2."&cate_id=".$cate_id  
	   ."&cate_name=".$cate_name2."&area_id=".$area_id."&area_name=".$area_name2
	   ."\" target=_blank><font color=#FFFFFF>".$singer_name."������</font></a>
	   ...<a href=\"singer_introduce.php?singer_id=".$singer_id
	   ."\" onclick=\"return QuerySinger(this.href)\"><font color=#FFFFFF>".$singer_name."���</font></a>
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
		  ... <a href='singer_introduce.php?singer_id=$singer_id' onclick='return QuerySinger(this.href)'>������ &gt;&gt; 		...</a>
		  </td></TR>
		</Table>
	   <TABLE width=100% border=0 cellSpacing=1 bgcolor=\"".$message[14]."\">
		<TR bgcolor=\"".$message[2]."\" height=18 align=center>
		<td widtd=3%>ѡ</td><td widtd=45%> �� �� �� �� </td><td widtd=6%>����</td><td widtd=12%><font color=red>�Ҽ�����</font></td>
		<td widtd=6%>���</td><td widtd=6%>���</td><td widtd=10%>����</td><td widtd=6%>����</td><td widtd=6%>�ղ�</td>
		</TR>
		<form name='SelectSongs'>
		";

		$k = rand(0, 100);
		$k = intval($k%10); //�������ʴ�Щ
		$listen = "listen/listen".$k.".php";

		while($tmp = $db->fetch_array($result)) {

			unset($download); //�����
			$mp3_url = explode('\|',$tmp['mp3_url'],10);
			$i = 1;
			$tmpurl = $mp3_url[0];
			$flag=Array('','��','��','��','��','��','��','��','��','��','��');
			while($tmpurl) {
				if($login) 
					@$download .= "<a href='$tmpurl'>$flag[$i]</a>";
				else
					@$download .= $flag[$i];
				$tmpurl = next($mp3_url);
				$i++;
			}
		
			$geci = ($tmp['geci'] == 1 ? '�鿴' : '�ṩ');
		
		print "
			<TR align=center>
			<td background='images/bg1.gif'><input type=checkbox name='SelectSongsID' value='".$tmp['song_id']."' onclick=this.blur()></td>
			<td align=left background='images/bg1.gif'>
			<a href='$listen?song_url=".$tmp['rm_url']."' onclick='return Listen(this.href);'>".$tmp['song_name']."</a>
			</td>
			<td background='images/bg1.gif'><a href='$listen?song_url=".$tmp['rm_url']."' onclick='return Listen(this.href);'>����</a></td>
			<td background='images/bg1.gif'>$download</td>
			<td background='images/bg1.gif'><a href='geci.php?song_id=".$tmp['song_id']."' onclick='return Geci(this.href);'>$geci</a></td>
			<td background='images/bg1.gif'><a href='ordersong.php?song_id=".$tmp['song_id']."&song_name=".urlencode($tmp['song_name'])."&song_url=".$tmp['rm_url']."&singer_id=$singer_id&singer_name=$singer_name2' onclick='return OrderSong(this.href);'>���</a></td>
			<td background='images/bg1.gif'>".$tmp['click']."</td>
			<td background='images/bg1.gif'><a href='board_add.php?singer_name=$singer_name2&brd_id=$singer_id&title=".urlencode('���ۡ�'.$tmp['song_name'].'��')."' onclick='return Post(this.href);'>����</td>
			<td background='images/bg1.gif'><a href='favorite.php?type=song&song_id=".$tmp['song_id']."' onclick='return Favorite(this.href);'>�ղ�</a></td>
			</TR>
			";
	}
    print" <TR height=18>
	<td colspan=9 background='images/bg1.gif'>
	�� <a href='listen/listen_cd.php?cd_id=".@$cd_id."' onclick='return Listen(this.href);'>������ȫ������</a> &nbsp;&nbsp;
	</td>
	</TR>";
?>
    <TR bgcolor=<?php echo $message[2];?>><td colspan=9>
	ѡȡ��������: 
	<input type=button class=button onclick='do_select(true);this.blur()' value='ȫѡ'>
	<input type=button class=button onclick='do_select(false);this.blur()' value='��ѡ'>
	<input type=button class=button onclick='play_song();this.blur()' value='����'>
	<input type=button class=button onclick='collect_song();this.blur()' value='�ղ�'>
	</td></form></TR>
    <TR bgcolor=<?php echo $message[2];?>><td colspan=9>
		<?php echo @$other_page; ?>
	</td></TR>
   </TABLE>
<!-- ���� --> 
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