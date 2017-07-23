<?php
//连接到数据库服务器
$connection = mysql_connect("localhost","root","");
echo $connection."<br>";
//关闭连接
$close = mysql_pconnect("localhost","root","");
echo $close;
?>