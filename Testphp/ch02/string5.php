<?php
	//使用字符串格式化函数示例
	echo ">>使用number_format()格式化示例<br>";
	$number = 1234.56;
	//只有一个参数，只返回整数部分
	$english_format_number = number_format($number);
	echo $english_format_number;
	//使用4个参数，小数点后保留两位并且小数点使用,表示，使用空格隔开整数位
	$france_format_number = number_format($number, 2, ',', ' ');
	echo $france_format_number;
	$number = 1234.5678;
	//使用4个参数，保留小数点后2位，小数点使用.表示同时省略整数部分的每三位分隔
	$english_format_number = number_format($number, 2, '.', '');
	echo $english_format_number;
	echo "<br>";
	echo ">>使用sprintf()格式化示例<br>";
	$money1 = 68.75;
	$money2 = 54.35;
	$money = $money1 + $money2;
	echo $money;//此时变量$money的值为 "123.1";
	echo "<br>";
	$formatted = sprintf ("%01.2f", $money);
	echo $formatted;//此时变量$formatted的值为 "123.10"
?>