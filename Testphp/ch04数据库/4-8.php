<?php
mysql_connect("localhost","root","");
mysql_select_db("phpbook");
$result = mysql_query("SELECT * FROM friends");
//��ȡ����е��ֶ���
$fields = mysql_num_fields($result);
$i=0;
//��ȡָ���ֶ����ڵ����ݱ������
$table = mysql_field_table($result , $i);
echo "$table �������µ��ֶ���Ϣ��<BR>";
while($i<$fields){
//��ȡ�ֶε�����
$type = mysql_field_name($result , $i);
//��ȡ�ֶε�����
$name = mysql_field_type($result , $i);
//��ȡ�ֶεĳ���
$len = mysql_field_len($result , $i);
//��ȡ�ֶεı�־
$flags = mysql_field_flags($result , $i);
//����������
echo $type."".$name."".$len."".$flags."<BR>";
$i++;
}
?>