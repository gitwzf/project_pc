<?php
$count = $_GET["count"];
if(!isset($count))$count = 1;
switch($count)
{
	case 1:
		echo '��һ�η���ҳ�棬��ӭ��';
		break;
	case 2:
		echo '�ڶ��η��ʸ�ҳ��';
		break;
	case 3:
		echo '�����η��ʸ�ҳ��';
		break;
	case 4:
		echo '���Ĵη��ʸ�ҳ��';
		break;
	case 5:
		echo '����η��ʸ�ҳ��';
		break;
	default:
		echo '�Ѿ�������η��ʸ�ҳ����';
		break;
}
$count ++;
echo '���<a href="3-1.php?count='.$count.'">���</a>�鿴��Ϣ';
?>