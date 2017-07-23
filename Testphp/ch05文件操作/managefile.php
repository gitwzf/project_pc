<html>
<title>文件管理综合应用</title>
</head>
<body>
<table border=0 width=100%>
<tr>
<td align=center bgcolor=lightblue>
<font size=4 color=red>我的PHP文件浏览器--文件显示</font></td>
</tr>
</table>
<?php
	$currentdir = $_GET['currentdir'];
	$type = $_GET["type"];
	$filename =  $_GET["filename"];
?>
<a href="filedir.php?currentdir=<?echo $currentdir; ?>">>>返回我的PHP文件浏览器</a><BR>
<?php
//文本文件的显示方法

if($type == ".TXT"){
	echo "111".$currentdir.'/'.$filename;
	$id = fopen($currentdir.$filename,"r");
	while(!feof($id)){
		$temp=fgets($id,256);
		$temp=str_replace("","&nbsp",$temp);
		print($temp."<BR>\n");
	}
	fclose($id);
	//htm/html文件的显示方法
}elseif($type==".HTM"||$type==".HTML"){
	readfile($currentdir.'/'.$filename);
}
?>
</body>
</html>