<?php
	//字符串转义函数示例
	echo "----addslashes()程序示例----<br>";
	$str = "This's a php book!";
	echo addslashes($str);
	echo "<br>";
	echo "----stripslashes()示例程序----<br>";
	function teststripslashes($value)
	{
	    $value = is_array($value) ? 
	    			array_map('teststripslashes', $value) : 
	    			stripslashes($value);
	    return $value;
	}
	$array = array("b\\'ook", "my\\'sql", array("bo\\'ok", "mys\\'ql"));
	$array = teststripslashes($array);
	echo "<pre>";
	//输出结果
	print_r($array);
	echo "</pre>";
	echo "----htmlspecialchars()示例程序----<br>";
	$new = htmlspecialchars("<a href='ch02.php?op=test'>Test</a>", ENT_QUOTES);
	echo $new;
	echo "<br>";
	echo "----htmlentities()程序示例----<br>";
	$str = "A 'quote' is <b>bold</b>";
	echo htmlentities($str)."<br>";
	echo htmlentities($str, ENT_QUOTES);
	echo "<br>";
	echo "----quotemeta()示例程序----<br>";
	$str1 = "chengwei's book";
	echo quotemeta($str1);
?>