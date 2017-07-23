<?php
function usershow($userid){
	if(isset($userid)){
		$conn = mysql_connect("localhost","root","");
		mysql_select_db("phpbook_ch18") or die("连接到数据库失败!");
		$sql = "select username, logincount from user where userid=".$userid ;
		$result = mysql_query($sql);
		$res = mysql_fetch_array($result);
		mysql_close($conn);
		$username = $res[0];
		$prepage = $PHP_SELF;
		$message[0] = $username;
		$message[1] = ",这是你第".$res[1]."次访问该网站.";
		$message[2] = "<a href='change.php?uid=$userid'>修改个人信息</a>&nbsp;";
		$message[3] = "<a href='showuserdetail.php?uid=$userid'>查看个人信息</a>&nbsp;";
		$message[4] = "<a href='showproduct.php?uid=$userid'>开始选购商品</a>&nbsp;";
		$message[5] = "<a href='showall.php?uid=$userid'>我的购物车</a>&nbsp;";
		$message[6] = "<a href='loginout.php'>注销</a>";
	}else{
		//用户未登录的情况
		$prepage = $PHP_SELF;
		$errmsg = $_GET['errmsg'];
		if(isset($errmsg))
			$message[0] = $errmsg;
		else 
			$message[0] = "";
		$message[1] = "<form method='post' name='login' action='login.php'>用户名 <input type='text' name='username' size='12'>";
		$message[2] = "密码&nbsp;&nbsp; <input type='password' name='password' size='12'>";
		$message[3] = "<input type='submit' name='submit' value='登录网站'>";
		$message[4] = "<a href='hint.php' target='_blank'>密码提示</a>&nbsp;&nbsp;";
		$message[5] = "<a href='register.php?message=new'>注册新用户</a></form>";
	}
	return $message;
}
//显示页面内容
?>
<html>
<head>
	<title>用户界面</title>
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
$msg = usershow($userid);
for ($i=0; $i < sizeof($msg); $i ++){
	echo $msg[$i];
}
?>
</body>
</html>