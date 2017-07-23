<?php
//新用户注册函数
function  register(){
	//开始是两个变量的定义,实际上是两段HTML代码.为了以后程序的简洁,很有必要这么做.
	$str1 = "
		<p>使用者须知:</p>
		<p>请仔细阅读以下条款:</p>
		<p>-----------------</p>
		<p>----- 协议内容 ---</p>
		<p>-----------------</p>
		<p><a href='register.php?message=agree'>同意</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href='usershow.php'>不同意</a></p>";
	$str2 = "<form method='post' action='register.php'>
		<table width='95%' border='1' cellspacing='0' cellpadding='0' bordercolorlight='#00FFFF' bordercolordark='#0000CC'>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>请填写如下信息,这是必须填写的.</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>用户名只能由26个英文字母(区分大小写)和数字0-9以及下划线组成,最长20个字符.</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>请输入你的用户名:
					<input type='text' name='username' size='12' maxlength='20'>
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>请输入你的密码:
				<input type='password' name='passwd' size='12'>
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>请重复输入你的密码:
				<input type='password' name='repasswd' size='12'>
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>密码提示问题是用于当你忘掉密码的时的提示,你应该牢记密码提示信息.
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>密码提示问题:
				<input type='text' name='passwdq' size='30'>
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>当你输入正确的密码提示问题时,将会给出该答案,帮助你找回忘记的密码.
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>密码提示问题答案:
				<input type='text' name='passwda' size='30'>
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>请输入你的Email:
				<input type='text' name='email' size='30'>
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>电话是我们和您联系所必须的.
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>请输入您的电话号码:
				<input type='text' name='tel' size='12'>
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>地址是我们给您送货所必须的.
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td><font size='2'>请输入您的详细住址:
				<input type='text' name='addr' size='40'>
				</font></td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td>
					<p><font size='2'>您最希望在网上购买什么商品?</font></p>
					<p><font size='2'>第一:
						<input type='text' name='liking1' size='20'>
					</font></p>
					<p><font size='2'>第二:
						<input type='text' name='liking2' size='20'>
					</font></p>
					<p><font size='2'>第三:
						<input type='text' name='liking3' size='20'>
						<input type='hidden' name='message' value='reg'>
					</font></p>
				</td>
			</tr>
			<tr bgcolor='#99ccff'>
				<td>
					<p>
						<input type='submit' name='submit' value='提交'>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type='reset' name='submit2' value='清空'>
					</p>
				</td>
			</tr>
		</table>
	</form>
	";
	//HTML结束,请注意它们是以变量的形式存在的
	//根据message的值决定下一步如何处理
	$msg = $_GET["message"];
	if(!isset($msg)) $msg = $_POST["message"];

	if($msg == 'new'){
		echo $str1;
		exit();
	}elseif ($msg == 'agree'){
		echo $str2;
		exit();
	}elseif ($msg == 'reg'){
		//检查用户的输入是否符合要求
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
			header("Location:register.php?message=".urlencode("必填项不能为空!"));
			exit;
		}
		if($password != $repasswd){
			header("Location:register.php?message=".urlencode("前后输入的密码不一致,请重新输入!"));
			exit;
		}
		//检查数据库中是否有同名的用户存在
		$conn = mysql_connect("localhost","root","");
		mysql_select_db("phpbook_ch18") or die("不能选择数据库!");
		$sql = "select username from user where username='".$username."'";
		$result = mysql_query($sql);
		
		if(mysql_fetch_object($result)){
			mysql_close($conn);
			header("Location:register.php?message=".urlencode("该用户已经存在!"));
		}
		//用户数据各项检测均正确,则将用户数据插入数据库
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
			header("Location:register.php?message=".urlencode("非正常原因注册失败!请重试"));
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
//页面部分
?>
<html>
<head>
	<title>用户注册</title>
	<meta http-equiv="content-type" content="text/html" charset="gb2312">
	<style>
	<!--
		.css1 {font-family:"宋体"; font-size:9pt ; font-style:normal ; line-height:normal;
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