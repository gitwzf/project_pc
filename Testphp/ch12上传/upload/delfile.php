<?php
  $id=$_REQUEST['id'];
  include('../inc/config.inc.php');
  include('../inc/db.class.php');
  include('../inc/pageft.php');
  $db=new db;//从数据库操作类生成实例
  $db->mysql($dbhost,$dbuser,$dbpassword,$dbname);//调用连接参数函数
  $db->createcon();//调用创建连接函数
  $result=$db->query("select * from uploadfile where f_id=$id");
  $row=$db->loop_query($result);
  unlink($row[f_url]);//删除该文件
  $db->query("delete from uploadfile where f_id=$id");//删除对应的数据库记录
  echo"<script language=\"javascript\">alert(\"成功删除文件！\")</script>";
  echo "<meta http-equiv=\"refresh\" content=\"0;URL=../admin/upfileman.php\">";
  $db->close();//关闭连接
?>
