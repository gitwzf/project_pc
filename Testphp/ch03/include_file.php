<!doctype html public "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>包含文件漏洞示例</title>
<meta name="Author" content="chengwei">
<meta name="Keywords" content="包含文件漏洞">
<meta name="Description" content="包含文件漏洞">
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