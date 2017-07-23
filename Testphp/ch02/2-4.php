<?php
$action = "show_book";
$show_br = true;
//使用“==”判断
//返回布尔类型，用于if条件分支判断
if ($action == "show_version") {
    echo "您选择的是显示当前版本号码！";
}
//下面的代码不再需要
if ($show_br == true) {
    echo "<br>\n";
}
//使用简化的判断方法
if ($show_br) {
    echo "<br>\n";
}
?>