<?php
//������ʾ�û�����ϸ��Ϣ
$conn = mysql_connect("localhost", "root", "");
mysql_select_db("phpbook_ch18");
$query = "select * from user where userid=".$id;
$result = mysql_query($query);
$res = mysql_fetch_array($result);
mysql_close($conn);

//���ո�ʽ���
?>	
<html>
	<head>
		<title>�û���ϸ����</title>
		<meta http-equiv="content-type" content="text/html" charset="gb2312">
	</head>
	<body bgcolor="#FFFFFF">
		<table border="1" width="760" cellpadding="0" cellspacing="5">
			<tr align="center">
				<td width="121">�û����</td>
				<td width="616"><?php echo $res["userid"]?></td>
			</tr>
			<tr align="center">
				<td width="121">�û�����</td>
				<td width="616"><?php echo $res["username"]?></td>
			</tr>
			<tr align="center">
				<td width="121">������ʾ����</td>
				<td width="616"><?php echo $res["userpassq"]?></td>
			</tr>
			<tr align="center">
				<td width="121">������ʾ�����</td>
				<td width="616"><?php echo $res["userpassa"]?></td>
			</tr>
			<tr align="center">
				<td width="121">�ʺŴ���ʱ��</td>
				<td width="616"><?php echo $res["createtime"]?></td>
			</tr>
			<tr align="center">
				<td width="121">�û�Email</td>
				<td width="616"><?php echo $res["email"]?></td>
			</tr>
			<tr align="center">
				<td width="121">�û��绰</td>
				<td width="616"><?php echo $res["usertel"]?></td>
			</tr>
			<tr align="center">
				<td width="121">�û�סַ</td>
				<td width="616"><?php echo $res["useraddr"]?></td>
			</tr>
			<tr align="center">
				<td width="121">�û�����</td>
				<td width="616"><?php echo $res["usercredit"]?></td>
			</tr>
			<tr align="center">
				<td width="121">�û��ϴη���ʱ��</td>
				<td width="616"><?php echo $res["currentlogintime"]?></td>
			</tr>
			<tr align="center">
				<td width="121">�û����ʱ�վ����</td>
				<td width="616"><?php echo $res["logincount"]?></td>
			</tr>
		</table>
	</body>
</html>
