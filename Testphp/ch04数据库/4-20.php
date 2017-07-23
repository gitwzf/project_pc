<?php
include_once("class.sqlserver.php");
//构造新的DB类
$SQLSDB = new sqlserver();
//打开新的数据库连接，完成初始化过程
$SQLSDB->Connect();
//开始进行sql查询操作
$selectsql="select * from phpbook";
$SQLSDB->Query($selectsql);
//循环输出查询得到的结果
while($Row=$SQLSDB->NextRecord()){
	   echo $Row["title"];
}
//关闭数据库连接
$SQLSDB->Close();
?>