<?php
//�ҵ�� my_favorite_song.php
require('config.php');
require('public.php');
require('inc/db.class.php');

if(!islogin()) error_quit3("����û�е�¼��$message[4] $message[6]"); //��¼��������ܴ������

$usertab = "inc/havelogin.inc.php";

html_head("�ҵ�ר����ѡ");
include('inc/head.inc.php');
now_pos('<a href=index.php>��ҳ</a> ���ҵ�ר����ѡ', '');
?>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 width=760 background='images/bg1.gif'>
<TR>
<TD width=160 valign=top align=left>
<!-- ���� -->
<?php include('inc/left.inc.php');?>
</TD>
<TD width=22 height=100%></TD>
<TD width=578 valign=top align=left>
<!-- ��Ҫ���� -->
 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 width=100% height=100%>
  <TR>
   <TD height=18 background='images/bg3.gif' valign=bottom>
   <font color="<? echo $message[14]; ?>">&nbsp;��</font>
   <font color=#FFFFFF> �� �� ר �� �� ѡ </font>
   <font color="<? echo $message[14]; ?>">��</font>
   .................................................. My Favortie Discs
   </TD>
  </TR>
  <TR><TD width=100% valign=top>
   <!-- ��ʼ��ʾ -->
   <TABLE width=100% border=1 cellSpacing=0 bordercolordark="#FFFFFF" bordercolorlight="<? echo $message[2];?>">    

	<?php
    $cds = split('\|',$m_user['favorite_cd'],100);
    $total= count($cds) -2;

	if($total < 1)  
		print "<TR><td colspan=7><h3>��û���ղ��κ�ר����</h3></td></tr>";

	else
	{
		if(!isset($page) || ($page <= 0)) $page = 1;
		$start = ($page - 1) * $perpage + 1;
		$end = $start + $perpage;
		if($start > $total) $start = $total;
		if($end > $total) $end = $total;

		$expr = $cds[$start];
		$start += 1;
		for($i=$start; $i<=$end; $i++) {
			if(!empty($cds[$i]))
				$expr .= ",".$cds[$i];
		}

		$other_page = "��ҳ: ";
		require('set_page.php');
		$other_page .= set_page($PHP_SELF."?page=", $total, $page, $perpage);
		$other_page .= "����¼";

		print "
			<TR bgcolor='$message[2]'><td colspan=9>
			$other_page
			</td></TR>
			<TR align=center bgcolor='$message[2]' height=18><td align=left>ר������</td><td width=10%>������</td><td>����</td><td width=12%>����</td>
			<td width=15%>����ʱ��</td><td width=15%>��������</td><td width=8%>ɾ��</td></TR>
			";

		$sql = "SELECT C.cd_id as cd_id, C.cd_name as cd_name, C.song_num as song_num, C.lang as lang, C.pub_date as pub_date, S.singer_id as singer_id, S.singer_name as singer_name FROM cd C, singer S WHERE cd_id IN (".$expr.") AND S.singer_id = C.singer_id";
		$res = $db->query($sql);
		while($tmp = $db->fetch_array($res))
		{
		 print "
		 <tr align=center height=18>
		 <td align=left><a href='cd.php?cd_id=$tmp[cd_id]&cd_name=".urlencode($tmp[cd_name])."&singer_id=$tmp[singer_id]&singer_name=".urlencode($tmp[singer_name])."'>
		 ��".$tmp[cd_name]."��</a></td>
		 <td>$tmp[song_num]</td>
		 <td><a href='singer.php?singer_id=$tmp[singer_id]&singer_name=".urlencode($tmp[singer_name])."'>$tmp[singer_name]</a></td>
		 <td>$tmp[lang]</td>	
		 <td>$tmp[pub_date]</td>
		 <td><a href='listen/listen_cd.php?cd_id=$tmp[cd_id]' onclick='return Listen(this.href);'>��������</a></td>
		 <td><a href='my_favorite_del.php?type=cd&id=$tmp[cd_id]'><font color=red>ɾ��</font></a></td>
		 </tr>";
		}
	}
?>
   <TR bgcolor="<?echo $message[2]; ?>"><td colspan=7 height=18>
   <?echo $other_page;?>
   <a href='my_favorite_del.php?type=cd&id=all'>����ҵ�ר����ѡ</a></td></tr>
   </TABLE>
<!-- ���� -->  
  </TD></TR>
 </TABLE>
</TD>
</TR>
</TABLE>

<?php
include('inc/foot.inc.php');
exit;
?>