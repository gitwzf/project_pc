<?php
//���ּ�� singer_introduce.php
//֧��HTML
require('config.php');
require('public.php');
require('inc/db.class.php');

if(!$singer_id) error_quit3('�Ƿ�����');
$res = $db->query_first("SELECT singer_name, imgurl, introduce FROM singer WHERE singer_id = '$singer_id'");

if(!$res[imgurl] || $res[imgurl] == 'http://') $res[imgurl] = $default_singer;

if(strncasecmp($res[imgurl], "http://", 7))
$res[imgurl] = $data_url.$res[imgurl];

$img = "<img src='$res[imgurl]' alt='ͷ��' width=110 align=left>";
$res[introduce] = bbcode($res[introduce]);

html_head("����".$res[singer_name]."���");
print "
<br>
<table width=98% bgcolor=#000000 align=center cellPadding=3 cellSpacing=1 height=95%>
<tr height=18 bgcolor=$message[2]><td align=left>���ּ�� ��$res[singer_name]</td></tr>
<tr bgcolor=$message[3]><td align=left valign=top background='images/bg1.gif'> $img $res[introduce] </td></tr>
<tr bgcolor=$message[2] height=18><td align=center>$message[0]</td></tr>
</table>
</body></html>
";

$db->close();
exit;
?>