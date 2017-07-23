<?php
	//显示当前时间
	$today1 = date("F j, Y, g:i a");                 // December 18, 2006, 2:39 pm
	$today2 = date("m.d.y");                         // 12.18.06
	$today3 = date("j, n, Y");                       // 18, 12, 2006
	$today4 = date("Ymd");                           // 20061218
	$today5 = date('h-i-s, j-m-y, it is w Day z ');  // 02-39-33, 18-12-06, 3931 3933 1 Monpm06 351
	$today6 = date('\i\t \i\s \t\h\e jS \d\a\y.');   // it is the 18th day.
	$today7 = date("D M j G:i:s T Y");               // Mon Dec 18 14:39:33 中国标准时间 2006
	$today8 = date('H:m:s \m \i\s\ \m\o\n\t\h');     // 14:12:33 m is month
	$today9 = date("H:i:s");                         // 14:39:33
	//显示结果
	echo "\$today1=".$today1."<br>";
	echo "\$today2=".$today2."<br>";
	echo "\$today3=".$today3."<br>";
	echo "\$today4=".$today4."<br>";
	echo "\$today5=".$today5."<br>";
	echo "\$today6=".$today6."<br>";
	echo "\$today7=".$today7."<br>";
	echo "\$today8=".$today8."<br>";
	echo "\$today9=".$today9."<br>";
?>