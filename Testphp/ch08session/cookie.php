<?php
if ($_GET['setcookie'] ==true) {
	setcookie("phpbook[0]", "�ڰ���",time()+3600);
	setcookie("phpbook[1]", "����",time()+3600);
	setcookie("phpbook[2]", "��������",time()+3600);
	echo 1;
}
if ($_GET['unsetcookie'] ==true) {
	setcookie("phpbook[0]", "�ڰ���", time()-3600);
	setcookie("phpbook[1]", "����", time()-3600);
	setcookie("phpbook[2]", "��������", time()-3600);
	echo 2;
}
if ($_GET['view'] ==true) {
	echo "phpbook[0] = <" .$_COOKIE['phpbook'][0]. "><br>";
	echo "phpbook count = " . count($_COOKIE['phpbook']);
}
echo "��ʼ����cookie>>>" ."<a href='file.php?setcookie=true'>��ʼ����</a>" ."<br>";
echo "ȡ���Ѿ�����cookie>>>" ."<a href='file.php?unsetcookie=true'>ȡ������</a>" ."<br>";
echo "�鿴�Ѿ�����cookie>>>" ."<a href='file.php?view=true'>�鿴����</a>" ."<br>";

?>