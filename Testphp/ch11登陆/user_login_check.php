<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>�û���¼</title>
	</head>
	<body>
		<?php
			$strSql="";
			$database_username="root";
			$database_password="";
			$database_name = "phpbook_ch11";
			$s_UserName = $_POST["UserName"];
			$s_UserPassword = $_POST["UserPassword"];
			$strSql = "select * from users where UserName like '".$s_UserName."' and UserPassword like '".$s_UserPassword."'";
			//��ȡ���ݿ�����
			$link = mysql_connect("localhost", $database_username, $database_password)
        		or die("Could not connect: " . mysql_error());
			mysql_select_db($database_name, $link) or die ('Can\'t use $database_name : ' . mysql_error());
			$result = mysql_query($strSql);
			if($row = mysql_fetch_object($result)){
				echo ("<h1 align=center>��ӭ����<font color=red>".$s_UserName."</font></h1>");
				$_SESSION["UserID"] = $row->UserID;
			}else{
				echo ("<h1 align=center>��������û�������������</h1>");
				echo ("<div align=center><input type=button name=btn value=���� onClick='window.history.go(-1)'></div>");
			}
		?>
	</body>
</html>