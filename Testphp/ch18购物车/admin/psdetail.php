<?php
//显示某一商品的详细信息,psdetail.php
$conn = mysql_connect("localhost", "root", "");
mysql_select_db("phpbook_ch18");
$query = "select * from product where productid=".$id;
$result = mysql_query($query);
$res = mysql_fetch_array($result);
mysql_close($conn);

//按照格式输出
?>	
<html>
	<head>
		<title>产品详细信息数据</title>
		<meta http-equiv="content-type" content="text/html" charset="gb2312">
	</head>
	<body bgcolor="#FFFFFF">
		<table border="1" width="760" cellpadding="0" cellspacing="5">
			<tr align="center">
				<td width="121">商品编号</td>
				<td width="616"><?php echo $res["productid"]?></td>
			</tr>
			<tr align="center">
				<td width="121">商品名称</td>
				<td width="616"><?php echo $res["productname"]?></td>
			</tr>
			<tr align="center">
				<td width="121">商品种类</td>
				<td width="616"><?php echo $res["category"]?></td>
			</tr>
			<tr align="center">
				<td width="121">商品价格</td>
				<td width="616"><?php echo $res["price"]?></td>
			</tr>
			<tr align="center">
				<td width="121">商品颜色</td>
				<td width="616"><?php echo $res["color"]?></td>
			</tr>
			<tr align="center">
				<td width="121">商品尺寸</td>
				<td width="616"><?php echo $res["size"]?></td>
			</tr>
			<tr align="center">
				<td width="121">商品图片</td>
				<?php
					if (file_exists("pic/".$res["productid"].".jpg")) {
						echo '<td width="616"><img src="pic/'.$res["productid"].'.jpg"></td>';
					}else 
					{
						echo '<td width="616">没有图片信息</td>';
					}
				?>
			</tr>
			<tr align="center">
				<td width="121">商品细节</td>
				<td width="616"><?php echo $res["detail"]?></td>
			</tr>
		</table>
	</body>
</html>