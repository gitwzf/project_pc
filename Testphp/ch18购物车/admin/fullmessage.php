<?php
//�鿴��������ϸ��Ϣ,fullmessage.php
session_start();
//����Ƿ��ǹ���Ա
if (!$_SESSION["manager"]) {
	header('WWW-Authenticate: Basic realm="My Realm"');
	header('HTTP/1.0 401 Unauthorized');
	echo '��û�е�¼���������ǹ���Ա!';
	exit();
}
//�������ݿ�,ȡ����Ӧ�����Ŷ�Ӧ��ȫ����Ϣ
$conn = mysql_connect("localhost", "root", "");
mysql_select_db("phpbook_ch18");
//�������Ϊ	job=delete,��ִ��ɾ���û��Ĳ���
if ($job == "delete") 
{
	$query = "delete from userorder where orderid=".$orderid;
	$result = mysql_query($query);
	if ($result) {
		header("Location:ordermanage.php");
		exit();
	}
}
//���job����ֵΪupdate�Ļ�,ִ�и��µĲ���
if ($job == "update") {
	$query = "update userorder set issend='".$issend."' where orderid=".$orderid;
	mysql_query($query);
}
$query = "select * from userorder where orderid=".$orderid;
$result = mysql_query($query);
$count = 1;
$message[0] = 0;
while ($obj = mysql_fetch_object($result)) {
	$message[$count] = $obj;
	$message[0] ++;
	$count ++ ;
}

//�ر����ݿ�����
mysql_close($conn);
//���濪ʼ���ҳ�����
?>
<html>
<head>
	<title>�û�������ϸ��Ϣ</title>
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
<table width="760" border="1" cellpadding="0" cellspacing="0" class="css1">
	<tr>
		<td width="290" height="60" >LOGO</td>
		<td width="468" valign="middle" align="left">��ӭ���ٹ����̨!</td>
		<td width="2">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#6666FF">�����ڵ�λ��:�û�������ϸ��Ϣ</td>
		<td width="2">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="3" height="13">
			<center>
				<form method="POST" action="">
				<?php
					echo "<p>��������:".$message[1]->orderid."</p>";
					if ($message[1]->userid ==0) 
					{
						$user = "��ע���û�!";
					}
					else 
					{
						$user = "ע���û�!";
					}
					echo "<p>�û�ID:".$message[1]->userid."(".$user.")</p>";
					echo "<p>��ϵ�绰:".$message[1]->tel."</p>";
					echo "<p>����ʱ��:".$message[1]->ordertime."</p>";
					echo "<p><a href='fullmessage.php?job=delete&orderid="
						.$message[1]->orderid."'>ɾ���ö���</a></p>";
					echo "<p>�Ƿ��Ѿ�����";
					if ($message[1]->issend =="n") 
					{
						echo "<select name='issend' size='1'>
								<option value='y'>��</option>
								<option value='n' selected>��</option>
							  </select>
							";
					}
					else 
					{
						echo "<select name='issend' size='1'>
								<option value='y' selected>��</option>
								<option value='n'>��</option>
							  </select>
							";
					}
					echo "</p>
						<table width='760' border='1' cellpadding='0' cellspacing='2' class='css1'
						bordercolorlight='#ccccff' bordercolordark='#6666ff'>
							<tr align='center'>
								<td width='140'>��Ʒ����</td>
								<td width='140'>��ƷID</td>
								<td width='140'>��Ʒ�۸�</td>
								<td width='140'>��Ʒ����</td>
								<td width='140'>�ܼ�</td>
							</tr>";
					$total = 0 ;
					for ($i = 1 ; $i <= $message[0]; $i ++)
					{
						echo "<tr align='center'>";
						echo "<td width='140'>".$message[$i]->productname."</td>";
						echo "<td width='140'>".$message[$i]->productid."</td>";
						echo "<td width='140'>".$message[$i]->price."</td>";
						echo "<td width='140'>".$message[$i]->quantity."</td>";
						echo "<td width='140'>".$message[$i]->sum."</td>";
						$total += $message[$i]->sum;
						echo "</tr>";
					}
					echo "</table>";
					echo "<p>�ܼ�:".$total."Ԫ</p>";
					echo "<input type='hidden' name='job' value='update'>";
					echo "<input type='hidden' name='orderid' value='".$message[1]->orderid."'>";
					echo "<input type='submit' name='submit' value='����'>";
				?>
				</form>
			</center>
		</td>
	</tr>
	<tr>
		<td colspan="3" bgcolor="#0000FF">&nbsp;</td>
	</tr>
</table>
</body>
</html>