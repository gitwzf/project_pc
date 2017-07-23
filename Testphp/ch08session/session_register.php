<?php
//演示session_unset()函数的使用效果
session_register('title','author','isbn'); //session自动开始
$title = 'PHP网络编程';
$author = 'cheng';
$isbn = 'PHP3124567890';
//unregistrered $title
session_unregister('title');
//此时title已经不是session的注册变量，而author仍然是注册变量
echo "title: $title - reg:".session_is_registered('title')." ";
echo "<br>author: $author - reg:".session_is_registered('author');
//title不再是session注册变量，但是全局变量中title的值依然存在
echo "<br>global title = ".$GLOBALS['title'];
//使用session_unset()函数，处理 $author 和 $isbn
session_unset();
echo "<br>author:$author - reg:".session_is_registered('author')." ";
echo "<br>isbn:$isbn - reg:".session_is_registered('isbn')." ";
//全局变量中isbn的值也一起销毁了
echo "<br>global isbn = ".$GLOBALS['isbn']." ";
echo "<br>session_encode=".session_encode();
session_register('content');
$content = '这是一本关于PHP网络编程的经典之作！';
echo "<br>session_encode=".session_encode();
?>