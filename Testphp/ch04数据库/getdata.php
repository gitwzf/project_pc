<?
//连接到数据库服务器，并选择数据库
$connection = mysql_connect( 'localhost' , 'root' , '');
mysql_select_db('phpbook');
//向数据库服务器发送一个SQL请求
$query = "SELECT * FROM friends";
$result = mysql_query($query,$connection);
//输出结果数据，使用mysql_result()
for($count = 0 ; $count < count(mysql_fetch_array($result)) ; $count++)
{
echo mysql_result($result , $count , "name")."<br>";
echo mysql_result($result,$count , "address")."<br>";
echo mysql_result($result , $count , "email")."<br><br>";
}
?>