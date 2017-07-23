<?php
//用户管理程序,usermanager.php
include_once("userfuncs.php");
session_start();
//检查是否是管理员
if (!$_SESSION["manager"]) {
	header('WWW-Authenticate: Basic realm="My Realm"');
	header('HTTP/1.0 401 Unauthorized');
	echo '你没有登录或者您不是管理员!';
	exit();
}

//如果参数为	delete,则执行删除用户的操作
if ($job == "delete") 
{
	$conn = mysql_connect("localhost", "root", "");
	mysql_select_db("phpbook_ch18");
	$query = "delete from user where userid=".$userid;
	$result = mysql_query($query);	
	if ($result) {
		$message = "成功删除该用户!";
	}
	mysql_close($conn);
}
//下面开始页面代码部分
?>
<html>
<head>
	<title>用户管理界面</title>
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
<body bgcolor="#FFFFFF">
<table width="760" border="0" cellpadding="0" cellspacing="0" class="css1">
	<tr align="center">
		<td width="290" height="60" >LOGO</td>
		<td width="468" valign="middle" align="left">欢迎光临管理后台!</td>
		<td width="2">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#6666FF">您现在的位置:用户管理模式</td>
		<td width="2">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="3" height="24">下面列出来用户表user数据库中的全部数据
	<?php
		//这儿开始为PHP脚本代码
		echo $message;
		//检查需要的信息是否已经输入
		$startpage = $startpage ? $startpage : 1;
		$usercount = getrownumber();
		//每页显示10个用户
		$pagesize = 10;
		
		if ($startpage > $usercount /10)
		{
			$pagesize = thelastnumber($usercount);	
		}
		//得到返回值
		$msgs = getMessages(($startpage-1)*10, $pagesize);
	?><td>
	</tr>
	<tr>
		<td colspan="3" height="13">
			<table width="760" border="1" cellpadding="0" cellspacing="0" class="css1"
			bordercolorlight="#CCCCFF" bordercolordark="#6666FF">
				<tr align="center">
					<td width="100">用户编号</td>
					<td width="100">用户名称</td>
					<td width="200">用户帐号创建时间</td>
					<td width="100">用户信用</td>
					<td width="100">详细数据</td>
					<td width="160">删除用户</td>
				</tr>
				<?php
				//按格式输出
				for ($count = 1 ; $count <= $msgs[0] ; $count ++)
				{
					echo "<tr align='center'>";
					echo "<td width='100'>".$msgs[$count]->userid."</td>";
					echo "<td width='200'>".$msgs[$count]->username."</td>";
					echo "<td width='200'>".$msgs[$count]->createtime."</td>";
					echo "<td width='100'>".$msgs[$count]->usercredit."</td>";
					echo "<td width='100'><a href='userdetail.php?id=".$msgs[$count]->userid."'
							target='_blank'>详细数据</a></td>";
					echo "<td width='200'><a href='usermanager.php?job=delete&id="
							.$msgs[$count]->userid."&startpage=".$startpage."'>删除用户</a></td>";
					echo "</tr>";
				}
				echo "</table>";
				echo "</td>";
				echo "</tr>";
				echo "<tr>";
				echo "<td colspan='3' height='16'>&nbsp;";
				//输出页面的选择超链接
				if($usercount >0)
				{
					echo "<p>";
					for ($i = 1; $i <= $usercount/10 +1 ; $i ++)
					{
						echo "&nbsp;&nbsp;&nbsp;&nbsp;<a href='usermanager.php?startpage=".$i."'>第";
						echo $i;
						echo "页</a>";
					}
					echo "</p>";
				}
				echo "</td>";
				echo "</tr>";
				echo "<tr>";
				echo "<td colspan='3' height='13'>";
				//输出用户总数
				echo "<p>";
				echo "共有用户".$usercount."位!";
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