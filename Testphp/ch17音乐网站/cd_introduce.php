<?php
//歌手简介 singer_introduce.php
//支持HTML
require('config.php');
require('public.php');
require('inc/db.class.php');

if(!$cd_id) error_quit3('非法操作');

$res = $db->query_first("SELECT C.*, S.singer_name FROM cd C, singer S WHERE C.cd_id = '$cd_id' AND C.singer_id = S.singer_id");


$res[introduce] = bbcode($res[introduce]);
if(!$res[imgurl]) $res[imgurl] = $default_cd_cover;

html_head($res[singer_name]." 》".$res[cd_name]." 》简介");
print "<br>
<table width=98% bgcolor=#000000 align=center cellPadding=3 cellSpacing=1 height=95%>
<tr height=18 bgcolor=$message[2]><td align=left>$res[singer_name] 》$res[cd_name] 》简介</td></tr>
<tr><td background='images/bg1.gif' valign=top>
        <table border=0 width=98%>
		<tr>
		 <td width=110 valign=top align=left rowspan=5><a    href='cd.php?cd_id=$res[cd_id]&cd_name=".urlencode($res[cd_name])."&singer_id=$res[singer_id]&singer_name=".urlencode($res[singer_name])."' target=_blank><img src='$res[imgurl]' width=110 height=110 border=0 alt='封面图'></a></td>
		 <td valign=top align=left><b>名称: </b><a    href='cd.php?cd_id=$res[cd_id]&cd_name=".urlencode($res[cd_name])."&singer_id=$res[singer_id]&singer_name=".urlencode($res[singer_name])."' target=_blank>$res[cd_name]</a></td>
		 <td valign=top align=left><b>语言: </b>$res[lang]</td></td>
		</tr>
		<tr><td><b>歌数: </b>$res[song_num] </td><td><b>点击: </b>$res[click]</td></tr>
        <tr><td colspan=2><b>演唱: </b><a href='singer.php?singer_id=$res[singer_id]&singer_name=".urlencode($res[singer_name])
			."' target=_blank>$res[singer_name]</a> </td></tr>
        <tr><td colspan=2><b>出版: </b>$res[pub_date] </td></tr>
		<tr><td colspan=2><b>添加: </b>$res[add_date]</td></tr>
		<tr><td colspan=3><hr size=1 noshade><b>简介: </b><br><span class=p6>$res[introduce]</span></td></tr>
        </table>
</td></tr>
<tr bgcolor=$message[2] height=18><td align=center>$message[0]</td></tr>
</table>
</body></html>";

$db->close();
exit;
?>