<?php
//�û��������,usermanager.php
include_once("userfuncs.php");
session_start();
//����Ƿ��ǹ���Ա
if (!$_SESSION["manager"]) {
	header('WWW-Authenticate: Basic realm="My Realm"');
	header('HTTP/1.0 401 Unauthorized');
	echo '��û�е�¼���������ǹ���Ա!';
	exit();
}

//�������Ϊ	delete,��ִ��ɾ���û��Ĳ���
if ($job == "delete") 
{
	$conn = mysql_connect("localhost", "root", "");
	mysql_select_db("phpbook_ch18");
	$query = "delete from user where userid=".$userid;
	$result = mysql_query($query);	
	if ($result) {
		$message = "�ɹ�ɾ�����û�!";
	}
	mysql_close($conn);
}
//���濪ʼҳ����벿��
?>
<html>
<head>
	<title>�û��������</title>
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
		<td colspan="2" bgcolor="#6666FF">�����ڵ�λ��:�û�����ģʽ</td>
		<td width="2">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="3" height="24">�����г����û���user���ݿ��е�ȫ������
	<?php
		//�����ʼΪPHP�ű�����
		echo $message;
		//�����Ҫ����Ϣ�Ƿ��Ѿ�����
		$startpage = $startpage ? $startpage : 1;
		$usercount = getrownumber();
		//ÿҳ��ʾ10���û�
		$pagesize = 10;
		
		if ($startpage > $usercount /10)
		{
			$pagesize = thelastnumber($usercount);	
		}
		//�õ�����ֵ
		$msgs = getMessages(($startpage-1)*10, $pagesize);
	?><td>
	</tr>
	<tr>
		<td colspan="3" height="13">
			<table width="760" border="1" cellpadding="0" cellspacing="0" class="css1"
			bordercolorlight="#CCCCFF" bordercolordark="#6666FF">
				<tr align="center">
					<td width="100">�û����</td>
					<td width="100">�û�����</td>
					<td width="200">�û��ʺŴ���ʱ��</td>
					<td width="100">�û�����</td>
					<td width="100">��ϸ����</td>
					<td width="160">ɾ���û�</td>
				</tr>
				<?php
				//����ʽ���
				for ($count = 1 ; $count <= $msgs[0] ; $count ++)
				{
					echo "<tr align='center'>";
					echo "<td width='100'>".$msgs[$count]->userid."</td>";
					echo "<td width='200'>".$msgs[$count]->username."</td>";
					echo "<td width='200'>".$msgs[$count]->createtime."</td>";
					echo "<td width='100'>".$msgs[$count]->usercredit."</td>";
					echo "<td width='100'><a href='userdetail.php?id=".$msgs[$count]->userid."'
							target='_blank'>��ϸ����</a></td>";
					echo "<td width='200'><a href='usermanager.php?job=delete&id="
							.$msgs[$count]->userid."&startpage=".$startpage."'>ɾ���û�</a></td>";
					echo "</tr>";
				}
				echo "</table>";
				echo "</td>";
				echo "</tr>";
				echo "<tr>";
				echo "<td colspan='3' height='16'>&nbsp;";
				//���ҳ���ѡ������
				if($usercount >0)
				{
					echo "<p>";
					for ($i = 1; $i <= $usercount/10 +1 ; $i ++)
					{
						echo "&nbsp;&nbsp;&nbsp;&nbsp;<a href='usermanager.php?startpage=".$i."'>��";
						echo $i;
						echo "ҳ</a>";
					}
					echo "</p>";
				}
				echo "</td>";
				echo "</tr>";
				echo "<tr>";
				echo "<td colspan='3' height='13'>";
				//����û�����
				echo "<p>";
				echo "�����û�".$usercount."λ!";
				echo "</p>";
				?>
				&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" bgcolor="#0000FF">&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>