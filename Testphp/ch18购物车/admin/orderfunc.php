<?php
//��������.orderfunc.php
function getordercount()
{
	$conn = mysql_connect("localhost","root", "");
	mysql_select_db("phpbook_ch18") or die("�������ݿ�ʧ��!");
	$query = "select orderid, count(*) from userorder group by orderid";
	$result = mysql_query($query);
	$count = 1;
	$order[0] = 1;
	while ($obj = mysql_fetch_object($result)) {
		$order[$count] = $obj;
		$order[0] ++ ;
		$count ++;
	}
	//�ر����ݿ�
	mysql_close($conn);
	return $order;
}
?>