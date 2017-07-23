<?php
	//counter.php
	//从文件中获取点击次数的记录并加一
	function gethits($filename){
		$hits = ""; //初始化点击次数
		if(file_exists($filename)){
			$fp = fopen($filename,"r");
			$hits = chop(fread($fp, filesize($filename)));
			fclose($fp);
		}
		if($hits == "")
			$hits = 0; //如果记录文件不存在,则初始化次数为0
		else
			$hits = (int)$hits;
		$hits ++;
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
	
	//格式化图片的显示参数
	$resource_dir = "count"; //目录
	$id = $_GET['id'];
	$width = $_GET['w'];
	$style = $_GET['s'];
	$id = intval($id);
	if($id < 0 || $id >16) $id = 0; //默认id = 0
	$width = (int) $width;
	if($width < 4) $width = 4;
	if($width > 12) $width = 12;
	if($style == "") $style = "0";
	elseif(!file_exists("./".$resource_dir."/".$style."/")) $style = "0";
	
	$hits = gethits("./".$resource_dir."/".$id.".txt");
	$hits = sprintf("%0".$width."d", $hits); //格式化输出
	$path = ConvertToImage($hits);
	echo "您已经访问了<img src='".$path."'>次";
?>