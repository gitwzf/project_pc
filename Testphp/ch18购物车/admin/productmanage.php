<?php
//��Ʒ����,productmanage.php
include_once("pfuns.php");
session_start();
//����Ƿ�����Ȩ�Ĺ�����
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
	$query = "delete from product where productid=".$id;
	$result = mysql_query($query);	
	if ($result) {
		$message = "�ɹ�ɾ������Ʒ!";
	}
	mysql_close($conn);
}
//���濪ʼҳ����벿��
?>
<html>
<head>
	<title>��Ʒ�������</title>
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
		<td colspan="3" height="24">�����г�����Ʒ��product���ݿ��е�ȫ������
	<?php
		//�����ʼΪPHP�ű�����
		echo "<font color='red'>".$message."</font>";
		//�����Ҫ����Ϣ�Ƿ��Ѿ�����
		$startpage = $startpage ? $startpage : 1;
		$productcount = getrownumber();
		//ÿҳ��ʾ10���û�
		$pagesize = 10;
		
		if ($startpage > $productcount /10)
		{
			$pagesize = thelastnumber($productcount);	
		}
		//�õ�����ֵ
		$msgs = getMessages(($startpage-1)*10, $pagesize);
	?><td>
	</tr>
	<tr>
		<td colspan="3" height="13">
			<table width="700" border="1" cellpadding="0" cellspacing="2" class="css1"
			bordercolorlight="#CCCCFF" bordercolordark="#6666FF">
				<tr align="center">
					<td width="95">��Ʒ����</td>
					<td width="94" height="12">��Ʒ���</td>
					<td width="96">��Ʒ�۸�</td>
					<td width="95">��Ʒ��ɫ</td>
					<td width="95">��Ʒ���</td>
					<td width="95">��Ʒ��ϸ��Ϣ</td>
					<td width="98">ɾ��</td>
				</tr>
				<?php
				//����ʽ���
				for ($count = 1 ; $count <= $msgs[0] ; $count ++)
				{
					echo "<tr align='center'>";
					echo "<td width='95'>".$msgs[$count]->productname."</td>";
					echo "<td width='94'>".$msgs[$count]->productid."</td>";
					echo "<td width='96'>".$msgs[$count]->price."</td>";
					echo "<td width='95'>".$msgs[$count]->color."</td>";
					echo "<td width='95'>".$msgs[$count]->size."</td>";
					echo "<td width='95'><a href='psdetail.php?id=".$msgs[$count]->productid."'
							target='_blank'>��ϸ����</a></td>";
					echo "<td width='98'><a href='productmanage.php?job=delete&id="
							.$msgs[$count]->productid."&startpage=".$startpage."'>ɾ����Ʒ</a></td>";
					echo "</tr>";
				}
				echo "</table>";
				echo "</td>";
				echo "</tr>";
				echo "<tr>";
				echo "<td colspan='3' height='13'>&nbsp;";
				//���ҳ���ѡ������
				if($productcount >0)
				{
					echo "<p>";
					for ($i = 1; $i <= $productcount/10 +1 ; $i ++)
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
				//�������Ʒ
				echo "<br>";
				echo "<a href='addproduct.php'>";
				echo "&nbsp;&nbsp;&nbsp;&nbsp;>>>>�������Ʒ";
				echo "</a>";
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