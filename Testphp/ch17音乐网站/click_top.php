<?php
//click_top.php
//�㳪����

require('config.php');
require('public.php');
require('inc/db.class.php');

$login = islogin();
if($login) 
 $usertab = "inc/havelogin.inc.php";
else
 $usertab = "inc/unlogin.inc.php";

html_head('�㳪���а�');
include('inc/head.inc.php');
now_pos('<a href=index.php>��ҳ</a> ���㳪���а�','');
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
 <TABLE align=center border=0 cellPadding=2 cellSpacing=0 width=100% height=100%>
  <TR><TD width=100% valign=top height=45><? include('inc/cate2.inc.php'); ?></TD></TR>
  <TR><TD width=100% valign=top height=2 bgcolor=#000000></TD></TR>
  <TR><TD width=100% valign=top>
  	   <TABLE border=1 cellSpacing=0 bordercolordark="#FFFFFF" bordercolorlight="<? echo $message[2];?>" width=100%>
        <TR align=center>
		 <TD height=18 colspan=6 background='images/bg3.gif' valign=bottom>
		   <font color="<? echo $message[14]; ?>">&nbsp;��</font>
		   <font color=#FFFFFF> �� �� �� �� </font>
		   <font color="<? echo $message[14]; ?>">��</font>
		   .............................................................. Hot Music
		  </TD></TR>
        <TR bgcolor='<?=$message[2];?>' align=center height=18>
          <Td align=left>������</td><td align=left>����ר��</Td><Th width=16%>����</td><td width=8%>���</td><td width=10%>���</Td>
	    </TR>
		<?php
		 $song = $db->query("SELECT S.song_id, S.song_name, S.click, S.cd_id, S.singer_id, C.cd_name, S2.singer_name FROM song S, cd C, singer S2 WHERE C.cd_id = S.cd_id AND S2.singer_id = S.singer_id ORDER BY S.click DESC LIMIT 0, 50");
		 #�µ�
		 while($tmp = $db->fetch_array($song))
		 {
             print "<TR align=center>
			        <TD align=left><img src=images/dot2.gif>
					 <a href=listen/listen_id.php?song_id=$tmp[song_id] onclick='return Listen(this.href)'>$tmp[song_name] </a>
					</td><td align=left>
					<a href='cd.php?cd_id=$tmp[cd_id]&cd_name=".urlencode($tmp[cd_name])."&singer_id=$tmp[singer_id]&singer_name=".urlencode($tmp[singer_name])."'>��".$tmp[cd_name]."��</a></td>
					<td><a href='singer.php?singer_id=$tmp[singer_id]&singer_name=".urlencode($tmp[singer_name])."'>$tmp[singer_name]</a> </TD>
					<td><a href='geci.php?song_id=$tmp[song_id]' onclick='return Geci(this.href)'>�鿴</a></td>
					<td>$tmp[click]</td>
 		           </TR>";
		 }
		 $db->free_result();
		 unset($song);
		 unset($tmp);
       ?>
	   </TABLE>
  </TD></TR>
 </TABLE>
</TD>
</TR>
</TABLE>
<?php
include('inc/foot.inc.php');
exit;
?>