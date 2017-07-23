<?php
//PHP获取变量演示
echo "------------ 4.2.0 以前版本效果示例 ----------------";
echo "<br>文件所在的文档根目录".$DOCUMENT_ROOT;
echo "<br>您要看的参数为：op=".$op.";user=".$user;
echo "<br>------------ 4.2.0 以后版本效果示例 ----------------";
$op = $_GET['op'];
$user = $_GET['user'];
echo "<br>文件所在的文档根目录".$_SERVER['DOCUMENT_ROOT'];
echo "<br>您要看的参数为：op=".$op.";user=".$user;
?>