<?php
//这是一段简单的示例代码
$user = 'root';
$pass = '';
$host = 'localhost';
$db_name = 'phpbook_ch13';
$db = mysql_connect($host,$user,$pass) or die(" connect to db error!");
mysql_select_db($db_name,$db);
require "Pager.class.php";
if ( isset($_GET['page']) ){
	$page = (int)$_GET['page'];
}else{
	$page = 1;
}
$page_size = 3;
$sql = "select * from news order by newsid";
$pager_option = array("sql" => $sql,"PageSize" => $page_size,"CurrentPageID" => $page);
if ( isset($_GET['numItems']) ){
	$pager_option['numItems'] = (int)$_GET['numItems'];
}
$pager = @new Pager($pager_option);
$data = $pager->getPageData();
if ( $pager->isFirstPage ){
	$turnover = "首页|上一页|";
}else{
	$turnover = "<a href='?page=1&numItems=".$pager->numItems."'>首页</a>|<a href='?page=".$pager->PreviousPageID."&numItems=".$pager->numItems."'>上一页</a>|";
}
if ( $pager->isLastPage ){
	$turnover .= "下一页|尾页";
}else{
	$turnover .= "<a href='?page=".$pager->NextPageID."&numItems=".$pager->numItems."'>下一页</a>|<a href='?page=".$pager->numPages."&numItems=".$pager->numItems."'>尾页</a>";
}
echo "数据显示<br>";
echo "<table border='1' cellspacing='0' cellpadding='0'>";
echo "<tr align='center'><td>文章序号</td><td>文章标题</td><td>文章内容</td><td>发表时间</td></tr>";
for ( $i = 0; $i < count($data); $i++){
	echo "<tr>";
	echo "<td>".$data[$i][0]."</td><td>".$data[$i][1]."</td><td>".$data[$i][2]."</td><td>".$data[$i][3]."</td>";
	echo "</tr>";
}
echo "</table><br>";
echo $turnover;
?>