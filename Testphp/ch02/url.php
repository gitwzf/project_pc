<?php
	echo ">>����URL�ַ�����ʾ���� >> parse_url()";
	echo "<br>";
	$temp = "http://username:password@hostname/path?arg=value#anchor";
	$urls = parse_url($temp);
	echo $temp;
	echo "<pre>";
	print_r($urls);
	echo "</pre>";
	echo ">>��ԭURL�����ִ���ʾ���� >> urlencode()";echo "<br>";
	$temp="chengwei@163.net";
	echo urlencode($temp);
	echo "<br>";
	echo ">>��ԭURL�����ִ���ʾ���� >> urldecode()";echo "<br>";
	$temp="mail+chengwei%40126.net";
	echo urldecode($temp);
	echo ">>���ַ�����rawurlencode()������ʾ >> rawurlencode()";echo "<br>";
	$temp="afafa #@+!123";
	echo rawurlencode($temp);
	echo "<br>";
	echo ">>��ԭrawurldecode()�����ִ���ʾ���� >> rawurldecode()";echo "<br>";
	$temp="doom%20bar%40com";
	echo rawurldecode($temp);
?>