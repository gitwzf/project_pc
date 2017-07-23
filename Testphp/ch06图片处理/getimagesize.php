<html>
<body>
<?
echo "使用getimagesize函数获取图像信息<BR>";
$cinfo = getimagesize("subway.jpg");
//显示有关图像的信息
echo $cinfo[0]."<BR>";
echo $cinfo[1]."<BR>";
echo $cinfo[2]."<BR>";
echo $cinfo[3]."<BR>";
?>
</body>
</html>