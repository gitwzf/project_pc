<?php
//���ӵ����ݿ�
$dbh = mysqli_connect("localhost:3308", "root", "12345", "phpbook") or die("�������ӵ����ݿ�");
//�ر��Զ��ύ
mysqli_autocommit($dbh, FALSE);
//ת������ʼ
if ($_POST['submit'] && is_numeric($_POST['amt'])) {
//���ӽ�Ŀ���˻�
$result = mysqli_query($dbh, "UPDATE accounts SET balance = balance + " . $_POST['amt'] . " WHERE id = " . $_POST['to']);
if ($result !== TRUE) {
mysqli_rollback($dbh); //���������ع�����ʼ״̬
}
//�ӵ�ǰ�˻��м��ٽ��
$result = mysqli_query($dbh, "UPDATE accounts SET balance = balance - " . $_POST['amt'] . " WHERE id = " . $_POST['from']);
if ($result !== TRUE) {
mysqli_rollback($dbh); //���������ع�����ʼ״̬
}
//û���κδ������ύ�����һ���������
mysqli_commit($dbh);
}
//��ȡ�˻����
//�������ݣ�����ҳ����ʾ����
$result = mysqli_query($dbh, "SELECT * FROM accounts");
while ($row = mysqli_fetch_assoc($result)) {
$accounts[] = $row;
}
//�ر����ݿ�����
mysqli_close($dbh);
?>
<HTML>
<HEAD>
<TITLE> �������û�ת�� </TITLE>
<META NAME="Generator" CONTENT="chengwei">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
</HEAD>

<BODY>
�������û�ת��<br>
<table width=500 border=0>
<form method='post' name='transfer'>
<tr>
	<td>
	ת��<input type='text' name='amt' size='10' maxlength='10'>from
	<select name='saving'>
		<option value='1'>Saving #1</option>
		<option value='4'>Saving #2</option>
	</select>
	to
	<select name='current'>
		<option value='2'>Current #1</option>
		<option value='3'>Current #2</option>
	</select>
	<input type='submit' name='submit' value='�ύ'>
	</td>
</tr>
</form>
</table>
<br>
��ǰ�ʻ��ʽ�<br>
<?
//���ݿ��ѯ����
mysql_connect("localhost","root","");
mysql_select_db("phpbook");
$query = "SELECT * FROM accounts";
$result = mysql_query($query);
echo "<table border='1'>";
while ($row = mysql_fetch_array($result, MYSQL_NUM)) {
    echo ("<tr><td>".$row[1]."</td><td>".$row[2]."</td></tr>");
}
echo "<table>";
mysql_free_result($result);
?>
</BODY>
</HTML>