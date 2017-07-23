<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>防止刷新计数器</title>
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
//从文件中获取点击次数的记录并加一
function gethits($filename,$user){
	$hits = ""; //初始化点击次数
	if(file_exists($filename)){
		$fp = fopen($filename,"r");
		$hits = chop(fread($fp, filesize($filename)));
		fclose($fp);
	}
	if($hits == "")
	$hits = 0; //如果记录文件不存在,则初始化次数为0
	else{
		$hits = (int)$hits;
		if(checkUserVisit($_SERVER['REMOTE_ADDR'],$user))
			$hits ++;
	}
	if($fp = fopen($filename, "w")){ //更新新的点击次数
		fwrite($fp, $hits);
		fclose($fp);
	}
	return $hits;
}

function ConvertToImage($content){
	$imageFile = "counter.gif";
	//Linux系统下，路径类似"/data/vhost/temp/counter/gif";
	$relativePath = "counter.gif";
	$noOfChars = strlen($content);
	$charHeight = 2;
	$charWidth = 2;
	$strWidth = $charWidth * $noOfChars;
	$strHeight = $charHeight;
	//图像中心位置
	$imgWidth = $strWidth * 15;
	$imgHeight = $strHeight * 15;
	$imgCenterX = $imgWidth / 2;
	$imgCenterY = $imgHeight / 2;
	//产生黑红相间的阴影效果
	$im = imagecreate($imgWidth,$imgHeight);
	$black = ImageColorAllocate($im,0,0,0);
	$red = ImageColorAllocate($im,255,0,0);
	ImageFilledRectangle($im,0,0,$imgWidht, $imgHeight, $black);
	ImageFilledRectangle($im,3,3,$imgWidht-4, $imgHeight-4, $red);
	//开始绘制字符串
	$drawPosX = $imgCenterX - ($strWidth / 2) + 1;
	$drawPosY = $imgCenterY - ($strHeight / 2);
	imagestring($im, 5, $drawPosX, $drawPosY, $content, $red);
	//输出到指定位置
	imagegif($im, $imageFile);
	return $relativePath;
}
//判断用户是否有过访问记录
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
//记录用户访问明细
function addUserInfo($ip,$user){
	$flag = checkUserVisit($ip, $user);
	if(flag){
		$link=mysql_connect("localhost","root","");
		mysql_select_db("phpbook_ch14",$link) or die("mysql_select_db failed");
		$show="insert into user_ip (user_ip, visit_time,user) values('".$ip."', now(),'".$user."')";
		$ip=mysql_query($show,$link) or die("mysql_query failed two");
	}
}
//格式化参数，输出部分
$resource_dir = "count"; //目录
if($id == "") $id = "3"; //默认id = 3
$width = (int) $width;
if($width < 4) $width = 4;
if($width > 12) $width = 12;
if($style == "") $style = "1";
elseif(!file_exists("./".$resource_dir."/".$style."/")) $style = "1";
$user = $_REQUEST["user"];
$hits = gethits("./".$resource_dir."/".$id.".txt",$user);
$hits = sprintf("%0".$width."d", $hits); //格式化输出
//添加用户访问信息
addUserInfo($_SERVER['REMOTE_ADDR'],$user);
$path = ConvertToImage($hits);
echo "您已经访问了<img src='".$path."'>次";
?>
</span></p>
</div>
</body>
</html>