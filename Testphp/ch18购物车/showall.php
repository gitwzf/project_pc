<?php
//���ﳵ����ʾ�����
include_once("cart.php");
session_start();
//ʡ�Ե�ǰ��Ŀ�ͷ
if (!isset($_SESSION["userid"])) {
	$_SESSION["userid"] = $_GET["uid"];
}
//���job��������clear,ִ����������
if($job != "clear"){
	//���job=del,��ִ��ɾ������.
	if($job == "del"){
		$cart->removeItem($id);
	}
	//���job=update,��ִ�и��²���
	if($job == "update"){
		echo "begin update".$cart->count;
		for ($i = 1; $i <= $cart->count; $i ++){
			$a = "a".$i;
			$cartInfo[$i] = $$a;
		}
		$cart->updateCart($cartInfo);
	}
	//��ʾ���ﳵ
	$cart->showCart($userid);
}else{
	//��ղ���
	unset($_SESSION["cart"]);
	echo "���Ĺ��ﳵ�Ѿ������!";
}
//��β����
?>