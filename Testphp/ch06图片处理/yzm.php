<?php
header ("Content-type: image/png");
srand((double)microtime()*1000000);

$img_height=20;
$img_width = 60;
$im = @imagecreate ($img_width, $img_height)
    or die ("���ܳ�ʼ��GD�ļ���");
$background_color = imagecolorallocate ($im, 255, 255, 255);
$text_color = imagecolorallocate ($im, 233, 14, 91);
//���Ƹ��ű���ѩ��
for ($i=1; $i<=100; $i++) {    //����100�������� 
        imagestring($im,1,mt_rand(1,$img_width),mt_rand(1,$img_height),"*",imageColorAllocate($im,mt_rand(200,255),mt_rand(200,255),mt_rand(200,255)));
}
while(($num=rand()%100000)<10000);
//��������ɵ���֤�뱣�浽session�У������Ժ��ʹ��
if (! isset($_SESSION['num'])) {
    $_SESSION['num'] = $num;
}
//�����ַ�����ͼ��
imagestring ($im, 4, 15, 2,  $num, $text_color);
imagepng ($im);
imagedestroy ($im);
?>