<?php
$name = "��С��";
$place = "�찲��";
$number = "001";

function myfunc($name)
{
  global $place;
  echo ("������".$name."��λ�ã�".$place."�����кţ�".$number." <br>");
}
myfunc($name);
?>
