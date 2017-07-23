<?php
//添加歌曲
require_once('../config.php');
require_once('../public.php');
require_once('../inc/db.class.php');
if(!is_admin())  error_quit3("错误，您不是管理员或还没有登录！");
function error_return($msg) {
	echo "<script language=javascript> alert('".$msg."'); window.history.go(-1);</script>";
	exit;
}
html_head("添加歌曲");
if(isset($step3)) { //最后
	if(!$singer_id || !$cd_id) error_return("发回重审，使用方法错误！");
	if($song_name == '') error_return('歌名必须填写!');
	if($rm_url == 'http://' || $rm_url =='') error_return('试听地址必须填写!');
	//mp3 url处理
	for($i=1; $i<6; $i++){
		$str = "mp3".$i;
		if($$str != 'http://' && $$str != '') $mp3_url .= $$str.'|';
	}
	$geci1 = 0;
	$song_id = $db->query_first("SELECT MAX(song_id) FROM song");
	$song_id = $song_id[0] + 1;
	if(!empty($geci)) {
		$geci1 = 1;
		$db->query("INSERT INTO geci VALUES('$song_id', '$zuoqu', '$tianci', '$tigong', '$geci')");
	}
	$db->query("INSERT INTO song (song_id, song_name, rm_url, mp3_url, geci, cd_id, singer_id) VALUES ('$song_id', '$song_name', '$rm_url', '$mp3_url', '$geci1', '$cd_id', '$singer_id')");
	$db->query("UPDATE cd set song_num=song_num+1 WHERE cd_id = '$cd_id'");
	$db->query("UPDATE singer set song_num=song_num+1 WHERE singer_id = '$singer_id'");
	echo "<p><span class=okmsg>添加成功!</span> <a href=main.php>按此返回</a></p>";
}else if(isset($step2)){ //显示添加页面
	if(!$singer_name || !$singer_id || !$cd_name || !$cd_id) error_return("发回重审，使用方法错误！");
	$singer_name = urldecode($singer_name);
	$cd_name = urldecode($cd_name);
	echo "<h3>添加新歌(3) -- $singer_name -- 填写资料</h3> ";
	echo "<h3>专辑: $cd_name </h3>";
	echo "<form action=$PHP_SELF method=post><input type=hidden name=step3 value='1'><input type=hidden name=singer_id value='$singer_id'><input type=hidden name=cd_id value='$cd_id'>歌曲名称： <input type=text name=song_name maxlength=40 size=25><br>试听地址： <input type=text name=rm_url size=35 value='http://'> (支持real/mp3格式,http://开头)<br>MP3下载1： <input type=text name=mp31 size=35 value='http://'> (http:// 开头喔) <br>MP3下载2： <input type=text name=mp32 size=35 value='http://'> (http:// 开头喔) <br>MP3下载3： <input type=text name=mp33 size=35 value='http://'> (http:// 开头喔) <br>MP3下载4： <input type=text name=mp34 size=35 value='http://'> (http:// 开头喔) <br>MP3下载5： <input type=text name=mp35 size=35 value='http://'> (http:// 开头喔) <br> 谱 曲 者：<input type=text name=zuoqu size=20> <br>作 词 者： <input type=text name=tianci size=20> <br> 歌词内容：<textarea name=geci rows=6 cols=45></textarea>(不支持HTML)<br><p align=left><input type=submit name=submit value='提交'><input type=reset value='重写'></p></form>";
}else if(isset($step1)){
	if(!$singer_name || !$singer_id) error_return("发回重审，使用方法错误！");
	$cd = $db->query("SELECT cd_id,cd_name FROM cd WHERE singer_id = '$singer_id' ORDER BY pub_date DESC");
	$singer_name = urldecode($singer_name); //url还原
	echo "<h3>添加新歌(2) -- $singer_name -- 选择专辑</h3> ";
	echo "目前有".$db->num_rows($cd)."个专辑,请点击以下现存专辑中的名字。(按发行时间排序) <a href='add_cd.php?singer_id=$singer_id&singer_name=".urlencode($singer_name)."'>需要新增?</a><hr size=1 noshade>";
	while($tmp = $db->fetch_array($cd)){
		echo "<a href='$PHP_SELF?step2=1&singer_id=$singer_id&singer_name=".urlencode($singer_name)."&cd_name=".urlencode($tmp[cd_name])."&cd_id=$tmp[cd_id]'>$tmp[cd_name]</a><br>";
	}
}else {//选择歌手
	$singer = $db->query("SELECT singer_id,singer_name FROM singer ORDER BY alpha");
	echo "<h3>添加新歌(1) -- 选择歌手</h3> ";
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