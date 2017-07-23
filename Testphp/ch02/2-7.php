<?php
$arr = array("phpbook" => array(6 => 15, 12 => 4, "database" => "mysql"));
echo $arr["phpbook"][6];    //打印输出结果为15
echo $arr["phpbook"][12];   //打印输出结果为4
echo $arr["phpbook"]["database"];  //打印输出结果为"mysql"
?>