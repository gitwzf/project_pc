<?php
	$counter = intval(file_get_contents("counter.dat")) + 1;
	$fp = fopen("counter.dat", "w");
	fwrite($fp, $counter);
	fclose($fp);
	//��ӡ���Ľ����������
	echo "Visitors: " . $counter;
?>