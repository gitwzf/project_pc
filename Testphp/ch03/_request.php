<?php
//����$_REQUESTʹ��Ч��
if($_POST != null){
	//����Cookies
	/* ʹ��Ĭ������ */
	setcookie("cookie_title", $_POST['post_title']);
	/* ���ù���ʱ��Ϊ1Сʱ */
	setcookie("cookie_author", $_POST['post_author'], time()+3600);
	/* ���ù���ʱ��Ϊ1Сʱ����Ч·��Ϊphpbook */
	setcookie("cookie_isbn", $_POST['post_isbn'], time()+3600, "/phpbook");
	//��ʼ��ȡrequest
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
		<td colspan="2"><b>ͼ��������Ϣ</b></td>
	</tr>
	<tr>
		<td>ͼ����⣺</td>
		<td><input type="text" size="20" maxlength="20" name="post_title"></td>
	</tr>
	<tr>
		<td>ͼ�����ߣ�</td>
		<td><input type="text" size="20" maxlength="20" name="post_author"></td>
	</tr>
	<tr>
		<td>����ʱ�䣺</td>
		<td><input type="text" size="20" maxlength="20" name="post_date"></td>
	</tr>
	<tr>
		<td>ͼ��ISBN��</td>
		<td><input type="text" size="20" maxlength="20" name="post_isbn"></td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;&nbsp;<input type="submit" name="post_submit" value="�ύ"></td>
	</tr>
	</form>
</table>