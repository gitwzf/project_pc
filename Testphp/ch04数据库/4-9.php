<?php
$connection = mysql_connect("localhost","root","");
mysql_select_db("phpbook",$connection);
$result=mysql_query('SELECT * FROM friends' , $connection);
//使用while循环，以便获取所有记录的字段长度
while($row = mysql_fetch_array($result)){
//获取当前行的各字段长度
$lengths = mysql_fetch_lengths($result);
echo"[".$row[id]."]";
echo$lengths[0]."";
echo$lengths[1]."";
echo$lengths[2]."";
echo$lengths[3]."<br>";
}
?>