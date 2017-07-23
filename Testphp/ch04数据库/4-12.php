<HTML>
<HEAD>
<TITLE>使用mysql_list_tables查看数据库</TITLE>
</HEAD>
<BODY>
<H2><FONT COLOR=BLUE>数据库信息</FONT></H2>
<?php
//连接到数据库服务器
$connect = mysql_connect('localhost','root','');
echo "<b><i>结构</i></b><br>";
echo "<b><font color=blue>主目录</font></b><br>";
//使用mysql_list_dbs获取数据库列表
$result1 = mysql_list_dbs();
//获取数据库数目，用mysql_affected_row()
$db_no = mysql_affected_rows();
for($i = 0 ; $i < $db_no ; $i++){
	 //获取数据库的名字
	 $db_name = mysql_tablename($result1 , $i);
	 echo "&nbsp;&nbsp;<fontcolor=red>";
	 echo $db_name."<br>";
	 echo "</font>";
	 //使用mysql_list_tables获取当前数据库的数据表的列表
	 $result2 = mysql_list_tables($db_name);
	 //获取当前数据库下数据表的个数
	 $tb_no = mysql_affected_rows();
	 for($j = 0 ; $j < $tb_no ; $j++){
		//获取数据表的名字
		$tb_name = mysql_tablename($result2,$j);
		echo "&nbsp;&nbsp;&nbsp;&nbsp;<fontcolor=green>";
		echo $tb_name."<br>";
		echo "</font>";
	 }
}
?>
</BODY>
</HTML>