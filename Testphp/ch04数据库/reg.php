<?php
//���ӵ����ݿ�
$dbconn = mysqli_connect("localhost", "root", "", "phpbook") or die("�������ӵ����ݿ�");
//�ر��Զ��ύ
mysqli_autocommit($dbconn, false);
//�û�ע������ʼ
$name = $_POST['name'];
$pwd = $_POST['pwd'];
$realname = $_POST['realname'];
$addr = $_POST['addr'];
$reg = $_POST['reg'];
if ($_POST['submit'] && isset($reg) && $reg =="reg") {
	//�����û���bookuser��
	$sql1 = "insert into bookuser(id,name,pwd,regtime) values(NULL,'".$name."','".$pwd."', now())";
	$result = mysqli_query($dbconn, $sql1);
	if ($result !== true) {
		mysqli_rollback($dbconn); //���������ع�����ʼ״̬
	}
	//��ȡ��ע���û���ID��Ϣ
	$conn = mysql_connect("localhost","root","");
	mysql_select_db("phpbook");
	$sql2 = "select id from bookuser where name='cheng'";
	$result = mysql_query($sql2);
	while($row = mysql_fetch_array($result)){
		$uid = $row[0];
	}
	//�����û����ϵ�userinfo��
	$sql3 = "insert into userinfo(uid,realname,address) values(".$uid.",'".$realname."','".$addr."')";
	$result = mysqli_query($dbconn, $sql3);
	
	if ($result !== true) {
		mysqli_rollback($dbconn); //���������ع�����ʼ״̬
	}
	//�����û���Ϣ��userdetail��
	$sql4 = "insert into userdetail(uid,money,exp) values(".$uid.",2000,500)";
	$result = mysqli_query($dbconn, $sql4);
	if ($result !== true) {
		mysqli_rollback($dbconn); //���������ع�����ʼ״̬
	}
	//û���κδ������ύ�����һ���������
	mysqli_commit($dbconn);
}
//�ر����ݿ�����
mysqli_close($dbconn);
?>
<HTML>
<HEAD>
<TITLE> �������û�ע��ʾ�� </TITLE>
<META NAME="Generator" CONTENT="chengwei">
<META NAME="Author" CONTENT="Cheng">
<META NAME="Keywords" CONTENT="�������û�ע��ʾ��">
<META NAME="Description" CONTENT="�������û�ע��ʾ��">
</HEAD>

<BODY>
�������û�ע��ʾ��<br>
<table width=500 border=0>
<form method='post' name='reg'>
<tr>
	<td>
	�û�����<input type='text' name='name' size='20' maxlength='20'><br>
	�û�����<input type='password' name='pwd' size='20' maxlength='20'><br>
	��ʵ����<input type='text' name='realname' size='20' maxlength='20'><br>
	�û�סַ<input type='text' name='addr' size='20' maxlength='100'><br>
	<input type='hidden' name='reg' value='reg'><br>
	<input type='submit' name='submit' value='�ύ'>
	</td>
</tr>
</form>
</table>
<br>
��ǰ�Ѿ�ע����û���Ϣ��<br>
<?php
//���ݿ��ѯ����
$conn = mysql_connect("localhost","root","");
mysql_select_db("phpbook");
$query = "SELECT * FROM bookuser";
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