<?php
//测试$GLOBALS使用效果
foreach ($GLOBALS as $key => $value) {
	echo "<font size='2'>Key: <font color='blue'>$key</font>; Value: <font color='red'>$value</font></font><br>\n";
}
?>