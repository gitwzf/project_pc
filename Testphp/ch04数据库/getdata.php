<?
//���ӵ����ݿ����������ѡ�����ݿ�
$connection = mysql_connect( 'localhost' , 'root' , '');
mysql_select_db('phpbook');
//�����ݿ����������һ��SQL����
$query = "SELECT * FROM friends";
$result = mysql_query($query,$connection);
//���������ݣ�ʹ��mysql_result()
for($count = 0 ; $count < count(mysql_fetch_array($result)) ; $count++)
{
echo mysql_result($result , $count , "name")."<br>";
echo mysql_result($result,$count , "address")."<br>";
echo mysql_result($result , $count , "email")."<br><br>";
}
?>