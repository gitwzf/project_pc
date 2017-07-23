<?php
//测试$_POST使用效果
if($_POST != null){
	foreach ($_POST as $key => $value) {
		echo "Key: <font color='blue'>$key</font>; Value: <font color='red'>$value</font><br>\n";
	}
}
?>
<style>
<!--
td{font-size:12px;}
//-->
</style>
<table border='1' width='500' cellpadding="0" cellpadding="2">
<form action="_post.php" name='f1' method="POST">
	<tr>
		<td colspan="2"><b>图书数据信息</b></td>
	</tr>
	<tr>
		<td>图书标题：</td>
		<td><input type="text" size="20" maxlength="20" name="title"></td>
	</tr>
	<tr>
		<td>图书作者：</td>
		<td><input type="text" size="20" maxlength="20" name="author"></td>
	</tr>
	<tr>
		<td>出版时间：</td>
		<td><input type="text" size="20" maxlength="20" name="publish_date"></td>
	</tr>
	<tr>
		<td>图书ISBN：</td>
		<td><input type="text" size="20" maxlength="20" name="isbn"></td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;&nbsp;<input type="submit" name="submit" value="提交"></td>
	</tr>
	</form>
</table>