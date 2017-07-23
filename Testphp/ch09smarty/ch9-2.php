<?php
 $title    = "模板测试";
 $file     = "这是一个模版测试的例子。<br>作者：cheng";
 $fp       = fopen("D:\\work\\web-apps\\phpbook\\ch09\\template.htm","r");
 $content  = fread ($fp , filesize("template.htm"));
 $content  = str_replace ("{content}" , $file, $content);
 $content  = str_replace ("{title}" , $title , $content);

 echo $content;
?>