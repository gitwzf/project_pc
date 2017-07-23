<?php
//用户管理程序,usrfuncs.php
//确定每一页的输出内容
function getMessages($msgStart, $number)
{
	$conn = mysql_connect("localhost", "root", "");
	mysql_select_db("phpbook_ch18") or die("连接数据库失败!");
	$sql = "select userid, username, createtime, usercredit from user limit "
			.$msgStart.",".$number;
	$result = mysql_query($sql);
	if(!$result)
	{
		//没有取到任何的数据,关闭数据库连接
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

//统计user表中的行数
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

//统计最后一页的输出条数,10条/页
function  thelastnumber($number)
{
	$lastnumber = $number % 10;
	return $lastnumber;
}
?>