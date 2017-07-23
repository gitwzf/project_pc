<?php
	echo "读出压缩文件解压缩后的数据输出标准设备演示部分 >> readgzfile()";echo"<br>";
	$id = gzopen("test1.gz","w");
	gzputs($id,"<html><center>诸葛亮</center></html>");
	gzclose($id);
	readgzfile("test1.gz");
	echo "读压缩文件解压缩后的数据到数组中演示部分 >> gzfile()";echo"<br>";
	$id = gzopen("test1.gz","w");
	gzputs($id,"elva is my girl friend.\n");
	gzputs($id,"And you?\n");
	gzclose($id);
	$temp = gzfile("test1.gz");
	echo $temp[0]."<br>";
	echo $temp[1]."<br>";
?>