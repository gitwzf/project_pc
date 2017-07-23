<?php
header("Content-type:image/gif");
$img=imagecreate(500,250);
$bgcolor=imagecolorallocate($img,255,255,255);
imagefill($img,0,0,$bgcolor);
//≤‚ ‘imagecolortransparent()
imagecolortransparent($img,$bgcolor);
//≤‚ ‘imagecolorallocate()ļÕimagecolorat()
for($i=0;$i<5;$i++){
	$imgcolor=imagecolorallocate($img,100+rand(0,120),100+rand(0,120),100+rand(0,120));
	imagefilledrectangle($img,30,50+$i*30,150,80+$i*30,$imgcolor);
}
$black=imagecolorallocate($img,0,0,0);
for($i=0;$i<5;$i++){
  	imagestring($img,5,40,60+$i*30,imagecolorat($img,35,60+$i*30),$black);
}
//≤‚ ‘imagecolorclosest()
$closecolor=imagecolorallocate($img,120,120,120);
imagefilledrectangle($img,200,50,290,150,$closecolor);
imagestring($img,5,220,160,"color_close=".imagecolorclosest($img,101,100,100),$black);
//≤‚ ‘imagecolorexact()
imagestring($img,5,220,170,"color_exact=".imagecolorexact($img,101,100,100),$black);
//≤‚ ‘imagecolorresolve()
imagestring($img,5,220,180,"color_resolve=".imagecolorresolve($img,101,100,100),$black);
//≤‚ ‘imagecolorstotal()
$ncolor=imagecolorstotal($img);
imagestring($img,5,320,100,"totalcolors=".$ncolor,$black);
//≤‚ ‘imagecolorset()
imagecolorset($img,7,30,60,90);
//≤‚ ‘imagecolorsforindex()
$rgb=imagecolorsforindex($img,7);
imagestring($img,5,320,120,"Red=".$rgb["red"]."Green=".$rgb["green"]."Blue=".$rgb["blue"],$black);
imagegif($img);
imagedestroy($img);
?>