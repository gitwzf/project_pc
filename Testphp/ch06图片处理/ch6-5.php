<?php
//���ͱ�ͷ��Ϣ
header("Content-type:image/gif");
$img = imagecreate(500,200);
$bgcolor = imagecolorallocate($img,255,255,255);
imagefill($img,0,0,$bgcolor);
$black=imagecolorallocate($img,0,0,0);
//ʹ��imagearc()���ƻ���
imagearc($img,100,75,100,60,45,270,$black);
//ʹ��imagedashedline()��������
imagedashedline($img,120,50,220,150,$black);
//ʹ��imageline()����ʵ��
imageline($img,140,50,240,150,$black);
//ʹ��imagerectangle()���ƾ���
imagerectangle($img,400,30,490,170,$black);
//ʹ��imagepolygon()���ƶ����
$pointlist=array(250,60,270,30,320,80,240,130,230,25,235,15);
imagepolygon($img,$pointlist,count($pointlist)/2,$black);
//��ʾͼ��
imagegif($img);
imagedestroy($img);
?>