<?php
//�û���ѯ user_query.php
//����user_name; ��user_nameΪ������ʾ���л�Ա.
require('config.php');
require('public.php');
require('inc/db.class.php');
require('set_page.php');

if(!$user_name) //��ʾ���л�Ա,��ҳ��ʾ, 15��/ҳ
 {
 
  html_head("��Ա�б�");
  include('inc/head.inc.php');
  now_pos('<a href=index.php>��ҳ</a> ����Ա�б�','');
  print "
  <div align=center><center>
  <table width=760 align=center border=1 cellSpacing=0 bordercolordark='#FFFFFF' bordercolorlight='$message[2]' background='images/bg1.gif'>
  ";

  if(!isset($page) || ($page <= 0)) $page=1;

  $total = $db->query_first("SELECT count(user_id) FROM user");
  $total = $total[0];

  $start = ($page-1) * $perpage;
  if($start > $total) $start = $total;

  $other_page = "��ҳ��";
  $other_page .= set_page($PHP_SELF."?page=", $total, $page, $perpage);
  $other_page .=" Users.&nbsp;&nbsp;������<input type=text class=input size=1 name=page maxlength=5>ҳ <input type=submit value='Go' class=button onclick=this.blur()>";

  print "
	  <TR bgcolor='$message[2]'>
		<TD height=18 colspan=5 background='images/bg3.gif' valign=bottom>
		 <font color='$message[14]'>&nbsp;��</font>
		 <font color=#FFFFFF> �� վ �� Ա </font>
		 <font color='$message[14]'>��</font>
		 .................................. ���ݻ��ָߵ����� .......................................Site Members
		</TD>
	  </TR>
	  <TR><form method=post action=$PHP_SELF><td bgcolor=$message[2] height=18 colspan=5>
	  $other_page
	  </td></form></tr>
	  ";

  $now = date(U);
  $result = $db->query("SELECT user_name,sex,face,province,numpost*2+numlogin+($now-firstlogin)/259200 as exp FROM user ORDER BY exp DESC, user_name LIMIT $start, $perpage");

  while($tmp = $db->fetch_array($result)) {
	$tmp[exp] = intval($tmp[exp]);
    if(in_array($tmp[user_name], $adminname))
		$flag = "<font color=red>��</font> ";
	else $flag = "";

    print "
	<tr><td width=20%>
	<a href='$PHP_SELF?user_name=".urlencode($tmp[user_name])."' onclick=\"window.open(this.href,'query','width=350,height=250,scrollbars=yes'); return false; \">
	<img src=\"images/face/".get_face($tmp[face], $tmp[sex]).".jpg\" border=0 align=left height=24 width=24>
	$tmp[user_name] $flag</a></td>
	<td width=15%>$tmp[sex]</td>
	<td width=20%>����$tmp[province] </td>
	<td width=20%>����ֵ <font color=red>$tmp[exp]</font></td>
	<td width=25%>".count_class($tmp[exp])."</td></tr>
	";
  }
  print "
	  <tr><form method=post action=$PHP_SELF><td bgcolor=$message[2] height=18 colspan=5>
	  $other_page
	  </td></form></tr>
	  </table></center></div>
	  ";
  include('inc/foot.inc.php');
 } else {
	 
	 $result = $db->query_first("SELECT user_name,email,sex,face,oicq,province,homepage,plan,firstlogin,lastlogin,numlogin,numpost,numlisten,lastfrom FROM user WHERE UPPER(user_name) = UPPER('$user_name')");
	 
	 if($result[user_name] == '') {
		  error_quit3("�ʺ�".$user_name."������!", 0);
	 }
 
	 $now = date(U);
	 if($result[firstlogin] <= 0) $result[firstlogin] = $now;
	 $exp = intval($result[numlogin]/* + $result[numlisten] */+ $result[numpost] * 2 + ($now - $result[firstlogin])/(3*86400));
	 
	 if(in_array($result[user_name], $adminname))
		 $flag = "<font color=red>��</font> ";
	 else $flag = "";
	 
	 html_head("��ѯ".$user_name);
	 
	 print "
		 <br>
		 <table width=95% align=center height=85% cellPadding=2 cellSpacing=1 bgcolor='#000000'>
         <tr height=18 bgcolor=$message[3]><td width=20%>����:</td><td width=30%>$flag $user_name</td>
			 <td width=20%>�Ա�:</td><td width=30%>$result[sex]</td></tr>
         <tr height=18 bgcolor=$message[3]><td>OICQ:</td><td>$result[oicq]</td><td>����:</td><td>$result[province]</td></tr>
         <tr height=18 bgcolor=$message[3]><td>Email:</td><td colspan=3><a href='mailto:$result[email]'>$result[email]</a></td></tr>
		 <tr height=18 bgcolor=$message[3]><td>��ҳ:</td>
			<td colspan=3><a href='$result[homepage]' target=_blank>$result[homepage]</a></td></tr>
		 <tr height=50><td colspan=4 background='images/bg1.gif'>
		 <img src=\"images/face/".get_face($result[face], $result[sex]).".jpg\" align=left>
		 ����վ<font color=red>$result[numlogin]</font>��,��������<font color=red>$result[numpost]</font>ƪ, ����<font color=red>$result[numlisten]</font>��, ӵ��<font color=red>$exp</font>�㾭��ֵ�������<font color=red>$result[lastlogin]</font>��<font color=red>$result[lastfrom]</font>����վһ��.</td></tr>
		 <tr><td colspan=4 background='images/bg1.gif' height=18><font color=red>!!!</font>
			 <a href='user_mail.php?do=post&receiver=$result[user_name]' onclick='return Post(this.href)'>���˸�<b>".$result[user_name]."</b>д�����Ļ�</a><font color=red>!!!</font></td></tr>
		 <tr><td colspan=4 valign=top background='images/bg1.gif'>[��Ƭ]<br><br>";
		 
    echo bbcode($result[plan]);
	print "
		</td></tr>
		</table>
		<div align=center> $message[1] $message[0] </div>
		</body></html>
	";
}

$db->close();
exit;
?>