<?php
	//�������壬����Բ��ʱ�ĽǶȴ�С
	define(ANGLELENGTH,3);
	/**
	 * ����ͼƬ
	 * @param $title	3Dͼ�ı���
	 * @param $dataArr	��ʾ����������
	 * @param $labelArr	��Ӧ���ݵı�ǩ��������
	 * @param $colorArr	��Ӧ��ͼ��ɫ������
	 * @param $a		�����Ļ�׼���
	 * @param $b		�����Ļ�׼�߶�
	 * @param $v		3D���ĸ߶�
	 * @param $font		�����С
	 * @return 			���Ƴɹ���ͼƬ����·��
	 */
	function drawPieImg($title, $dataArr, $labelArr, $colorArr, $a=250, $b=120, $v=20, $font=10){
		$ox = 5+$a;
		$oy = 5+$b;
		$fw = imagefontwidth($font);
		$fh = imagefontheight($font);
		$n = count($dataArr);//�������鳤��
		$w = 10+$a*2;
		$h = 10+$b*2+$v+($fh+2)*$n;
		//��������
		$img = imagecreate($w, $h);
		//תRGBΪ����ɫ
		for($i=0; $i<$n; $i++)
			$colorArr[$i] = drawIndexColor($img,$colorArr[$i]);//Ϊͼ��$img������ɫ
		$clrbk = imagecolorallocate($img, 0xff, 0xff, 0xff);
		$clrt = imagecolorallocate($img, 0x00, 0x00, 0x00);
		//��䱳��ɫ
		imagefill($img, 0, 0, $clrbk);
		//���
		$tot = 0;
		for($i=0; $i<$n; $i++)
			$tot += $dataArr[$i];
		//ÿ���������ʼ�Ƕȴ�С
		$sd = 0;
		//ÿ��������ռ�ݵĽǶȴ�С
		$ed = 0;
		$ly = 10+$b*2+$v;
		for($i=0; $i<$n; $i++){
			$sd = $ed;
			$ed += $dataArr[$i]/$tot*360;
			//��3d����
			draw3DSector($img, $ox, $oy+20, $a, $b, $v, $sd, $ed, $colorArr[$i]);
			//����ǩ
			imagefilledrectangle($img, 5, $ly, 5+$fw, $ly+$fh, $colorArr[$i]);
			imagerectangle($img, 5, $ly, 5+$fw, $ly+$fh, $clrt);
			//����ת��
			$str = iconv("GB2312", "UTF-8", $labelArr[$i]);
			imagettftext($img, $font, 0, 5+2*$fw, $ly+13, $clrt, "./simhei.ttf", $str.":".$dataArr[$i]."(".(round(10000*($dataArr[$i]/$tot))/100)."%)");
			$ly += $fh+2;
		}
		//����ͼƬ����
		imagettftext($img, 15, 0, 5, 15, $clrt, "./simhei.ttf", iconv("GB2312", "UTF-8",$title));
		//���ͼ��
		//header("Content-type: image/png");
		//������ɵ�ͼƬ
		$imgFileName = "./".time().".png";
		imagepng($img,$imgFileName);
		return $imgFileName;
	}
	/**
	 * ����3d����
	 */
	function draw3DSector($img, $ox, $oy, $a, $b, $v, $sd, $ed, $clr) {
		drawSector($img, $ox, $oy, $a, $b, $sd, $ed, $clr);
		if($sd<180){
			list($red, $green, $blue) = drawDarkColor($img, $clr);
			//Ϊͼ�������ɫ
			$clr=imagecolorallocate($img, $red, $green, $blue);
			if($ed>180)
				$ed = 180;
			list($sx, $sy) = getExy($a,$b,$sd);
			$sx += $ox;
			$sy += $oy;
			list($ex, $ey) = getExy($a, $b, $ed);
			$ex += $ox;
			$ey += $oy;
			imageline($img, $sx, $sy, $sx, $sy+$v, $clr);
			imageline($img, $ex, $ey, $ex, $ey+$v, $clr);
			drawArc($img, $ox, $oy+$v, $a, $b, $sd, $ed, $clr);
			list($sx, $sy) = getExy($a, $b, ($sd+$ed)/2);
			$sy += $oy+$v/2;
			$sx += $ox;
			imagefill($img, $sx, $sy, $clr);
		} 
	}
	/**
	 * ������Բ��
	 */
	function drawArc($img,$ox,$oy,$a,$b,$sd,$ed,$clr){
		$n = ANGLELENGTH >0 ? ceil(($ed-$sd)/ANGLELENGTH) : -1;
		$d = $sd;
		list($x0,$y0) = getExy($a,$b,$d);
		for($i=0; $i<$n; $i++){
			$d = ($d+ANGLELENGTH)>$ed?$ed:($d+ANGLELENGTH);
			list($x, $y) = getExy($a, $b, $d);
			imageline($img, $x0+$ox, $y0+$oy, $x+$ox, $y+$oy, $clr);
			$x0 = $x;
			$y0 = $y;
		}
	}
	/**
	 * ��������
	 */
	function drawSector($img, $ox, $oy, $a, $b, $sd, $ed, $clr) {
		$n = ANGLELENGTH > 0 ? ceil(($ed-$sd)/ANGLELENGTH) : -1;
		$d = $sd;
		list($x0,$y0) = getExy($a, $b, $d);
		imageline($img, $x0+$ox, $y0+$oy, $ox, $oy, $clr);
		for($i=0; $i<$n; $i++) {
			$d = ($d+ANGLELENGTH)>$ed?$ed:($d+ANGLELENGTH);
			list($x, $y) = getExy($a, $b, $d);
			imageline($img, $x0+$ox, $y0+$oy, $x+$ox, $y+$oy, $clr);
			$x0 = $x;
			$y0 = $y;
		}
		imageline($img, $x0+$ox, $y0+$oy, $ox, $oy, $clr);
		list($x, $y) = getExy($a/2, $b/2, ($d+$sd)/2);
		imagefill($img, $x+$ox, $y+$oy, $clr);
	}
	/**
	 * ����$clr��ɫ��ȡ��Ӧ��������Ӱɫ
	 * @param $img		ͼ��
	 * @param $clr		��ɫ
	 * @return rgb��ɫ����
	 */
	function drawDarkColor($img,$clr){
		$rgb = imagecolorsforindex($img,$clr);
		return array($rgb["red"]/2,$rgb["green"]/2,$rgb["blue"]/2);
	}
	/**
	 * ��Ƕ�$d��Ӧ����Բ�ϵĵ�����
	 *
	 * @param $a	������
	 * @param $b	������
	 * @param $d	�Ƕ�
	 * @return ��Ӧ��Բ������
	 */
	function getExy($a, $b, $d){
		$d = deg2rad($d); 
		return array(round($a*cos($d)), round($b*sin($d)));
	}
	/**
	 * Ϊͼ�����RGB����ɫ
	 */
	function drawIndexColor($img, $clr){ 
		$red = ($clr>>16) & 0xff;
		$green = ($clr>>8)& 0xff;
		$blue = ($clr) & 0xff;
		return imagecolorallocate($img, $red, $green, $blue);
	}
//����ʾ��
$title = "����԰��������ֲ����";
$dataArr = array(20, 10, 20, 20, 10, 20, 30, 10); //����
$labelArr = array("����", "����¹", "����", "����", "�ϻ�", "ʨ��", "����", "����");//��ǩ
$colorArr = array(0x99ff00, 0xff6666, 0x0099ff, 0xff99ff, 0xffff99, 0x99ffff, 0xff3333, 0x009999); //��ɫ
$result = drawPieImg($title, $dataArr,$labelArr,$colorArr);
echo "<img src='./".$result."'/>";
?>