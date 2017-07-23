<?
php//播放文件整个CD.
//传入$cd_id
require('./public.php');
require('./inc/db.class.php');
require('./config.php');

$cd_id = $_GET['cid'];
if(!$cd_id) error_quit3("非法操作!");
$db = new db_class;
$db->connect();
$urls = $db->query("SELECT rm_url FROM song WHERE cd_id = '$cd_id' ORDER BY click DESC");

$msg = '';
while($tmp = $db->fetch_array($urls))
{
   $tmp[0] = str_replace(' ','%20',$tmp[0]);
   $tmp[0] .= "\n";
   if(!strncasecmp($tmp[0], "http://", 7))
       $msg .= $tmp[0];
   else
       $msg .= $data_url.$tmp[0];
}
$db->close();

$k = rand(0,99);
$file = "./tmp/ulist".$k.$message[15];

if($fd=fopen($file,"w"))
{
  flock($fd,LOCK_EX);
  fputs($fd,$msg,strlen($msg));
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
<td width="247"><img src="img/top.gif" width="248" height="18"></td>
<td><a href='javascript:window.close();'><img src="img/close.gif" width="21" height="18" border="0"></a></td>
</tr>
<tr>
<td><img src="img/left.gif" width="6" height="66"></td>
<td><embed name=player1 src="<?phpecho $file; ?>" type=audio/mpeg width="248" height="66" border="0" autostart="true"></embed></td>
<td><img src="img/right.gif" width="21" height="66"></td>
</tr>
<tr>
<td><img src="img/below_left.gif" width="6" height="32"></td>
<td><img src="img/below.gif" width="248" height="32" border="0"></td>
<td><img src="img/below_right.gif" width="21" height="32"></td>
</tr>
</table>
</body></html>