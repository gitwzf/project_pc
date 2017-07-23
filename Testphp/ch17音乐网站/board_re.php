<?php
//版面回复,支持BBcode
require('config.php');
require('public.php');
require('inc/db.class.php');

if(!islogin()) error_quit3("您还没有登录！$message[4] $message[6]"); //登录后才能享受此项服务。
if(!$thread_id || !isset($brd_id)) error_quit3('非法操作');


if(!isset($submit) || $title == '' || $text == '')
{
 if(!$post_id) error_quit3('非法操作');

 $res = $db->query_first("SELECT title,author,text FROM post WHERE post_id = '$post_id'");

 $str = "回复文章: <a href='board_read.php?brd_id=$brd_id&post_id=$post_id&thread_id=$thread_id'>$res[title]</a>";

 html_head("回复文章: ".$res[title]);
 if(!eregi('^Re:',$res[title])) $res[title] = "Re:".$res[title];
 $res[text] = quote_code($res[text],1);

 print "
 <br>
 <table width=98% bgcolor=#000000 align=center cellPadding=3 cellSpacing=1 height=95%>
 <tr height=20 bgcolor=$message[2]><td align=left> $str </td></tr>
 <tr><form action=$PHP_SELF method=post><td align=left width=100% valign=top background='images/bg1.gif'> 
 文章标题: <input type=text size=50 maxlength=80 class=input name=title value='$res[title]'><br><br>
 文章内容: (不支持HTML, $message[6])<br>
 <textarea name=text cols=70 rows=14 class=input WRAP=virtual>
【在 $res[author] 的大作中提到:】
$res[text]</textarea><Br>
 <input type=checkbox name=use_sign value='1' checked>使用签名档
 <input type=hidden name=thread_id value='$thread_id'>
 <input type=hidden name=brd_id value='$brd_id'>
 <input type=submit name=submit value='提交' class=button> <input type=reset value='重来' class=button>
 </td></form></tr>
 <tr bgcolor=$message[2] height=18><td align=center>$message[1] $message[0]</td></tr>
 </table>";
}
else
{
  if(($use_sign == 1) && !empty($m_user[sign])) {
   	$sign = "\n\n--\n".$m_user[sign];
   	$text .= $sign;
 }
 $text .= "\n--"; //多加一个回车
 $fromip = getenv("REMOTE_ADDR");
 $now = date("Y-m-d H:i:s");

 $db->query("INSERT INTO post (title, author, brd_id, date, thread_id, text, fromip) VALUES ('$title', '$m_user[user_name]', '$brd_id', '$now', '$thread_id', '$text', '$fromip')");
 $m_user[numpost] ++;
 $db->query("UPDATE user SET numpost=numpost+1 WHERE user_id = '$m_user[user_id]'");

 html_head("回复成功");
 print "<br><br><br>
 <div class=okmsg>回复成功</div> 请刷新列表后就能看到您的文章了。
 <p align=center> $message[0] </p>
 <script language=javascript>
	setTimeout('window.close()', 1000);
	opener.document.location.reload();
 </script>";
}
print "</body></html>";
$db->close();
exit;
?>