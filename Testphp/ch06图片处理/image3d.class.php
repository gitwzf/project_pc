<?php
	//变量定义，画椭圆弧时的角度大小
	define(ANGLELENGTH,3);
	/**
	 * 绘制图片
	 * @param $title	3D图的标题
	 * @param $dataArr	显示的数据数组
	 * @param $labelArr	对应数据的标签分类数组
	 * @param $colorArr	对应绘图颜色的数组
	 * @param $a		画布的基准宽度
	 * @param $b		画布的基准高度
	 * @param $v		3D柱的高度
	 * @param $font		字体大小
	 * @return 			绘制成功的图片访问路径
	 */
	function drawPieImg($title, $dataArr, $labelArr, $colorArr, $a=250, $b=120, $v=20, $font=10){
		$ox = 5+$a;
		$oy = 5+$b;
		$fw = imagefontwidth($font);
		$fh = imagefontheight($font);
		$n = count($dataArr);//计算数组长度
		$w = 10+$a*2;
		$h = 10+$b*2+$v+($fh+2)*$n;
		//创建画板
		$img = imagecreate($w, $h);
		//转RGB为索引色
		for($i=0; $i<$n; $i++)
			$colorArr[$i] = drawIndexColor($img,$colorArr[$i]);//为图像$img分配颜色
		$clrbk = imagecolorallocate($img, 0xff, 0xff, 0xff);
		$clrt = imagecolorallocate($img, 0x00, 0x00, 0x00);
		//填充背景色
		imagefill($img, 0, 0, $clrbk);
		//求和
		$tot = 0;
		for($i=0; $i<$n; $i++)
			$tot += $dataArr[$i];
		//每个分类的起始角度大小
		$sd = 0;
		//每个分类所占据的角度大小
		$ed = 0;
		$ly = 10+$b*2+$v;
		for($i=0; $i<$n; $i++){
			$sd = $ed;
			$ed += $dataArr[$i]/$tot*360;
			//画3d扇面
			draw3DSector($img, $ox, $oy+20, $a, $b, $v, $sd, $ed, $colorArr[$i]);
			//画标签
			imagefilledrectangle($img, 5, $ly, 5+$fw, $ly+$fh, $colorArr[$i]);
			imagerectangle($img, 5, $ly, 5+$fw, $ly+$fh, $clrt);
			//中文转码
			$str = iconv("GB2312", "UTF-8", $labelArr[$i]);
			imagettftext($img, $font, 0, 5+2*$fw, $ly+13, $clrt, "./simhei.ttf", $str.":".$dataArr[$i]."(".(round(10000*($dataArr[$i]/$tot))/100)."%)");
			$ly += $fh+2;
		}
		//绘制图片标题
		imagettftext($img, 15, 0, 5, 15, $clrt, "./simhei.ttf", iconv("GB2312", "UTF-8",$title));
		//输出图形
		//header("Content-type: image/png");
		//输出生成的图片
		$imgFileName = "./".time().".png";
		imagepng($img,$imgFileName);
		return $imgFileName;
	}
	/**
	 * 绘制3d扇面
	 */
	function draw3DSector($img, $ox, $oy, $a, $b, $v, $sd, $ed, $clr) {
		drawSector($img, $ox, $oy, $a, $b, $sd, $ed, $clr);
		if($sd<180){
			list($red, $green, $blue) = drawDarkColor($img, $clr);
			//为图像分配颜色
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
	 * 绘制椭圆弧
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
	 * 绘制扇面
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
	 * 根据$clr颜色获取对应的柱的阴影色
	 * @param $img		图像
	 * @param $clr		颜色
	 * @return rgb颜色数组
	 */
	function drawDarkColor($img,$clr){
		$rgb = imagecolorsforindex($img,$clr);
		return array($rgb["red"]/2,$rgb["green"]/2,$rgb["blue"]/2);
	}
	/**
	 * 求角度$d对应的椭圆上的点坐标
	 *
	 * @param $a	横坐标
	 * @param $b	纵坐标
	 * @param $d	角度
	 * @return 对应椭圆点坐标
	 */
	function getExy($a, $b, $d){
		$d = deg2rad($d); 
		return array(round($a*cos($d)), round($b*sin($d)));
	}
	/**
	 * 为图像分配RGB索引色
	 */
	function drawIndexColor($img, $clr){ 
		$red = ($clr>>16) & 0xff;
		$green = ($clr>>8)& 0xff;
		$blue = ($clr) & 0xff;
		return imagecolorallocate($img, $red, $green, $blue);
	}
//测试示例
$title = "动物园动物种类分布情况";
$dataArr = array(20, 10, 20, 20, 10, 20, 30, 10); //数据
$labelArr = array("大象", "长颈鹿", "鳄鱼", "鸵鸟", "老虎", "狮子", "猴子", "斑马");//标签
$colorArr = array(0x99ff00, 0xff6666, 0x0099ff, 0xff99ff, 0xffff99, 0x99ffff, 0xff3333, 0x009999); //颜色
$result = drawPieImg($title, $dataArr,$labelArr,$colorArr);
echo "<img src='./".$result."'/>";
?>