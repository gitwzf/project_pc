<?php
//favorite.php ����ղ��ҵ��

require('config.php');
require('public.php');
require('inc/db.class.php');

if(!islogin()) error_quit3("����û�е�¼��$message[4] $message[6]"); //��¼��������ܴ������
if($type != 'cd' && $type != 'song') error_quit3("�Ƿ�������");

if($type == 'cd')
{
	if(!$cd_id) error_quit3("�Ƿ�������");

	$cds = split('\|', $m_user[favorite_cd]);
	$num = count($cds) - 2;
	if($num > $message[8]) error_quit3("���ר����ѡ�Ѿ�������!");
	if($num < 1) $m_user[favorite_cd] .= '|';
	
	if(in_array($cd_id, $cds)) error_quit3("��ר�����Ѿ���¼��!");

 	$m_user[favorite_cd] .=  $cd_id.'|'; //����session
	$db->query("UPDATE user SET favorite_cd = '$m_user[favorite_cd]' WHERE user_id = '$m_user[user_id]'");

}
else
{
	if(!$song_id) error_quit3("�Ƿ�������");

	$ids = split(',', $song_id);
	$num1 = count($ids);

	$songs = split('\|', $m_user[favorite_song]);
	$num = count($songs) - 2;
	if($num > $message[8]) error_quit3("��ĸ�����ѡ�Ѿ�������!");
	if($num < 1) $m_user[favorite_song] .= '|';

	for($i=0; $i<$num1; $i++)
	{
	 if(in_array($ids[$i], $songs)) continue;
	 $m_user[favorite_song] .=  $ids[$i].'|'; //����session
	}
	 $db->query("UPDATE user SET favorite_song = '$m_user[favorite_song]' WHERE user_id = '$m_user[user_id]'");
}

html_head("�ҵ��");
print "<br><br><p align=center class=p3>��ȥ�ظ��ģ���ӳɹ���</p>";
print "<script language=Javascript>setTimeout('window.close()',1000)</script>";
print "</body></html>";

$db->close();
exit;
?>