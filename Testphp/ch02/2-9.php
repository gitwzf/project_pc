<?php
$arr = array(5 => 1, 12 => 2);
$arr[] = 3;    //��$arr[13] = 3;Ч��һ��
$arr["x"] = 4;  //����һ����ֵΪx��������Ԫ��
unset($arr[5]); //��������ɾ����ֵΪ5������Ԫ��
unset($arr);   //ɾ����������
?>