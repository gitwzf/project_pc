<?php
//����ظ�,֧��BBcode
require('config.php');
require('public.php');
require('inc/db.class.php');

if(!islogin()) error_quit3("����û�е�¼��$message[4] $message[6]"); //��¼��������ܴ������
if(!isset($post_id)) error_quit3('�Ƿ�����');

if(!isset($submit) || $title == '' || $text == '')
{

 $res = $db->query_first("SELECT * FROM post WHERE post_id = $post_id");
 if(($res[author] != $m_user[user_name]) && !is_admin()) error_quit3("��û�в���Ȩ��!");

 $str = "�޸�����: ".$res[title]."&nbsp;&nbsp;&nbsp;[".$singer_name."������]";
 $res[text] = quote_code($res[text], 2);

 html_head("��������");

 print "
	 <br>
	 <table width=98% bgcolor=#000000 align=center cellPadding=3 cellSpacing=1 height=95%>
	 <tr height=18 bgcolor=$message[2]><td align=left> $str </td></tr>
	 <tr><form action=$PHP_SELF method=post><td align=left width=100% valign=top background='images/bg1.gif'>
	 ���±���: <input type=text size=50 maxlength=80 name=title value='$res[title]' class=input><br><br>
	 ��������: (��֧��HTML, $message[6])<br>
	 <textarea name=text cols=70 rows=14 class=input WRAP=virtual>$res[text]</textarea><Br>
	 <input type=hidden name=post_id value='$post_id'> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 <input type=submit name=submit value='�ύ' class=button> <input type=reset value='����' class=button>
	 </td></form></tr>
	 <tr bgcolor=$message[2] height=18><td align=center>$message[1] $message[0]</td></tr>
	 </table>
	 ";
}

else
{
	$text .= "\n\n--\n�� ".$m_user[user_name]."��".date("Y-m-d H:i:s")."����޸Ĺ�����\n--";
	$db->query("UPDATE post SET title = '$title', text = '$text' WHERE post_id = '$post_id'");

	html_head("�޸ĳɹ�");
	print "<br><br><br>
	<div class=okmsg align=center>�޸ĳɹ�</div>
		<p align=center>��ˢ�º���ܿ��������������ˡ�<br><br> $message[0] </p>
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