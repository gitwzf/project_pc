<?php
$arrays = array ( "colors"  => array ( "a" => "red","b" => "blue","c" => "black"),
              "numbers" => array ( 1, 2, 3, 4, 5),
              "phpbook"   => array ("first",3 => "second","third"));
//��ӡ�������$fruits�Ĳ���Ԫ��ֵ
echo $arrays["phpbook"][3];  //��ӡ������Ϊ"second"
echo $arrays["colors"]["a"];  //��ӡ������Ϊ"red"
unset($arrays["phpbook"][0]); //ɾ������Ԫ��"first"
//����һ���µĶ�ά����
$test["php"]["book"] = "this is a good book!";
?>