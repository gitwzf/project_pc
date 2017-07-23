<?php
//管理选单
require('../config.php');
require('../public.php');
if(!is_admin())  error_quit3("错误，您不是管理员或还没有登录！");
{
	html_head("管理选单");
	echo "<h3>管理菜单</h3>";
	echo "<a href='main.php' target='main'>回主菜单</a><br>";
	echo "<a href='add_singer.php' target='main'>添加歌手</a><br>";
	echo "<a href='add_cd.php' target='main'>添加专辑</a><br>";
	echo "<a href='add_song.php' target='main'>添加歌曲</a><br>";
	echo "<a href='check_geci.php' target='main'>审核歌词</a><br>";
	echo "<a href='modify.php' target='main'>修改删除</a><br>";
	echo "<a href='recommend_cd.php' target='main'>推荐专辑</a><br>";
	echo "<a href='db_sync.php' target='main'>数据整理</a><br>";
	echo "<a href='get_userpwd.php' target='main'>找回密码</a><br>";
	echo "</body></html>";
}
exit;
?>