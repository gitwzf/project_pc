<?php
//��������
define("CONSTANT", "Hello,phpbook.");
echo "|first=".CONSTANT;//��ӡ������Ϊ"Hello,phpbook."
echo "|second=".Constant;//��ӡ������Ϊ"Constant"����Ϊǰ��û�ж���
define("CONSTANT", "Hello,phpbook.",true);
//��ӡ������Ϊ"Hello,phpbook.",��Ϊ�趨�˲����ִ�Сд
echo "|third=".Constant;
?>