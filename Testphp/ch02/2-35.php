<?php
$i = 0;
while ($i++ < 5) {
    echo "��ӡ��1�仰<br>\n";
    while (1) {
        echo "&nbsp;&nbsp;��ӡ��2�仰<br>\n";
        while (1) {
            echo "&nbsp;&nbsp;��ӡ��3�仰<br>\n";
            continue 3;
        }
        echo "��ӡ��4�仰.<br>\n";
    }
    echo "��ӡ��5�仰.<br>\n";
}
?>