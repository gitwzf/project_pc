<?php
//�����ļ� singer.php �г����ø�����ص�ר��
//�������������singer_id, singer_name����, area_id,area_name �� cate_id, cate_name ���п���
require('config.php');
require('public.php');
require('inc/db.class.php');

$singer_id=1;
$singer_name='Beyond';
$cate_name='�ֶ����';
$area_name='��̨';
$cate_id='3';
$area_id='1';

if($singer_id == '' || $singer_name == '') error_quit("�Ƿ�����!");

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

/* ���⣬λ�ô��� */
$title = $singer_name;
$pos = "<a href=index.php>��ҳ</a>";
if($cate_name != '' || $area_name != '') 
{
 $title .= " - ".$area_name.$cate_name;
 if($cate_name != '' && $area_name != '')
 $pos .= " ��<a href='cate2.php?cate_id=$cate_id&cate_name=$cate_name2&area_id=$area_id&area_name=$area_name2'>$area_name$cate_name</a>";
 else
	if($cate_name != '')
	 $pos .= " ��<a href='cate.php?cate_id=$cate_id&cate_name=$cate_name2'>$cate_name</a>";
	else
	 $pos .= " ��<a href='area.php?area_id=$area_id&area_name=$area_name2'>$area_name</a>";
}
$pos .= " ��".$singer_name;
/* ������ */

html_head($title);
include('inc/head.inc.php');
now_pos($pos, '');
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

<?php
	$db->query("UPDATE singer SET click=click+1 WHERE singer_id = '$singer_id'");
	$query = "SELECT * FROM cd WHERE singer_id = '$singer_id' ORDER BY pub_date DESC";
	$result = $db->query($query);
	$cd_num = $db->num_rows($result);

print "
 <TR>
  <TD height=18 background='images/bg3.gif' valign=bottom>
   <font color=\"".$message[14]."\">&nbsp;��</font>
   <font color=#FFFFFF>".$singer_name."ר���б� -- ��".$cd_num."��</font>
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

	$flag = 1;
    while($tmp = $db->fetch_array($result)) {
		if($tmp['imgurl'] == '' || $tmp['imgurl'] == 'http://')
			$tmp['imgurl'] = $default_cd_cover;
		if(strncasecmp($tmp['imgurl'], "http://", 7))
		    $tmp['imgurl'] = $data_url.$tmp['imgurl'];
		
		$s = array();

		$s[0] = "<a href=\"cd.php?cd_id=".$tmp['cd_id']."&cd_name=".urlencode($tmp['cd_name'])."&cate_id=".$cate_id
			."&cate_name=".$cate_name2."&area_id=".$area_id."&area_name=".$area_name2."&singer_id=".$singer_id
			."&singer_name=".$singer_name2."\"><img src=\"".$tmp['imgurl']."\" width=110 height=110 border=0 alt=\"����ͼ\"></a><br>\n"
			."<a href='favorite.php?type=cd&cd_id=".$tmp['cd_id']."' onclick='return Favorite(this.href);'>�ղر�ר��</a>";

		$s[1] = "
			<table width=100% border=0 cellPadding=0 cellSpacing=0>
			 <tr><td><b>ר������: </b></td><td><a href='cd.php?cd_id=".$tmp['cd_id']."&cd_name=".urlencode($tmp['cd_name'])."&cate_id=$cate_id&cate_name=$cate_name2&area_id=$area_id&area_name=$area_name2&singer_id=$singer_id&singer_name=$singer_name2'>".$tmp['cd_name']."</a></td><td><b>��������: </b></td><td>".$tmp['lang']."</td></tr>
			 <tr><td><b>������Ŀ: </b></td><td>".$tmp['song_num']." ��</td><td><b>�������: </b></td><td>".$tmp['click']."</td></tr>
             <tr><td><b>����ʱ��: </b></td><td>".$tmp['pub_date']."</td><td><b>���ʱ��: </b></td><td>".$tmp['add_date']."</td></tr>
			 <tr><td colspan=4><b>���: </b><span class=p6>".$tmp['introduce']."</span></td></tr>
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
 <!-- ������Ҫ���� -->
</TD>
</TR>
</TABLE>
<?php
include('inc/foot.inc.php');
exit;
?>