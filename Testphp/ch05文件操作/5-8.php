<?php
//���ļ�����Щ����
$handle = fopen("phpbook.txt", "r");
$data = fgets($handle, 15);
//��ӡ�������ָ��λ��
echo ftell($handle);
fclose($handle);
?>