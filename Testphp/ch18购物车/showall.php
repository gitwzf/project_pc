<?php
//购物车的显示与更新
include_once("cart.php");
session_start();
//省略掉前面的开头
if (!isset($_SESSION["userid"])) {
	$_SESSION["userid"] = $_GET["uid"];
}
//如果job参数不是clear,执行下面的语句
if($job != "clear"){
	//如果job=del,则执行删除操作.
	if($job == "del"){
		$cart->removeItem($id);
	}
	//如果job=update,则执行更新操作
	if($job == "update"){
		echo "begin update".$cart->count;
		for ($i = 1; $i <= $cart->count; $i ++){
			$a = "a".$i;
			$cartInfo[$i] = $$a;
		}
		$cart->updateCart($cartInfo);
	}
	//显示购物车
	$cart->showCart($userid);
}else{
	//清空操作
	unset($_SESSION["cart"]);
	echo "您的购物车已经被清空!";
}
//结尾部分
?>