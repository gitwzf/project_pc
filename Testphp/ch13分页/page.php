<html>
<HEAD>
<title>��ҳ��ʾģ��</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<LINK href="images/style.css" type="text/css" rel="stylesheet">
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
}
</style>
<body>
<?php
// �������ݿ�����
$link = mysql_connect("localhost", "root", "") or die("Could not connect: " . mysql_error());
mysql_select_db("phpbook_ch13",$link) or die("ѡ�����ݿ�ʧ��!");
// ��ȡ��ǰҳ��
if( isset($_GET['page']) ){
	$page = intval( $_GET['page'] );
}else{
	$page = 1;
}
// ÿҳ����
$page_size = 5;
// ��ȡ��������
$sql = "select count(*) as amount from news";
$result = mysql_query($sql);
$rows = mysql_fetch_row($result);
$amount = $rows[0];
// �����ܹ��ж���ҳ
if( $amount ){
	if( $amount < $page_size ){ $page_count = 1; }               //�����������С��$page_size����ôֻ��һҳ
	if( $amount % $page_size ){                                  //ȡ������������ÿҳ��������
		$page_count = (int)($amount / $page_size) + 1;           //�������������ҳ������������������ÿҳ���Ľ��ȡ���ټ�һ
	}else{
		$page_count = $amount / $page_size;                      //���û����������ҳ������������������ÿҳ���Ľ��
	}
}else{
	$page_count = 0;
}
// ��ҳ����
$page_string = '';
if( $page == 1 ){
	$page_string .= '��һҳ|��һҳ|';
}else{
	$page_string .= '<a href=?page=1>��һҳ</a>|<a href=?page='.($page-1).'>��һҳ</a>|';
}
if( ($page == $page_count) || ($page_count == 0) ){
	$page_string .= '��һҳ|βҳ';
}else{
	$page_string .= '<a href=?page='.($page+1).'>��һҳ</a>|<a href=?page='.$page_count.'>βҳ</a>';
}
// ��ȡ���ݣ��Զ�ά�����ʽ���ؽ��
if( $amount ){
	$sql = "select * from news order by newsid desc limit ". ($page-1)*$page_size .", $page_size";
	$result = mysql_query($sql);

	while ( $row = mysql_fetch_row($result) ){
		$rowset[] = $row;
	}
}else{
	$rowset = array();
}
//�����ʾ
echo '<table border=1>
		<tr>
			<td>���ű��</td>
			<td>���ű���</td>
			<td>��������</td>
			<td>����ʱ��</td>
			<td>��������</td>
		</tr>';
for($i = 0 ; $i <sizeof($rowset); $i ++){
	echo '<tr>';
	echo '<td>'.$rowset[$i][0]."</td>";
	echo '<td>'.$rowset[$i][1]."</td>";
	echo '<td>'.$rowset[$i][2]."</td>";
	echo '<td>'.$rowset[$i][3]."</td>";
	echo '<td>'.$rowset[$i][4]."</td>";
	echo '</tr>';
}
echo "</table>";
//��ҳ��ʾ����
echo $page_string;
//�ر����ݿ�����
mysql_close($link);
?>
</body>
</html>