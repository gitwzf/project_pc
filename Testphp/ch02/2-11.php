<?php
//����a
$a = array( 'color' => 'red', 'taste' => 'sweet', 'shape' => 'round', 'name'  => 'apple',
                  4 /*��ֵ�������0*/);
//����a��ȫ��ͬ������ĸ�ֵ
$a['color']	= 'red';
$a['taste'] = 'sweet';
$a['shape'] = 'round';
$a['name']  = 'apple';
$a[]       = 4;        //��ֵ�������0
//����b
$b[] = 'a';
$b[] = 'b';
$b[] = 'c';
//���������b����ͬ��array(0 => 'a' , 1 => 'b' , 2 => 'c')�����߼򵥹�����ʽarray('a', 'b', 'c')
?>