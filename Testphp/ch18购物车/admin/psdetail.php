<?php
//��ʾĳһ��Ʒ����ϸ��Ϣ,psdetail.php
$conn = mysql_connect("localhost", "root", "");
mysql_select_db("phpbook_ch18");
$query = "select * from product where productid=".$id;
$result = mysql_query($query);
$res = mysql_fetch_array($result);
mysql_close($conn);

//���ո�ʽ���
?>	
<html>
	<head>
		<title>��Ʒ��ϸ��Ϣ����</title>
		<meta http-equiv="content-type" content="text/html" charset="gb2312">
	</head>
	<body bgcolor="#FFFFFF">
		<table border="1" width="760" cellpadding="0" cellspacing="5">
			<tr align="center">
				<td width="121">��Ʒ���</td>
				<td width="616"><?php echo $res["productid"]?></td>
			</tr>
			<tr align="center">
				<td width="121">��Ʒ����</td>
				<td width="616"><?php echo $res["productname"]?></td>
			</tr>
			<tr align="center">
				<td width="121">��Ʒ����</td>
				<td width="616"><?php echo $res["category"]?></td>
			</tr>
			<tr align="center">
				<td width="121">��Ʒ�۸�</td>
				<td width="616"><?php echo $res["price"]?></td>
			</tr>
			<tr align="center">
				<td width="121">��Ʒ��ɫ</td>
				<td width="616"><?php echo $res["color"]?></td>
			</tr>
			<tr align="center">
				<td width="121">��Ʒ�ߴ�</td>
				<td width="616"><?php echo $res["size"]?></td>
			</tr>
			<tr align="center">
				<td width="121">��ƷͼƬ</td>
				<?php
					if (file_exists("pic/".$res["productid"].".jpg")) {
						echo '<td width="616"><img src="pic/'.$res["productid"].'.jpg"></td>';
					}else 
					{
						echo '<td width="616">û��ͼƬ��Ϣ</td>';
					}
				?>
			</tr>
			<tr align="center">
				<td width="121">��Ʒϸ��</td>
				<td width="616"><?php echo $res["detail"]?></td>
			</tr>
		</table>
	</body>
</html>