<?php
$connection = mysql_connect('localhost','root','');
mysql_select_db('phpbook');
$query = "SELECT * FROM friends";
$result = mysql_query("$query",$connection);
//ȡ�ؽ�����ݣ�ʹ����mysql_fetch_row()
while($row = mysql_fetch_row($result)){
//���������ݣ����������±������ʷ��ؽ��
echo $row[0]."<br>";
echo $row[1]."<br>";
echo $row[2]."<br><br>";
}
?>