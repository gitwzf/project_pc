<?php
$connection = mysql_connect("localhost","root","");
mysql_select_db("phpbook");
$query = "SELECT * FROM friends";
$result = mysql_query($query);
echo "�����ݿ��л�ȡȫ�����ݵ�����<br>";
//��ý���еļ�¼��
$rows = mysql_num_rows($result);
//��ý���е��ֶ���
$fields = mysql_num_fields($result);
echo "�ܹ���ѯ�� $rows �� ��$fields �ֶΣ�<br>";
//ɾ��һ����¼
$query = "DELETE FROM friends WHERE name ='����'";
mysql_query($query);
echo "ɾ��nameΪ�����һ����¼<br>";
//���ɾ�����е���Ŀ
$rows = mysql_affected_rows($connection);
echo $rows. "��¼��ɾ����";
?>