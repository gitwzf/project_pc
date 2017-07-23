<?php
if(!$file = fopen("test.php","r")){
	echo "不能打开文件";    //如果fopen返回0，则打开文件失败
}else{
	while(!feof($file)){	//遍历是否到文件结束
		echo fgetc($file);
	}
}
?>