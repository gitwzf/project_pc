<?php
/* Р§зг 1 */
$i = 1;
while ($i <= 10) {
    print $i++;  
}
print "<br>";
/* Р§зг2 */
$i = 1;
while ($i <= 10):
    print $i;
    $i++;
endwhile;
?>