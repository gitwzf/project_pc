<?php
//������
require('../config.php');
require('../public.php');

if(!is_admin())  error_quit3("���������ǹ���Ա��û�е�¼��");
{
 print "
<html>
<head>
<title>����ϵͳ����</title>
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