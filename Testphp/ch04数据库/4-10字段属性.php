<HTML>
<HEAD><TITLE>��ʾ����mysql_fetch_field()���÷�</TITLE></HEAD>
<BODY>
<TABLE BGCOLOR=#FFCC99 BORDER=1>
<TR><th>name</th><th>table</th><th>type</th><th>max_length</th><th>notnull</th><th>primarykey</th><th>uniqe_key</th><th>multiplekey</th><th>numeric</th><th>blob</th><th>unsigned</th><th>zerofielled</th>
</TR>
<?php
//���ݿ��ѯ����
mysql_connect("localhost","root","");
mysql_select_db("phpbook");
$query = "SELECT * FROM friends";
$result = mysql_query($query);
//ȡ���ֶε�������Ϣ��ʹ����mysql_fetch_field()
while($field = mysql_fetch_field($result)){
echo "<tr><td>$field->name</td>";
echo "<td>$field->table</td>";
echo "<td>$field->type</td>";
echo "<td>$field->max_length</td>";
echo "<td>$field->not_null</td>";
echo "<td>$field->primary_key</td>";
echo "<td>$field->unique_key</td>";
echo "<td>$field->multiple_key</td>";
echo "<td>$field->numeric</td>";
echo "<td>$field->blob</td>";
echo "<td>$field->unsigned</td>";
echo "<td>$field->zerofill</td></tr>";
}
?>
</TABLE>
</BODY>
</HTML>