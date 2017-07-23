<?php
//favorite.php 添加收藏我的最爱

require('config.php');
require('public.php');
require('inc/db.class.php');

if(!islogin()) error_quit3("您还没有登录！$message[4] $message[6]"); //登录后才能享受此项服务。
if($type != 'cd' && $type != 'song') error_quit3("非法操作！");

if($type == 'cd')
{
	if(!$cd_id) error_quit3("非法操作！");

	$cds = split('\|', $m_user[favorite_cd]);
	$num = count($cds) - 2;
	if($num > $message[8]) error_quit3("你的专辑精选已经满额了!");
	if($num < 1) $m_user[favorite_cd] .= '|';
	
	if(in_array($cd_id, $cds)) error_quit3("本专辑你已经收录过!");

 	$m_user[favorite_cd] .=  $cd_id.'|'; //更新session
	$db->query("UPDATE user SET favorite_cd = '$m_user[favorite_cd]' WHERE user_id = '$m_user[user_id]'");

}
else
{
	if(!$song_id) error_quit3("非法操作！");

	$ids = split(',', $song_id);
	$num1 = count($ids);

	$songs = split('\|', $m_user[favorite_song]);
	$num = count($songs) - 2;
	if($num > $message[8]) error_quit3("你的歌曲精选已经满额了!");
	if($num < 1) $m_user[favorite_song] .= '|';

	for($i=0; $i<$num1; $i++)
	{
	 if(in_array($ids[$i], $songs)) continue;
	 $m_user[favorite_song] .=  $ids[$i].'|'; //更新session
	}
	 $db->query("UPDATE user SET favorite_song = '$m_user[favorite_song]' WHERE user_id = '$m_user[user_id]'");
}

html_head("我的最爱");
print "<br><br><p align=center class=p3>除去重复的，添加成功！</p>";
print "<script language=Javascript>setTimeout('window.close()',1000)</script>";
print "</body></html>";

$db->close();
exit;
?>