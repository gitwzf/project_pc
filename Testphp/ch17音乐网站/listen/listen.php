<?php
//播放文件.listen.php
//播放音乐url中不能含有 & 这个符号
require('../config.php');
require('../public.php');
require('../inc/db.class.php');

if($needlogin == 1 && !islogin()) {
	echo "<b>请先登录! </b>没注册者请从首页注册! (为更好的为网友服务, 普及收藏夹功能, 即日起注册用户才能在线听歌!<font size=+1 color=red>注册完全是免费的,也没有任何广告, hehe!) </font>\n";
	exit;
}

if(!$SelectSongsID) error_quit3("非法操作!");
$SongsID = split(",",$SelectSongsID);
$times = count($SongsID);

$msg = '';
for($i=0; $i<$times; $i++)
{	
	$rm_url = $db->query_first("SELECT rm_url FROM song WHERE song_id = '$SongsID[$i]'");
	$rm_url = $rm_url[0];
	if($rm_url != 'http://' && $rm_url != '')
	{
		$db->query_first("UPDATE song SET click=click+1 WHERE song_id = '$SongsID[$i]'");
		$rm_url = str_replace(' ','%20',$rm_url);
		if (!strncasecmp($rm_url, "http://", 7))
		    $msg .= $rm_url."\n";
		else
		    $msg .= $data_url.$rm_url."\n";
	}
}

if(islogin()) 
{ 
	$db->query("UPDATE user SET numlisten=numlisten+'$times' WHERE user_id = '$m_user[user_id]'");
	$m_user[numlisten] += $times;
}

$k = rand(0,9);
$file = "../tmp/ulist".$k.$message[15];

if($fd=fopen($file,"w"))
{
  flock($fd,LOCK_EX);
  fputs($fd, $msg, strlen($msg));
  flock($fd,LOCK_UN);
  fclose($fd);
}

header("Expires: Mon, 26 Jul 1997 05:00:00 GMT");
header("Last-Modified: " . gmdate("D, d M Y H:i:s") . "GMT");
header("Cache-Control: no-cache, must-revalidate");
header("Pragma: no-cache");
?>
<html>
<head>
<title>★音乐欣赏中……★</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table border="0" cellspacing="0" cellpadding="0">
<tr>
<td width="6"><img src="../images/listen/top_left.gif" width="6" height="18"></td>
<td width="247"><img src="../images/listen/top.gif" width="248" height="18"></td>
<td><a href='javascript:window.close();'><img src="../images/listen/close.gif" width="21" height="18" border="0"></a></td>
</tr>
<tr>
<td><img src="../images/listen/left.gif" width="6" height="66"></td>
<td><embed name=player1 src="<?php echo $file; ?>" type="audio/x-pn-realaudio-plugin" width="248" height="66" border="0" autostart="true"></embed></td>
<td><img src="../images/listen/right.gif" width="21" height="66"></td>
</tr>
<tr>
<td><img src="../images/listen/below_left.gif" width="6" height="32"></td>
<td><img src="../images/listen/below.gif" width="248" height="32" border="0"></td>
<td><img src="../images/listen/below_right.gif" width="21" height="32"></td>
</tr>
</table>
</body></html>