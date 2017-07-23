<?php
//对数据库的操作
$connection = mysql_connect('localhost','root','');
mysql_select_db('phpbook');
$query = "SELECT * FROM friends";
$result = mysql_query($query,$connection);
//取回结果数据，使用了mysql_fetch_array()
while($row = mysql_fetch_array($result)){
//输出结果数据，用字段名作为下标来访问结果数据
echo $row["name"]."<br>";
echo $row["address"]."<br>";
echo $row["email"]."<br><br>"; //等同于 echo $row[2]. "<br><br>";
}
?>