<?php
//点歌记录，只显示最新的100篇。其它的，按搜索来分.
require('config.php');
require('public.php');
require('inc/db.class.php');

$login = islogin();
if($login) 
 $usertab = "inc/havelogin.inc.php";
else
 $usertab = "inc/unlogin.inc.php";


if(!$search) $search='date';//非搜索模式
if(!$page) $page = 1; //分页

if($search == 'date') //日期模式
{
	if($month >0 && $month < 10) $month = "0".intval($month);
	if($day >0 && $day < 10) $day = "0".intval($day);
	
	if(!$year) $year = date('Y');
	if(!$month) $month = date('m');
	if(!$day) $day = date('d');

	$string = $year.'-'.$month.'-'.$day; //查询字符串
	$query1 = "SELECT COUNT(*) FROM ordersong WHERE date LIKE '$string%' AND flag != 0";
	$total = $db->query_first($query1);
	$total = $total[0];
    $totalpage = intval($total/$perpage) + 1;
    if($totalpage >= $page)
     $start = ($page - 1) * $perpage;
    else
     $start = ($totalpage-1) * $perpage;

    if($totalpage > 1) {
     $otherpage = " 其他各页：";
     for($i=1;$i<=$totalpage;$i++) 
     $other_page .= ($i == $page) ? $i." " : "<a href=$PHP_SELF?search=date&year=".$year."&month=".$month."&day=".$day."&page=".$i.">".$i."</a> ";
    } 
	$query2 = "SELECT order_id, receiver, sender, song_name, song_id, singer_name, singer_id, ssay FROM ordersong WHERE date LIKE '$string%' AND flag != 0 ORDER BY date DESC LIMIT $start, $perpage";
}
else
{
	if($keyword == 'all') $keyword = '';
	if($type == 'sender')
	{
     $query1 = "SELECT COUNT(*) FROM ordersong WHERE sender LIKE '$keyword%' AND flag != '0'";
  	 $query2 = "SELECT order_id, receiver, sender, song_name, song_id, singer_name, singer_id, ssay FROM ordersong WHERE sender LIKE '$keyword%' AND flag != 0 ORDER BY date DESC";
	}
	else
	{
	 $tmp = '>'.$keyword; //收歌者保留了email的原因
	 $query1 = "SELECT COUNT(*) FROM ordersong WHERE receiver LIKE '%$tmp%' AND flag != '0'";
	 $query2 = "SELECT order_id, receiver, sender, song_name, song_id, singer_name, singer_id, ssay FROM ordersong WHERE receiver LIKE '%$tmp%' AND flag != 0 ORDER BY date DESC";
	}

	$total = $db->query_first($query1);
	$total = $total[0];
    $totalpage = intval($total/$perpage) + 1;
    if($totalpage >= $page)
     $start = ($page - 1) * $perpage;
    else
     $start = ($totalpage-1) * $perpage;

    if($totalpage > 1) {
     $otherpage = " 其他各页：";
     for($i=1;$i<=$totalpage;$i++) 
	     $other_page .= ($i == $page) ? $i." " : "<a href=$PHP_SELF?search=keyword&type=".$type."&keyword=".urlencode($keyword)."&page=".$i.">".$i."</a> ";
    } 
   $query2 .= " LIMIT $start, $perpage";
}
   $res = $db->query($query2);

////////////////////////////////////////////////////////
// HTML开始输出。
////////////////////////////////////////////////////////
html_head("风情点歌机、温馨传情意");
include('inc/head.inc.php');
now_pos('<a href=index.php>首页</a> 》风情点歌记录', '');

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
 <TABLE align=center border=0 cellPadding=2 cellSpacing=0 width=100%>
  <TR><TD width=100% valign=top>
<!-- 开始显示 -->
   <TABLE width=100% border=0 cellSpacing=0>
	<TR>
	  <TD height=18 colspan=6 background='images/bg3.gif' valign=bottom>
	   <font color="<? echo $message[14]; ?>">&nbsp;■</font>
	   <font color=#FFFFFF> 凭 歌 寄 语 </font>
	   <font color="<? echo $message[14]; ?>">■</font>
	   ............................................................... Order Song
	  </TD>
    </TR>
<?php
if($search == 'date')
{
print "
    <TR height=18>
	<td colspan=6 align=center><font color=red>".$year."年".$month."月".$day."日 的风情点歌记录 </font></td>
	</TR> ";
}
else
{
print "
    <TR height=18>
	<td colspan=6 align=left><b>风情点歌记录搜索</b> 关键字: <font color=red>$keyword </font></td>
	</TR> ";
}

print "
	<TR height=18 bgcolor='$message[2]'>
	  <TD width=12%>点歌人</TD>
	  <TD>演唱者</TD>
	  <TD>歌曲名</TD>
	  <TD width=6%>歌词</TD>
	  <TD width=12%>收歌人</TD>
	  <TD width=20%>简短祝福语</TD>
    </TR>
	";
while($tmp = $db->fetch_array($res)) {
	print "
		<TR height=18>
		  <TD><a href='user_query.php?user_name=$tmp[sender]' onclick='return Query(this.href)'>$tmp[sender]</a></TD>
		  <TD><a href='singer.php?singer_id=$tmp[singer_id]&singer_name=$tmp[singer_name]'>$tmp[singer_name]</a></TD>
		  <TD><a href='listen/listen_id.php?song_id=$tmp[song_id]' onclick='return Listen(this.href);'>$tmp[song_name]</a></TD>
		  <TD><a href='geci.php?song_id=$tmp[song_id]' onclick='return Geci(this.href)'>查看</a></TD>
		  <TD>$tmp[receiver]</TD>
		  <TD><a href='order_read.php?order_id=$tmp[order_id]' onclick='return ReadOrder(this.href)'>$tmp[ssay] ... </a></TD>
		</TR>
		<TR width=100%><TD colspan=6><img border=0 src='images/line.gif'></TD></TR>
		";
}

print "
    <TR bgcolor='$message[2]' height=18>
	<td colspan=6 align=right>共有$total 笔资料 现在是第<font color=red> $page</font>/$totalpage 页 $other_page</td>
	</TR>
	<TR width=100%><TD colspan=6><img border=0 src='images/line2.gif'></TD></TR>
	";
?>

       </TABLE>
   <!-- 结束 -->  
  </TD></TR>
  <TR><TD width=100% valign=top><? include('inc/search_order.inc.php');?></TD></TR>
 </TABLE>
</TD>
</TR>
</TABLE>

<?php
include('inc/foot.inc.php');
exit;
?>