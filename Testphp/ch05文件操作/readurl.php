<?php
//通过HTTP从URL中取得 HTML 源文件，并将文件读入数组。
$lines = file('http://localhost/phpbook/ch05/news.htm');
//在数组中循环，显示 html 的源文件并加上行号。
foreach ($lines as $line_num => $line) {
    echo "行号#<b>{$line_num}</b> : " . htmlspecialchars($line) . "<br>\n";
}
//另一个例子，将web页面读入字符串。
//$html = implode ('', file ('http://localhost/phpbook/ch05/news.htm'));
?>