<?php
//������,����brd_id
require('config.php');
require('public.php');
require('inc/db.class.php');

if(!isset($brd_id)) error_quit("�Ƿ�����");
//if(!$singer_name) $singer_name = '������';
if($brd_id == 0) $singer_name = '������';

if($brd_id !=0)
{
	$checker = $db->query_first("SELECT singer_name FROM singer WHERE singer_id = $brd_id");
	if($singer_name != $checker[0]) {
		error_quit("�Ƿ�����");
	}
}

$login = islogin();
if($login) 
 $usertab = "inc/havelogin.inc.php";
else
 $usertab = "inc/unlogin.inc.php";

 /* ���⣬λ�ô��� */
$title = $singer_name."������";
$pos = "<a href=index.php>��ҳ</a>";
if($cate_name != '' || $area_name != '') 
{
 $title .= " - ".$area_name.$cate_name;
 if($cate_name != '' && $area_name != '')
 $pos .= " ��<a href='cate2.php?cate_id=$cate_id&cate_name=".urlencode($cate_name)."&area_id=$area_id&area_name=".urlencode($area_name)."'>$area_name$cate_name</a>";
 else
	if($cate_name != '')
	 $pos .= " ��<a href='cate.php?cate_id=$cate_id&cate_name=".urlencode($cate_name)."'>$cate_name</a>";
	else
	 $pos .= " ��<a href='area.php?area_id=$area_id&area_name=".urlencode($area_name)."'>$area_name</a>";
}

if($brd_id == 0) 
	$pos .= " ��".$singer_name."������";
else
	$pos .= " ��<a href='singer.php?cate_id=$cate_id&cate_name=".urlencode($cate_name)."&area_id=$area_id&area_name=".urlencode($area_name)."&singer_id=$brd_id&singer_name=".urlencode($singer_name)."'>$singer_name</a> ��".$singer_name."������";

$url = $PHP_SELF."?brd_id=".$brd_id."&singer_name=".urlencode($singer_name)
."&cate_id=".$cate_id."&cate_name=".urlencode($cate_name)."&area_id=".$area_id."&area_name=".urlencode($area_name);

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
 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 width=100%>
  <TR>
   <TD height=18 background='images/bg3.gif' valign=bottom>
   <font color="<? echo $message[14]; ?>">&nbsp;��</font>
   <font color=#FFFFFF> �� �� �� �� �� -- <? echo $singer_name;?></font>
   <font color="<? echo $message[14]; ?>">��</font>
   .................................. Discuss Board
   </TD>
  </TR>
  <TR><TD width=100% valign=top>
<!-- ��ʼ��ʾ -->
<TABLE width=100% border=1 cellSpacing=0 bordercolordark="#FFFFFF" bordercolorlight="<? echo $message[2];?>">
<?php
//cheng. 2007.1105: ��ͷ��
if($brd_id != 0) {
	$tmp = $db->query_first("SELECT imgurl, LEFT(introduce, 300) as intro FROM singer WHERE singer_id = '$brd_id'");
	if(!$tmp[imgurl] || $tmp[imgurl] == 'http://') $tmp[imgurl] = $default_singer;

	if(strncasecmp($tmp[imgurl], "http://", 7))
	    $tmp[imgurl] = $data_url.$tmp[imgurl];

	//$tmp[intro] = bbcode($tmp[intro]);
	print "
	  <TR><TD width=100% height=1 colSpan=5 valign=top bgcolor=#000000></TD></TR>
	  <TR><TD width=100% colSpan=5 valign=top>
	  <img src='$tmp[imgurl]' alt='$singer_name' align='left'>\n<font size=+1> <b>$singer_name</b></font><br><br>
	  $tmp[intro]
	  ... <a href='singer_introduce.php?singer_id=$brd_id' onclick='return QuerySinger(this.href)'>������ &gt;&gt; ...</a>
	  </td></TR>
	";
}

if(!$page) $page = 1;

$total = $db->query_first("SELECT COUNT(*) FROM post WHERE brd_id = '$brd_id'");
$total = $total[0];
$start = ($page-1) * $perpage;
if($start > $total) $start = $total;

