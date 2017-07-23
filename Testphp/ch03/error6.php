<?php
$name = "张小三";
$place = "天安门";
$number = "001";

function myfunc($name)
{
  global $place;
  echo ("姓名：".$name."；位置：".$place."；序列号：".$number." <br>");
}
myfunc($name);
?>
