<?php
//����ظ�,֧��BBcode
require('config.php');
require('public.php');
require('inc/db.class.php');

if(!islogin()) error_quit3("����û�е�¼��$message[4] $message[6]"); //��¼��������ܴ������
if(!$thread_id || !isset($brd_id)) error_quit3('�Ƿ�����');


if(!isset($submit) || $title == '' || $text == '')
{
 if(!$post_id) error_quit3('�Ƿ�����');

 $res = $db->query_first("SELECT title,author,text FROM post WHERE post_id = '$post_id'");

 $str = "�ظ�����: <a href='board_read.php?brd_id=$brd_id&post_id=$post_id&thread_id=$thread_id'>$res[title]</a>";

 html_head("�ظ�����: ".$res[title]);
 if(!eregi('^Re:',$res[title])) $res[title] = "Re:".$res[title];
 $res[text] = quote_code($res[text],1);

 print "
 <br>
 <table width=98% bgcolor=#000000 align=center cellPadding=3 cellSpacing=1 height=95%>
 <tr height=20 bgcolor=$message[2]><td align=left> $str </td></tr>
 <tr><form action=$PHP_SELF method=post><td align=left width=100% valign=top background='images/bg1.gif'> 
 ���±���: <input type=text size=50 maxlength=80 class=input name=title value='$res[title]'><br><br>
 ��������: (��֧��HTML, $message[6])<br>
 <textarea name=text cols=70 rows=14 class=input WRAP=virtual>
���� $res[author] �Ĵ������ᵽ:��
$res[text]</textarea><Br>
 <input type=checkbox name=use_sign value='1' checked>ʹ��ǩ����
 <input type=hidden name=thread_id value='$thread_id'>
 <input type=hidden name=brd_id value='$brd_id'>
 <input type=submit name=submit value='�ύ' class=button> <input type=reset value='����' class=button>
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
 $text .= "\n--"; //���һ���س�
 $fromip = getenv("REMOTE_ADDR");
 $now = date("Y-m-d H:i:s");

 $db->query("INSERT INTO post (title, author, brd_id, date, thread_id, text, fromip) VALUES ('$title', '$m_user[user_name]', '$brd_id', '$now', '$thread_id', '$text', '$fromip')");
 $m_user[numpost] ++;
 $db->query("UPDATE user SET numpost=numpost+1 WHERE user_id = '$m_user[user_id]'");

 html_head("�ظ��ɹ�");
 print "<br><br><br>
 <div class=okmsg>�ظ��ɹ�</div> ��ˢ���б����ܿ������������ˡ�
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