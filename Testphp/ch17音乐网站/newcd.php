<?php
//新专辑(发行时间上讲)..前50张。
require('config.php');
require('public.php');
require('inc/db.class.php');


html_head("最新发行专辑");
include('inc/head.inc.php');
now_pos('<a href=index.php>首页</a> 》最新发行专辑', '');

print "
<div align=center><center>
<TABLE border=0 cellPadding=2 cellSpacing=1 width=760 align=center background='images/bg1.gif'>
<TR bgcolor='$message[2]'>
<TD height=18 colspan=4 background='images/bg3.gif' valign=bottom>
 <font color='$message[14]'>&nbsp;■</font>
 <font color=#FFFFFF> 新 碟 靡 靡 </font>
 <font color='$message[14]'>■</font>
 ...................根据发行日期排列[Top48]......................................... New Disc
</TD>
</TR>
<TR align=center><td width=100%>
 <table width=100% cellspacing=1 cellPadding=2 border=0>
";

  $res = $db->query("SELECT C.cd_id as cd_id, C.cd_name as cd_name, C.imgurl as imgurl, C.pub_date as pub_date, S.singer_name as singer_name, S.singer_id as singer_id FROM cd C, singer S WHERE C.singer_id = S.singer_id ORDER BY C.pub_date DESC LIMIT 0, 48");

  $i = 1;
  while($tmp = $db->fetch_array($res)) {
	  if($tmp[imgurl] == '' || $tmp[imgurl] == 'http://')
		  $tmp[imgurl] = $default_cd_cover;

	  if($i == 1) print "\t<tr>\n";

	  $href = "cd.php?cd_id=$tmp[cd_id]&cd_name=".urlencode($tmp[cd_name])."&singer_id=$tmp[singer_id]&singer_name="	  
	  .urlencode($tmp[singer_name]);

	  print "
		  <td width=16%><a href='$href'><img src='$tmp[imgurl]' width=100 height=100 alt=$tmp[cd_name] title=$tmp[cd_name] border=0><br>
		  《".$tmp[cd_name]."》</a><br>
		  <a href='singer.php?singer_id=$tmp[singer_id]&singer_name=".urlencode($tmp[singer_name])."'>$tmp[singer_name]</a>
		  $tmp[pub_date]
		  </td>
		  ";

	 if($i == 6) {
		 $i = 1;
		 print "</tr>\n";
	 } else 
		 $i++;
  }
  
  if($i != 1) {
	  $i = 7-$i;
	  print "\t<td colspan=$i></td></tr>\n";
  }
  print "</table>\n";
?>

</td></TR>
</TABLE></center></div>

<?php
include('inc/foot.inc.php');
exit;
?>