<?php
 $title    = "ģ�����";
 $file     = "����һ��ģ����Ե����ӡ�<br>���ߣ�cheng";
 $fp       = fopen("D:\\work\\web-apps\\phpbook\\ch09\\template.htm","r");
 $content  = fread ($fp , filesize("template.htm"));
 $content  = str_replace ("{content}" , $file, $content);
 $content  = str_replace ("{title}" , $title , $content);

 echo $content;
?>