<?php
	$counter = intval(file_get_contents("counter.dat"));
	//��ȡ�û�������Ϣ
	if(!isset($_COOKIE['visitor'])){
		$counter++;
		$fp = fopen("counter.dat", "w");
		flock($fp, LOCK_EX);   //��������������
		fwrite($fp, $counter);
		flock($fp, LOCK_UN);   //�ͷ�����
		fclose($fp);
		setcookie("visitor", 1, time()+3600);
	}
	
	//��ʾͼƬ������
	strval($counter);
	for($i=0; $i<strlen($counter); $i++){
		echo "<img src='count/0/" . substr($counter, $i, 1) . ".gif' border='0'>";
	}
?>