<?php
//��ȡ�ļ���׺������
function getsufix($filename){
	return substr(strrchr($filename, '.'), 1);
}
//��������ļ�������
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
