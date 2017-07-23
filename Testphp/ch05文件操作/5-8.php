<?php
//打开文件并读些数据
$handle = fopen("phpbook.txt", "r");
$data = fgets($handle, 15);
//打印输出现在指针位置
echo ftell($handle);
fclose($handle);
?>