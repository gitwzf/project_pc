<?php
//����$_COOKIEʹ��Ч��
$value = "����PHP�����̵ĵ�����!";
$author = "chengwei";
/* ʹ��Ĭ������ */
setcookie("php_book", $value);
/* ���ù���ʱ��Ϊ1Сʱ */
setcookie("author", $author, time()+3600);
/* ���ù���ʱ��Ϊ1Сʱ����Ч·��Ϊphpbook */
setcookie("isbn", "PHP1234567890", time()+3600, "/phpbook");

//ʹ��$_COOKIE��ȡ���
foreach ($_COOKIE as $key => $value) {
	echo "Key: <font color='blue'>$key</font>; Value: <font color='red'>$value</font><br>\n";
}
?>