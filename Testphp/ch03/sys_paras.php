<?php
//PHP��ȡ������ʾ
echo "------------ 4.2.0 ��ǰ�汾Ч��ʾ�� ----------------";
echo "<br>�ļ����ڵ��ĵ���Ŀ¼".$DOCUMENT_ROOT;
echo "<br>��Ҫ���Ĳ���Ϊ��op=".$op.";user=".$user;
echo "<br>------------ 4.2.0 �Ժ�汾Ч��ʾ�� ----------------";
$op = $_GET['op'];
$user = $_GET['user'];
echo "<br>�ļ����ڵ��ĵ���Ŀ¼".$_SERVER['DOCUMENT_ROOT'];
echo "<br>��Ҫ���Ĳ���Ϊ��op=".$op.";user=".$user;
?>