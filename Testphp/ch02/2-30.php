<?php
/* ���� 1 */
$i = 1;
while ($i <= 10) {
    print $i++;  
}
print "<br>";
/* ����2 */
$i = 1;
while ($i <= 10):
    print $i;
    $i++;
endwhile;
?>