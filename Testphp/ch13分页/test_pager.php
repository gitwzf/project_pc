<?php
//����һ�μ򵥵�ʾ������
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
	$turnover = "��ҳ|��һҳ|";
}else{
	$turnover = "<a href='?page=1&numItems=".$pager->numItems."'>��ҳ</a>|<a href='?page=".$pager->PreviousPageID."&numItems=".$pager->numItems."'>��һҳ</a>|";
}
if ( $pager->isLastPage ){
	$turnover .= "��һҳ|βҳ";
}else{
	$turnover .= "<a href='?page=".$pager->NextPageID."&numItems=".$pager->numItems."'>��һҳ</a>|<a href='?page=".$pager->numPages."&numItems=".$pager->numItems."'>βҳ</a>";
}
echo "������ʾ<br>";
echo "<table border='1' cellspacing='0' cellpadding='0'>";
echo "<tr align='center'><td>�������</td><td>���±���</td><td>��������</td><td>����ʱ��</td></tr>";
for ( $i = 0; $i < count($data); $i++){
	echo "<tr>";
	echo "<td>".$data[$i][0]."</td><td>".$data[$i][1]."</td><td>".$data[$i][2]."</td><td>".$data[$i][3]."</td>";
	echo "</tr>";
}
echo "</table><br>";
echo $turnover;
?>