<?php
//finish.php
include_once("cart.php");
session_start();
$conn = mysql_connect("localhost", "root", "");
mysql_select_db("phpbook_ch18");
function getproductcategory($pid)
{
	$query = "select category from product where productid=".$pid;
	$result = mysql_query($query);
	$res = mysql_fetch_array($result);
	$category = $res[0];
	return $category;
}

//如果用户已经登陆，则获取用户的信用信息
//并且更新
echo "userid=".$userid ."<br>";
if (isset($userid)) {
	$query = "select usercredit from user where userid=".$userid;
	$result = mysql_query($query);
	$res = mysql_fetch_array($result);
	$credit = $res[0];
	
	//开始更新用户信用信息
	$credit += $total;
	$query = "update user set usercredit = ".$credit;
	mysql_query($query);
}
else 
{
	$userid = 0;
}
//向订单数据库中插入数据
for ($i = 1; $i <=$cart->count ; $i ++)
{
	//处理query字段,分行处理,便于阅读和发现错误
	$date = date("Y-m-d H:i:s",time());
	$query = "INSERT INTO userorder VALUES("
			.$cart->cartid.","
			.$cart->cartitem[$i]["productid"].",'"
			.$cart->cartitem[$i]["productname"]."',"
			.$cart->cartitem[$i]["price"].","
			.$cart->cartitem[$i]["quantity"].",'"
			.getproductcategory($cart->cartitem[$i]["productid"])."',"
			.$userid.","
			.($cart->cartitem[$i]["price"] * $cart->cartitem[$i]["quantity"]).",'"
			.$addr."','"
			.$tel."','"
			.$date."','n');";
		//echo "userorder query=".$query;	
		//向数据库发送信息,更新订单数据库
		mysql_query($query);
			
		//更新商品的购买数量,这时进行商品排序的重要依据
		$query = "select count from product where productid="
				.$cart->cartitem[$i]["productid"];
		$result = mysql_query($query);
		$res = mysql_fetch_array($result);
		$pcount = $res->count;
		$query = "update product set count = "
				.$pcount
				." where productid="
				.$cart->cartitem[$i]["productid"];
		$r = mysql_query($query);
		if($r)
			echo "更新成功";
}
//销毁变量cart,标识购物完成.
unset($_SESSION["cart"]);
//输出页面相关的信息,略去
echo "恭喜您,已经完成了本次网上购物.接下来我们将马上发货,请您注意查收!<br>
		<a href='showproduct.php'>继续购物<a>&nbsp; 
		<a href='change.php?uid=".$userid."'>修改个人信息</a>&nbsp; 
		<a href='showall.php?uid=".$userid."'>查看购物车</a>&nbsp; 
		<a href='loginout.php?uid=".$userid."'>注销</a>
	";
?>