<?php
	$counter = intval(file_get_contents("counter.dat")) + 1;
	$fp = fopen("counter.dat", "w");
	fwrite($fp, $counter);
	fclose($fp);
	//打印最后的结果给访问者
	echo "Visitors: " . $counter;
?>