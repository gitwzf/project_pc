<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>��ֹˢ�¼�����</title>
<style type="text/css">
<!--
.style1 {
 font-size: 24px;
 color: #00FF99;
}
.style2 {color: #FF00FF}
.style3 {color: #CC0033}
-->
</style>
</head>

<body>
<div align="center">
  <p><span class="style3">
<?php
//counter.php
//���ļ��л�ȡ��������ļ�¼����һ
function gethits($filename,$user){
	$hits = ""; //��ʼ���������
	if(file_exists($filename)){
		$fp = fopen($filename,"r");
		$hits = chop(fread($fp, filesize($filename)));
		fclose($fp);
	}
	if($hits == "")
	$hits = 0; //�����¼�ļ�������,���ʼ������Ϊ0
	else{
		$hits = (int)$hits;
		if(checkUserVisit($_SERVER['REMOTE_ADDR'],$user))
			$hits ++;
	}
	if($fp = fopen($filename, "w")){ //�����µĵ������
		fwrite($fp, $hits);
		fclose($fp);
	}
	return $hits;
}

function ConvertToImage($content){
	$imageFile = "counter.gif";
	//Linuxϵͳ�£�·������"/data/vhost/temp/counter/gif";
	$relativePath = "counter.gif";
	$noOfChars = strlen($content);
	$charHeight = 2;
	$charWidth = 2;
	$strWidth = $charWidth * $noOfChars;
	$strHeight = $charHeight;
	//ͼ������λ��
	$imgWidth = $strWidth * 15;
	$imgHeight = $strHeight * 15;
	$imgCenterX = $imgWidth / 2;
	$imgCenterY = $imgHeight / 2;
	//�����ں�������ӰЧ��
	$im = imagecreate($imgWidth,$imgHeight);
	$black = ImageColorAllocate($im,0,0,0);
	$red = ImageColorAllocate($im,255,0,0);
	ImageFilledRectangle($im,0,0,$imgWidht, $imgHeight, $black);
	ImageFilledRectangle($im,3,3,$imgWidht-4, $imgHeight-4, $red);
	//��ʼ�����ַ���
	$drawPosX = $imgCenterX - ($strWidth / 2) + 1;
	$drawPosY = $imgCenterY - ($strHeight / 2);
	imagestring($im, 5, $drawPosX, $drawPosY, $content, $red);
	//�����ָ��λ��
	imagegif($im, $imageFile);
	return $relativePath;
}
//�ж��û��Ƿ��й����ʼ�¼
function checkUserVisit($ip,$user){
	$link=mysql_connect("localhost","root","");
	mysql_select_db("phpbook_ch14",$link) or die("mysql_select_db failed");
	$show="SELECT COUNT(*) FROM `user_ip` WHERE user='".$user."' AND NOW()-`visit_time` < 5 AND user_ip='".$ip."'";
	$result=mysql_query($show,$link) or die("mysql_query failed one ");
	list($count)=mysql_fetch_array($result);
	if($count == 0)
		return true;
	else 
		return false;
}
//��¼�û�������ϸ
function addUserInfo($ip,$user){
	$flag = checkUserVisit($ip, $user);
	if(flag){
		$link=mysql_connect("localhost","root","");
		mysql_select_db("phpbook_ch14",$link) or die("mysql_select_db failed");
		$show="insert into user_ip (user_ip, visit_time,user) values('".$ip."', now(),'".$user."')";
		$ip=mysql_query($show,$link) or die("mysql_query failed two");
	}
}
//��ʽ���������������
$resource_dir = "count"; //Ŀ¼
if($id == "") $id = "3"; //Ĭ��id = 3
$width = (int) $width;
if($width < 4) $width = 4;
if($width > 12) $width = 12;
if($style == "") $style = "1";
elseif(!file_exists("./".$resource_dir."/".$style."/")) $style = "1";
$user = $_REQUEST["user"];
$hits = gethits("./".$resource_dir."/".$id.".txt",$user);
$hits = sprintf("%0".$width."d", $hits); //��ʽ�����
//����û�������Ϣ
addUserInfo($_SERVER['REMOTE_ADDR'],$user);
$path = ConvertToImage($hits);
echo "���Ѿ�������<img src='".$path."'>��";
?>
</span></p>
</div>
</body>
</html>