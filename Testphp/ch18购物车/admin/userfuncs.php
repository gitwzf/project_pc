<?php
//�û��������,usrfuncs.php
//ȷ��ÿһҳ���������
function getMessages($msgStart, $number)
{
	$conn = mysql_connect("localhost", "root", "");
	mysql_select_db("phpbook_ch18") or die("�������ݿ�ʧ��!");
	$sql = "select userid, username, createtime, usercredit from user limit "
			.$msgStart.",".$number;
	$result = mysql_query($sql);
	if(!$result)
	{
		//û��ȡ���κε�����,�ر����ݿ�����
		mysql_close($conn);
		$msgs[0] = 0;
		return  $msgs;
	}
	$count = 0;
	
	while ($obj = mysql_fetch_object($result)) 
	{
		$count ++;
		$msgs[$count] = $obj;
		$msgs[0] = $count;
	}
	mysql_close($conn);
	return $msgs;
}

//ͳ��user���е�����
function getrownumber()
{
	$conn = mysql_connect("localhost","root","");
	mysql_select_db("phpbook_ch18");
	$query = "select count(*) from user";
	
	$result = mysql_query($query);
	$number = mysql_fetch_array($result);
	mysql_close($conn);
	return $number[0];
}

//ͳ�����һҳ���������,10��/ҳ
function  thelastnumber($number)
{
	$lastnumber = $number % 10;
	return $lastnumber;
}
?>