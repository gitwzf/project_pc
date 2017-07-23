<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>用户注册</title>
	</head>
	<body>
		<?php
			$strSql="";
			$database_username="root";
			$database_password="";
			$database_name = "phpbook_ch11";
			$link = mysql_connect("localhost", $database_username, $database_password)
        		or die("Could not connect: " . mysql_error());
			mysql_select_db($database_name, $link) or die ('Can\'t use $database_name : ' . mysql_error());
			$s_UserName = $_POST["UserName"];
			$strSql = "select * from users where username like '".$s_UserName."'";
			$result = mysql_query($strSql)
    				or die("Invalid query: " . mysql_error());
			if ($row = mysql_fetch_object($result)){
				echo ("<h1 align=center>用户名<font color=red>".$s_UserName."</font>已经存在！</h1>");
				echo ("<div align=center><input type=button name=btn value=返回 onClick='window.history.go(-1)'></div>");
			}else{
				//把新用户记录到数据库
				$s_UserPassword = $_POST["UserPassword"];
				$s_NickName = $_POST["NickName"];
				$s_Sex = $_POST["Sex"];
				$s_Email = $_POST["Email"];
				$strSql = "insert into users (username,userpassword,nickname,sex,email,regtime) values('".$s_UserName."','".$s_UserPassword."','".$s_NickName."','".$s_Sex."','".$s_Email."', now())";
				$res = mysql_query($strSql);	
				echo ("<h1 align=center>用户注册成功，您的注册信息如下</h1>");
				echo ("<p align=center>用户名：<font color=blue>".$s_UserName."</font></p>");
				echo ("<p align=center>密码：<font color=blue>".$s_UserPassword."</font></p>");
				echo ("<p align=center>昵称：<font color=blue>".$s_NickName."</font></p>");
				if($s_Sex == "0"){
					echo ("<p align=center>性别：<font color=blue>男</font></p>");
				}else{
					echo ("<p align=center>性别：<font color=blue>女</font></p>");
				}
				echo ("<p align=center>Email：<font color=blue>".$s_Email."</font></p>");
			}
		?>
	</body>
</html>