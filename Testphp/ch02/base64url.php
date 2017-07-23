<?php
	echo ">>将字符串以Base64编码演示部分 >> base64_encode()";echo "<br>";
	$temp="This is an encode test.";
	echo base64_encode($temp);
	echo "<br>";
	echo ">>将Base64编码字符串解码演示部分 >> base64_decode()";echo "<br>";
	$temp="VGhpcyBpcyBhbiBlbmNvZGVkIHN0cmluZw==";
	echo base64_decode($temp);
?>