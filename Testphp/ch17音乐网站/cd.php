<?php
//ר�����б��ļ�.cd.php
require('config.php');
require('public.php');
require('inc/db.class.php');

$singer_id=1;
$singer_name='Beyond';
$cd_id='1';
$cd_name='����һ';
$cate_name='�ֶ����';
$area_name='��̨';
$cate_id='3';
$area_id='1';

if($singer_id == '' || $singer_name == '' || $cd_id == '' || $cd_name == '') error_quit("�Ƿ�����!");

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
$title = $cd_name." - ".$singer_name;
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
$pos .= " ��<a href='".($cate_id != $message[11] ? 'singer.php' : 'movie-tv_detail.php')."?cate_id=$cate_id&cate_name=$cate_name2&area_id=$area_id&area_name=$area_name2&singer_id=$singer_id&singer_name=$singer_name2'>$singer_name</a> ��$cd_name";
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
 print "
 <TR>
  <TD height=18 background='images/bg3.gif' valign=bottom>
   <font color=\"".$message[14]."\">&nbsp;��</font>
   <font color=#FFFFFF>".$singer_name.": ��".$cd_name."��</font>
   <font color=\"".$message[14]."\">��</font>
   ...<a href=\"board.php?brd_id=".$singer_id."&singer_name=".$singer_name2."&cate_id=".$cate_id  
   ."&cate_name=".$cate_name2."&area_id=".$area_id."&area_name=".$area_name2
   ."\" target=_blank><font color=#FFFFFF>".$singer_name."������</font></a>
   ...<a href=\"singer_introduce.php?singer_id=".$singer_id
   ."\" onclick=\"return QuerySinger(this.href)\"><font color=#FFFFFF>".$singer_name."���</font></a>
   ...<a href=\"allsong.php?singer_id=".$singer_id."&singer_name=".$singer_name2."&cate_id=".$cate_id  
   ."&cate_name=".$cate_name2."&area_id=".$area_id."&area_name=".$area_name2
   ."\"><font color=#FFFFFF>�г�".$singer_name."ȫ������</font></a> ... 
   </TD>
  </TR>
  <TR><TD width=100% valign=top>
";
    $tmp = $db->query_first("SELECT * FROM cd WHERE cd_id = '$cd_id'");
	if($tmp['imgurl'] == '' || $tmp['imgurl'] == 'http://')
		$tmp['imgurl'] = $default_cd_cover;
		
	if(strncasecmp($tmp['imgurl'], "http://", 7))
		$tmp['imgurl'] = $data_url.$tmp['imgurl'];

	print "
		<Table border=0>
		<tr><td width=\"22%\">
		<a href=\"cd.php?cd_id=".$tmp['cd_id']."&cd_name=".urlencode($tmp['cd_name'])."&cate_id=".$cate_id
		."&cate_name=".$cate_name2."&area_id=".$area_id."&area_name=".$area_name2."&singer_id=".$singer_id
		."&singer_name=".$singer_name2."\"><img src=\"".$tmp['imgurl']."\" width=110 height=110 border=0 alt=\"����ͼ\"></a>
		</td><td width=\"78%\">
			<table width=100% border=0 cellPadding=0 cellSpacing=0>
			 <tr><td><b>ר������: </b></td><td><a href='cd.php?cd_id=".$tmp['cd_id']."&cd_name=".urlencode($tmp['cd_name'])."&cate_id=$cate_id&cate_name=$cate_name2&area_id=$area_id&area_name=$area_name2&singer_id=$singer_id&singer_name=$singer_name2'>".$tmp['cd_name']."</a></td><td><b>��������: </b></td><td>".$tmp['lang']."</td></tr>
			 <tr><td><b>������Ŀ: </b></td><td>".$tmp['song_num']." ��</td><td><b>�������: </b></td><td>".$tmp['click']."</td></tr>
             <tr><td><b>����ʱ��: </b></td><td>".$tmp['pub_date']."</td><td><b>���ʱ��: </b></td><td>".$tmp['add_date']."</td></tr>
			 <tr><td colspan=4><b>���: </b><span class=p6>".$tmp['introduce']."</span></td></tr>
		     </table>
		</td><tr>\n</Table>\n";

	print "
	   <TABLE width=100% border=0 cellSpacing=1 bgcolor=\"".$message[14]."\">
		<TR bgcolor=\"".$message[2]."\" height=18 align=center>
		<td widtd=3%>ѡ</td><td widtd=45%> �� �� �� �� </td><td widtd=6%>����</td><td widtd=12%><font color=red>�Ҽ�����</font></td>
		<td widtd=6%>���</td><td widtd=6%>���</td><td widtd=10%>����</td><td widtd=6%>����</td><td widtd=6%>�ղ�</td>
		</TR>
		<form name='SelectSongs'>
		";

	$db->query("UPDATE cd SET click=click+1 WHERE cd_id = '$cd_id'");
	$query = "SELECT * FROM song WHERE cd_id = '$cd_id' ORDER BY click DESC";
	$result = $db->query($query);
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
	�� <a href='listen/listen_cd.php?cd_id=$cd_id' onclick='return Listen(this.href);'>�������ű�ר��</a> &nbsp;&nbsp;
	�� <a href='favorite.php?type=cd&cd_id=$cd_id' onclick='return Favorite(this.href);'>����".$cd_name."����ӵ��ҵ�ר����ѡ</a></td>
	</TR>";
