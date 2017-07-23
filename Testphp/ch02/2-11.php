<?php
//数组a
$a = array( 'color' => 'red', 'taste' => 'sweet', 'shape' => 'round', 'name'  => 'apple',
                  4 /*键值将会等于0*/);
//数组a完全等同于下面的赋值
$a['color']	= 'red';
$a['taste'] = 'sweet';
$a['shape'] = 'round';
$a['name']  = 'apple';
$a[]       = 4;        //键值将会等于0
//数组b
$b[] = 'a';
$b[] = 'b';
$b[] = 'c';
//上面的数组b将等同于array(0 => 'a' , 1 => 'b' , 2 => 'c')，或者简单构造形式array('a', 'b', 'c')
?>