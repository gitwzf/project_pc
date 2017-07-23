<?php
	echo ">>验证日期的正确性 >> checkdate()<br>";
	if(checkdate(2,29,2007)==true)
		echo "日期(2,29,2007)输入正确<br>";
	else 
		echo "日期(2,29,2007)输入错误<br>";
	if(checkdate(9,30,1979)==false)
		echo "日期(9,30,1979)输入错误<br>";
	else 
		echo "日期(9,30,1979)输入正确<br>";
	echo ">>获取时间及日期 >> getdate()<br>";
	$temp = getdate(time());
	echo "今天是 ".$temp["weekday"].",".$temp["month"].$temp["mday"].".";
	echo "<br>";
	echo ">>取目前时间演示部分 >> gettimeofday()<br>";
	$temp=gettimeofday();
	echo $temp["sec"]."".$temp["usec"];
	echo "<br>";
	echo ">>取UNIX时间戳记演示部分 >> mktime()<br>";
	echo date("FjY",mktime(1,2,3,9,11,1980));
	echo"<br>";
	echo ">>取得目前时间的UNIX时间戳记演示部分 >> time()<br>";
	echo time();echo"<br>";
	echo ">>取得目前时间的UNIX时间戳记的百万分之一秒值演示部分 >> microtime()<br>";
	echo microtime();
?>