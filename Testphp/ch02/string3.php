<?php
//�ַ����ȽϺ���ʾ��
echo ">>strcmpʾ��<br>";
if(strcmp('cheng','wei')==0){
	echo 'string "cheng" and "wei" is equals.';
}
echo "<br>";
echo ">>strcasecmpʾ��<br>";
$str1 = "Hello";
$str2 = "hello";
if (strcasecmp($str1, $str2) == 0) {
    echo '�ڲ����ִ�Сд������£�['.$str1.']��['.$str2 .']����ȵģ�';
}
?>