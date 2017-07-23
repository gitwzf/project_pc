<?
//发送标头信息
Header("Content-type:image/gif");
$temp = imagecreate(600,200);
$bgcolor = imagecolorallocate($temp,255,255,255);
imagefill($temp,0,0,$bgcolor);
$w = imagesx($temp);
$h = imagesy($temp);
$black = imagecolorallocate($temp,0,0,0);
imagestring($temp,5,10,10,"width=".$w.";height=".$h,$black);
imagearc($temp,$w/2,$h/2,10,10,0,360,$black);
imagestring($temp,5,$w/2,$h/2,"This is the center!",$black);
imageinterlace($temp,0);
//显示图像
imagegif($temp);
imagedestroy($temp);
?>