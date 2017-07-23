<?php
//播放文件整个CD
require('./public.php');
require('./inc/db.class.php');
require('./config.php');
//传入$cd_id
$cd_id = $_GET['cid'];
if(!$cd_id) error_quit3("非法操作!");
$db = new db_class;
$db->connect();
$urls = $db->query("SELECT rm_url FROM song WHERE cd_id = '$cd_id' ORDER BY click DESC");

$msg = '';
$count = 0;
while($tmp = $db->fetch_array($urls))
{
	$tmp[0] = str_replace(' ','%20',$tmp[0]);
	$msg .= "<entry><ref href=\"";
	$msg .= $tmp[0];
	$msg .= "\"/></entry>\n";
	$count ++ ;
}
$db->close();

$k = 12;//rand(0,99);
$file = "./tmp/ulist".$k.$message[16];
$msghead = "<asx version=\"3\">\n<title>taraera</title>\n<repeat>\n";
$msgtail = "</repeat>\n</asx>";
if($fd=fopen($file,"w"))
{
	flock($fd,LOCK_EX);
	fputs($fd,$msghead,strlen($msghead));
	fputs($fd,$msg,strlen($msg));
	fputs($fd,$msgtail,strlen($msgtail));
	flock($fd, LOCK_UN);
	fclose($fd);
}

header("Expires: Mon, 26 Jul 2000 05:00:00 GMT");
header("Cache-Control: no-cache, must-revalidate");
header("Content-type: text/html");
?>
<html>
	<head>
		<title>★音乐欣赏中★</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<meta http-equiv="Cache-Control" content="no-cache; must-revalidate">
	</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	<table border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="6"><img src="img/top_left.gif" width="6" height="18"></td>
			<td width="300"><img src="img/top.gif" width="300" height="18"></td>
			<td><a href='javascript:window.close();'><img src="img/close.gif" width="21" height="18" border="0"></a></td>
		</tr>
		<tr>
			<td><img src="img/left.gif" width="6" height="66"></td>
			<td><embed style="FILTER: Gray()" width=300 height=69 type=application/x-mplayer2 loop="-1" showcontrols="1" ShowDisplay="0" ShowStatusBar="1" autostart="1" name='wmp' src="<?echo $file; ?>"></embed></td>
			<td><img src="img/right.gif" width="21" height="66"></td>
		</tr>
</table>
</body>
</html>