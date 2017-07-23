<?php
$arr = array ('ch01', 'ch02', 'ch03', 'ch04', 'last', 'ch05');
while (list (, $chapter) = each ($arr)) {
    if ($chapter == 'last') {
        break;    /* 这儿不能写 'break 2;'. */
    }
    echo "$chapter <br>\n";
}
/* 使用break+数字的情况. */
$i = 0;
while (++$i) {
    switch ($i) {
      case 4:
        echo "当前i的值为4.<br>\n";
        break 1;  /*只结束switch. */
      case 8:
        echo "当前i的值为8.<br>\n";
        break 2;  /*同时结束switch和while. */
      default:
        break;
    }
}
?>