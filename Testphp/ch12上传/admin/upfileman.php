<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>上传文件管理</title>
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
	if(confirm("确认删除？并且不能恢复！确定要删除该文件吗？"))
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
//从数据库操作类生成实例
$db=new db;
//调用连接参数函数
$db->mysql($host,$user,$password,$database);
//调用创建连接函数
$db->createcon();
?>
<body topmargin="0">
<table width="700" height="25" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#739E18">
  <tr>
    <td align="center" class="s1">上传文件管理 <a href=../upload/index.html>继续上传</a></td>
  </tr>
</table>
<table width="700" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#333333">
  <tr>
    <td><table width="100%" border="0" cellpadding="1" cellspacing="1">
  <tr>
    <td height="30" colspan="6" bgcolor="#EFEFE7">&nbsp;<span class="s2">上传文件列表</span></td>
  </tr>
<?php
$querysql="select * from uploadfile";
$result=$db->query($querysql);
//取得信息总数
$total=mysql_num_rows($result);
//调用分页函数turnpage()，每页显示10条信息，使用当前URL进行跳转。
turnpage($total,10);
//使用全局变量
$result=$db->query("select * from uploadfile order by f_id desc limit $firstcount,$displaypg ");
while($rows=$db->loop_query($result)){
?>
  <tr>
    <td height="25" align="left" bgcolor="#EFEFE7">&nbsp;ID:<?php echo $rows[f_id];?></td>
    <td height="25" align="left" bgcolor="#EFEFE7">&nbsp;文件名称:<a title="点击查看该图片" href=<?php echo $rows[f_save];?> target="_blank"><?php echo $rows[f_name];?></a>&nbsp;</td>
    <td align="left" bgcolor="#EFEFE7">&nbsp;状态：
	<?php 
	if($rows[f_status]==0)
		echo"<font color=red>未通过</font>";
	else
		echo"<font color=red>已通过</font>";
	 ?></td>
    <td height="25" align="left" bgcolor="#EFEFE7">&nbsp;上传时间:<?php echo $rows[f_date];?></td>
    <td height="25" align="center" bgcolor="#EFEFE7">&nbsp;
    	<a onClick="return ConfirmDel()" href="../upload/delfile.php?id=<?php echo $rows[f_id];?>">删除该文件</a><?php 
     if($rows[f_status]==0)
    	echo"/<a href=filepass.php?id=$rows[f_id]>通过审核</a>";
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
//输出分页
echo $pageresult;
?></td>
  </tr>
</table>
</body>
</html>