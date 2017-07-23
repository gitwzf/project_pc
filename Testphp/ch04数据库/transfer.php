<?php
//连接到数据库
$dbh = mysqli_connect("localhost:3308", "root", "12345", "phpbook") or die("不能连接到数据库");
//关闭自动提交
mysqli_autocommit($dbh, FALSE);
//转帐事务开始
if ($_POST['submit'] && is_numeric($_POST['amt'])) {
//增加金额到目标账户
$result = mysqli_query($dbh, "UPDATE accounts SET balance = balance + " . $_POST['amt'] . " WHERE id = " . $_POST['to']);
if ($result !== TRUE) {
mysqli_rollback($dbh); //如果出错，则回滚到开始状态
}
//从当前账户中减少金额
$result = mysqli_query($dbh, "UPDATE accounts SET balance = balance - " . $_POST['amt'] . " WHERE id = " . $_POST['from']);
if ($result !== TRUE) {
mysqli_rollback($dbh); //如果出错，则回滚到开始状态
}
//没有任何错误，则提交，完成一次事务操作
mysqli_commit($dbh);
}
//获取账户余额
//保存数据，用于页面显示部分
$result = mysqli_query($dbh, "SELECT * FROM accounts");
while ($row = mysqli_fetch_assoc($result)) {
$accounts[] = $row;
}
//关闭数据库连接
mysqli_close($dbh);
?>
<HTML>
<HEAD>
<TITLE> 事务处理用户转帐 </TITLE>
<META NAME="Generator" CONTENT="chengwei">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
</HEAD>

<BODY>
事务处理用户转帐<br>
<table width=500 border=0>
<form method='post' name='transfer'>
<tr>
	<td>
	转账<input type='text' name='amt' size='10' maxlength='10'>from
	<select name='saving'>
		<option value='1'>Saving #1</option>
		<option value='4'>Saving #2</option>
	</select>
	to
	<select name='current'>
		<option value='2'>Current #1</option>
		<option value='3'>Current #2</option>
	</select>
	<input type='submit' name='submit' value='提交'>
	</td>
</tr>
</form>
</table>
<br>
当前帐户资金：<br>
<?
//数据库查询操作
mysql_connect("localhost","root","");
mysql_select_db("phpbook");
$query = "SELECT * FROM accounts";
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