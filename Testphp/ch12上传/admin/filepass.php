<?php
  $id=$_REQUEST['id'];
  include('../inc/config.inc.php');
  include('../inc/db.class.php');
  $db=new db;//从数据库操作类生成实例
  $db->mysql($host,$user,$password,$database);//调用连接参数函数
  $db->createcon();//调用创建连接函数
  $db->query("update uploadfile set f_status=1 where f_id=$id");//更改文件状态
  echo "<meta http-equiv=\"refresh\" content=\"0;URL=upfileman.php\">";
  $db->close();//关闭数据库连接
?>
