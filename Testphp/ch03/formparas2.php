<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
<title>���ձ����ݵĲ���</title>
<meta name="author" content="">
<meta name="keywords" content="">
<meta name="description" content="">
</head>

<body>
<?php
$name = $_POST["name"];
$pwd = $_POST["pwd"];
$jobs = $_POST["jobs"];
echo "<br>";
echo $name;
if($sex=1)
	echo "����";
else
	echo "Ůʿ";
echo "�����á���ӭ�����ʸñ����������Ϊ��";
echo $pwd;
echo "<br>";
echo "����".$jobs."��λ������";
?>
</body>
</html>
