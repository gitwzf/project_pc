<?php
	//ʹ���ַ�����ʽ������ʾ��
	echo ">>ʹ��number_format()��ʽ��ʾ��<br>";
	$number = 1234.56;
	//ֻ��һ��������ֻ������������
	$english_format_number = number_format($number);
	echo $english_format_number;
	//ʹ��4��������С���������λ����С����ʹ��,��ʾ��ʹ�ÿո��������λ
	$france_format_number = number_format($number, 2, ',', ' ');
	echo $france_format_number;
	$number = 1234.5678;
	//ʹ��4������������С�����2λ��С����ʹ��.��ʾͬʱʡ���������ֵ�ÿ��λ�ָ�
	$english_format_number = number_format($number, 2, '.', '');
	echo $english_format_number;
	echo "<br>";
	echo ">>ʹ��sprintf()��ʽ��ʾ��<br>";
	$money1 = 68.75;
	$money2 = 54.35;
	$money = $money1 + $money2;
	echo $money;//��ʱ����$money��ֵΪ "123.1";
	echo "<br>";
	$formatted = sprintf ("%01.2f", $money);
	echo $formatted;//��ʱ����$formatted��ֵΪ "123.10"
?>