<HTML>
<HEAD>
<TITLE>ʹ��mysql_list_tables�鿴���ݿ�</TITLE>
</HEAD>
<BODY>
<H2><FONT COLOR=BLUE>���ݿ���Ϣ</FONT></H2>
<?php
//���ӵ����ݿ������
$connect = mysql_connect('localhost','root','');
echo "<b><i>�ṹ</i></b><br>";
echo "<b><font color=blue>��Ŀ¼</font></b><br>";
//ʹ��mysql_list_dbs��ȡ���ݿ��б�
$result1 = mysql_list_dbs();
//��ȡ���ݿ���Ŀ����mysql_affected_row()
$db_no = mysql_affected_rows();
for($i = 0 ; $i < $db_no ; $i++){
	 //��ȡ���ݿ������
	 $db_name = mysql_tablename($result1 , $i);
	 echo "&nbsp;&nbsp;<fontcolor=red>";
	 echo $db_name."<br>";
	 echo "</font>";
	 //ʹ��mysql_list_tables��ȡ��ǰ���ݿ�����ݱ���б�
	 $result2 = mysql_list_tables($db_name);
	 //��ȡ��ǰ���ݿ������ݱ�ĸ���
	 $tb_no = mysql_affected_rows();
	 for($j = 0 ; $j < $tb_no ; $j++){
		//��ȡ���ݱ������
		$tb_name = mysql_tablename($result2,$j);
		echo "&nbsp;&nbsp;&nbsp;&nbsp;<fontcolor=green>";
		echo $tb_name."<br>";
		echo "</font>";
	 }
}
?>
</BODY>
</HTML>