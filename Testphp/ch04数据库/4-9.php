<?php
$connection = mysql_connect("localhost","root","");
mysql_select_db("phpbook",$connection);
$result=mysql_query('SELECT * FROM friends' , $connection);
//ʹ��whileѭ�����Ա��ȡ���м�¼���ֶγ���
while($row = mysql_fetch_array($result)){
//��ȡ��ǰ�еĸ��ֶγ���
$lengths = mysql_fetch_lengths($result);
echo"[".$row[id]."]";
echo$lengths[0]."";
echo$lengths[1]."";
echo$lengths[2]."";
echo$lengths[3]."<br>";
}
?>