<?php
if(!$file = fopen("test.php","r")){
	echo "���ܴ��ļ�";    //���fopen����0������ļ�ʧ��
}else{
	while(!feof($file)){	//�����Ƿ��ļ�����
		echo fgetc($file);
	}
}
?>