$burl = $PHP_SELF."?brd_id=".$brd_id."&singer_name=".urlencode($singer_name)."&cate_id=".$cate_id
."&cate_name=".urlencode($cate_name)."&area_id=".$area_id."&area_name=".urlencode($area_name)."&page=";

$other_page = "��ҳ��";
require('set_page.php');
$other_page .= set_page($burl, $total, $page, $perpage);
$other_page .="ƪ����.";
$other_page .= "&nbsp;&nbsp;<a href='board_add.php?brd_id=$brd_id&singer_name=".urlencode($singer_name)."' onclick='return Post(this.href)'>��������</a>";


print "	
	<TR bgcolor=$message[2] height=18><td colspan=5> $other_page </td></TR>\n";

$res = $db->query("SELECT post_id, title, author, date, thread_id, click, flag FROM post WHERE brd_id = $brd_id ORDER BY date DESC LIMIT $start, $perpage");

print "
    <TR bgcolor=$message[3] height=18 align=center>
	<td align=left>����</td><td width=16%>����</td><td width=14%>ʱ��</td><td width=8%>����</td><td width=16%>����</td>
	</TR>";

while($tmp=$db->fetch_array($res))
{
	if(strlen($tmp[title]) > 36) $tmp[title] = substr($tmp[title],0,36)."..."; //������ֻ��ʾ25����

	if($tmp[flag] == 0)
		$flag = "";
	else
		$flag = "<img src=images/pj/".$tmp[flag].".gif border=0>";

	if(ereg(date("Y-m-d"),$tmp[date])) {
		if(!ereg('Re:',$tmp[title])) 
			$flag .= "<font color=red>�� </font>";
		$tmp[title] = str_replace('Re:','<font color=red>Re:</font>',$tmp[title]);
	}
	else if(!ereg('Re:',$tmp[title])) 
		$flag .= "�� "; //��� Re: ����

	$tmp[title] =$flag.$tmp[title];//re:�ķ�����

	if(is_admin())
	{
		$admin_str  = "<a href=board_del.php?post_id=$tmp[post_id]><font color=red>ɾ</font></a>|";
		$admin_str .= "<a href='board_edit.php?post_id=$tmp[post_id]&singer_name=$singer_name' onclick='Post(this.href); return false;'><font color=darkred>��</font></a>|";
		$admin_str .= "<a href=board_pj.php?post_id=$tmp[post_id]&pj=0><font color=darkblue>��</font></a>|";
		$admin_str .= "<a href=board_pj.php?post_id=$tmp[post_id]&pj=1><font color=green>��</font></a>|";
		$admin_str .= "<a href=board_pj.php?post_id=$tmp[post_id]&pj=2><font color=blue>��</a>";
	} else if($m_user[user_name] == $tmp[author]) {
		$admin_str  = "<a href=board_del.php?post_id=$tmp[post_id]><font color=red>ɾ��</font></a>|";
		$admin_str .= "<a href='board_edit.php?post_id=$tmp[post_id]&singer_name=$singer_name' onclick='Post(this.href); return false;'><font color=darkblue>�޸�</font></a>";
	}
	else 
		$admin_str = "- - -";

	$tmp[date] = substr($tmp[date], 5, 11);
	print "
	<TR height=18 align=center>
	   <td align=left><a href='board_read.php?brd_id=$brd_id&post_id=$tmp[post_id]&singer_name=".urlencode($singer_name)."&thread_id=$tmp[thread_id]' onclick='return Post(this.href)'>$tmp[title]</a></td><td><a href='user_query.php?user_name=".urlencode($tmp[author])."' onclick='return Query(this.href)'>$tmp[author]</a></td><td>$tmp[date]</td><td>$tmp[click]</td><td>$admin_str</td>
	</TR>";
}

print "	
	<TR bgcolor=$message[2] height=18><td colspan=5> $other_page </td></TR>\n";
?>
   </TABLE>
<!-- ���� -->  
  </TD></TR>
 </TABLE>
</TD>
</TR>
</TABLE>
<!-- footer --> 
<?php
include('inc/foot.inc.php');
exit;
?>
