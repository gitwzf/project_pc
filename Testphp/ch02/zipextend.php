<?php
	echo "����ѹ���ļ���ѹ��������������׼�豸��ʾ���� >> readgzfile()";echo"<br>";
	$id = gzopen("test1.gz","w");
	gzputs($id,"<html><center>�����</center></html>");
	gzclose($id);
	readgzfile("test1.gz");
	echo "��ѹ���ļ���ѹ��������ݵ���������ʾ���� >> gzfile()";echo"<br>";
	$id = gzopen("test1.gz","w");
	gzputs($id,"elva is my girl friend.\n");
	gzputs($id,"And you?\n");
	gzclose($id);
	$temp = gzfile("test1.gz");
	echo $temp[0]."<br>";
	echo $temp[1]."<br>";
?>