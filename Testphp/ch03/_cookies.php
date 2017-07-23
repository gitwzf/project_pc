<?php
//测试$_COOKIE使用效果
$value = "这是PHP网络编程的第三章!";
$author = "chengwei";
/* 使用默认设置 */
setcookie("php_book", $value);
/* 设置过期时间为1小时 */
setcookie("author", $author, time()+3600);
/* 设置过期时间为1小时，有效路径为phpbook */
setcookie("isbn", "PHP1234567890", time()+3600, "/phpbook");

//使用$_COOKIE获取结果
foreach ($_COOKIE as $key => $value) {
	echo "Key: <font color='blue'>$key</font>; Value: <font color='red'>$value</font><br>\n";
}
?>