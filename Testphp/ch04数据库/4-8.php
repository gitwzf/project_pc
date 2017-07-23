<?php
mysql_connect("localhost","root","");
mysql_select_db("phpbook");
$result = mysql_query("SELECT * FROM friends");
//获取结果中的字段数
$fields = mysql_num_fields($result);
$i=0;
//获取指定字段所在的数据表的名称
$table = mysql_field_table($result , $i);
echo "$table 表有如下的字段信息：<BR>";
while($i<$fields){
//获取字段的名称
$type = mysql_field_name($result , $i);
//获取字段的类型
$name = mysql_field_type($result , $i);
//获取字段的长度
$len = mysql_field_len($result , $i);
//获取字段的标志
$flags = mysql_field_flags($result , $i);
//输出结果数据
echo $type."".$name."".$len."".$flags."<BR>";
$i++;
}
?>