?>
    <TR bgcolor=<?echo $message[2];?>><td colspan=9>
	ѡȡ��������: 
	<input type=button class=button onclick='do_select(true);this.blur()' value='ȫѡ'>
	<input type=button class=button onclick='do_select(false);this.blur()' value='��ѡ'>
	<input type=button class=button onclick='play_song();this.blur()' value='����'>
	<input type=button class=button onclick='collect_song();this.blur()' value='�ղ�'>
	</td></form></TR>
   </TABLE>
<!-- ���� --> 

<!-- ��ʼ���������� -->
   <TABLE width=100% border=1 cellSpacing=0 bordercolordark="#FFFFFF" bordercolorlight="<? echo $message[2];?>" background='images/bg1.gif'>
<?php
	if(!@$page) $page = 1;
	$brd_id = $singer_id;
	$string1  = "<font color=\"".$message[14]."\">��</font> <font color=#FFFFFF>".$singer_name."������ -- ����15ƪ���� </font><font color=\"".$message[14]."\">��</font>";

	$string1 .=" .... <a href='board_add.php?brd_id=$brd_id&singer_name=$singer_name2&title=".urlencode('�ҿ���'.$singer_name.'��')."' onclick='return Post(this.href)'><font color=#FFFFFF>����������</font></a> ........ ";

	$string1 .= " .... <a href='board.php?brd_id=$singer_id&singer_name=$singer_name2&cate_id=$cate_id&cate_name=$cate_name2&area_id=$area_id&area_name=$area_name2'><font color=#FFFFFF>��ʾ�������� &gt;&gt; </font></a>.... ";
	
	$res = $db->query("SELECT post_id, title, author, date, thread_id, click FROM post WHERE brd_id = '$brd_id' ORDER BY date DESC LIMIT 0, 15");
	
	print "
	<TR height=18><td colspan=5 background='images/bg3.gif' valign='bottom'>
	$string1
	</td></TR>
    <TR height=18 bgcolor='$message[2]' align=center>
	<td widtd=53%>���۱���</td><td widtd=15%>����</td><td widtd=12%>ʱ��</td><td widtd=10%>����</td><td widtd=10%>����</td>
	</TR>";
	
	while($tmp=$db->fetch_array($res)) {
		if(strlen($tmp[title]) > 50) $tmp[title] = substr($tmp[title], 0, 50)."..."; //������ֻ��ʾ25����
		if(eregi(date("Y-m-d"),$tmp[date])) {
			$flag = "<font color=red>�� </font>";
			$tmp[title] = str_replace('Re:','<font color=red>Re:</font>',$tmp[title]);
		} else
			$flag = "�� "; //Re: ����
		
		if(!ereg('Re:',$tmp[title])) $tmp[title] =$flag.$tmp[title];//re:�ķ�����
		
		if(is_admin() || ($m_user[user_name] == $tmp[author]))
			$admin_str = "<a href=board_del.php?post_id=$tmp[post_id]&singer_name=$singer_name2><font color=red>ɾ��</font></a>";
		else
			$admin_str = "--";
		
		$tmp[date] = substr($tmp[date], 5, 11);
		
		print "
			<TR height=18 align=center>
			<td align=left><a href='board_read.php?brd_id=$brd_id&post_id=$tmp[post_id]&singer_name=$singer_name2&thread_id=$tmp[thread_id]' onclick='return Post(this.href)'>$tmp[title]</a></td>
			<td align=left><a href='user_query.php?user_name=".urlencode($tmp[author])."' onclick='return QuerySinger(this.href)'>$tmp[author]</a></td>
			<td>$tmp[date]</td><td>$tmp[click]</td><td>$admin_str</td>
			</TR>
			";
	}
	
	print "<TR height=18><td colspan=5 background='images/bg3.gif' valign='bottom'>".$string1."</td></TR>\n";
?>
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