<?php
//商品管理,manageoperate.php
$conn = mysql_connect("localhost", "root", "");
mysql_select_db("phpbook_ch18") or die("连接数据库失败!");
$message = "";
$query = "select categoryname from categories where categoryid=".$category;
$result = mysql_query($query);
$res = mysql_fetch_object($result);
$categoryname = $res->categoryname;

$date = date("Y-m-d H:i:s", time());
$query = "insert into product(productname,category,createtime,price,color,size,count,detail)
			values('"
			.$productname."','"
			.$categoryname."','"
			.$date."',"
			.$price.",'"
			.$color."','"
			.$size."',0,'"
			.$detail."')";
if (mysql_query($query)) 
{
	$message = "已经加入新商品".$productname;	
}
//接下来,选择对应的商品ID,将图片名称改为相应的商品ID.
//同时复制到图片目录下面取
$opr = "select productid from product where productname='".$productname.
			"' and size='".$size."' and color='".$color."'";
$result = mysql_query($opr);
$obj = mysql_fetch_object($result);
$productid = $obj->productid;
copy($pic,"pic/".$productid.".jpg");

//断开数据库连接
mysql_close($conn);
header("Location:productmanage.php?message=".urlencode($message));
?>