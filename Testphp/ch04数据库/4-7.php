<?php
//�����ݿ�Ĳ���
$connection = mysql_connect('localhost','root','');
mysql_select_db('phpbook');
$query = "SELECT * FROM friends";
$result = mysql_query($query,$connection);
//ȡ�ؽ�����ݣ�ʹ����mysql_fetch_object()
while($row = mysql_fetch_object($result)){
//�������е�һ������
echo $row->name."<br>";
echo $row->address."<br>";
echo $row->email."<br><br>";
}
?>