
<table border=0 align=center width=760 border=1 bgcolor='gray'>
<tr>
<td valign=middle width=220 height=60><a href='index.php'>
	站点LOGO</a>
</td>
<td align=left width=300>
	页头广告 300*60
</td>
<td align=right valign=middle width=240>
<!-- 统计部分 -->
	 <?php
	   require('count.php');
  	   $ret = visit_count();
	   if($ret == -1) {
		   echo "\n</td></tr></table><br><br><br><br>\n"
		   ."<center><h3>注意：每个IP只能上一次站，您的IP：".getenv("REMOTE_ADDR")."\");</h3></center>\n"
		   ."<script>alert(\"每个IP只能上一次站，您的IP：".getenv("REMOTE_ADDR")."\");</script>\n"
		   ."</body></html>\n";
		   $db->close();
		   exit();
	   } elseif($ret == -2) {
		   echo "\n</td></tr></table><br><br><br><br>\n"
		   ."<center><h3>注意：目前本站访问人数已达上限(".$maxactive.")，请稍后再来，谢谢！</h3></center>\n"
		   ."<script>alert(\"注意：目前本站访问人数已达上限(".$maxactive.")，请稍后再来，谢谢！\");</script>\n"
		   ."</body></html>\n";
		   $db->close();
		   exit();
	   } elseif($ret == -3) { //guest too many
		   echo "\n</td></tr></table><br><br><br><br>\n"
		   ."<center><h3>注意：目前本站<b>访客</b>人数已达上限("
		   .($maxactive / 20).")，请以会员身份登录，谢谢！</h3></center>\n"
		   ."<table border=0 width=180 align=center><tr><td>\n";
		   include("inc/unlogin.inc.php");
		   echo "</td></tr></table>\n</body></html>\n";
		   $db->close();
		   exit();
	   } else
		   echo $ret;
	 ?>
<!-- 比较复杂 -->
</td>
</tr>
</table>
<table border=0 width=760 align=center cellspacing=0 cellpadding=0 background='images/bg2.gif'>
<tr> 
<td height="18" align="right">
<a href=index.php title='在线听歌和送歌'><font color=#E7E7E7>通俗歌曲</font></a>&nbsp;
<a href='area.php?area_id=6&area_name=<?php echo urlencode('古典音乐');?>' title='古典音乐'><font color=#E7E7E7>古典音乐</font></a>&nbsp;
<a href='movie-tv.php?cate_id=<?php echo $message[11];?>&cate_name=<?php echo urlencode('影视金曲');?>' title='影视金曲'><font color=#E7E7E7>影视金曲</font></a>&nbsp;
<a href='singer.php?singer_id=96&singer_name=<?php echo urlencode('校园民谣');?>' title='校园民谣'><font color=#E7E7E7>校园民谣</font></a>&nbsp;
<a href='singer.php?singer_id=95&singer_name=<?php echo urlencode('革命金曲');?>' title='革命金曲'><font color=#E7E7E7>革命金曲</font></a>&nbsp;
<a href=member.php title='本站会员'><font color=#E7E7E7>会员专区</font></a>&nbsp;
<a href='board.php?brd_id=0' title='留言讨论'><font color=#E7E7E7>留言讨论区</font></a>&nbsp;
<a href=help.php target=_blank style='cursor: help' title='新手看进来, 让您玩得更开心'><font color=#E7E7E7>帮助</font></a>&nbsp;
</td>
</tr>
</table>