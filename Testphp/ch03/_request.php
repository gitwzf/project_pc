<?php
//测试$_REQUEST使用效果
if($_POST != null){
	//设置Cookies
	/* 使用默认设置 */
	setcookie("cookie_title", $_POST['post_title']);
	/* 设置过期时间为1小时 */
	setcookie("cookie_author", $_POST['post_author'], time()+3600);
	/* 设置过期时间为1小时，有效路径为phpbook */
	setcookie("cookie_isbn", $_POST['post_isbn'], time()+3600, "/phpbook");
	//开始获取request
	foreach ($_REQUEST as $key => $value) {
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
<form action="_request.php?opr=add&m=book" name='f1' method="POST">
	<tr>
		<td colspan="2"><b>图书数据信息</b></td>
	</tr>
	<tr>
		<td>图书标题：</td>
		<td><input type="text" size="20" maxlength="20" name="post_title"></td>
	</tr>
	<tr>
		<td>图书作者：</td>
		<td><input type="text" size="20" maxlength="20" name="post_author"></td>
	</tr>
	<tr>
		<td>出版时间：</td>
		<td><input type="text" size="20" maxlength="20" name="post_date"></td>
	</tr>
	<tr>
		<td>图书ISBN：</td>
		<td><input type="text" size="20" maxlength="20" name="post_isbn"></td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;&nbsp;<input type="submit" name="post_submit" value="提交"></td>
	</tr>
	</form>
</table>