<?php
include_once("./ch8-4.php");
$_SESSION['title'] = 'PHP������';
$link = mysql_connect('localhost','root','');
mysql_select_db('phpbook',$link);
$selectSql = "SELECT * FROM phpbook_global_sessions WHERE SessionExpTime > ".time();
$result = mysql_query($selectSql, $link);
//����������
while ($row = mysql_fetch_array($result)) {
	echo "key=" . $row[0] . ";time=" . $row[1] . ";value=" . $row[2] . "<br>";
}
?>