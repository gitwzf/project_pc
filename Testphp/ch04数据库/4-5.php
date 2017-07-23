<?php
$connection = mysql_connect('localhost','root','');
mysql_select_db('phpbook');
$query = "SELECT * FROM friends";
$result = mysql_query("$query",$connection);
//取回结果数据，使用了mysql_fetch_row()
while($row = mysql_fetch_row($result)){
//输出结果数据，用数字作下标来访问返回结果
echo $row[0]."<br>";
echo $row[1]."<br>";
echo $row[2]."<br><br>";
}
?>