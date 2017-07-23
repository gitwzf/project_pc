<?php
//自PHP 4.1.0版本起，下面的方法均可使用
   echo $_POST['username'];
   echo $_REQUEST['username'];
   import_request_variables('p', 'p_');
   echo $p_username;
//自PHP 3开始HTTP_POST_VARS参数可以被使用
   echo $HTTP_POST_VARS['username'];
//如果PHP配置文件中的register_globals参数被设置为on 时，可以直接使用表单标签名名称。注意，从PHP 4.2.0版本开始默认值为register_globals 为off。所以不推荐使用此种方法。
   echo $username;
?>
