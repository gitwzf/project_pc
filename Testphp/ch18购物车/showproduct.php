<?php
//��ʾ��Ʒ����
include_once("cart.php"); //�������ﳵ��

//������ȡ
$category = $_GET["category"];

//�����ݿ��л�ȡ��Ʒ��Ϣ
function getMessages($msgStart, $number,$category=""){
	$conn = mysql_connect("localhost", "root", "");
	mysql_select_db("phpbook_ch18");
	//�����ݿ��в��Ҷ�Ӧλ�õ���Ʒ
	if($category != "")
		$query = "select productid,productname,price,size,abstract from product
			where category='".$category."' limit ".$msgStart.",".$number;
	else 
		$query = "select productid,productname,price,size,abstract from product 
			limit ".$msgStart.",".$number;
	$result = mysql_query($query);
	
	//û���ҵ���Ӧ����Ʒ
	if (!$result) {
		mysql_close($conn);
		$msgs[0] = 0;
		return $msgs;
	}
	//�ҵ���Ʒ,��ʼ����,�����������
	$count = 0;
	while ($obj = mysql_fetch_object($result)) {
		$msgs[$count] = $obj;
		$count ++;
	}
	mysql_close($conn);
	return $msgs;
}
//ȡ�����һҳ��ʾ����
function  thelastnumber($number){
	$lastnumber = $number % 6;
	return $lastnumber;
}

//��ȡ���ݿ������з�������������
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
//ҳ�洦��ʼ
//��������ز���

session_start();
//�������Ϊ�����Ʒ,��ִ����Ӧ�Ĳ���
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
//ȷ��ҳ��
$page = $page ? $page : 1;
$productcount = getrownumber($category);
$pagesize = 6 ; //ÿҳ��ʾ6����Ʒ

//�����ҳ�����һҳ,����ʾ��ҳ����Ʒ����
if ($page > $productcount/6) {
	$pagesize = thelastnumber($productcount);
}
$allpages = ($productcount %6  > 0 ? intval($productcount/6+1) : intval($productcount/6));

//�õ���Ʒ��Ϣ�ķ���ֵ
$msgs = getMessages(($page-1)*6, $pagesize, $category);
echo "<html>\n";
echo "<head><title>��Ʒչʾ</title></head>\n";
echo "<body>\n";
//���ո�ʽ�����Ʒ����
echo "<form method='post' action='showproduct.php'>\n
		<p><font size='2'>����".($allpages)."ҳ �����ǵ�".$page."ҳ";
//�����ҳ���ǵ�һҳ,����ʾǰһҳ�ĳ�����
if ($page != 1) {
	echo "&nbsp;<a href='showproduct.php?page=".($page-1)."'>��һҳ</a>";			
}else 
	echo "&nbsp;��һҳ";
//�����ҳ�������һҳ,����ʾ��һҳ�ĳ�����
if ($page != ($productcount/6+1)) {
	echo "&nbsp;<a href='showproduct.php?page=".($page+1)."'>��һҳ</a>";
}else 
	echo "&nbsp;��һҳ";
echo "&nbsp;��ת����
	<input type='text' name='page' size='4' maxlength='4'>ҳ
	</font></p>";
//��ʼѭ�����ÿ����Ʒ����Ϣ
for ($count = 0; $count < sizeof($msgs); $count++){
	echo "<hr width='550' size='1' align='left'>
			<table width='549' border='1' cellspacing='0' cellpadding='0'>
				<tr>";
	if (file_exists("admin/pic/".$msgs[$count]->productid.".jpg")) {
		echo "<td height='100' bgcolor='#ccccff'><img src='admin/pic/".$msgs[$count]->productid.".jpg'></td>";
	}
	else 
		echo "<td height='100' bgcolor='#ccccff'><font size='2'>����ͼƬ��Ϣ</font></td>";
	echo "<td height='20' bgcolor='#ccccff'><font size='2'>��Ʒ����:"
		.$msgs[$count]->productname."</font></td>
				</tr>";
	echo "<tr>
			<td colspan='2' height='20' bgcolor='#ccccff'><font size='2'>���:"
		.$msgs[$count]->productname."</font></td>
		  </tr>";
	echo "<tr>
			<td colspan='2' height='20' bgcolor='#ccccff'><font size='2'>����:"
		.$msgs[$count]->price."Ԫ</font></td>
		  </tr>";
	echo "<tr>
			<td colspan='2' height='20' bgcolor='#ccccff'><font size='2'>���:"
		.$msgs[$count]->abstract."</font></td>
		  </tr>";
	echo "<tr>
			<td width='282' height='20' bgcolor='#ccccff'><font size='2'>
			<a href='showdetail.php?id=".$msgs[$count]->productid."'>��ϸ����"
			."</a></font></td>
			<td width='268' height='20' bgcolor='#ccccff'><font size='2'>
			<a href='showproduct.php?job=addproduct&id=".$msgs[$count]->productid."'>���빺�ﳵ"
			."</a></font></td>
		  </tr>";
	echo "</table>";
}
echo "</form>";
echo "</body>";
echo "</html>";
?>