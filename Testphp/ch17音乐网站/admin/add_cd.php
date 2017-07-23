<?php
//添加专辑
require_once('../config.php');
require_once('../public.php');
require_once('../inc/db.class.php');
if(!is_admin())  error_quit3("错误，您不是管理员或还没有登录！");
function error_return($msg) {
	print "<script language=javascript> alert('".$msg."'); window.history.go(-1);</script>";
	exit;
}
html_head("添加专辑");
if(isset($step2)) { //执行添加的操作
	if($singer_id == '') error_return('系统错误，重来！');
	if($cd_name == '') error_return('专辑名称必须填写!');
	if($imgurl == 'http://') $imgurl = '';
	$add_date = date("Y-m-d H:i:s");
	$check = $db->query_first("SELECT count(*) FROM cd WHERE cd_name = '$cd_name'");
	if($check[0] > 0) {
		error_return('专辑已经存在!');
	}
	$db->query("INSERT INTO cd (cd_name, imgurl, introduce, lang, pub_date, add_date, singer_id) VALUES ('$cd_name', '$imgurl', '$introduce', '$lang', '$pub_date', '$add_date', '$singer_id')");
	$db->query("UPDATE singer set cd_num=cd_num+1 WHERE singer_id = '$singer_id'");
	echo "<p><span class=okmsg>添加成功!</span> <a href=main.php>按此返回</a></p>";
}
else if(isset($step1)){//填写相关的资料页面
	if(!$singer_name || !$singer_id) error_return("发回重审，使用方法错误！");
	$singer_name = urldecode($singer_name);
	echo "<h3>添加专辑(2) -- $singer_name -- 填写资料</h3> ";
	echo "<form action=$PHP_SELF method=post><input type=hidden name=step2 value='1'><input type=hidden name=singer_id value='$singer_id'>专辑名称： <input type=text name=cd_name maxlength=40 size=15> 	<br>语言种类： <input type=text name=lang size=15> (英文/中文/台语/越语 etc)<br>发行时间： <input type=text name=pub_date size=15> (如: 2006-12-23) <br>专辑封面： <input type=text name=imgurl maxlength=200 size=40 value='http://'>(没有请留空, 以<font color=red>http://</font>开头) <br>专辑简介：<textarea name=introduce rows=4 cols=45></textarea>(不支持HTML)<br><p align=left><input type=submit name=submit value='提交'><input type=reset value='重写'></p></form>";
}else { //选择歌手
	$sql = "SELECT singer_id,singer_name FROM singer ORDER BY alpha, singer_name";
	$singer = $db->query($sql);
	echo "<h3>添加专辑(1) -- 选择歌手</h3>";
	echo "目前有".$db->num_rows($singer)."位歌手,请点击以下现存歌手中的名字。(按字母顺序) <a href=add_singer.php>需要新增?</a><hr size=1 noshade>";
	$i = 1;
	while($tmp = $db->fetch_array($singer)){
		echo "<a href='$PHP_SELF?step1=1&singer_id=$tmp[singer_id]&singer_name=".urlencode($tmp[singer_name])."'>$tmp[singer_name]</a>";
		if($i == 10) { $i = 1; echo "<br>\n"; }
		else echo "、";
		$i ++;
	}
}
echo "</body></html>";
$db->close();
exit;
?>