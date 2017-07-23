<?php
$handle = fopen('phpbook.txt', "r");
//读取部分数据
$data = fgets($handle, 4096);
//移动文件指针到开始位置，和rewind($handle);函数效果一样。
$result = fseek($handle, 0);
echo $result;
?>