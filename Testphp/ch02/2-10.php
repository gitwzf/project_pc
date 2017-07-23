<?php
//创建一个简单的数组
$array = array(1, 2, 3, 4, 5);
print_r($array);
//现在删除其中的所有元素，但保持数组原来的结构
foreach ($array as $i => $value) {
    unset($array[$i]);
}
print_r($array);
//添加一个单元（注意此时的键名是5，而不是0）
$array[] = 6;
print_r($array);
//重新索引数组
$array = array_values($array);
$array[] = 7;
print_r($array);
?>