<?php
	echo ">>��֤���ڵ���ȷ�� >> checkdate()<br>";
	if(checkdate(2,29,2007)==true)
		echo "����(2,29,2007)������ȷ<br>";
	else 
		echo "����(2,29,2007)�������<br>";
	if(checkdate(9,30,1979)==false)
		echo "����(9,30,1979)�������<br>";
	else 
		echo "����(9,30,1979)������ȷ<br>";
	echo ">>��ȡʱ�估���� >> getdate()<br>";
	$temp = getdate(time());
	echo "������ ".$temp["weekday"].",".$temp["month"].$temp["mday"].".";
	echo "<br>";
	echo ">>ȡĿǰʱ����ʾ���� >> gettimeofday()<br>";
	$temp=gettimeofday();
	echo $temp["sec"]."".$temp["usec"];
	echo "<br>";
	echo ">>ȡUNIXʱ�������ʾ���� >> mktime()<br>";
	echo date("FjY",mktime(1,2,3,9,11,1980));
	echo"<br>";
	echo ">>ȡ��Ŀǰʱ���UNIXʱ�������ʾ���� >> time()<br>";
	echo time();echo"<br>";
	echo ">>ȡ��Ŀǰʱ���UNIXʱ����ǵİ����֮һ��ֵ��ʾ���� >> microtime()<br>";
	echo microtime();
?>