<?php
    $host="localhost";
    $user="root";
    $password="phpbooktest";
    $db="phpbook_db";
    $dbcon=mysql_connect($host,$user,$password) or die("不能连接到数据库！");
    mysql_select_db($db,$dbcon) or die("不能选择数据库".$db);
    $res=mysql_query("call aa(@a)",$dbcon);  //调用存储过程
    $row=mysql_fetch_row($res);
    echo $row[0];
?>