<?php
    $host="localhost";
    $user="root";
    $password="phpbooktest";
    $db="phpbook_db";
    $dbcon=mysql_connect($host,$user,$password) or die("�������ӵ����ݿ⣡");
    mysql_select_db($db,$dbcon) or die("����ѡ�����ݿ�".$db);
    $res=mysql_query("call aa(@a)",$dbcon);  //���ô洢����
    $row=mysql_fetch_row($res);
    echo $row[0];
?>