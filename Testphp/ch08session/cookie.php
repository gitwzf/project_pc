<?php
if ($_GET['setcookie'] ==true) {
	setcookie("phpbook[0]", "第八章",time()+3600);
	setcookie("phpbook[1]", "作者",time()+3600);
	setcookie("phpbook[2]", "出版日期",time()+3600);
	echo 1;
}
if ($_GET['unsetcookie'] ==true) {
	setcookie("phpbook[0]", "第八章", time()-3600);
	setcookie("phpbook[1]", "作者", time()-3600);
	setcookie("phpbook[2]", "出版日期", time()-3600);
	echo 2;
}
if ($_GET['view'] ==true) {
	echo "phpbook[0] = <" .$_COOKIE['phpbook'][0]. "><br>";
	echo "phpbook count = " . count($_COOKIE['phpbook']);
}
echo "开始设置cookie>>>" ."<a href='file.php?setcookie=true'>开始设置</a>" ."<br>";
echo "取消已经设置cookie>>>" ."<a href='file.php?unsetcookie=true'>取消设置</a>" ."<br>";
echo "查看已经设置cookie>>>" ."<a href='file.php?view=true'>查看设置</a>" ."<br>";

?>