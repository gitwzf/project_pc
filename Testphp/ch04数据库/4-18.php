<?php
//���ӵ����ݿ�����������ʧ�ܾ���ֹ�ű�������ִ��
$connect = @mysql_connect('localhost','root','pass') or die('�������ӵ����ݿ⣡');
//ѡ�����ݿ⣬���ʧ�ܾ���ֹ�ű�������ִ��
mysql_select_db('phpbook') or die('����ѡ������ݿ⣡');
$query = 'SELECT * FROM friends';
mysql_query($query);
if(mysql_errno()){
//�������ִ�����󣬾͸���һ����ʾ��Ϣ
//�������Ĵ��ź��ı�����
echo "ִ�г�����:".mysql_errno()."".mysql_error()."<br>";
}else{
//����ִ�����󣬸���һ����ʾ
echo "û���κδ��󣬿��Խ��������Ĳ�����";
}
mysql_close($connect);
?>