<?php
//��ʾsession_unset()������ʹ��Ч��
session_register('title','author','isbn'); //session�Զ���ʼ
$title = 'PHP������';
$author = 'cheng';
$isbn = 'PHP3124567890';
//unregistrered $title
session_unregister('title');
//��ʱtitle�Ѿ�����session��ע���������author��Ȼ��ע�����
echo "title: $title - reg:".session_is_registered('title')." ";
echo "<br>author: $author - reg:".session_is_registered('author');
//title������sessionע�����������ȫ�ֱ�����title��ֵ��Ȼ����
echo "<br>global title = ".$GLOBALS['title'];
//ʹ��session_unset()���������� $author �� $isbn
session_unset();
echo "<br>author:$author - reg:".session_is_registered('author')." ";
echo "<br>isbn:$isbn - reg:".session_is_registered('isbn')." ";
//ȫ�ֱ�����isbn��ֵҲһ��������
echo "<br>global isbn = ".$GLOBALS['isbn']." ";
echo "<br>session_encode=".session_encode();
session_register('content');
$content = '����һ������PHP�����̵ľ���֮����';
echo "<br>session_encode=".session_encode();
?>