<?php
//歌手文件 singer.php 列出跟该歌手相关的专辑
//传入变量，至少singer_id, singer_name必有, area_id,area_name 和 cate_id, cate_name 可有可无
require('config.php');
require('public.php');
require('inc/db.class.php');

$singer_id=1;
$singer_name='Beyond';
$cate_name='乐队组合';
$area_name='港台';
$cate_id='3';
$area_id='1';

if($singer_id == '' || $singer_name == '') error_quit("非法操作!");

//urlencode
$cate_name2 = urlencode($cate_name);
$area_name2 = urlencode($area_name);
$singer_name2 = urlencode($singer_name);
//end

$login = islogin();
if($login) 
 $usertab = "inc/havelogin.inc.php";
else
 $usertab = "inc/unlogin.inc.php";

/* 标题，位置处理 */
$title = $singer_name;
$pos = "<a href=index.php>首页</a>";
if($cate_name != '' || $area_name != '') 
{
 $title .= " - ".$area_name.$cate_name;
 if($cate_name != '' && $area_name != '')
 $pos .= " 》<a href='cate2.php?cate_id=$cate_id&cate_name=$cate_name2&area_id=$area_id&area_name=$area_name2'>$area_name$cate_name</a>";
 else
	if($cate_name != '')
	 $pos .= " 》<a href='cate.php?cate_id=$cate_id&cate_name=$cate_name2'>$cate_name</a>";
	else
	 $pos .= " 》<a href='area.php?area_id=$area_id&area_name=$area_name2'>$area_name</a>";
}
$pos .= " 》".$singer_name;
/* 处理完 */

html_head($title);
include('inc/head.inc.php');
now_pos($pos, '');
?>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 width=760 background='images/bg1.gif'>
<TR>
<TD width=160 valign=top align=left>
<!-- 左栏 -->
<?php include('inc/left.inc.php');?>
</TD>
<TD width=22 height=100%></TD>
<TD width=578 valign=top align=left>
<!-- 主要部分 -->
 <TABLE align=center border=0 cellPadding=2 cellSpacing=0 width=100% height=100%>

<?php
	$db->query("UPDATE singer SET click=click+1 WHERE singer_id = '$singer_id'");
	$query = "SELECT * FROM cd WHERE singer_id = '$singer_id' ORDER BY pub_date DESC";
	$result = $db->query($query);
	$cd_num = $db->num_rows($result);

print "
 <TR>
  <TD height=18 background='images/bg3.gif' valign=bottom>
   <font color=\"".$message[14]."\">&nbsp;■</font>
   <font color=#FFFFFF>".$singer_name."专辑列表 -- 共".$cd_num."张</font>
   <font color=\"".$message[14]."\">■</font>
   ...<a href=\"board.php?brd_id=".$singer_id."&singer_name=".$singer_name2."&cate_id=".$cate_id  
   ."&cate_name=".$cate_name2."&area_id=".$area_id."&area_name=".$area_name2
   ."\" target=_blank><font color=#FFFFFF>".$singer_name."讨论区</font></a>
   ...<a href=\"singer_introduce.php?singer_id=".$singer_id
   ."\" onclick=\"return QuerySinger(this.href)\"><font color=#FFFFFF>".$singer_name."简介</font></a>
   ...<a href=\"allsong.php?singer_id=".$singer_id."&singer_name=".$singer_name2."&cate_id=".$cate_id  
   ."&cate_name=".$cate_name2."&area_id=".$area_id."&area_name=".$area_name2
   ."\"><font color=#FFFFFF>列出".$singer_name."全部歌曲</font></a> ... 
   </TD>
  </TR>
  <TR><TD width=100% valign=top>
";

	$flag = 1;
    while($tmp = $db->fetch_array($result)) {
		if($tmp['imgurl'] == '' || $tmp['imgurl'] == 'http://')
			$tmp['imgurl'] = $default_cd_cover;
		if(strncasecmp($tmp['imgurl'], "http://", 7))
		    $tmp['imgurl'] = $data_url.$tmp['imgurl'];
		
		$s = array();

		$s[0] = "<a href=\"cd.php?cd_id=".$tmp['cd_id']."&cd_name=".urlencode($tmp['cd_name'])."&cate_id=".$cate_id
			."&cate_name=".$cate_name2."&area_id=".$area_id."&area_name=".$area_name2."&singer_id=".$singer_id
			."&singer_name=".$singer_name2."\"><img src=\"".$tmp['imgurl']."\" width=110 height=110 border=0 alt=\"封面图\"></a><br>\n"
			."<a href='favorite.php?type=cd&cd_id=".$tmp['cd_id']."' onclick='return Favorite(this.href);'>收藏本专辑</a>";

		$s[1] = "
			<table width=100% border=0 cellPadding=0 cellSpacing=0>
			 <tr><td><b>专辑名称: </b></td><td><a href='cd.php?cd_id=".$tmp['cd_id']."&cd_name=".urlencode($tmp['cd_name'])."&cate_id=$cate_id&cate_name=$cate_name2&area_id=$area_id&area_name=$area_name2&singer_id=$singer_id&singer_name=$singer_name2'>".$tmp['cd_name']."</a></td><td><b>语言种类: </b></td><td>".$tmp['lang']."</td></tr>
			 <tr><td><b>歌曲数目: </b></td><td>".$tmp['song_num']." 首</td><td><b>点击次数: </b></td><td>".$tmp['click']."</td></tr>
             <tr><td><b>出版时间: </b></td><td>".$tmp['pub_date']."</td><td><b>添加时间: </b></td><td>".$tmp['add_date']."</td></tr>
			 <tr><td colspan=4><b>简介: </b><span class=p6>".$tmp['introduce']."</span></td></tr>
		     </table>";
		$s[2] = "22%";
		$s[3] = "78%";
		
		$i = $flag % 2;
		$i += 2;
		$j = 5 - $i;

		print "<Table border=0>\n<tr align=center><td width=\"".$s[$i]."\">\n".$s[$i-2]."\n</td>"
		."<td width=\"".$s[$j]."\">\n".$s[$j-2]."\n</td><tr>\n</Table>\n";

		print "<img src=\"images/".($flag % 2 ? "line" : "line2").".gif\" width=\"569\" height=\"1\" align=\"center\"><br>\n";

		$flag++;
	}
	unset($s);
	unset($result);
	$db->free_result();
?>
   </TD>
  </TR>
  </TABLE>
 <!-- 结束主要部分 -->
</TD>
</TR>
</TABLE>
<?php
include('inc/foot.inc.php');
exit;
?>