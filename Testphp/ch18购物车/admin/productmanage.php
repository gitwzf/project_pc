<?php
//商品管理,productmanage.php
include_once("pfuns.php");
session_start();
//检查是否是授权的管理者
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
	$query = "delete from product where productid=".$id;
	$result = mysql_query($query);	
	if ($result) {
		$message = "成功删除该商品!";
	}
	mysql_close($conn);
}
//下面开始页面代码部分
?>
<html>
<head>
	<title>商品管理界面</title>
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
		<td colspan="3" height="24">下面列出来产品表product数据库中的全部数据
	<?php
		//这儿开始为PHP脚本代码
		echo "<font color='red'>".$message."</font>";
		//检查需要的信息是否已经输入
		$startpage = $startpage ? $startpage : 1;
		$productcount = getrownumber();
		//每页显示10个用户
		$pagesize = 10;
		
		if ($startpage > $productcount /10)
		{
			$pagesize = thelastnumber($productcount);	
		}
		//得到返回值
		$msgs = getMessages(($startpage-1)*10, $pagesize);
	?><td>
	</tr>
	<tr>
		<td colspan="3" height="13">
			<table width="700" border="1" cellpadding="0" cellspacing="2" class="css1"
			bordercolorlight="#CCCCFF" bordercolordark="#6666FF">
				<tr align="center">
					<td width="95">商品名称</td>
					<td width="94" height="12">商品编号</td>
					<td width="96">商品价格</td>
					<td width="95">商品颜色</td>
					<td width="95">商品规格</td>
					<td width="95">商品详细信息</td>
					<td width="98">删除</td>
				</tr>
				<?php
				//按格式输出
				for ($count = 1 ; $count <= $msgs[0] ; $count ++)
				{
					echo "<tr align='center'>";
					echo "<td width='95'>".$msgs[$count]->productname."</td>";
					echo "<td width='94'>".$msgs[$count]->productid."</td>";
					echo "<td width='96'>".$msgs[$count]->price."</td>";
					echo "<td width='95'>".$msgs[$count]->color."</td>";
					echo "<td width='95'>".$msgs[$count]->size."</td>";
					echo "<td width='95'><a href='psdetail.php?id=".$msgs[$count]->productid."'
							target='_blank'>详细数据</a></td>";
					echo "<td width='98'><a href='productmanage.php?job=delete&id="
							.$msgs[$count]->productid."&startpage=".$startpage."'>删除商品</a></td>";
					echo "</tr>";
				}
				echo "</table>";
				echo "</td>";
				echo "</tr>";
				echo "<tr>";
				echo "<td colspan='3' height='13'>&nbsp;";
				//输出页面的选择超链接
				if($productcount >0)
				{
					echo "<p>";
					for ($i = 1; $i <= $productcount/10 +1 ; $i ++)
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
				//添加新商品
				echo "<br>";
				echo "<a href='addproduct.php'>";
				echo "&nbsp;&nbsp;&nbsp;&nbsp;>>>>添加新商品";
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