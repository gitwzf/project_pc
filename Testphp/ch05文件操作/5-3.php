<?php
$fp = fopen('phpbook.txt', 'r');
if (!$fp) {
    echo '���ܴ�phpbook.txt�ļ���';
}
while (false !== ($char = fgetc($fp))) {
    echo "$char\n";
}
?>