<!doctype html public "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>�����ļ�©��ʾ��</title>
<meta name="Author" content="chengwei">
<meta name="Keywords" content="�����ļ�©��">
<meta name="Description" content="�����ļ�©��">
</head>
<body>
<?php
if (isset($_GET["file"])) {
	include_once($_GET["file"]);
} else {
	include_once("default.php");
}
?>
</body>
</html>