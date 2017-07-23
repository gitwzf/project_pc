<?php
//获取文件后缀名函数
function getsufix($filename){
	return substr(strrchr($filename, '.'), 1);
}
//生成随机文件名函数
function random($length){
	$hash = 'ch12-';
	$chars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
	$max = strlen($chars) - 1;
	mt_srand((double)microtime() * 1000000);
	for($i = 0; $i < $length; $i++){
		$hash .= $chars[mt_rand(0, $max)];
	}
	return $hash;
}
?>
