<?php
//添加商品,addproduct.php
session_start();
//检查是否是管理员
if (!$_SESSION["manager"]) {
	header('WWW-Authenticate: Basic realm="My Realm"');
	header('HTTP/1.0 401 Unauthorized');
	echo '你没有登录或者您不是管理员!';
	exit();
}
?>
<html>
<head>
	<title>添加商品</title>
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
		<td colspan="2" bgcolor="#6666FF">您现在的位置:商品管理模式&nbsp;-&nbsp;添加商品</td>
		<td width="2">&nbsp;</td>
	</tr>
	<?php
	//连接数据库,选择商品类别信息
	$conn = mysql_connect("localhost", "root", "");
	mysql_select_db("phpbook_ch18");
	$query = "select categoryid, categoryname from categories";
	$result = mysql_query($query);	
	
	$i = 1;
	$category[0] = 0;
	while ($obj = mysql_fetch_object($result)) {
		$category[$i] = $obj;
		$category[0] ++ ;
		$i ++;
	}
	//断开数据库
	mysql_close($conn);
	//添加类别
	?>
	<tr>
		<td width="100">&nbsp;</td>
		<td width="468">
			<table border="1" cellpadding="0" cellspacing="2" width="100%" 
				bordercolorlight="#ff3333" bordercolordark="#ff9999" bgcolor="ccffcc" class="css1">
				<tr>
					<td>
						<form action="manageoperate.php" method="POST">
							<p>添加商品信息</p>
							<p><input type="hidden" name="tags" value="2">
							输入商品名称:
							<input type="text" name="productname">
							<br>
							输入商品的种类:
							<select name="category" size="1">
							<?php
							for ($i = 1 ; $i <= $category[0]; $i ++)
							{
								echo "<option value='".$category[$i]->categoryid."'>".
								$category[$i]->categoryname."</option>";
							}
							?>
							</select>
							<br>
							输入商品的价格:
							<input type="text" name="price">元
							<br>
							输入商品的颜色:
							<input type="text" name="color">
							<br>
							输入商品的尺寸:
							<input type="text" name="size">
							<br>
							<br>
							上传商品的图片:
							<input type="file" name="pic">
							</p>
							<P>输入商品的细节:</P>
							<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<textarea name="detail" rows="6"></textarea>
							<input type="submit" name="submit" value="添加商品信息">
							</p>
						</form>
					</td>
				</tr>
			</table>
		</td>
		<td width="192">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="3" bgcolor="#0000ff">&nbsp;</td>
	</tr>
</table>
</body>
</html>	