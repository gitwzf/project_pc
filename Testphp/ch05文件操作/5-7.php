<?php
$handle = fopen('phpbook.txt', "r");
//��ȡ��������
$data = fgets($handle, 4096);
//�ƶ��ļ�ָ�뵽��ʼλ�ã���rewind($handle);����Ч��һ����
$result = fseek($handle, 0);
echo $result;
?>