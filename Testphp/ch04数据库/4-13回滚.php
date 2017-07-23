<?php
  	//连接数据库
  	$dbcon = mysqli_connect($host, $user, $pass, $db);
　	//首先，关闭自动提交
　	mysqli_autocommit($dbcon, FALSE);
　	//具体的数据库操作
  	$sql1 = "insert into User(id,name) values(NULL,'phpbook')";
　	$result = mysqli_query($dbcon, $sql1);
　	if ($result !== TRUE) {
　　		mysqli_rollback($dbcon); //如果出错，则回滚到执行前的状态
　	}
　	$sql2 = "insert into UserInfo(uid,realname,address) values(302,'PHP网络编程','北京市海淀区')";
　	$result = mysqli_query($dbcon, $sql2);
　	if ($result !== TRUE) {
　　		mysqli_rollback($dbcon); //如果出错，则回滚到执行前的状态
　	}
　	//如果都没有错误，则提交处理
　	mysqli_commit($dbcon);
　	//关闭数据库连接
　	mysqli_close($dbcon);
?>