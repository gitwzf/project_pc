<?php
function usershow($userid){
	if(isset($userid)){
		$conn = mysql_connect("localhost","root","");
		mysql_select_db("phpbook_ch18") or die("���ӵ����ݿ�ʧ��!");
		$sql = "select username, logincount from user where userid=".$userid ;
		$result = mysql_query($sql);
		$res = mysql_fetch_array($result);
		mysql_close($conn);
		$username = $res[0];
		$prepage = $PHP_SELF;
		$message[0] = $username;
		$message[1] = ",�������".$res[1]."�η��ʸ���վ.";
		$message[2] = "<a href='change.php?uid=$userid'>�޸ĸ�����Ϣ</a>&nbsp;";
		$message[3] = "<a href='showuserdetail.php?uid=$userid'>�鿴������Ϣ</a>&nbsp;";
		$message[4] = "<a href='showproduct.php?uid=$userid'>��ʼѡ����Ʒ</a>&nbsp;";
		$message[5] = "<a href='showall.php?uid=$userid'>�ҵĹ��ﳵ</a>&nbsp;";
		$message[6] = "<a href='loginout.php'>ע��</a>";
	}else{
		//�û�δ��¼�����
		$prepage = $PHP_SELF;
		$errmsg = $_GET['errmsg'];
		if(isset($errmsg))
			$message[0] = $errmsg;
		else 
			$message[0] = "";
		$message[1] = "<form method='post' name='login' action='login.php'>�û��� <input type='text' name='username' size='12'>";
		$message[2] = "����&nbsp;&nbsp; <input type='password' name='password' size='12'>";
		$message[3] = "<input type='submit' name='submit' value='��¼��վ'>";
		$message[4] = "<a href='hint.php' target='_blank'>������ʾ</a>&nbsp;&nbsp;";
		$message[5] = "<a href='register.php?message=new'>ע�����û�</a></form>";
	}
	return $message;
}
//��ʾҳ������
?>
<html>
<head>
	<title>�û�����</title>
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
<body bgcolor="#FFFFFF" class="css1">
<?php
$msg = usershow($userid);
for ($i=0; $i < sizeof($msg); $i ++){
	echo $msg[$i];
}
?>
</body>
</html>