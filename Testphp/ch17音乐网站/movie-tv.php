<?php
//movie-tv.php  Ӱ��
//�������cate_id, cate_name
require('config.php');
require('public.php');
require('inc/db.class.php');

if($cate_id == '' || $cate_name == '') error_quit("�Ƿ�����!");

$login = islogin();
if($login) 
 $usertab = "inc/havelogin.inc.php";
else
 $usertab = "inc/unlogin.inc.php";

html_head($cate_name);
include('inc/head.inc.php');
now_pos('<a href=index.php>��ҳ</a> ��'.$cate_name, '');
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
 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 width=100%>
  <TR>
  <TD height=18 background='images/bg3.gif' valign=bottom>
		<font color="<? echo $message[14]; ?>">&nbsp;��</font>
		<font color=#FFFFFF> Ӱ �� �� �� </font>
		<font color="<? echo $message[14]; ?>">��</font>
		............................................................ Movie Music
  </TD>
  </TR>
  <TR><TD width=100% valign=top>
  <!-- ��ʼ��ʾ -->
<?php
	$query = "SELECT singer_id, alpha, singer_name, song_num, click FROM singer WHERE cate_id = '$cate_id' ORDER BY alpha";
	$result = $db->query($query);

	$a = "";	
	$i = $db->num_rows();
	echo "Ŀǰ����<font color='#0000ff'>".$i."</font>��Ӱ��ר��<br>\n";
	$i = -1;

	while($tmp = $db->fetch_array($result)) {
		$i %= 3;
		if($a != $tmp[alpha]) {
			$a = $tmp[alpha];
			table_patch($i, 3);
			$i = 0;
			print "<Table border=0>\n<tr height=18><td><font face=Arial><b>".strtoupper($a)."</b></font></td>\n<td width=33%></td><td width=33%></td><td width=33%></td></tr>\n";
		}
		$c = "<a href=\"movie-tv_detail.php?cate_id=$cate_id&cate_name=".urlencode($cate_name)
		."&singer_id=$tmp[singer_id]&singer_name=".urlencode($tmp[singer_name])."\">��".$tmp[singer_name]."��</a> "
		."[<font color='#0000ff'>$tmp[song_num]</font>��]"
		."[<font color='#0000ff'>$tmp[click]</font>��]\n";

		if($i == 0)
			print "<tr><td></td><td width=33%>".$c."</td>\n";
		else if($i == 1)
			print "<td width=33%>".$c."</td>\n";
		else if($i == 2)
			print "<td width=33%>".$c."</td></tr>\n";
		$i++;
	}

	table_patch($i, 3);
	unset($result);
	$db->free_result();

?>
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