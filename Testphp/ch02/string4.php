<?php
	//�ַ���ת�庯��ʾ��
	echo "----addslashes()����ʾ��----<br>";
	$str = "This's a php book!";
	echo addslashes($str);
	echo "<br>";
	echo "----stripslashes()ʾ������----<br>";
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
	//������
	print_r($array);
	echo "</pre>";
	echo "----htmlspecialchars()ʾ������----<br>";
	$new = htmlspecialchars("<a href='ch02.php?op=test'>Test</a>", ENT_QUOTES);
	echo $new;
	echo "<br>";
	echo "----htmlentities()����ʾ��----<br>";
	$str = "A 'quote' is <b>bold</b>";
	echo htmlentities($str)."<br>";
	echo htmlentities($str, ENT_QUOTES);
	echo "<br>";
	echo "----quotemeta()ʾ������----<br>";
	$str1 = "chengwei's book";
	echo quotemeta($str1);
?>