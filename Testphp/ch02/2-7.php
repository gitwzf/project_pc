<?php
$arr = array("phpbook" => array(6 => 15, 12 => 4, "database" => "mysql"));
echo $arr["phpbook"][6];    //��ӡ������Ϊ15
echo $arr["phpbook"][12];   //��ӡ������Ϊ4
echo $arr["phpbook"]["database"];  //��ӡ������Ϊ"mysql"
?>