<html>
<title>�ļ������ۺ�Ӧ��</title>
</head>
<body>
<table border=0 width=100%>
<tr>
<td align=center bgcolor=lightblue>
<font size=4 color=red>�ҵ�PHP�ļ������--�ļ���ʾ</font></td>
</tr>
</table>
<?php
	$currentdir = $_GET['currentdir'];
	$type = $_GET["type"];
	$filename =  $_GET["filename"];
?>
<a href="filedir.php?currentdir=<?echo $currentdir; ?>">>>�����ҵ�PHP�ļ������</a><BR>
<?php
//�ı��ļ�����ʾ����

if($type == ".TXT"){
	echo "111".$currentdir.'/'.$filename;
	$id = fopen($currentdir.$filename,"r");
	while(!feof($id)){
		$temp=fgets($id,256);
		$temp=str_replace("","&nbsp",$temp);
		print($temp."<BR>\n");
	}
	fclose($id);
	//htm/html�ļ�����ʾ����
}elseif($type==".HTM"||$type==".HTML"){
	readfile($currentdir.'/'.$filename);
}
?>
</body>
</html>