<?php
//字符串比较函数示例
echo ">>strcmp示例<br>";
if(strcmp('cheng','wei')==0){
	echo 'string "cheng" and "wei" is equals.';
}
echo "<br>";
echo ">>strcasecmp示例<br>";
$str1 = "Hello";
$str2 = "hello";
if (strcasecmp($str1, $str2) == 0) {
    echo '在不区分大小写的情况下，['.$str1.']和['.$str2 .']是相等的！';
}
?>