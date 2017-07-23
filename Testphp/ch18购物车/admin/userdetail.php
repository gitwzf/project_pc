<?php
//用于显示用户的详细信息
$conn = mysql_connect("localhost", "root", "");
mysql_select_db("phpbook_ch18");
$query = "select * from user where userid=".$id;
$result = mysql_query($query);
$res = mysql_fetch_array($result);
mysql_close($conn);

//按照格式输出
?>	
<html>
	<head>
		<title>用户详细数据</title>
		<meta http-equiv="content-type" content="text/html" charset="gb2312">
	</head>
	<body bgcolor="#FFFFFF">
		<table border="1" width="760" cellpadding="0" cellspacing="5">
			<tr align="center">
				<td width="121">用户编号</td>
				<td width="616"><?php echo $res["userid"]?></td>
			</tr>
			<tr align="center">
				<td width="121">用户名称</td>
				<td width="616"><?php echo $res["username"]?></td>
			</tr>
			<tr align="center">
				<td width="121">密码提示问题</td>
				<td width="616"><?php echo $res["userpassq"]?></td>
			</tr>
			<tr align="center">
				<td width="121">密码提示问题答案</td>
				<td width="616"><?php echo $res["userpassa"]?></td>
			</tr>
			<tr align="center">
				<td width="121">帐号创建时间</td>
				<td width="616"><?php echo $res["createtime"]?></td>
			</tr>
			<tr align="center">
				<td width="121">用户Email</td>
				<td width="616"><?php echo $res["email"]?></td>
			</tr>
			<tr align="center">
				<td width="121">用户电话</td>
				<td width="616"><?php echo $res["usertel"]?></td>
			</tr>
			<tr align="center">
				<td width="121">用户住址</td>
				<td width="616"><?php echo $res["useraddr"]?></td>
			</tr>
			<tr align="center">
				<td width="121">用户积分</td>
				<td width="616"><?php echo $res["usercredit"]?></td>
			</tr>
			<tr align="center">
				<td width="121">用户上次访问时间</td>
				<td width="616"><?php echo $res["currentlogintime"]?></td>
			</tr>
			<tr align="center">
				<td width="121">用户访问本站次数</td>
				<td width="616"><?php echo $res["logincount"]?></td>
			</tr>
		</table>
	</body>
</html>
