<?php
$arrays = array ( "colors"  => array ( "a" => "red","b" => "blue","c" => "black"),
              "numbers" => array ( 1, 2, 3, 4, 5),
              "phpbook"   => array ("first",3 => "second","third"));
//打印输出数组$fruits的部分元素值
echo $arrays["phpbook"][3];  //打印输出结果为"second"
echo $arrays["colors"]["a"];  //打印输出结果为"red"
unset($arrays["phpbook"][0]); //删除数组元素"first"
//创建一个新的二维数组
$test["php"]["book"] = "this is a good book!";
?>