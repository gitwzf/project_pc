<?php
//�����ļ�.listenX.php
//�������֣�����url��ַ
require('../config.php');
require('../public.php');
require('../inc/db.class.php');

if($needlogin == 1 && !islogin()) {
	echo "<b>���ȵ�¼! </b>ûע���������ҳע��! (Ϊ���õ�Ϊ���ѷ���, �ռ��ղؼй���, ������ע���û�������������!<font size=+1 color=red>ע����ȫ����ѵ�,Ҳû���κι��, hehe!) </font>\n";
	exit;
}

if(!$song_url) error_quit3("�Ƿ�����!");

$db->query("UPDATE song SET click=click+1 WHERE rm_url = '$song_url'");
$song_url = eregi_replace(' ','%20',$song_url);

if(islogin()) 
{ 
	$db->query("UPDATE user SET numlisten=numlisten+1 WHERE user_id = '$m_user[user_id]'");
	$m_user[numlisten] += 1;
}

if (strncasecmp($song_url, "http://", 7))
{
    $song_url = $data_url . $song_url;
}

header("Expires: Mon, 26 Jul 2000 05:00:00 GMT");
header("Cache-Control: no-cache, must-revalidate");
header("Content-type: text/html");
?>
<html>
<head>
<title>�����������С�����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="Cache-Control" content="no-cache; must-revalidate">
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
<td><embed name=player1 src="<?php echo $song_url; ?>" type=audio/x-pn-realaudio-plugin width="248" height="66" border="0" autostart="true"></embed></td>
<td><img src="../images/listen/right.gif" width="21" height="66"></td>
</tr>
<tr>
<td><img src="../images/listen/below_left.gif" width="6" height="32"></td>
<td><img src="../images/listen/below.gif" width="248" height="32" border="0"></td>
<td><img src="../images/listen/below_right.gif" width="21" height="32"></td>
</tr>
</table>
</body></html>