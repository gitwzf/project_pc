<?php
  $id=$_REQUEST['id'];
  include('../inc/config.inc.php');
  include('../inc/db.class.php');
  $db=new db;//�����ݿ����������ʵ��
  $db->mysql($host,$user,$password,$database);//�������Ӳ�������
  $db->createcon();//���ô������Ӻ���
  $db->query("update uploadfile set f_status=1 where f_id=$id");//�����ļ�״̬
  echo "<meta http-equiv=\"refresh\" content=\"0;URL=upfileman.php\">";
  $db->close();//�ر����ݿ�����
?>
