<?php
//movie-tv_detail.php  Ӱ����ϸ������singer.php
//�������������singer_id, singer_name����, area_id,area_name �� cate_id, cate_name ���п���
require('config.php');
require('public.php');
require('inc/db.class.php');

if($cate_id == '' || $cate_name == '' || $singer_id == '' || $singer_name == '') error_quit("�Ƿ�����!");

//urlencode
$cate_name2 = urlencode($cate_name);
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
$pos .= " ��<a href='movie-tv.php?cate_id=$cate_id&cate_name=$cate_name2'>$cate_name</a>";
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
<TD width=22></TD>
<TD width=578 valign=top align=left>
<!-- ��Ҫ���� -->
 <TABLE align=center border=0 cellPadding=2 cellSpacing=0 width=100%>
  <TR><TD width=100% valign=top>
<!-- ��ʼ��ʾ -->
   <TABLE width=100% border=0 cellSpacing=0>
	<TR>
	<TD height=18 background='images/bg3.gif' valign=bottom>
		<font color="<? echo $message[14]; ?>">&nbsp;��</font>
		<font color=#FFFFFF> <? echo $singer_name; ?> </font>
		<font color="<? echo $message[14]; ?>">��</font>
		..........................................
	</TD>
	</TR>
	<TR>
	<TD align=center width=100%>
	
	<?php
	$db->query("UPDATE singer SET click=click+1 WHERE singer_id = '$singer_id'");
	$query = "SELECT * FROM cd WHERE singer_id = '$singer_id' ORDER BY pub_date DESC";
	$result = $db->query($query);

	$flag = 1;
    while($tmp = $db->fetch_array($result)) {
		if($tmp[imgurl] == '' || $tmp[imgurl] == 'http://')
			$tmp[imgurl] = $default_cd_cover;

	    $s = array();
		$s[0] = "<a href=\"cd.php?cd_id=".$tmp[cd_id]."&cd_name=".urlencode($tmp[cd_name])."&cate_id=".$cate_id
			."&cate_name=".$cate_name2."&area_id=".$area_id."&area_name=".$area_name2."&singer_id=".$singer_id
			."&singer_name=".$singer_name2."\"><img src=\"".$tmp[imgurl]."\" width=110 height=110 border=0 alt=\"����ͼ\"></a><br>"
			."<a href='favorite.php?type=cd&cd_id=".$tmp[cd_id]."' onclick='return Favorite(this.href);'>�ղر�ר��</a>";

		$s[1] = "
			<table width=100% border=0 cellPadding=0 cellSpacing=0>
			 <tr><td><b>ר������: </b></td><td><a href='cd.php?cd_id=$tmp[cd_id]&cd_name=".urlencode($tmp[cd_name])."&cate_id=$cate_id&cate_name=$cate_name2&area_id=$area_id&area_name=$area_name2&singer_id=$singer_id&singer_name=$singer_name2'>$tmp[cd_name]</a></td><td><b>��������: </b></td><td>$tmp[lang]</td></tr>
			 <tr><td><b>������Ŀ: </b></td><td>$tmp[song_num] ��</td><td><b>�������: </b></td><td>$tmp[click]</td></tr>
             <tr><td><b>����ʱ��: </b></td><td>$tmp[pub_date]</td><td><b>���ʱ��: </b></td><td>$tmp[add_date]</td></tr>
			 <tr><td colspan=4><b>���: </b><span class=p6>$tmp[introduce]</span></td></tr>
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

	//����Ӱ�Ӽ��
	$result = $db->query_first("SELECT imgurl, introduce FROM singer WHERE singer_id = '$singer_id'");
	if(!$result[imgurl] || $result[imgurl] == 'http://') $result[imgurl] = $default_singer;
	$img = "<img src='$result[imgurl]' alt='ͼƬ' width=120 align=left>";
	$result[introduce] = bbcode($result[introduce]);
	print "
	<br>
	<table width=100% align=center border=0 cellPadding=0 cellSpacing=0>
    <tr><td height=18 background='images/bg3.gif' valign=bottom>
		<font color=\"".$message[14]."\">&nbsp;��</font>
		<font color=#FFFFFF> ".$singer_name."֮������ </font>
		<font color=\"".$message[14]."\">��</font>
		....................................
	</td></tr>
	<tr bgcolor=$message[2]><td align=left>".$img.$result[introduce]."</td></tr>
	</table>
	";
	//end
?>

   </TD></TR>
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

