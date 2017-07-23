<?php
header ("Content-type: image/png");
srand((double)microtime()*1000000);

$img_height=20;
$img_width = 60;
$im = @imagecreate ($img_width, $img_height)
    or die ("不能初始化GD文件流");
$background_color = imagecolorallocate ($im, 255, 255, 255);
$text_color = imagecolorallocate ($im, 233, 14, 91);
//绘制干扰背景雪花
for ($i=1; $i<=100; $i++) {    //先用100个做测试 
        imagestring($im,1,mt_rand(1,$img_width),mt_rand(1,$img_height),"*",imageColorAllocate($im,mt_rand(200,255),mt_rand(200,255),mt_rand(200,255)));
}
while(($num=rand()%100000)<10000);
//把随机生成的验证码保存到session中，方便以后的使用
if (! isset($_SESSION['num'])) {
    $_SESSION['num'] = $num;
}
//绘制字符串到图像
imagestring ($im, 4, 15, 2,  $num, $text_color);
imagepng ($im);
imagedestroy ($im);
?>