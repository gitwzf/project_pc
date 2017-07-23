<?php
//添加歌手
require_once('../config.php');
require_once('../public.php');
require_once('../inc/db.class.php');
if(!is_admin())  error_quit3("错误，您不是管理员或还没有登录！");
function error_return($msg) {
	echo "<script language=javascript> alert('".$msg."'); window.history.go(-1);</script>";
	exit;
}
html_head("添加歌手");
if(isset($submit)) {
	if($alpha < 'a' || $alpah > 'z') error_return('首字母有误,请重写');
	if($singer_name == '') error_return('歌手姓名必须填写!');
	if($imgurl == 'http://') $imgurl = '';
	$check = $db->query_first("SELECT count(*) FROM singer WHERE singer_name = '$singer_name'");
	if($check[0] > 0) {
		error_return('歌手已经存在!');
	}
	$db->query("INSERT INTO singer (alpha, singer_name, imgurl, introduce, area_id, cate_id) VALUES ('$alpha', '$singer_name', '$imgurl', '$introduce', '$area_id', '$cate_id')");
	echo "<p><span class=okmsg>添加成功!</span> <a href=main.php>按此返回</a></p>";
}else{
	$cate = $db->query("SELECT * FROM cate");
	$area = $db->query("SELECT * FROM area");
	echo "<h3>添加歌手</h3>";
	echo "<form action=$PHP_SELF method=post>拼音首字： <select name=alpha size=1>";
	$a = "a";
	for(;;){
		$a = strval($a);
		echo "<option value='$a'>$a</option>\n";
		if($a == "z") break;
		$a++;
	}
	echo "</select> (请选择，即为歌手的拼音首字母，方便排序) <br>歌手姓名： <input type=text name=singer_name maxlength=40 size=15><br>所属类别： <select name=cate_id size=1> ";
	while($tmp = $db->fetch_array($cate))
		echo "<option value='$tmp[cate_id]'> $tmp[cate_name] </option>\n";
	echo "</select>(请选择)<br>所在地区	: <select name=area_id size=1>";
	while($tmp = $db->fetch_array($area))
		echo "<option value='$tmp[area_id]'> $tmp[area_name] </option>\n";
	echo "</select>(请选择)<br>头像链接： <input type=text name=imgurl maxlength=200 size=40 value='http://'>(没有请留空, 以<font color=red>http://</font>开头) <br>歌手简介：<textarea name=introduce rows=10 cols=45></textarea>(不支持HTML)<br><p align=left><input type=submit name=submit value='提交'><input type=reset value='重写'></p></form>";
}
echo "</body></html>";
$db->close();
exit;
?>