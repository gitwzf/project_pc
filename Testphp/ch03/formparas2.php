<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
<title>接收表单传递的参数</title>
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
	echo "先生";
else
	echo "女士";
echo "，您好。欢迎您访问该表单。你的密码为：";
echo $pwd;
echo "<br>";
echo "您在".$jobs."单位工作。";
?>
</body>
</html>
