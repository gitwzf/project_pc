<?php
session_start();
//测试$_SESSION使用效果
$value = "这是PHP网络编程的第三章!";
$author = "chengwei";
if (!isset($_SESSION['title'])) {
    $_SESSION['title'] = $value;
} else {
    $_SESSION['author'] = $author;
}
//使用$_SESSION获取结果
foreach ($_SESSION as $key => $value) {
	echo "Key: <font color='blue'>$key</font>; Value: <font color='red'>$value</font><br>\n";
}
?>