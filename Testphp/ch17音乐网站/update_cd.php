<?php
//update_cd.php
//���¼����CD
require('config.php');
require('public.php');
require('inc/db.class.php');

$login = islogin();
if($login) 
 $usertab = "inc/havelogin.inc.php";
else
 $usertab = "inc/unlogin.inc.php";

html_head('���¼����ר��-��ܰ������');
include('inc/head.inc.php');
now_pos('<a href=index.php>��ҳ</a> ������ר��','');
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
  <TR><TD width=100% valign=top>
  	   <TABLE border=1 cellSpacing=0 bordercolordark="#FFFFFF" bordercolorlight="<? echo $message[2];?>" width=100%>
        <TR align=center>
		 <TD height=18 colspan=6 background='images/bg3.gif' valign=bottom>
		   <font color="<? echo $message[14]; ?>">&nbsp;��</font>
		   <font color=#FFFFFF> �� �� ר �� </font>
		   <font color="<? echo $message[14]; ?>">��</font>
		   .............................................................. Update Disc
		  </TD></TR>
        <TR bgcolor=<?echo $message[2];?> align=center height=18>
          <td align=left>ר����</td><td width=8%>����</td><td width=10%>����</td><td>����</td>
		  <td width=8%>���</td><td width=23%>����ʱ��</td>
		   </TR>
		<?php
		 $cd = $db->query("SELECT C.cd_id as cd_id, C.cd_name as cd_name, C.song_num as song_num, C.lang as lang, C.click as click, C.add_date as add_date, S.singer_id as singer_id, S.singer_name as singer_name FROM cd C, singer S WHERE C.singer_id = S.singer_id ORDER BY add_date DESC LIMIT 0, 50");
		 #�µ�
		 while($tmp = $db->fetch_array($cd)) {
			 print "
				 <TR align=center height=15>
				 <TD align=left><img src=images/dot2.gif> <a href='cd.php?cd_id=$tmp[cd_id]&cd_name=".urlencode($tmp[cd_name])."&singer_id=$tmp[singer_id]&singer_name=".urlencode($tmp[singer_name])."'>$tmp[cd_name]</a></TD>
				 <TD>$tmp[song_num]</td>
				 <td>$tmp[lang]</TD>
				 <TD><a href='singer.php?singer_id=$tmp[singer_id]&singer_name=".urlencode($tmp[singer_name])."'>$tmp[singer_name]</a> </TD>
				 <TD>$tmp[click]</td>
				 <td>$tmp[add_date]</TD>
			 </TR>
			";
		 }
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