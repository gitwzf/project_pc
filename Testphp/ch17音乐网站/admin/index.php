<?php
//管理功能
require('../config.php');
require('../public.php');

if(!is_admin())  error_quit3("错误，您不是管理员或还没有登录！");
{
 print "
<html>
<head>
<title>音乐系统管理</title>
</head>

<frameset cols='150,*' border=0 frameborder=0> 
  <frame src=menu.php name=menu target=main frameborder=1>
  <frame src=main.php name=main>
</frameset>
<noframes>
<body bgcolor=#FFFFFF>
 your browse dont support frames!
</body>
</noframes>
</html>
   ";
 exit;
}

?>