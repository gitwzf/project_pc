<?php
	$counter = intval(file_get_contents("counter.dat"));
	//获取用户访问信息
	if(!isset($_COOKIE['visitor'])){
		$counter++;
		$fp = fopen("counter.dat", "w");
		flock($fp, LOCK_EX);   //进行排他型锁定
		fwrite($fp, $counter);
		flock($fp, LOCK_UN);   //释放锁定
		fclose($fp);
		setcookie("visitor", 1, time()+3600);
	}
	
	//显示图片计数器
	strval($counter);
	for($i=0; $i<strlen($counter); $i++){
		echo "<img src='count/0/" . substr($counter, $i, 1) . ".gif' border='0'>";
	}
?>