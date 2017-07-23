<?php
//查看订单的详细信息,fullmessage.php
session_start();
//检查是否是管理员
if (!$_SESSION["manager"]) {
	header('WWW-Authenticate: Basic realm="My Realm"');
	header('HTTP/1.0 401 Unauthorized');
	echo '你没有登录或者您不是管理员!';
	exit();
}
//连接数据库,取得相应订单号对应的全部信息
$conn = mysql_connect("localhost", "root", "");
mysql_select_db("phpbook_ch18");
//如果参数为	job=delete,则执行删除用户的操作
if ($job == "delete") 
{
	$query = "delete from userorder where orderid=".$orderid;
	$result = mysql_query($query);
	if ($result) {
		header("Location:ordermanage.php");
		exit();
	}
}
//如果job参数值为update的话,执行更新的操作
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

//关闭数据库连接
mysql_close($conn);
//下面开始输出页面代码
?>
<html>
<head>
	<title>用户订单详细信息</title>
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
<table width="760" border="1" cellpadding="0" cellspacing="0" class="css1">
	<tr>
		<td width="290" height="60" >LOGO</td>
		<td width="468" valign="middle" align="left">欢迎光临管理后台!</td>
		<td width="2">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#6666FF">您现在的位置:用户订单详细信息</td>
		<td width="2">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="3" height="13">
			<center>
				<form method="POST" action="">
				<?php
					echo "<p>订单号码:".$message[1]->orderid."</p>";
					if ($message[1]->userid ==0) 
					{
						$user = "非注册用户!";
					}
					else 
					{
						$user = "注册用户!";
					}
					echo "<p>用户ID:".$message[1]->userid."(".$user.")</p>";
					echo "<p>联系电话:".$message[1]->tel."</p>";
					echo "<p>创建时间:".$message[1]->ordertime."</p>";
					echo "<p><a href='fullmessage.php?job=delete&orderid="
						.$message[1]->orderid."'>删除该订单</a></p>";
					echo "<p>是否已经发货";
					if ($message[1]->issend =="n") 
					{
						echo "<select name='issend' size='1'>
								<option value='y'>是</option>
								<option value='n' selected>否</option>
							  </select>
							";
					}
					else 
					{
						echo "<select name='issend' size='1'>
								<option value='y' selected>是</option>
								<option value='n'>否</option>
							  </select>
							";
					}
					echo "</p>
						<table width='760' border='1' cellpadding='0' cellspacing='2' class='css1'
						bordercolorlight='#ccccff' bordercolordark='#6666ff'>
							<tr align='center'>
								<td width='140'>商品名称</td>
								<td width='140'>商品ID</td>
								<td width='140'>商品价格</td>
								<td width='140'>商品数量</td>
								<td width='140'>总计</td>
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
					echo "<p>总计:".$total."元</p>";
					echo "<input type='hidden' name='job' value='update'>";
					echo "<input type='hidden' name='orderid' value='".$message[1]->orderid."'>";
					echo "<input type='submit' name='submit' value='更新'>";
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