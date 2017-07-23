<?php
	echo ">>剖析URL字符串演示部分 >> parse_url()";
	echo "<br>";
	$temp = "http://username:password@hostname/path?arg=value#anchor";
	$urls = parse_url($temp);
	echo $temp;
	echo "<pre>";
	print_r($urls);
	echo "</pre>";
	echo ">>还原URL编码字串演示部分 >> urlencode()";echo "<br>";
	$temp="chengwei@163.net";
	echo urlencode($temp);
	echo "<br>";
	echo ">>还原URL编码字串演示部分 >> urldecode()";echo "<br>";
	$temp="mail+chengwei%40126.net";
	echo urldecode($temp);
	echo ">>将字符串以rawurlencode()编码演示 >> rawurlencode()";echo "<br>";
	$temp="afafa #@+!123";
	echo rawurlencode($temp);
	echo "<br>";
	echo ">>还原rawurldecode()编码字串演示部分 >> rawurldecode()";echo "<br>";
	$temp="doom%20bar%40com";
	echo rawurldecode($temp);
?>