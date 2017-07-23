<?php
$count = $_GET["count"];
if(!isset($count))$count = 1;
switch($count)
{
	case 1:
		echo '第一次访问页面，欢迎！';
		break;
	case 2:
		echo '第二次访问该页面';
		break;
	case 3:
		echo '第三次访问该页面';
		break;
	case 4:
		echo '第四次访问该页面';
		break;
	case 5:
		echo '第五次访问该页面';
		break;
	default:
		echo '已经超过五次访问该页面了';
		break;
}
$count ++;
echo '点击<a href="3-1.php?count='.$count.'">这儿</a>查看信息';
?>