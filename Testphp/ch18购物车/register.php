<?php
//���û�ע�ắ��
function  register(){
	//��ʼ�����������Ķ���,ʵ����������HTML����.Ϊ���Ժ����ļ��,���б�Ҫ��ô��.
	$str1 = "
		<p>ʹ������֪:</p>
		<p>����ϸ�Ķ���������:</p>
		<p>-----------------</p>
		<p>----- Э������ ---</p>
		<p>-----------------</p>
		<p><a href='register.php?message=agree'>ͬ��</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href='usershow.php'>��ͬ��</a></p>";
	$str2 = "<form method='post' action='register.php'>
		<table width='95%' border='1' cellspacing='0' cellpadding='0' bordercolorlight='#00FFFF' bordercolordark='#0000CC'>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>����д������Ϣ,���Ǳ�����д��.</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>�û���ֻ����26��Ӣ����ĸ(���ִ�Сд)������0-9�Լ��»������,�20���ַ�.</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>����������û���:
					<input type='text' name='username' size='12' maxlength='20'>
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>�������������:
				<input type='password' name='passwd' size='12'>
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>���ظ������������:
				<input type='password' name='repasswd' size='12'>
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>������ʾ���������ڵ������������ʱ����ʾ,��Ӧ���μ�������ʾ��Ϣ.
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>������ʾ����:
				<input type='text' name='passwdq' size='30'>
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>����������ȷ��������ʾ����ʱ,��������ô�,�������һ����ǵ�����.
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>������ʾ�����:
				<input type='text' name='passwda' size='30'>
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>���������Email:
				<input type='text' name='email' size='30'>
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>�绰�����Ǻ�����ϵ�������.
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>���������ĵ绰����:
				<input type='text' name='tel' size='12'>
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>��ַ�����Ǹ����ͻ��������.
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>������������ϸסַ:
				<input type='text' name='addr' size='40'>
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td>
					<p><font size='2'>����ϣ�������Ϲ���ʲô��Ʒ?</font></p>
					<p><font size='2'>��һ:
						<input type='text' name='liking1' size='20'>
					</font></p>
					<p><font size='2'>�ڶ�:
						<input type='text' name='liking2' size='20'>
					</font></p>
					<p><font size='2'>����:
						<input type='text' name='liking3' size='20'>
						<input type='hidden' name='message' value='reg'>
					</font></p>
				</td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td>
					<p>
						<input type='submit' name='submit' value='�ύ'>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type='reset' name='submit2' value='���'>
					</p>
				</td>
			</tr>
		</table>
	</form>
	";
	//HTML����,��ע���������Ա�������ʽ���ڵ�
	//����message��ֵ������һ����δ���
	$msg = $_GET["message"];
	if(!isset($msg)) $msg = $_POST["message"];

	if($msg == 'new'){
		echo $str1;
		exit();
	}elseif ($msg == 'agree'){
		echo $str2;
		exit();
	}elseif ($msg == 'reg'){
		//����û��������Ƿ����Ҫ��
		$username = $_POST["username"];
		$password = $_POST["passwd"];
		$repasswd = $_POST["repasswd"];
		$passwda = $_POST["passwda"];
		$passwdq = $_POST["passwdq"];
		$email = $_POST["email"];
		$tel = $_POST["tel"];
		$addr = $_POST["addr"];
		$liking1 = $_POST["liking1"];
		$liking2 = $_POST["liking2"];
		$liking3 = $_POST["liking3"];
		if($username =="" || $password == "" || $repasswd == "" 
			|| $passwdq == "" || $passwda == "" || $email == ""
			|| $tel == "" || $addr == ""){
			header("Location:register.php?message=".urlencode("�������Ϊ��!"));
			exit;
		}
		if($password != $repasswd){
			header("Location:register.php?message=".urlencode("ǰ����������벻һ��,����������!"));
			exit;
		}
		//������ݿ����Ƿ���ͬ�����û�����
		$conn = mysql_connect("localhost","root","");
		mysql_select_db("phpbook_ch18") or die("����ѡ�����ݿ�!");
		$sql = "select username from user where username='".$username."'";
		$result = mysql_query($sql);
		
		if(mysql_fetch_object($result)){
			mysql_close($conn);
			header("Location:register.php?message=".urlencode("���û��Ѿ�����!"));
		}
		//�û����ݸ��������ȷ,���û����ݲ������ݿ�
		$date = date("Y-m-d H:i:s",time());
		$sql = "INSERT INTO user (userid, username, userpass, userpassq"
				.", userpassa, email, usertel, useraddr, createtime, logintime, "
				."currentlogintime, logincount, liking1, liking2, liking3) "
				."VALUES (NULL, '".$username."', '".md5($password)
				."', '".$passwdq."', '".$passwda."', '".$email."', '".$tel
				."', '".$addr."', '".$date."', '".$date."', '".$date."', 1, '".$liking1
				."', '".$liking2."', '".$liking3."')";
		if(mysql_query($sql)){
			$sql2 = "select userid from user where username = '".$username."'";
			$result = mysql_query($sql2);
			$res = mysql_fetch_object($result);
			$userid = $res->userid;
			session_register($userid);
			$_SESSION['userid'] = $userid;
			$reuturnpage = "Location:login.php?username=".$username."&password=".$password;
			mysql_close($conn);
			header($reuturnpage);
		}else{
			mysql_close($conn);
			header("Location:register.php?message=".urlencode("������ԭ��ע��ʧ��!������"));
			exit;
		}
	}else{
		$msg = $_GET["message"];
		$errmsg = $_GET["errmsg"];
		echo $msg;
		echo "<br>".$errmsg;
		exit();
	}
}
//ҳ�沿��
?>
<html>
<head>
	<title>�û�ע��</title>
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
	register();
?>
</body>
</html>