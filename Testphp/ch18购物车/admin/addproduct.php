<?php
//�����Ʒ,addproduct.php
session_start();
//����Ƿ��ǹ���Ա
if (!$_SESSION["manager"]) {
	header('WWW-Authenticate: Basic realm="My Realm"');
	header('HTTP/1.0 401 Unauthorized');
	echo '��û�е�¼���������ǹ���Ա!';
	exit();
}
?>
<html>
<head>
	<title>�����Ʒ</title>
	<meta http-equiv="content-type" content="text/html" charset="gb2312">
	<style>
	<!--
		.css1 {font-family:"����"; font-size:9pt ; font-style:normal ; line-height:normal;
		font-weight:normal; color:#000000}
		A:link {
			color:#ff3333 ; text-decoration:none
		}
		A:visited {
			color:#ff3333 ; text-decoration:none
		}
		A:active {
			text-decoration:none
		}
		A:hover {
			color:#aa0000; text-decoration:none
		}
	//-->
	</style>
</head>
<body bgcolor="#FFFFFF">
<table width="760" border="0" cellpadding="0" cellspacing="0" class="css1">
	<tr align="center">
		<td width="290" height="60" >LOGO</td>
		<td width="468" valign="middle" align="left">��ӭ���ٹ����̨!</td>
		<td width="2">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#6666FF">�����ڵ�λ��:��Ʒ����ģʽ&nbsp;-&nbsp;�����Ʒ</td>
		<td width="2">&nbsp;</td>
	</tr>
	<?php
	//�������ݿ�,ѡ����Ʒ�����Ϣ
	$conn = mysql_connect("localhost", "root", "");
	mysql_select_db("phpbook_ch18");
	$query = "select categoryid, categoryname from categories";
	$result = mysql_query($query);	
	
	$i = 1;
	$category[0] = 0;
	while ($obj = mysql_fetch_object($result)) {
		$category[$i] = $obj;
		$category[0] ++ ;
		$i ++;
	}
	//�Ͽ����ݿ�
	mysql_close($conn);
	//������
	?>
	<tr>
		<td width="100">&nbsp;</td>
		<td width="468">
			<table border="1" cellpadding="0" cellspacing="2" width="100%" 
				bordercolorlight="#ff3333" bordercolordark="#ff9999" bgcolor="ccffcc" class="css1">
				<tr>
					<td>
						<form action="manageoperate.php" method="POST">
							<p>�����Ʒ��Ϣ</p>
							<p><input type="hidden" name="tags" value="2">
							������Ʒ����:
							<input type="text" name="productname">
							<br>
							������Ʒ������:
							<select name="category" size="1">
							<?php
							for ($i = 1 ; $i <= $category[0]; $i ++)
							{
								echo "<option value='".$category[$i]->categoryid."'>".
								$category[$i]->categoryname."</option>";
							}
							?>
							</select>
							<br>
							������Ʒ�ļ۸�:
							<input type="text" name="price">Ԫ
							<br>
							������Ʒ����ɫ:
							<input type="text" name="color">
							<br>
							������Ʒ�ĳߴ�:
							<input type="text" name="size">
							<br>
							<br>
							�ϴ���Ʒ��ͼƬ:
							<input type="file" name="pic">
							</p>
							<P>������Ʒ��ϸ��:</P>
							<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<textarea name="detail" rows="6"></textarea>
							<input type="submit" name="submit" value="�����Ʒ��Ϣ">
							</p>
						</form>
					</td>
				</tr>
			</table>
		</td>
		<td width="192">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="3" bgcolor="#0000ff">&nbsp;</td>
	</tr>
</table>
</body>
</html>	