<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>�ϴ��ļ�����</title>
<style type="text/css">
<!--
body,td,th {
	font-size: 9pt;
}
body {
	background-color: #EFEFE7;
}
.s1 {
	font-weight: bold;
	color: #FFFFFF;
}
a:link {
	color: #0000FF;
	text-decoration: none;
}
a:visited {
	color: #0000FF;
	text-decoration: none;
}
a:hover {
	text-decoration: underline;
}
a:active {
	text-decoration: underline;
}
-->
</style>
<script type="text/javascript"><!--
function ConfirmDel(){
	if(confirm("ȷ��ɾ�������Ҳ��ָܻ���ȷ��Ҫɾ�����ļ���"))
		return true;
	else
		return false;
}//-->
</script>
</head>
<?php
include('../inc/config.inc.php');
include('../inc/db.class.php');
include('../inc/turnpage.class.php');
//�����ݿ����������ʵ��
$db=new db;
//�������Ӳ�������
$db->mysql($host,$user,$password,$database);
//���ô������Ӻ���
$db->createcon();
?>
<body topmargin="0">
<table width="700" height="25" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#739E18">
  <tr>
    <td align="center" class="s1">�ϴ��ļ����� <a href=../upload/index.html>�����ϴ�</a></td>
  </tr>
</table>
<table width="700" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#333333">
  <tr>
    <td><table width="100%" border="0" cellpadding="1" cellspacing="1">
  <tr>
    <td height="30" colspan="6" bgcolor="#EFEFE7">&nbsp;<span class="s2">�ϴ��ļ��б�</span></td>
  </tr>
<?php
$querysql="select * from uploadfile";
$result=$db->query($querysql);
//ȡ����Ϣ����
$total=mysql_num_rows($result);
//���÷�ҳ����turnpage()��ÿҳ��ʾ10����Ϣ��ʹ�õ�ǰURL������ת��
turnpage($total,10);
//ʹ��ȫ�ֱ���
$result=$db->query("select * from uploadfile order by f_id desc limit $firstcount,$displaypg ");
while($rows=$db->loop_query($result)){
?>
  <tr>
    <td height="25" align="left" bgcolor="#EFEFE7">&nbsp;ID:<?php echo $rows[f_id];?></td>
    <td height="25" align="left" bgcolor="#EFEFE7">&nbsp;�ļ�����:<a title="����鿴��ͼƬ" href=<?php echo $rows[f_save];?> target="_blank"><?php echo $rows[f_name];?></a>&nbsp;</td>
    <td align="left" bgcolor="#EFEFE7">&nbsp;״̬��
	<?php 
	if($rows[f_status]==0)
		echo"<font color=red>δͨ��</font>";
	else
		echo"<font color=red>��ͨ��</font>";
	 ?></td>
    <td height="25" align="left" bgcolor="#EFEFE7">&nbsp;�ϴ�ʱ��:<?php echo $rows[f_date];?></td>
    <td height="25" align="center" bgcolor="#EFEFE7">&nbsp;
    	<a onClick="return ConfirmDel()" href="../upload/delfile.php?id=<?php echo $rows[f_id];?>">ɾ�����ļ�</a><?php 
     if($rows[f_status]==0)
    	echo"/<a href=filepass.php?id=$rows[f_id]>ͨ�����</a>";
	 ?></td>
    </tr>
<?php
}
?>
</table></td>
  </tr>
</table>
<table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="50" align="center">
<?php
//�����ҳ
echo $pageresult;
?></td>
  </tr>
</table>
</body>
</html>