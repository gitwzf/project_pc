<?php
	//�ָ��ȡ�ַ���ʾ������
	echo ">>strstr����ʾ��<br>";
	$email = 'chengwei@phpbook.com';
	$domain = strstr($email, '@');
	echo $domain."<br>"; //��ӡ����ʼ�������
	echo ">>substr����ʾ��<br>";
	$str = "abcdef";
	echo substr($str, 1);     			  //������Ϊbcdef
	echo "<br>";echo substr($str, 1, 3);  //������Ϊbcd
	echo "<br>";echo substr($str, 0, 4);  //������Ϊabcd
	echo "<br>";echo substr($str, 0, 8);  //������Ϊabcdef
	echo "<br>";echo substr($str, -1, 1); //������Ϊf
	echo "<br>";
	echo ">>strtok����ʾ��<br>";
	$string = "PHP���﷨\t�ṹ��\n���ú�����";
	//ͬʱʹ�ûس��ͻ��н�ȡ
	$tok = strtok($string, "\n\t");
	while ($tok !== false) {
	    echo "�ָ����=$tok<br/>";
	    $tok = strtok("\n\t");
	}
	echo ">>chunk_split����ʾ��<br>";
	$data = "�����Ҹ��ˣ�heihei.";
	$new_string = chunk_split(base64_encode($data));
	echo $new_string;
	echo "<br>";
	echo ">>split����ʾ��<br>";
	$date = "11/30/1978";
	list($month, $day, $year) = split ('[/.-]', $date);
	echo $year."��-". $month."��-". $day."�� \n";
?>