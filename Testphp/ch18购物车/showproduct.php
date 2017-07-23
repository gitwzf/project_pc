<?php
//显示商品程序
include_once("cart.php"); //包含购物车类

//变量获取
$category = $_GET["category"];

//从数据库中获取商品信息
function getMessages($msgStart, $number,$category=""){
	$conn = mysql_connect("localhost", "root", "");
	mysql_select_db("phpbook_ch18");
	//从数据库中查找对应位置的商品
	if($category != "")
		$query = "select productid,productname,price,size,abstract from product
			where category='".$category."' limit ".$msgStart.",".$number;
	else 
		$query = "select productid,productname,price,size,abstract from product 
			limit ".$msgStart.",".$number;
	$result = mysql_query($query);
	
	//没有找到对应的商品
	if (!$result) {
		mysql_close($conn);
		$msgs[0] = 0;
		return $msgs;
	}
	//找到商品,开始处理,放入对象数组
	$count = 0;
	while ($obj = mysql_fetch_object($result)) {
		$msgs[$count] = $obj;
		$count ++;
	}
	mysql_close($conn);
	return $msgs;
}
//取得最后一页显示个数
function  thelastnumber($number){
	$lastnumber = $number % 6;
	return $lastnumber;
}

//获取数据库中所有符合条件的行数
function getrownumber($category = ""){
	$conn = mysql_connect("localhost","root","");
	mysql_select_db("phpbook_ch18");
	if ($category != "") {
		$query = "select count(*) from product where category = '".$category."'";
	}else{
		$query = "select count(*) from product";
	}
	
	$result = mysql_query($query);
	$number = mysql_fetch_array($result);
	mysql_close($conn);
	return $number[0];
}
//页面处理开始
//其他的相关操作

session_start();
//如果参数为添加商品,则执行相应的操作
if($job == "addproduct"){
	if(isset($cart)){
		$cart->add_item($id);
	}
	else {
		$cart = new Cart();
		$cart->add_item($id);
	}
	$_SESSION["cart"] = $cart;
	header("Location:showall.php?count=".$cart->count);
}
//确定页码
$page = $page ? $page : 1;
$productcount = getrownumber($category);
$pagesize = 6 ; //每页显示6个商品

//如果该页是最后一页,则显示该页的商品个数
if ($page > $productcount/6) {
	$pagesize = thelastnumber($productcount);
}
$allpages = ($productcount %6  > 0 ? intval($productcount/6+1) : intval($productcount/6));

//得到商品信息的返回值
$msgs = getMessages(($page-1)*6, $pagesize, $category);
echo "<html>\n";
echo "<head><title>商品展示</title></head>\n";
echo "<body>\n";
//按照格式输出商品内容
echo "<form method='post' action='showproduct.php'>\n
		<p><font size='2'>共有".($allpages)."页 现在是第".$page."页";
//如果该页不是第一页,则显示前一页的超链接
if ($page != 1) {
	echo "&nbsp;<a href='showproduct.php?page=".($page-1)."'>上一页</a>";			
}else 
	echo "&nbsp;上一页";
//如果该页不是最后一页,则显示后一页的超链接
if ($page != ($productcount/6+1)) {
	echo "&nbsp;<a href='showproduct.php?page=".($page+1)."'>下一页</a>";
}else 
	echo "&nbsp;下一页";
echo "&nbsp;跳转到第
	<input type='text' name='page' size='4' maxlength='4'>页
	</font></p>";
//开始循环输出每个商品的信息
for ($count = 0; $count < sizeof($msgs); $count++){
	echo "<hr width='550' size='1' align='left'>
			<table width='549' border='1' cellspacing='0' cellpadding='0'>
				<tr>";
	if (file_exists("admin/pic/".$msgs[$count]->productid.".jpg")) {
		echo "<td height='100' bgcolor='#ccccff'><img src='admin/pic/".$msgs[$count]->productid.".jpg'></td>";
	}
	else 
		echo "<td height='100' bgcolor='#ccccff'><font size='2'>暂无图片信息</font></td>";
	echo "<td height='20' bgcolor='#ccccff'><font size='2'>商品名称:"
		.$msgs[$count]->productname."</font></td>
				</tr>";
	echo "<tr>
			<td colspan='2' height='20' bgcolor='#ccccff'><font size='2'>规格:"
		.$msgs[$count]->productname."</font></td>
		  </tr>";
	echo "<tr>
			<td colspan='2' height='20' bgcolor='#ccccff'><font size='2'>单价:"
		.$msgs[$count]->price."元</font></td>
		  </tr>";
	echo "<tr>
			<td colspan='2' height='20' bgcolor='#ccccff'><font size='2'>简介:"
		.$msgs[$count]->abstract."</font></td>
		  </tr>";
	echo "<tr>
			<td width='282' height='20' bgcolor='#ccccff'><font size='2'>
			<a href='showdetail.php?id=".$msgs[$count]->productid."'>详细介绍"
			."</a></font></td>
			<td width='268' height='20' bgcolor='#ccccff'><font size='2'>
			<a href='showproduct.php?job=addproduct&id=".$msgs[$count]->productid."'>放入购物车"
			."</a></font></td>
		  </tr>";
	echo "</table>";
}
echo "</form>";
echo "</body>";
echo "</html>";
?>