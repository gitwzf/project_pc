<?php
//��Ʒ����,manageoperate.php
$conn = mysql_connect("localhost", "root", "");
mysql_select_db("phpbook_ch18") or die("�������ݿ�ʧ��!");
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
	$message = "�Ѿ���������Ʒ".$productname;	
}
//������,ѡ���Ӧ����ƷID,��ͼƬ���Ƹ�Ϊ��Ӧ����ƷID.
//ͬʱ���Ƶ�ͼƬĿ¼����ȡ
$opr = "select productid from product where productname='".$productname.
			"' and size='".$size."' and color='".$color."'";
$result = mysql_query($opr);
$obj = mysql_fetch_object($result);
$productid = $obj->productid;
copy($pic,"pic/".$productid.".jpg");

//�Ͽ����ݿ�����
mysql_close($conn);
header("Location:productmanage.php?message=".urlencode($message));
?>