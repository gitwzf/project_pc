<?php
$connection = mysql_connect("localhost","root","");
mysql_select_db("phpbook");
$query = "SELECT * FROM friends";
$result = mysql_query($query);
echo "从数据库中获取全部数据的条数<br>";
//获得结果中的记录数
$rows = mysql_num_rows($result);
//获得结果中的字段数
$fields = mysql_num_fields($result);
echo "总共查询到 $rows 行 和$fields 字段！<br>";
//删除一个记录
$query = "DELETE FROM friends WHERE name ='莉莉'";
mysql_query($query);
echo "删除name为莉莉的一条记录<br>";
//获得删除的行的数目
$rows = mysql_affected_rows($connection);
echo $rows. "记录被删除！";
?>