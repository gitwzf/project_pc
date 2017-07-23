<?php
$fp = fopen('phpbook.txt', 'r');
if (!$fp) {
    echo '不能打开phpbook.txt文件！';
}
while (false !== ($char = fgetc($fp))) {
    echo "$char\n";
}
?>