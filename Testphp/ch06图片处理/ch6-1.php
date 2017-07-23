<?
//发送标头信息
header("Content-type:image/gif");
//从文件中建立gif图像
$temp=imagecreatefromgif("d:\\images\\book.gif");
//显示图像
imagegif($temp);
?>