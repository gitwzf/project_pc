<?php
//ɾ�������ղ�
require_once('config.php');
require_once('public.php');
if($type != 'cd' && $type != 'song')  error_quit3("�Ƿ�������");
if(!islogin()) error_quit3("����û�е�¼��$message[4] $message[6]"); //��¼��֤
if($id == 'all' && !isset($confirm)) {
	html_head("���Ҫ����ղؼ���");
	echo "<br><br>\n"
	."<font color=red size=+1>���棺����������Լ����ղظ���/ר����ȷ�Ϻ��޷���أ�<br>��ȷ��ô��</font>\n"
	."<input type=button value='����' onclick=\"window.history.back()\">\n"
	."<input type=button value='ȷ��' onclick=\"window.location='".$PHP_SELF."?type=".$type."&id=all&confirm=1'\">\n"."</body></html>\n";
	exit;
} else {
	require('inc/db.class.php');
}
if($id == 'all'){//�������
	if($type == 'cd'){//��ר��
		$m_user[favorite_cd] = '';
		$db->query("UPDATE user SET favorite_cd = '$m_user[favorite_cd]' WHERE user_id = '$m_user[user_id]'");
	}else{//�����
		$m_user[favorite_song] = '';
		$db->query("UPDATE user SET favorite_song = '$m_user[favorite_song]' WHERE user_id = '$m_user[user_id]'");
	}
}else{//ɾ������
	if($type == 'cd'){//ɾר��
		$str = '\|'.$id.'\|';
		$m_user[favorite_cd] = eregi_replace($str, '|', $m_user[favorite_cd]);
		$db->query("UPDATE user SET favorite_cd = '$m_user[favorite_cd]' WHERE user_id = '$m_user[user_id]'");
	}else {//ɾ����
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