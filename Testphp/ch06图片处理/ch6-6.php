<?php
header("Content-type:image/gif");
$img=imagecreate(500,250);
$bgcolor=imagecolorallocate($img,255,255,255);
imagefill($img,0,0,$bgcolor);
$black=imagecolorallocate($img,0,0,0);
$red=imagecolorallocate($img,255,0,0);
$yellow=imagecolorallocate($img,255,255,0);
//≤‚ ‘imagefill()
$pointlist=array(30,50,70,60,20,170);
imagepolygon($img,$pointlist,count($pointlist)/2,$black);
imagefill($img,40,60,$yellow);
//≤‚ ‘imagefilledpolygon()
$pointlist=array(100,20,150,80,140,160,110,120);
imagefilledpolygon($img,$pointlist,count($pointlist)/2,$yellow);
//≤‚ ‘imagefilledrectangle()
imagefilledrectangle($img,200,35,250,120,$red);
//≤‚ ‘imagefilltoborder()
imagerectangle($img,300,50,500,150,$black);
imagerectangle($img,320,90,370,130,$red);
imagerectangle($img,400,90,470,130,$black);
imagefilltoborder($img,360,100,$black,$black);
imagegif($img);
imagedestroy($img);
?>