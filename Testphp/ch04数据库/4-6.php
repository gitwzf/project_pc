<?php
//�����ݿ�Ĳ���
$connection = mysql_connect('localhost','root','');
mysql_select_db('phpbook');
$query = "SELECT * FROM friends";
$result = mysql_query($query,$connection);
//ȡ�ؽ�����ݣ�ʹ����mysql_fetch_array()
while($row = mysql_fetch_array($result)){
//���������ݣ����ֶ�����Ϊ�±������ʽ������
echo $row["name"]."<br>";
echo $row["address"]."<br>";
echo $row["email"]."<br><br>"; //��ͬ�� echo $row[2]. "<br><br>";
}
?>