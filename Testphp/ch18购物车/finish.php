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

//����û��Ѿ���½�����ȡ�û���������Ϣ
//���Ҹ���
echo "userid=".$userid ."<br>";
if (isset($userid)) {
	$query = "select usercredit from user where userid=".$userid;
	$result = mysql_query($query);
	$res = mysql_fetch_array($result);
	$credit = $res[0];
	
	//��ʼ�����û�������Ϣ
	$credit += $total;
	$query = "update user set usercredit = ".$credit;
	mysql_query($query);
}
else 
{
	$userid = 0;
}
//�򶩵����ݿ��в�������
for ($i = 1; $i <=$cart->count ; $i ++)
{
	//����query�ֶ�,���д���,�����Ķ��ͷ��ִ���
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
		//�����ݿⷢ����Ϣ,���¶������ݿ�
		mysql_query($query);
			
		//������Ʒ�Ĺ�������,��ʱ������Ʒ�������Ҫ����
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
			echo "���³ɹ�";
}
//���ٱ���cart,��ʶ�������.
unset($_SESSION["cart"]);
//���ҳ����ص���Ϣ,��ȥ
echo "��ϲ��,�Ѿ�����˱������Ϲ���.���������ǽ����Ϸ���,����ע�����!<br>
		<a href='showproduct.php'>��������<a>&nbsp; 
		<a href='change.php?uid=".$userid."'>�޸ĸ�����Ϣ</a>&nbsp; 
		<a href='showall.php?uid=".$userid."'>�鿴���ﳵ</a>&nbsp; 
		<a href='loginout.php?uid=".$userid."'>ע��</a>
	";
?>