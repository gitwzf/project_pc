<?php
//����ѡ��
require('../config.php');
require('../public.php');
if(!is_admin())  error_quit3("���������ǹ���Ա��û�е�¼��");
{
	html_head("����ѡ��");
	echo "<h3>����˵�</h3>";
	echo "<a href='main.php' target='main'>�����˵�</a><br>";
	echo "<a href='add_singer.php' target='main'>��Ӹ���</a><br>";
	echo "<a href='add_cd.php' target='main'>���ר��</a><br>";
	echo "<a href='add_song.php' target='main'>��Ӹ���</a><br>";
	echo "<a href='check_geci.php' target='main'>��˸��</a><br>";
	echo "<a href='modify.php' target='main'>�޸�ɾ��</a><br>";
	echo "<a href='recommend_cd.php' target='main'>�Ƽ�ר��</a><br>";
	echo "<a href='db_sync.php' target='main'>��������</a><br>";
	echo "<a href='get_userpwd.php' target='main'>�һ�����</a><br>";
	echo "</body></html>";
}
exit;
?>