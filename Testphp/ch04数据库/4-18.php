<?php
//连接到数据库服务器，如果失败就终止脚本的向下执行
$connect = @mysql_connect('localhost','root','pass') or die('不能连接到数据库！');
//选择数据库，如果失败就终止脚本的向下执行
mysql_select_db('phpbook') or die('不能选择该数据库！');
$query = 'SELECT * FROM friends';
mysql_query($query);
if(mysql_errno()){
//如果请求执行有误，就给出一个提示信息
//输出错误的代号和文本表述
echo "执行出错了:".mysql_errno()."".mysql_error()."<br>";
}else{
//请求执行无误，给出一个提示
echo "没有任何错误，可以进行其他的操作！";
}
mysql_close($connect);
?>