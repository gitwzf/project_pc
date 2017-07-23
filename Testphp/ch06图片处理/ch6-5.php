<?php
//发送标头信息
header("Content-type:image/gif");
$img = imagecreate(500,200);
$bgcolor = imagecolorallocate($img,255,255,255);
imagefill($img,0,0,$bgcolor);
$black=imagecolorallocate($img,0,0,0);
//使用imagearc()绘制弧线
imagearc($img,100,75,100,60,45,270,$black);
//使用imagedashedline()绘制虚线
imagedashedline($img,120,50,220,150,$black);
//使用imageline()绘制实线
imageline($img,140,50,240,150,$black);
//使用imagerectangle()绘制矩形
imagerectangle($img,400,30,490,170,$black);
//使用imagepolygon()绘制多边形
$pointlist=array(250,60,270,30,320,80,240,130,230,25,235,15);
imagepolygon($img,$pointlist,count($pointlist)/2,$black);
//显示图像
imagegif($img);
imagedestroy($img);
?>