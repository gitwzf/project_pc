<?
//���ӵ����ݿ������
$connection = mysql_connect("localhost","root","");
//����һ�����ݿ�
$create_db = mysql_create_db("testdb",$connection);
if(!$create_db){
echo "���ܴ������ݿ⣬��͹���Ա��ϵ��<br>";
}else{
echo "���ݿ�ɹ�������<br>";
//ѡ��һ�����ݿ�
$select_db = mysql_select_db("testdb",$connection);
if($select_db){
echo "�ɹ�ѡ�����ݿ⣡<br>";
}else{
echo "�������ݿ�ʧ�ܣ�<br>";
}
//ɾ��һ�����ݿ�
$drop_db = mysql_drop_db("testdb",$connection);
if($drop_db){
echo	"�ɹ�ɾ�����ݿ⣡<br>";
}else{
echo "ɾ�����ݿ�ʧ�ܣ�<br>";
}
}
?>