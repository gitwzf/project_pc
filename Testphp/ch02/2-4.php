<?php
$action = "show_book";
$show_br = true;
//ʹ�á�==���ж�
//���ز������ͣ�����if������֧�ж�
if ($action == "show_version") {
    echo "��ѡ�������ʾ��ǰ�汾���룡";
}
//����Ĵ��벻����Ҫ
if ($show_br == true) {
    echo "<br>\n";
}
//ʹ�ü򻯵��жϷ���
if ($show_br) {
    echo "<br>\n";
}
?>