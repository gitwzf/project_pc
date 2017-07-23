<?php
$fp = fsockopen("www.baidu.com", 80, &$errno, &$errstr, 10);
if(!$fp) {
        echo "$errstr ($errno)<br>\n";
} else {
	fputs($fp,"GET / HTTP/1.0\nHost: www.baidu.com\n\n");
    while(!feof($fp)) {
		echo fgets($fp,128);
	}
    fclose($fp);
}
?>