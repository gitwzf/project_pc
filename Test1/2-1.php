<?php
//���ͱ�ͷ��Ϣ
header("Content-type:image/gif");
$img=imagecreate(300,150);
$bgcolor=imagecolorallocate($img,255,255,0);
imagefill($img,0,0,$bgcolor);
$red=imagecolorallocate($img,255,0,0);
//ͨ��ѭ��������������
for($i=0;$i<300;$i++){
	$y=50*sin($i/150*pi());
	imagesetpixel($img,$i,80+$y,$red);
}
//��ʾͼ��
imagegif($img);
imagedestroy($img);
?>