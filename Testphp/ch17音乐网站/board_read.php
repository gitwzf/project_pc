<?php
//�����Ķ�board_id,֧��BBcode
require('config.php');
require('public.php');
require('inc/db.class.php');

if(!$post_id || !isset($brd_id)) error_quit3('�Ƿ�����');
if(!$thread_id) $thread_id = $post_id;

$res = $db->query_first("SELECT * FROM post WHERE post_id = '$post_id'");
$db->query("UPDATE post SET click=click+1 WHERE post_id = '$post_id'");
$other = $db->query("SELECT post_id,title,author FROM post WHERE thread_id = '$thread_id' OR post_id = '$thread_id' ORDER BY date DESC LIMIT 0, 10");//���10ƪ
$other_num = $db->num_rows($other);
if($other_num > 1) //ֻ��һƪ������
{
       $other_str = "
	   <Table cellSpacing=1 border=0 cellspadding=0 width=100% align=center>
	   <tr><td bgcolor=$message[3]>�������<font color=red>$other_num</font>ƪ</td></tr>"; 
       while($tmp = $db->fetch_array($other))
       {
          if(!eregi('^Re:',$tmp[title])) $tmp[title] = "�� ".$tmp[title];
        
          if($tmp[post_id] != $post_id) 
            $tmp[title] = "<a href=".$PHP_SELF."?brd_id=".$brd_id."&thread_id=".$thread_id."&post_id=".$tmp[post_id]."&singer_name=".urlencode($singer_name).">".$tmp[title]."</a>";
		  else
			$tmp[title] = "<font color=666666>".$tmp[title]."</font>";

          $other_str .=  "<tr bgcolor=$message[3]><td>$tmp[title] (<i>write by <a href='user_query.php?user_name=".urlencode($tmp[author])."' onclick='return Query(this.href)'>$tmp[author]</a></i>)</td></tr>"; 
       }
	   $other_str .= "</table>";
}

$res[text] = bbcode($res[text]); 
$res[text] = quote_code($res[text],0); //�������ַ�����

html_head("�Ķ�����: ".$res[title]);
$str = "[<a href='board_re.php?brd_id=$brd_id&thread_id=$thread_id&post_id=$post_id'>�ظ�����</a>]
        [<a href='board_add.php?brd_id=$brd_id&singer_name=".urlencode($singer_name)."'>��������</a>]
		";
if(($res[author] != $m_user[user_name]) && !is_admin()) 
	$str .= "[�޸�����]";
else
	$str .= "[<a href='board_edit.php?singer_name=".urlencode($singer_name)."&post_id=$post_id'>�޸�����</a>]\n";

$str .= "[�����ѱ��Ķ�<font color=red>$res[click]</font>��]";

if($res[flag] != 0) 
	$res[title] .= "\t<img src=images/pj/".$res[flag].".gif border=0>\n";

print "
<br>
<table width=98% bgcolor=#000000 align=center cellPadding=2 cellSpacing=1 height=95%>
<tr height=18 bgcolor=$message[2]><td align=left> $str </td></tr>
<tr bgcolor=$message[3]><td valign=top background='images/bg1.gif'>
����: $res[title] <br>
����: <a href='user_query.php?user_name=".urlencode($res[author])."' onclick='return Query(this.href)'>$res[author]</a> <br>
ʱ��: $res[date] &nbsp;&nbsp; ����: ".$singer_name."������
<hr size=1 noshade>
$res[text] <br>
����Դ: <a href=$site_addr>$site_name</a> [FROM: $res[fromip]]<br> 
</td></tr>
";

if($other_str) 
	print "<tr bgcolor=$message[2] height=1><td>
	$other_str
	</td></tr>\n";

print "
<tr height=18 bgcolor=$message[2]><td align=left> $str </td></tr>
<tr bgcolor=$message[3] height=18><td align=center>$message[1] $message[0]</td></tr>
</table>
</body></html>
";
$db->close();
exit;
?>