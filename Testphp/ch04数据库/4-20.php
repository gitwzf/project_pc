<?php
include_once("class.sqlserver.php");
//�����µ�DB��
$SQLSDB = new sqlserver();
//���µ����ݿ����ӣ���ɳ�ʼ������
$SQLSDB->Connect();
//��ʼ����sql��ѯ����
$selectsql="select * from phpbook";
$SQLSDB->Query($selectsql);
//ѭ�������ѯ�õ��Ľ��
while($Row=$SQLSDB->NextRecord()){
	   echo $Row["title"];
}
//�ر����ݿ�����
$SQLSDB->Close();
?>