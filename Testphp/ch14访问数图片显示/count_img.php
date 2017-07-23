<?php
	//counter.php
	//���ļ��л�ȡ��������ļ�¼����һ
	function gethits($filename){
		$hits = ""; //��ʼ���������
		if(file_exists($filename)){
			$fp = fopen($filename,"r");
			$hits = chop(fread($fp, filesize($filename)));
			fclose($fp);
		}
		if($hits == "")
			$hits = 0; //�����¼�ļ�������,���ʼ������Ϊ0
		else
			$hits = (int)$hits;
		$hits ++;
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
	
	//��ʽ��ͼƬ����ʾ����
	$resource_dir = "count"; //Ŀ¼
	$id = $_GET['id'];
	$width = $_GET['w'];
	$style = $_GET['s'];
	$id = intval($id);
	if($id < 0 || $id >16) $id = 0; //Ĭ��id = 0
	$width = (int) $width;
	if($width < 4) $width = 4;
	if($width > 12) $width = 12;
	if($style == "") $style = "0";
	elseif(!file_exists("./".$resource_dir."/".$style."/")) $style = "0";
	
	$hits = gethits("./".$resource_dir."/".$id.".txt");
	$hits = sprintf("%0".$width."d", $hits); //��ʽ�����
	$path = ConvertToImage($hits);
	echo "���Ѿ�������<img src='".$path."'>��";
?>