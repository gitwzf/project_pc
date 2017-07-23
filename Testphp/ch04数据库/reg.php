<?php
//连接到数据库
$dbconn = mysqli_connect("localhost", "root", "", "phpbook") or die("不能连接到数据库");
//关闭自动提交
mysqli_autocommit($dbconn, false);
//用户注册事务开始
$name = $_POST['name'];
$pwd = $_POST['pwd'];
$realname = $_POST['realname'];
$addr = $_POST['addr'];
$reg = $_POST['reg'];
if ($_POST['submit'] && isset($reg) && $reg =="reg") {
	//增加用户到bookuser表
	$sql1 = "insert into bookuser(id,name,pwd,regtime) values(NULL,'".$name."','".$pwd."', now())";
	$result = mysqli_query($dbconn, $sql1);
	if ($result !== true) {
		mysqli_rollback($dbconn); //如果出错，则回滚到开始状态
	}
	//获取刚注册用户的ID信息
	$conn = mysql_connect("localhost","root","");
	mysql_select_db("phpbook");
	$sql2 = "select id from bookuser where name='cheng'";
	$result = mysql_query($sql2);
	while($row = mysql_fetch_array($result)){
		$uid = $row[0];
	}
	//增加用户资料到userinfo表
	$sql3 = "insert into userinfo(uid,realname,address) values(".$uid.",'".$realname."','".$addr."')";
	$result = mysqli_query($dbconn, $sql3);
	
	if ($result !== true) {
		mysqli_rollback($dbconn); //如果出错，则回滚到开始状态
	}
	//增加用户信息到userdetail表
	$sql4 = "insert into userdetail(uid,money,exp) values(".$uid.",2000,500)";
	$result = mysqli_query($dbconn, $sql4);
	if ($result !== true) {
		mysqli_rollback($dbconn); //如果出错，则回滚到开始状态
	}
	//没有任何错误，则提交，完成一次事务操作
	mysqli_commit($dbconn);
}
//关闭数据库连接
mysqli_close($dbconn);
?>
<HTML>
<HEAD>
<TITLE> 事务处理用户注册示例 </TITLE>
<META NAME="Generator" CONTENT="chengwei">
<META NAME="Author" CONTENT="Cheng">
<META NAME="Keywords" CONTENT="事务处理用户注册示例">
<META NAME="Description" CONTENT="事务处理用户注册示例">
</HEAD>

<BODY>
事务处理用户注册示例<br>
<table width=500 border=0>
<form method='post' name='reg'>
<tr>
	<td>
	用户名称<input type='text' name='name' size='20' maxlength='20'><br>
	用户密码<input type='password' name='pwd' size='20' maxlength='20'><br>
	真实姓名<input type='text' name='realname' size='20' maxlength='20'><br>
	用户住址<input type='text' name='addr' size='20' maxlength='100'><br>
	<input type='hidden' name='reg' value='reg'><br>
	<input type='submit' name='submit' value='提交'>
	</td>
</tr>
</form>
</table>
<br>
当前已经注册的用户信息：<br>
<?php
//数据库查询操作
$conn = mysql_connect("localhost","root","");
mysql_select_db("phpbook");
$query = "SELECT * FROM bookuser";
$result = mysql_query($query);
echo "<table border='1'>";
while ($row = mysql_fetch_array($result, MYSQL_NUM)) {
    echo ("<tr><td>".$row[1]."</td><td>".$row[2]."</td></tr>");
}
echo "<table>";
mysql_free_result($result);
?>
</BODY>
</HTML>