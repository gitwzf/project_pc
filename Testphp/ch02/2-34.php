<?php
$arr = array ('ch01', 'ch02', 'ch03', 'ch04', 'last', 'ch05');
while (list (, $chapter) = each ($arr)) {
    if ($chapter == 'last') {
        break;    /* �������д 'break 2;'. */
    }
    echo "$chapter <br>\n";
}
/* ʹ��break+���ֵ����. */
$i = 0;
while (++$i) {
    switch ($i) {
      case 4:
        echo "��ǰi��ֵΪ4.<br>\n";
        break 1;  /*ֻ����switch. */
      case 8:
        echo "��ǰi��ֵΪ8.<br>\n";
        break 2;  /*ͬʱ����switch��while. */
      default:
        break;
    }
}
?>