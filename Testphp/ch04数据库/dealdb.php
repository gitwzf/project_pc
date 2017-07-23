<?
//连接到数据库服务器
$connection = mysql_connect("localhost","root","");
//建立一个数据库
$create_db = mysql_create_db("testdb",$connection);
if(!$create_db){
echo "不能创建数据库，请和管理员联系！<br>";
}else{
echo "数据库成功创建！<br>";
//选择一个数据库
$select_db = mysql_select_db("testdb",$connection);
if($select_db){
echo "成功选择数据库！<br>";
}else{
echo "连接数据库失败！<br>";
}
//删除一个数据库
$drop_db = mysql_drop_db("testdb",$connection);
if($drop_db){
echo	"成功删除数据库！<br>";
}else{
echo "删除数据库失败！<br>";
}
}
?>