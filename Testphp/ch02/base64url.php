<?php
	echo ">>���ַ�����Base64������ʾ���� >> base64_encode()";echo "<br>";
	$temp="This is an encode test.";
	echo base64_encode($temp);
	echo "<br>";
	echo ">>��Base64�����ַ���������ʾ���� >> base64_decode()";echo "<br>";
	$temp="VGhpcyBpcyBhbiBlbmNvZGVkIHN0cmluZw==";
	echo base64_decode($temp);
?>