<?php
session_start();
//����$_SESSIONʹ��Ч��
$value = "����PHP�����̵ĵ�����!";
$author = "chengwei";
if (!isset($_SESSION['title'])) {
    $_SESSION['title'] = $value;
} else {
    $_SESSION['author'] = $author;
}
//ʹ��$_SESSION��ȡ���
foreach ($_SESSION as $key => $value) {
	echo "Key: <font color='blue'>$key</font>; Value: <font color='red'>$value</font><br>\n";
}
?>