<?php
//数据整理
require('../config.php');
require('../public.php');
require('../inc/db.class.php');
if(!is_admin()) error_quit3("错误，您不是管理员或还没有登录！");
//错误时借助js返回
function error_return($msg) {
	global $db;
	$db->close();
	print "<script language=javascript> alert('".$msg."'); window.history.go(-1);</script>";
	exit;
}
html_head("本站数据整理！");
echo "<br><h3>数据整理！</h3>\n";
//开始帐号清理
if($do == "acct_sync") {
	if(!$tl) $tl = 120;
	$res = $db->query("SELECT user_name FROM user WHERE (TO_DAYS(NOW()) - TO_DAYS(lastlogin)) > $tl");
	$tol = $db->num_rows();
	while($tmp = $db->fetch_array($res)) {
		$tmp[0] = addslashes($tmp[0]);
		$db->query("DELETE FROM mail WHERE '$tmp[0]' IN (sender, receiver)");
		$db->query("DELETE FROM ordersong WHERE '$tmp[0]' IN (sender, receiver)");
		$db->query("DELETE FROM user WHERE user_name = '$tmp[0]'");
	}
	echo "系统已清除超过<font color=red>".$tl."</font>天的帐号<font color=red>"
		.$tol."</font>个(注：未清除文章)！<br>\n<a href=".$PHP_SELF.">按此返回……</a>\n";
} else if($do == "acct_del") { //删除帐号
	if(!$tl) error_return("请输入帐号！");
	$chk = $db->query_first("SELECT user_id FROM user WHERE user_name = '$tl'");
	$chk = $chk[0];
	if(empty($chk)) error_return("错误的代号：".$tl);
	$db->query("DELETE FROM mail WHERE '$tl' IN (sender, receiver)");
	$db->query("DELETE FROM ordersong WHERE '$tl' IN (sender, receiver)");
	$db->query("DELETE FROM post WHERE author = '$tl'");
	$db->query("DELETE FROM user WHERE user_id = '$chk'");
	echo "系统已清除<font color=red>".$tl."</font>的所有文章、点歌记录、悄悄话！！<br>\n"
		."<a href=".$PHP_SELF.">按此返回……</a>\n";
} else if($do == "post_sync") { //讨论区整理
	$tl2 = intval($tl / 2);
	//公共信息
	$cnt2 = $db->query_first("SELECT COUNT(*) FROM post WHERE brd_id = 0 AND flag != 1 AND (TO_DAYS(NOW()) - TO_DAYS(date)) > $tl2");
	$cnt2 = $cnt2[0];
	$db->query("DELETE FROM post WHERE brd_id = 0 AND flag != 1 AND (TO_DAYS(NOW()) - TO_DAYS(date)) > $tl2");
	//其它信息
	$cnt = $db->query_first("SELECT COUNT(*) FROM post WHERE flag != 1 AND (TO_DAYS(NOW()) - TO_DAYS(date)) > $tl");
	$cnt = $cnt[0];
	$db->query("DELETE FROM post WHERE flag != 1 AND (TO_DAYS(NOW()) - TO_DAYS(date)) > $tl");
	echo "系统已清除<font color=red>".$tl."</font>天前的音乐评论<font color=red>".$cnt."</font>条；"
	."<font color=red>".$tl2."</font>天前的留言讨论<font color=red>".$cnt2."</font>条 ！！<br>\n"
	."<a href=".$PHP_SELF.">按此返回……</a>\n";
} else if($do == "mail_sync") { //整理悄悄话
	if(!$tl) $tl = 60;
	$cnt = $db->query_first("SELECT COUNT(*) FROM mail WHERE flag != '*' AND (TO_DAYS(NOW()) - TO_DAYS(date)) > $tl");
	$cnt = $cnt[0];
	$db->query("DELETE FROM mail WHERE flag != '*' AND (TO_DAYS(NOW()) - TO_DAYS(date)) > $tl");
	echo "系统已清除<font color=red>".$tl."</font>天前的个人悄悄话，此次共清理出<font color=red>".$cnt."</font>条记录！！<br>\n"."<a href=".$PHP_SELF.">按此返回……</a>\n";
} else if($do == "os_sync") { //点歌记录
	if(!$tl) $tl = 60;
	$cnt = $db->query_first("SELECT COUNT(*) FROM ordersong WHERE (TO_DAYS(NOW()) - TO_DAYS(date)) > $tl");
	$cnt = $cnt[0];
	$db->query("DELETE FROM ordersong WHERE (TO_DAYS(NOW()) - TO_DAYS(date)) > $tl");
		echo "系统已清除<font color=red>".$tl."</font>天前的点歌记录，此次共清理出<font color=red>".$cnt."</font>条记录！！<br>\n"	."<a href=".$_SERVER['PHP_SELF'].">按此返回……</a>\n";
} else if($do == "z_numli") { //听歌次数
	$db->query("UPDATE user SET numlisten = 0 WHERE user_name LIKE '$tl'");
	if($tl == '%%') $tl = "全部用户";
	echo "系统已将 <font color=red>".$tl."</font>的听歌次数清零。<br>\n"
		."<a href=".$_SERVER['PHP_SELF'].">按此返回……</a>\n";
} else if($do == "z_numlo") { //上站次数
	$db->query("UPDATE user SET numlogin = 0 WHERE user_name LIKE '$tl'");
	if($tl == '%%') $tl = "全部用户";
	echo "系统已将 <font color=red>".$tl."</font>的上站次数清零。<br>\n"
		."<a href=".$_SERVER['PHP_SELF'].">按此返回……</a>\n";
} else if($do == "z_numpo") { //发文次数
	$db->query("UPDATE user SET numpost = 0 WHERE user_name LIKE '$tl'");
	if($tl == '%%') $tl = "全部用户";
	echo "系统已将 <font color=red>".$tl."</font>的发文次数清零。<br>\n"
		."<a href=".$_SERVER['PHP_SELF'].">按此返回……</a>\n";
} else { //显示管理界面
print <<<__EOF__
<ol type="1">
	<li>
		<form action="$PHP_SELF" method="post">
		删除所有超过<input type="text" size="6" value="60" name="tl">天(默认为60天)的点歌记录！
		<input type="hidden" name="do" value="os_sync">
		<input type="submit" value="确认"> 
		</form>
	</li>
	<li>
		<form action="$PHP_SELF" method="post">
		删除所有超过<input type="text" size="6" value="60" name="tl">天(默认为60天)的用户悄悄话！
		<input type="hidden" name="do" value="mail_sync">
		<input type="submit" value="确认"> 
		</li>
		</form>
	<li>
		<form action="$PHP_SELF" method="post">
		删除所有超过<input type="text" size="6" value="120" name="tl">天(默认为120天, 公共讨论区取半数)的音乐评论！
		<input type="hidden" name="do" value="post_sync">
		<input type="submit" value="确认"> 
		</li>
		</form>
	<li>
		<form action="$PHP_SELF" method="post">
		删除所有超过<input type="text" size="6" value="120" name="tl">天(默认为120天)没有上过站的会员！
		<input type="hidden" name="do" value="acct_sync">
		<input type="submit" value="确认"> 
		</form>
	</li>
	<li>
		<form action="$PHP_SELF" method="post">
		删除违规帐号<input type="text" size="20"  name="tl"> (输入站内用户代号)！
		<input type="hidden" name="do" value="acct_del">
		<input type="submit" value="确认"> 
		</form>
	</li>
	<li>
		<form action="$PHP_SELF" method="post">
		听歌次数清零<input type="text" size="20"  name="tl"> (输入站内用户代号, 输入 %% 表示全部用户)！
		<input type="hidden" name="do" value="z_numli">
		<input type="submit" value="确认"> 
		</form>
	</li>
	<li>
		<form action="$PHP_SELF" method="post">
		发表次数清零<input type="text" size="20"  name="tl"> (输入站内用户代号, 输入 %% 表示全部用户)！
		<input type="hidden" name="do" value="z_numpo">
		<input type="submit" value="确认"> 
		</form>
	</li>
	<li>
		<form action="$PHP_SELF" method="post">
		上站次数清零<input type="text" size="20"  name="tl"> (输入站内用户代号, 输入 %% 表示全部用户)！
		<input type="hidden" name="do" value="z_numlo">
		<input type="submit" value="确认"> 
		</form>
	</li>
</ol>
__EOF__;
}
echo "</body></html>\n";
$db->close();
exit;
?>