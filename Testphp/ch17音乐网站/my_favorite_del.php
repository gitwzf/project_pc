<?php
//删除爱好收藏
require_once('config.php');
require_once('public.php');
if($type != 'cd' && $type != 'song')  error_quit3("非法操作！");
if(!islogin()) error_quit3("您还没有登录！$message[4] $message[6]"); //登录验证
if($id == 'all' && !isset($confirm)) {
	html_head("真的要清空收藏夹吗");
	echo "<br><br>\n"
	."<font color=red size=+1>警告：您即将清空自己的收藏歌曲/专辑，确认后将无法挽回！<br>您确认么？</font>\n"
	."<input type=button value='返回' onclick=\"window.history.back()\">\n"
	."<input type=button value='确认' onclick=\"window.location='".$PHP_SELF."?type=".$type."&id=all&confirm=1'\">\n"."</body></html>\n";
	exit;
} else {
	require('inc/db.class.php');
}
if($id == 'all'){//清空所有
	if($type == 'cd'){//清专辑
		$m_user[favorite_cd] = '';
		$db->query("UPDATE user SET favorite_cd = '$m_user[favorite_cd]' WHERE user_id = '$m_user[user_id]'");
	}else{//清歌曲
		$m_user[favorite_song] = '';
		$db->query("UPDATE user SET favorite_song = '$m_user[favorite_song]' WHERE user_id = '$m_user[user_id]'");
	}
}else{//删除个别
	if($type == 'cd'){//删专辑
		$str = '\|'.$id.'\|';
		$m_user[favorite_cd] = eregi_replace($str, '|', $m_user[favorite_cd]);
		$db->query("UPDATE user SET favorite_cd = '$m_user[favorite_cd]' WHERE user_id = '$m_user[user_id]'");
	}else {//删歌曲
		$ids = split(',', $id);
		$num = count($ids);
		for($i=0;$i<$num;$i++){
			$str = '\|'.$ids[$i].'\|';
			$m_user[favorite_song] = eregi_replace($str, '|', $m_user[favorite_song]);
		}
		$db->query("UPDATE user SET favorite_song = '$m_user[favorite_song]' WHERE user_id = '$m_user[user_id]'");
	}
}
$db->close();
$url = "my_favorite_".$type.".php";
header("Location: $url");
exit;
?>