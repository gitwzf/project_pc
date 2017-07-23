<?php
//音乐分类
require_once('config.php');
require_once('public.php');
require_once('inc/db.class.php');
if($cate_id == '' || $cate_name == '' || $area_id == '' || $area_name == '') error_quit("非法操作!");
$login = islogin();
if($login) 
 $usertab = "inc/havelogin.inc.php";
else
 $usertab = "inc/unlogin.inc.php";
html_head($area_name.$cate_name);
include_once('inc/head.inc.php');
now_pos('<a href=index.php>首页</a> 》'.$area_name.$cate_name, '');
?>
<table align=center border=0 cellpadding=0 cellspacing=0 width=760 background='images/bg1.gif'>
<tr>
<td width=160 valign=top align=left>
<!-- 左栏 -->
<?php include_once('inc/left.inc.php');?>
</td>
<td width=22></td>
<td width=578 valign=top align=left>
<!-- 主要部分 -->
 <table align=center border=0 cellpadding=0 cellspacing=0 width=100%>
 <tr>
  <td height=18 background='images/bg3.gif' valign=bottom>
   <font color="<? echo $message[14]; ?>">&nbsp;■</font>
   <font color=#ffffff> <? echo $area_name.$cate_name; ?>列表 </font>
   <font color="<? echo $message[14]; ?>">■</font>
   </td>
  </tr>
  <tr><td width=100% valign=top>
<?php
	$query = "SELECT singer_id, alpha, singer_name, cd_num, song_num, click FROM singer WHERE cate_id = '$cate_id' AND area_id = '$area_id' ORDER BY alpha";
	$result = $db->query($query);
	$a = "";
	$i = $db->num_rows();
	echo "目前本类共有<font color='#0000ff'>".$i."</font>位歌手<br>\n";
	$i = -1;
	while($tmp = $db->fetch_array($result)) {
		$i %= 3;
		if($a != $tmp[alpha]) {
			$a = $tmp[alpha];
			table_patch($i, 3);
			$i = 0;
			echo "<Table border=0>\n<tr height=18><td><font face=Arial><b>".strtoupper($a)."</b></font></td>\n<td width=33%></td><td width=33%></td><td width=33%></td></tr>\n";
		}
		$c = "<a href=\"singer.php?cate_id=$cate_id&cate_name=".urlencode($cate_name)."&area_id=$area_id&area_name=".urlencode($area_name)
		."&singer_id=$tmp[singer_id]&singer_name=".urlencode($tmp[singer_name])."\">$tmp[singer_name]</a> "
		."[<font color='#0000ff'>$tmp[cd_num]</font>张][<font color='#0000ff'>$tmp[song_num]</font>首]"
		."[<font color='#0000ff'>$tmp[click]</font>次]\n";
		if($i == 0)
			echo "<tr><td></td><td width=33%>".$c."</td>\n";
		else if($i == 1)
			echo "<td width=33%>".$c."</td>\n";
		else if($i == 2)
			echo "<td width=33%>".$c."</td></tr>\n";
		$i++;
	}
	table_patch($i, 3);
	unset($result);
	$db->free_result();
?>
  </td></tr>
 </table>
</td>
</tr>
</table>
<?php
include_once('inc/foot.inc.php');
exit;
?>