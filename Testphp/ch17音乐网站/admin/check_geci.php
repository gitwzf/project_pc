<?php 
//审歌词,带审查的保存在tmpgeci表中
require_once('../config.php');
require_once('../public.php');
require_once('../inc/db.class.php');
if(!is_admin()) error_quit3("错误，您不是管理员或还没有登录！");
//错误时通过js返回
function error_return($msg) {
	echo "<script language=javascript> alert('".$msg."'); window.history.go(-1);</script>";
	exit;
}
if(isset($step2)) { //提交请求
	if(!$id || !$song_id) error_return('系统错误，重来！');
	if($geci == '') error_return('歌词必须填写!');
	if($do != 'del' && $do != 'add') error_return('系统错误，重来！');
	$check = $db->query_first("SELECT song_id FROM geci WHERE song_id = '$song_id'");
	if(($do != 'del') && ($song_id == $check[0])) {
		$do = 'del';
		echo "<p> Warning: 歌词已经存在啦，删掉处理！</p>\n";
	}
	if($do == 'add'){
		$geci = addslashes($geci);
		$db->query("INSERT INTO geci VALUES('$song_id', '$zuoqu', '$tianci', '$tigong', '$geci')");
		$db->query("UPDATE user SET numpost=numpost+1 WHERE user_name = '$tigong'"); //加经验
		$db->query("UPDATE song SET geci = '1' WHERE song_id = '$song_id'");
	}
	$db->query("DELETE FROM tmpgeci WHERE id = '$id'");
	$db->close();
	@header("Location: $PHP_SELF");
	echo "<a href='$PHP_SELF'> 返回 </a>";
	exit;
}else if(isset($step1)) { //歌词审查
	html_head("歌词审查");
	if($id == '') error_return('系统错误，重来！');
	$tmp = $db->query_first("SELECT * FROM tmpgeci WHERE id = '$id'");
	echo "<h3>歌词审核(2)</h3> ";
	echo "<p>$tmp[tigong] 提供的 $tmp[song_name] 的歌词如下: </p>";
	echo "<form method=post action='".$_SERVER['PHP_SELF']."'>
   	<input type=hidden name=id value='$tmp[id]'>
   	<input type=hidden name=song_id value='$tmp[song_id]'>
   	<input type=hidden name=tigong value='$tmp[tigong]'>
   	作曲: <input type=text value='$tmp[zuoqu]' size=15> 作词: <input type=text value='$tmp[tianci]' size=15> <br><br>
   歌词: <textarea cols=55 rows=12 name=geci>$tmp[geci]</textarea> <br>
   操作: <input type=radio name=do value='add' checked>通过 <input type=radio name=do value='del'>删除
   <input type=submit name=step2 value='确定'>
   <input type=reset  value='重来'>
   </form>";
}else { //选择歌手
	html_head("歌词审查");
	$tmpgeci = $db->query("SELECT id, tigong, song_name FROM tmpgeci");
	echo "<h3>歌词审核(1)</h3> ";
	echo "目前有".$db->num_rows($tmpgeci)."篇, 请点击以下现存歌词临时档进行处理。<hr size=1 noshade>";
	while($tmp = $db->fetch_array($tmpgeci)) {
		echo "· <a href='$PHP_SELF?step1=1&id=$tmp[id]'>$tmp[song_name]</a> (by $tmp[tigong]) <br>";
	}
}
echo "</body></html>";
$db->close();
exit;
?>