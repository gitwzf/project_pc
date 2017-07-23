<?php
//版面回复,支持BBcode
require('config.php');
require('public.php');
require('inc/db.class.php');

if(!islogin()) error_quit3("您还没有登录！$message[4] $message[6]"); //登录后才能享受此项服务。
if(!isset($post_id)) error_quit3('非法操作');

if(!isset($submit) || $title == '' || $text == '')
{

 $res = $db->query_first("SELECT * FROM post WHERE post_id = $post_id");
 if(($res[author] != $m_user[user_name]) && !is_admin()) error_quit3("您没有操作权限!");

 $str = "修改文章: ".$res[title]."&nbsp;&nbsp;&nbsp;[".$singer_name."讨论区]";
 $res[text] = quote_code($res[text], 2);

 html_head("发表文章");

 print "
	 <br>
	 <table width=98% bgcolor=#000000 align=center cellPadding=3 cellSpacing=1 height=95%>
	 <tr height=18 bgcolor=$message[2]><td align=left> $str </td></tr>
	 <tr><form action=$PHP_SELF method=post><td align=left width=100% valign=top background='images/bg1.gif'>
	 文章标题: <input type=text size=50 maxlength=80 name=title value='$res[title]' class=input><br><br>
	 文章内容: (不支持HTML, $message[6])<br>
	 <textarea name=text cols=70 rows=14 class=input WRAP=virtual>$res[text]</textarea><Br>
	 <input type=hidden name=post_id value='$post_id'> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 <input type=submit name=submit value='提交' class=button> <input type=reset value='重来' class=button>
	 </td></form></tr>
	 <tr bgcolor=$message[2] height=18><td align=center>$message[1] $message[0]</td></tr>
	 </table>
	 ";
}

else
{
	$text .= "\n\n--\n※ ".$m_user[user_name]."于".date("Y-m-d H:i:s")."最后修改过本文\n--";
	$db->query("UPDATE post SET title = '$title', text = '$text' WHERE post_id = '$post_id'");

	html_head("修改成功");
	print "<br><br><br>
	<div class=okmsg align=center>修改成功</div>
		<p align=center>请刷新后就能看到您的新文章了。<br><br> $message[0] </p>
		<script language=javascript>
		setTimeout('window.close()', 1000);
		opener.document.location.reload();
	</script>
 ";
}

print "</body></html>";

$db->close();
exit;
?>