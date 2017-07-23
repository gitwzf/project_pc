<?php
//user_mail.php 用户mail主程序.列出所有程序啦...
//如果传入mailId读单篇。

/*
CREATE TABLE mail(
 id int(11) DEFAULT '0' NOT NULL auto_increment, #mailId
 receiver varchar(15) NOT NULL, #收件人
 sender varchar(15) NOT NULL, #发件人
 title varchar(100) NOT NULL, #标题
 content text,                #内容
 fromip varchar(50) NOT NULL, #来源
 date datetime,		          #时间
 flag char(1) DEFAULT 'N',#阅读标记 N.没读过 ' '.读过了
 PRIMARY KEY (id)
 );
 */
require('config.php');
require('public.php');
require('inc/db.class.php');

 if(!islogin()) error_quit("您还没有登录！$message[4] $message[6]"); //登录后才能享受此项服务。

if(!$id && $do != 'post') //显示所有信件，分页显示
 {
  $usertab = "inc/havelogin.inc.php";
  
  html_head("我的悄悄话");
  include('inc/head.inc.php');
  now_pos('<a href=index.php>首页</a> 》我的悄悄话', '');
  html_head("我的悄悄话");
?>
<TABLE align=center border=0 cellPadding=0 cellSpacing=0 width=760 background='images/bg1.gif'>
<TR>
<TD width=160 valign=top align=left>
<!-- 左栏 -->
<? include('inc/left.inc.php');?>
</TD>
<TD width=22 height=100%></TD>
<TD width=578 valign=top align=left>
<!-- 主要部分 -->
 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 width=100%>
  <TR>
   <TD height=18 background='images/bg3.gif' valign=bottom>
   <font color="<? echo $message[14]; ?>">&nbsp;■</font>
   <font color=#FFFFFF> 我 的 悄 悄 话 </font>
   <font color="<? echo $message[14]; ?>">■</font>
   .................................................... My Private Messages
   </TD>
  </TR>
  <TR><TD width=100% valign=top>
  <!-- 开始显示 -->
   <TABLE width=100% border=1 cellSpacing=0 bordercolordark="#FFFFFF" bordercolorlight="<? echo $message[2];?>">

<?php

if(!isset($page) || ($page <= 0)) $page=1;
$total = $db->query_first("SELECT COUNT(id) FROM mail WHERE receiver = '$m_user[user_name]'");
$total = $total[0];

$start = ($page-1) * $perpage;
if($start > $total) $start = $total;

$burl = $PHP_SELF."?page=";
$string = "分页：";
require('set_page.php');
$string .= set_page($burl, $total, $page, $perpage);
$string .= "条悄悄话 &nbsp;&nbsp; <a href='".$PHP_SELF."?do=post' onclick='return Post(this.href)'>写悄悄话给别人</a>";

print "
	<TR bgcolor=$message[3] height=18><td colspan=5>$string</td></TR>";

$res = $db->query("SELECT id, sender, title, date, flag FROM mail WHERE receiver = '$m_user[user_name]' OR flag = '*' ORDER BY date DESC LIMIT $start, $perpage");

if($db->num_rows($res)<=0)
	print "<tr><td colspan=5><h3>本页您没有悄悄话</h3></td></tr>";

print "
    <TR bgcolor=$message[2] height=18 align=center>
	<td width=6%>标记</td><td align=left>标题</td><td width=16%>作者</td><td width=22%>时间</td><td width=12%>管理</th>
	</TR>
";

while($tmp=$db->fetch_array($res))
{
	if(strlen($tmp[title]) > 40) $tmp[title] = substr($tmp[title],0,40)."..."; //长标题只显示25个字

	if(!ereg('Re:',$tmp[title])) $tmp[title] = "◆ ".$tmp[title];//re:的非主题

	$admin_str = "<a href='".$PHP_SELF."?id=$tmp[id]&do=del'><font color=red>删除</font></a>";

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
<!-- 结束 -->  
  </TD></TR>
 </TABLE>
</TD>
</TR>
</TABLE>

<?php
include('inc/foot.inc.php');
} else {
 if($do == 'del') { //删除

  $chkmsg = $db->query_first("SELECT receiver, flag FROM mail WHERE id = '$id'");
  if(!is_admin() && ($chkmsg[receiver] != $m_user[user_name] || $chkmsg[flag] == '*')) error_quit("没有删除权限！");

  $db->query("DELETE FROM mail WHERE id = '$id'");
  $db->close();
  header("Location: $PHP_SELF");
  exit;

 } else if($do == 're') { //回复

	$chkmail = $db->query_first("SELECT COUNT(*) FROM mail WHERE receiver = '$m_user[user_name]'");
	if($chkmail[0] > $message[13]) error_quit3("您的悄悄话太多了，请稍加整理才能再写！", 0);

	$res = $db->query_first("SELECT * FROM mail WHERE id = '$id'");
	$res[content] = quote_code($res[content],1); //引用文字反编码

	$str = "回复信件: <a href='".$PHP_SELF."?id=$id'>$res[title]</a>";
	html_head("回复信件: ".$res[title]);
	if(!eregi('^Re:',$res[title])) $res[title] = "Re:".$res[title];
	print "<br>
	<table width=98% bgcolor='#000000' align=center cellPadding=3 cellSpacing=1 height=95%>
	<tr height=18 bgcolor=$message[2]><td align=left>$str</td></tr>
	<tr><form action=$PHP_SELF method=post><td align=left width=100% background='images/bg1.gif' valign=top> 
	标题: <input type=text size=50 maxlength=80 class=input name=title value='$res[title]'><br>
	收件: <input type=text size=20 maxlength=80 class=input name=receiver value='$res[sender]' readonly ='true'><br><br>
	内容: (不支持HTML, $message[6])<br>
	<textarea name=content cols=70 rows=14 class=input WRAP=virtual>
【在 $res[sender] 的大作中提到:】
$res[content]
	</textarea><Br>
	<input type=checkbox name=use_sign value='1' checked>使用签名档
	<input type=hidden name=do value='post'>
	<input type=submit name=submit value='提交' class=button onclick=this.blur()>
	<input type=reset value='重来' class=button onclick=this.blur()>
	</td></form></tr>
	<tr bgcolor=$message[2] height=18><td align=center>$message[1] $message[0]</td></tr>
	</table>
	</body></html>
	";
	$db->close();
	exit;
 }

 else if($do == 'post') { //发表..
 	$chkmail = $db->query_first("SELECT COUNT(*) FROM mail WHERE receiver = '$m_user[user_name]'");
	if($chkmail[0] > $message[13]) error_quit3("您的悄悄话太多了，请稍加整理才能再写！", 0);

	if($title == '' || $content == '' || $receiver == '') //有空值
	 {
		html_head("发送悄悄话-".$tmp[title]);
		$str = "写一条新悄悄话给<b>".$sender."</b>";
		print "<br>
		<table width=98% bgcolor='#000000' align=center cellPadding=3 cellSpacing=1 height='95%'>
		<tr height=18 bgcolor=$message[2]><td align=left>$str</td></tr>
		<tr><form action=$PHP_SELF method=post><td align=left width=100% valign=top background='images/bg1.gif'> 
		标题: <input type=text size=50 maxlength=80 class=input name=title value='$title'><br>
		收件: <input type=text size=20 maxlength=80 class=input name=receiver value='$receiver'><br><br>
		内容: (不支持HTML, $message[6])<br>
		<textarea name=content cols=70 rows=14 class=input WRAP=virtual>$content</textarea><Br>
		<input type=checkbox name=use_sign value='1' checked>使用签名档<bR>
		";
	
		if(is_admin()) 
		print "
			<input type=checkbox name=flag value='*'> 把此悄悄话做为系统公告？(不要乱用)
			";

		print "
		<input type=hidden name=do value='post'>
		<input type=submit name=submit value='提交' class=button onclick=this.blur()>
		<input type=reset value='重来' class=button onclick=this.blur()>
		</td></form></tr>
		<tr bgcolor=$message[3]><td align=center>$message[1] $message[0]</td></tr>
		</table>
		</body></html>";
		$db->close();
		exit;
	 } else {
		 $chkuser = $db->query_first("SELECT user_id FROM user WHERE user_name = '$receiver'");
		 if($chkuser[0] == '') error_quit3("本站无此用户 - ".$receiver, 0);
		 if(($use_sign == 1) && !empty($m_user[sign])) {
			 $sign = "\n\n--\n".$m_user[sign];
			 $content .= $sign;
		 }
		 $content .= "\n--"; //多加一个回车
		 $fromip = getenv("REMOTE_ADDR");
		 $now = date("Y-m-d H:i:s");
		 if(is_admin() && $flag != '') {
			 $flag = '*';
			 $receiver = 'AllUsers';
		 } else
			 $flag = 'N';
		 
		 $db->query("INSERT INTO mail (receiver, sender, title, content, fromip, date, flag) VALUES ('$receiver', '$m_user[user_name]', '$title', '$content', '$fromip', '$now', '$flag')");
		 
		 html_head("发送成功");
		 print "<br><Br>
			 <div class=okmsg>发送成功</div>
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
	 $res[content] = quote_code($res[content],0); //引用文字反编码
	 if($res[id] != $id) error_quit3("非法操作!");
	 
	 html_head("阅读悄悄话-".$tmp[title]);
	 
	 $str = "[<a href='".$PHP_SELF."?id=$id&do=re'>回复此悄悄话</a>] [<a href='javascript:window.close()'>关闭窗口</a>]";
	 
	 print "<br>
	 <table width=98% bgcolor=#000000 align=center cellPadding=3 cellSpacing=1 height=95%>
	 <tr height=18 bgcolor=$message[2]><td align=left> $str </td></tr>
	 <tr><td background='images/bg1.gif' valign=top>
       标题: $res[title] <br>
	   作者: <a href='user_query.php?user_name=".urlencode($res[author])."' onclick='return Query(this.href)'>$res[sender]</a> <br>
	   时间: $res[date]
	   <hr size=1 noshade>
	   $res[content] <br>
       ※来源: <a href=$site_addr>$site_name</a> [FROM: $res[fromip]]<br> 
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