<?php
//��������, ordermanage.php
include_once("orderfunc.php");
session_start();
//����Ƿ��ǹ���Ա
if (!$_SESSION["manager"]) {
	header('WWW-Authenticate: Basic realm="My Realm"');
	header('HTTP/1.0 401 Unauthorized');
	echo '��û�е�¼���������ǹ���Ա!';
	exit();
}
$order = getordercount();
?>
<html>
<head>
	<title>�û������������</title>
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
	<tr>
		<td width="290" height="60" >LOGO</td>
		<td width="468" valign="middle" align="left">��ӭ���ٹ����̨!</td>
		<td width="2">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#6666FF">�����ڵ�λ��:�û���������</td>
		<td width="2">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="3" height="13">
			<center>
				<table width="700" border="1" cellpadding="0" cellspacing="2" class="css1"
					bordercolorlight="#ccccff" bordercolordark="#6666ff">
					<tr>
						<td>��������</td>
					</tr>
					<?php
					for ($i = 1 ; $i <= $order[0] ; $i ++)
					{
						echo "<tr>";
						echo "<td><a href='fullmessage.php?orderid=".$order[$i]->orderid."' target='_blank'>"
							.$order[$i]->orderid."</a>(����鿴��ϸ��Ϣ)</td>";
						echo "</tr>";
					}
					?>
				</table>
			</center>
		</td>
	</tr>
	<tr>
		<td colspan="3" bgcolor="#0000FF">&nbsp;</td>
	</tr>
</table>
</body>
</html>