<html>
<body>
<?
echo "ʹ��getimagesize������ȡͼ����Ϣ<BR>";
$cinfo = getimagesize("subway.jpg");
//��ʾ�й�ͼ�����Ϣ
echo $cinfo[0]."<BR>";
echo $cinfo[1]."<BR>";
echo $cinfo[2]."<BR>";
echo $cinfo[3]."<BR>";
?>
</body>
</html>