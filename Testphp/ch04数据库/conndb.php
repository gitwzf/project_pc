<?php
//���ӵ����ݿ������
$connection = mysql_connect("localhost","root","");
echo $connection."<br>";
//�ر�����
$close = mysql_pconnect("localhost","root","");
echo $close;
?>