<?php
$handle = fopen ("http://www.example.com/", "rb");
$contents = "";
do {
    $data = fread($handle, 8192);
    if (strlen($data) == 0) {
        break;
    }
    $contents .= $data;
} while(true);
fclose ($handle);
?>