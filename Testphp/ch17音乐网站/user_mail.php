<?php
//user_mail.php �û�mail������.�г����г�����...
//�������mailId����ƪ��

/*
CREATE TABLE mail(
 id int(11) DEFAULT '0' NOT NULL auto_increment, #mailId
 receiver varchar(15) NOT NULL, #�ռ���
 sender varchar(15) NOT NULL, #������
 title varchar(100) NOT NULL, #����
 content text,                #����
 fromip varchar(50) NOT NULL, #��Դ
 date datetime,		          #ʱ��
 flag char(1) DEFAULT 'N',#�Ķ���� N.û���� ' '.������
 PRIMARY KEY (id)
 );
 */
require('config.php');
require('public.php');
require('inc/db.class.php');

 if(!islogin()) error_quit("����û�е�¼��$message[4] $message[6]"); //��¼��������ܴ������

if(!$id && $do != 'post') //��ʾ�����ż�����ҳ��ʾ
 {
  $usertab = "inc/havelogin.inc.php";
  
  html_head("�ҵ����Ļ�");
  include('inc/head.inc.php');
  now_pos('<a href=index.php>��ҳ</a> ���ҵ����Ļ�', '');
  html_head("�ҵ����Ļ�");
?>
<TABLE align=center border=0 cellPadding=0 cellSpacing=0 width=760 background='images/bg1.gif'>
<TR>
<TD width=160 valign=top align=left>
<!-- ���� -->
<? include('inc/left.inc.php');?>
</TD>
<TD width=22 height=100%></TD>
<TD width=578 valign=top align=left>
<!-- ��Ҫ���� -->
 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 width=100%>
  <TR>
   <TD height=18 background='images/bg3.gif' valign=bottom>
   <font color="<? echo $message[14]; ?>">&nbsp;��</font>
   <font color=#FFFFFF> �� �� �� �� �� </font>
   <font color="<? echo $message[14]; ?>">��</font>
   .................................................... My Private Messages
   </TD>
  </TR>
  <TR><TD width=100% valign=top>
  <!-- ��ʼ��ʾ -->
   <TABLE width=100% border=1 cellSpacing=0 bordercolordark="#FFFFFF" bordercolorlight="<? echo $message[2];?>">

<?php

if(!isset($page) || ($page <= 0)) $page=1;
$total = $db->query_first("SELECT COUNT(id) FROM mail WHERE receiver = '$m_user[user_name]'");
$total = $total[0];

$start = ($page-1) * $perpage;
if($start > $total) $start = $total;

$burl = $PHP_SELF."?page=";
$string = "��ҳ��";
require('set_page.php');
$string .= set_page($burl, $total, $page, $perpage);
$string .= "�����Ļ� &nbsp;&nbsp; <a href='".$PHP_SELF."?do=post' onclick='return Post(this.href)'>д���Ļ�������</a>";

print "
	<TR bgcolor=$message[3] height=18><td colspan=5>$string</td></TR>";

$res = $db->query("SELECT id, sender, title, date, flag FROM mail WHERE receiver = '$m_user[user_name]' OR flag = '*' ORDER BY date DESC LIMIT $start, $perpage");

if($db->num_rows($res)<=0)
	print "<tr><td colspan=5><h3>��ҳ��û�����Ļ�</h3></td></tr>";

print "
    <TR bgcolor=$message[2] height=18 align=center>
	<td width=6%>���</td><td align=left>����</td><td width=16%>����</td><td width=22%>ʱ��</td><td width=12%>����</th>
	</TR>
";

while($tmp=$db->fetch_array($res))
{
	if(strlen($tmp[title]) > 40) $tmp[title] = substr($tmp[title],0,40)."..."; //������ֻ��ʾ25����

	if(!ereg('Re:',$tmp[title])) $tmp[title] = "�� ".$tmp[title];//re:�ķ�����

	$admin_str = "<a href='".$PHP_SELF."?id=$tmp[id]&do=del'><font color=red>ɾ��</font></a>";

	print "
	<TR height=18 align=center>
		<td><font color=red>$tmp[flag]</font></td>
		<td align=left><a href='".$PHP_SELF."?id=$tmp[id]' onclick='return Post(this.href)'>$tmp[title]</a></td>
		<td><a href='user_query.php?user_name=".urlencode($tmp[sender])."' onclick='return Query(this.href)'>$tmp[sender]</a></td>
		<td>$tmp[date]</td>
		<td>$admin_str</td>
	</TR>
	";
}

print "
	<TR bgcolor=$message[2] height=18><td colspan=5> $string </td></TR>
";
?>
   </TABLE>
<!-- ���� -->  
  </TD></TR>
 </TABLE>
</TD>
</TR>
</TABLE>

<?php
include('inc/foot.inc.php');
} else {
 if($do == 'del') { //ɾ��

  $chkmsg = $db->query_first("SELECT receiver, flag FROM mail WHERE id = '$id'");
  if(!is_admin() && ($chkmsg[receiver] != $m_user[user_name] || $chkmsg[flag] == '*')) error_quit("û��ɾ��Ȩ�ޣ�");

  $db->query("DELETE FROM mail WHERE id = '$id'");
  $db->close();
  header("Location: $PHP_SELF");
  exit;

 } else if($do == 're') { //�ظ�

	$chkmail = $db->query_first("SELECT COUNT(*) FROM mail WHERE receiver = '$m_user[user_name]'");
	if($chkmail[0] > $message[13]) error_quit3("�������Ļ�̫���ˣ����Լ����������д��", 0);

	$res = $db->query_first("SELECT * FROM mail WHERE id = '$id'");
	$res[content] = quote_code($res[content],1); //�������ַ�����

	$str = "�ظ��ż�: <a href='".$PHP_SELF."?id=$id'>$res[title]</a>";
	html_head("�ظ��ż�: ".$res[title]);
	if(!eregi('^Re:',$res[title])) $res[title] = "Re:".$res[title];
	print "<br>
	<table width=98% bgcolor='#000000' align=center cellPadding=3 cellSpacing=1 height=95%>
	<tr height=18 bgcolor=$message[2]><td align=left>$str</td></tr>
	<tr><form action=$PHP_SELF method=post><td align=left width=100% background='images/bg1.gif' valign=top> 
	����: <input type=text size=50 maxlength=80 class=input name=title value='$res[title]'><br>
	�ռ�: <input type=text size=20 maxlength=80 class=input name=receiver value='$res[sender]' readonly ='true'><br><br>
	����: (��֧��HTML, $message[6])<br>
	<textarea name=content cols=70 rows=14 class=input WRAP=virtual>
���� $res[sender] �Ĵ������ᵽ:��
$res[content]
	</textarea><Br>
	<input type=checkbox name=use_sign value='1' checked>ʹ��ǩ����
	<input type=hidden name=do value='post'>
	<input type=submit name=submit value='�ύ' class=button onclick=this.blur()>
	<input type=reset value='����' class=button onclick=this.blur()>
	</td></form></tr>
	<tr bgcolor=$message[2] height=18><td align=center>$message[1] $message[0]</td></tr>
	</table>
	</body></html>
	";
	$db->close();
	exit;
 }

 else if($do == 'post') { //����..
 	$chkmail = $db->query_first("SELECT COUNT(*) FROM mail WHERE receiver = '$m_user[user_name]'");
	if($chkmail[0] > $message[13]) error_quit3("�������Ļ�̫���ˣ����Լ����������д��", 0);

	if($title == '' || $content == '' || $receiver == '') //�п�ֵ
	 {
		html_head("�������Ļ�-".$tmp[title]);
		$str = "дһ�������Ļ���<b>".$sender."</b>";
		print "<br>
		<table width=98% bgcolor='#000000' align=center cellPadding=3 cellSpacing=1 height='95%'>
		<tr height=18 bgcolor=$message[2]><td align=left>$str</td></tr>
		<tr><form action=$PHP_SELF method=post><td align=left width=100% valign=top background='images/bg1.gif'> 
		����: <input type=text size=50 maxlength=80 class=input name=title value='$title'><br>
		�ռ�: <input type=text size=20 maxlength=80 class=input name=receiver value='$receiver'><br><br>
		����: (��֧��HTML, $message[6])<br>
		<textarea name=content cols=70 rows=14 class=input WRAP=virtual>$content</textarea><Br>
		<input type=checkbox name=use_sign value='1' checked>ʹ��ǩ����<bR>
		";
	
		if(is_admin()) 
		print "
			<input type=checkbox name=flag value='*'> �Ѵ����Ļ���Ϊϵͳ���棿(��Ҫ����)
			";

		print "
		<input type=hidden name=do value='post'>
		<input type=submit name=submit value='�ύ' class=button onclick=this.blur()>
		<input type=reset value='����' class=button onclick=this.blur()>
		</td></form></tr>
		<tr bgcolor=$message[3]><td align=center>$message[1] $message[0]</td></tr>
		</table>
		</body></html>";
		$db->close();
		exit;
	 } else {
		 $chkuser = $db->query_first("SELECT user_id FROM user WHERE user_name = '$receiver'");
		 if($chkuser[0] == '') error_quit3("��վ�޴��û� - ".$receiver, 0);
		 if(($use_sign == 1) && !empty($m_user[sign])) {
			 $sign = "\n\n--\n".$m_user[sign];
			 $content .= $sign;
		 }
		 $content .= "\n--"; //���һ���س�
		 $fromip = getenv("REMOTE_ADDR");
		 $now = date("Y-m-d H:i:s");
		 if(is_admin() && $flag != '') {
			 $flag = '*';
			 $receiver = 'AllUsers';
		 } else
			 $flag = 'N';
		 
		 $db->query("INSERT INTO mail (receiver, sender, title, content, fromip, date, flag) VALUES ('$receiver', '$m_user[user_name]', '$title', '$content', '$fromip', '$now', '$flag')");
		 
		 html_head("���ͳɹ�");
		 print "<br><Br>
			 <div class=okmsg>���ͳɹ�</div>
			 <p align=center> $message[0] </p>
			 <script language=javascript>
			 setTimeout('window.close()', 500); opener.document.location.reload();
			</script>
			</body></html>
			";
		 $db->close();
		 exit;
	 }
 } else { 
	 $res = $db->query_first("SELECT * FROM mail WHERE id = '$id'");
	 $res[content] = bbcode($res[content]); 
	 $res[content] = quote_code($res[content],0); //�������ַ�����
	 if($res[id] != $id) error_quit3("�Ƿ�����!");
	 
	 html_head("�Ķ����Ļ�-".$tmp[title]);
	 
	 $str = "[<a href='".$PHP_SELF."?id=$id&do=re'>�ظ������Ļ�</a>] [<a href='javascript:window.close()'>�رմ���</a>]";
	 
	 print "<br>
	 <table width=98% bgcolor=#000000 align=center cellPadding=3 cellSpacing=1 height=95%>
	 <tr height=18 bgcolor=$message[2]><td align=left> $str </td></tr>
	 <tr><td background='images/bg1.gif' valign=top>
       ����: $res[title] <br>
	   ����: <a href='user_query.php?user_name=".urlencode($res[author])."' onclick='return Query(this.href)'>$res[sender]</a> <br>
	   ʱ��: $res[date]
	   <hr size=1 noshade>
	   $res[content] <br>
       ����Դ: <a href=$site_addr>$site_name</a> [FROM: $res[fromip]]<br> 
	   <hr size=1 noshade>
	</td></tr>
	<tr height=18 bgcolor=$message[2]><td align=left> $str </td></tr>
	</table>
		   ";

	if($res[flag] == 'N')
	 {
	   $db->query("UPDATE mail set flag='' WHERE id = '$id'");
	   print "
		<script language=javascript>
			opener.document.location.reload();
		</script>
		";
	  }
	print "
	</body></html>
	";

	$db->close();
	exit;
 }
}
?>