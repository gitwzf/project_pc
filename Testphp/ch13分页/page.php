<html>
<HEAD>
<title>分页显示模块</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<LINK href="images/style.css" type="text/css" rel="stylesheet">
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
}
</style>
<body>
<?php
// 建立数据库连接
$link = mysql_connect("localhost", "root", "") or die("Could not connect: " . mysql_error());
mysql_select_db("phpbook_ch13",$link) or die("选择数据库失败!");
// 获取当前页数
if( isset($_GET['page']) ){
	$page = intval( $_GET['page'] );
}else{
	$page = 1;
}
// 每页数量
$page_size = 5;
// 获取总数据量
$sql = "select count(*) as amount from news";
$result = mysql_query($sql);
$rows = mysql_fetch_row($result);
$amount = $rows[0];
// 记算总共有多少页
if( $amount ){
	if( $amount < $page_size ){ $page_count = 1; }               //如果总数据量小于$page_size，那么只有一页
	if( $amount % $page_size ){                                  //取总数据量除以每页数的余数
		$page_count = (int)($amount / $page_size) + 1;           //如果有余数，则页数等于总数据量除以每页数的结果取整再加一
	}else{
		$page_count = $amount / $page_size;                      //如果没有余数，则页数等于总数据量除以每页数的结果
	}
}else{
	$page_count = 0;
}
// 翻页链接
$page_string = '';
if( $page == 1 ){
	$page_string .= '第一页|上一页|';
}else{
	$page_string .= '<a href=?page=1>第一页</a>|<a href=?page='.($page-1).'>上一页</a>|';
}
if( ($page == $page_count) || ($page_count == 0) ){
	$page_string .= '下一页|尾页';
}else{
	$page_string .= '<a href=?page='.($page+1).'>下一页</a>|<a href=?page='.$page_count.'>尾页</a>';
}
// 获取数据，以二维数组格式返回结果
if( $amount ){
	$sql = "select * from news order by newsid desc limit ". ($page-1)*$page_size .", $page_size";
	$result = mysql_query($sql);

	while ( $row = mysql_fetch_row($result) ){
		$rowset[] = $row;
	}
}else{
	$rowset = array();
}
//结果显示
echo '<table border=1>
		<tr>
			<td>新闻编号</td>
			<td>新闻标题</td>
			<td>新闻内容</td>
			<td>新闻时间</td>
			<td>新闻作者</td>
		</tr>';
for($i = 0 ; $i <sizeof($rowset); $i ++){
	echo '<tr>';
	echo '<td>'.$rowset[$i][0]."</td>";
	echo '<td>'.$rowset[$i][1]."</td>";
	echo '<td>'.$rowset[$i][2]."</td>";
	echo '<td>'.$rowset[$i][3]."</td>";
	echo '<td>'.$rowset[$i][4]."</td>";
	echo '</tr>';
}
echo "</table>";
//分页显示部分
echo $page_string;
//关闭数据库连接
mysql_close($link);
?>
</body>
</html>