<?php
//ͨ��HTTP��URL��ȡ�� HTML Դ�ļ��������ļ��������顣
$lines = file('http://localhost/phpbook/ch05/news.htm');
//��������ѭ������ʾ html ��Դ�ļ��������кš�
foreach ($lines as $line_num => $line) {
    echo "�к�#<b>{$line_num}</b> : " . htmlspecialchars($line) . "<br>\n";
}
//��һ�����ӣ���webҳ������ַ�����
//$html = implode ('', file ('http://localhost/phpbook/ch05/news.htm'));
?>