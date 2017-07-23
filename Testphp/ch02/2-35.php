<?php
$i = 0;
while ($i++ < 5) {
    echo "打印第1句话<br>\n";
    while (1) {
        echo "&nbsp;&nbsp;打印第2句话<br>\n";
        while (1) {
            echo "&nbsp;&nbsp;打印第3句话<br>\n";
            continue 3;
        }
        echo "打印第4句话.<br>\n";
    }
    echo "打印第5句话.<br>\n";
}
?>