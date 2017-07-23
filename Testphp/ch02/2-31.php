<?php
/* 例子 1 */
for ($i = 1; $i <= 10; $i++) {
    echo $i;
}
echo "<br>";

/* 例子 2 */
for ($i = 1; ; $i++) {
    if ($i > 10) {
        break;
    }
    echo $i;
}
echo "<br>";
/* 例子 3 */
$i = 1;
for (;;) {
    if ($i > 10) {
        break;
    }
    echo $i;
    $i++;
}
echo "<br>";
/* 例子 4 */
for ($i = 1; $i <= 10; print $i, $i++);
?>