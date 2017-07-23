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
require_once("13-2.php");
$mypager = new Page();

// 建立数据库连接
$link = mysql_connect("localhost", "root", "") or die("Could not connect: " . mysql_error());
mysql_select_db("phpbook_ch13",$link) or die("选择数据库失败!");
// 获取当前页数
if( isset($_GET['page']) ){
	$page = intval( $_GET['page'] );
}
else{
	$page = 1;
}
// 每页数量
$page_size = 5;
// 获取总数据量
$sql = "select count(*) as amount from news";
$result = mysql_query($sql);
$rows = mysql_fetch_row($result);
$amount = $rows[0];
$mypager->pageparse(5,$amount,"13-3.php?page");
$mypager->pageshow();
//关闭数据库连接
mysql_close($link);
?>
</body>
</html>