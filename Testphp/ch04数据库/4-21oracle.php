<html>
<head><title>向ORACLE数据库中插入数据</title></head>
<body>
<form name="testoracle" action="<?echo $_SERVER['PHP_SELF'];?>" method="post">
<table border="1" cellspacing="0" cellpadding="0">
	<tr>
		<th>ISBN</th>
		<th>书籍名称</th>
		<th>书籍介绍</th>
	</tr>
<tr>
	<td><input type="text" name="isbn" maxlength="50" size="20"></td>
	<td><input type="text" name="title" maxlength="255" size="30"></td>
	<td><input type="text" name="description" maxlength="255" size="50"></td>
</tr>
<tr align="center">
	<td colspan="3"><input type="submit" value="提交">&nbsp;&nbsp;
	<input type="reset" value="重写"></td>
</tr>
</table>
</form>
<?php
//先设置两个环境变量ORACLE_HOME，ORACLE_SID
putenv("ORACLE_HOME=/app/oracle/product/10.2.0.2");
putenv("ORACLE_SID=ora10");
//设置网页显示中文
putenv("NLS_LANG=Simplified_Chinese.zhs16cgb231280");
if($connection = ora_logon("scott","tiger")) {
	//表phpbook有isbn,title,description三个字段
	if($_POST['submit'] && isset($_POST['op']) && $_POST['op'] == 'add'){
		$isbn = $_POST['isbn'];
		$title = $_POST['title'];
		$description = $_POST['description'];
		$sql = "insert into phpbook(id,title,description) values ";
		$sql .= "('" . $isbn . "','". $title ."','". $description ."')";
		if($cursor = ora_do($connection,$sql)) {
			echo ("插入数据成功!");
		}
		echo "当前已有数据：<br>";
		$query = 'select * from phpbook';
		if($cursor = ora_do($connection,$query)) {
			ora_fetch($cursor);//取数据
			$isbn_tmp=ora_getcolumn($cursor,0);
			$title_tmp=ora_getcolumn($cursor,1);
			$description_tmp=ora_getcolumn($cursor,2);
			echo ("ISBN :".$isbn_tmp);
			echo ("标题 :".$title_tmp);
			echo ("描述 :".$description_tmp);
			ora_close($cursor);
		}
	}
	ora_logoff($connection);
}
?>
</body>
</html>