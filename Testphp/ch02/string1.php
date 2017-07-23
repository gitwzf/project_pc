<?php
	//分割、截取字符串示例程序
	echo ">>strstr函数示例<br>";
	$email = 'chengwei@phpbook.com';
	$domain = strstr($email, '@');
	echo $domain."<br>"; //打印输出邮件的域名
	echo ">>substr函数示例<br>";
	$str = "abcdef";
	echo substr($str, 1);     			  //输出结果为bcdef
	echo "<br>";echo substr($str, 1, 3);  //输出结果为bcd
	echo "<br>";echo substr($str, 0, 4);  //输出结果为abcd
	echo "<br>";echo substr($str, 0, 8);  //输出结果为abcdef
	echo "<br>";echo substr($str, -1, 1); //输出结果为f
	echo "<br>";
	echo ">>strtok函数示例<br>";
	$string = "PHP的语法\t结构和\n常用函数。";
	//同时使用回车和换行截取
	$tok = strtok($string, "\n\t");
	while ($tok !== false) {
	    echo "分隔结果=$tok<br/>";
	    $tok = strtok("\n\t");
	}
	echo ">>chunk_split函数示例<br>";
	$data = "今天我高兴，heihei.";
	$new_string = chunk_split(base64_encode($data));
	echo $new_string;
	echo "<br>";
	echo ">>split函数示例<br>";
	$date = "11/30/1978";
	list($month, $day, $year) = split ('[/.-]', $date);
	echo $year."年-". $month."月-". $day."日 \n";
?>