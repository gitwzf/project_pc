<?php
  $id=$_REQUEST['id'];
  include('../inc/config.inc.php');
  include('../inc/db.class.php');
  include('../inc/pageft.php');
  $db=new db;//�����ݿ����������ʵ��
  $db->mysql($dbhost,$dbuser,$dbpassword,$dbname);//�������Ӳ�������
  $db->createcon();//���ô������Ӻ���
  $result=$db->query("select * from uploadfile where f_id=$id");
  $row=$db->loop_query($result);
  unlink($row[f_url]);//ɾ�����ļ�
  $db->query("delete from uploadfile where f_id=$id");//ɾ����Ӧ�����ݿ��¼
  echo"<script language=\"javascript\">alert(\"�ɹ�ɾ���ļ���\")</script>";
  echo "<meta http-equiv=\"refresh\" content=\"0;URL=../admin/upfileman.php\">";
  $db->close();//�ر�����
?>
