<?php
//����$_POSTʹ��Ч��
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
		<td colspan="2"><b>ͼ��������Ϣ</b></td>
	</tr>
	<tr>
		<td>ͼ����⣺</td>
		<td><input type="text" size="20" maxlength="20" name="title"></td>
	</tr>
	<tr>
		<td>ͼ�����ߣ�</td>
		<td><input type="text" size="20" maxlength="20" name="author"></td>
	</tr>
	<tr>
		<td>����ʱ�䣺</td>
		<td><input type="text" size="20" maxlength="20" name="publish_date"></td>
	</tr>
	<tr>
		<td>ͼ��ISBN��</td>
		<td><input type="text" size="20" maxlength="20" name="isbn"></td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;&nbsp;<input type="submit" name="submit" value="�ύ"></td>
	</tr>
	</form>
</table>