<?php
//数据库连接
$conn = mysql_connect("localhost","root","");
mysql_select_db("phpbook_ch18") or die("选择数据库失败!");
$result = mysql_list_tables("phpbook_ch18");
$i = 0;
$flag = false ; //设定标识为false
//判断是否存在User用户表
while ($i <mysql_numrows($result)) {
	if(mysql_table_name($result, $i) =="user" || mysql_table_name($result, $i) == "User"){
		$flag = true;
		break;
	}
	$i ++ ;
}
//如果用户表不存在,则尝试创建一个新的
if(!$flag){
	$query = "
	CREATE TABLE `user` (
	  `userid` int(10) unsigned NOT NULL auto_increment,
	  `username` varchar(20) default '0',
	  `userpass` varchar(30) default '0',
	  `email` varchar(40) NOT NULL default '0',
	  `usertel` varchar(6) default '0',
	  `useraddr` varchar(255) default '0',
	  `createtime` datetime default '0000-00-00 00:00:00',
	  `logintime` datetime default '0000-00-00 00:00:00',
	  `currentlogintime` datetime default '0000-00-00 00:00:00',
	  `logincount` int(10) NOT NULL default '0',
	  `usercredit` int(10) NOT NULL default '0',
	  `liking1` varchar(30) default NULL,
	  `liking2` varchar(30) default NULL,
	  `liking3` varchar(30) default NULL,
	  PRIMARY KEY  (`userid`)
	) TYPE=MyISAM;
	";
	//输出成功信息
	if(mysql_query($query)){
		echo "成功创建用户表";
	}else{
		//否则,输出已经存在的相关信息
		echo "用户表已经存在";
	}
	//关闭数据库连接
	mysql_close($conn);
}
?>