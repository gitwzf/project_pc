<?php
session_start();
$_vars=Array(
   "_GET",
   "_POST",
   "_COOKIE",
   "_SESSION",
   "HTTP_POST_VARS",
   "HTTP_GET_VARS",
   "HTTP_COOKIE_VARS",
   "HTTP_SESSION_VARS",
   "_FILES",
   "_ENV");//把服务器内的相关变量放在这里 
reset($_vars);
for($Tmpa=0;$Tmpa<count($_vars);$Tmpa++){
   extract($$_vars[$Tmpa],"EXTR_PREFIX_SAME",$_vars[$Tmpa]);
   
   if(strcmp($_vars[$Tmpa],"_FILES")==0){
      while(list($key,$value)=each($$_vars[$Tmpa])){
         ${$key."_name"}   =$_FILES["$key"]["name"];
         ${$key."_type"}   =$_FILES["$key"]["type"];
         ${$key."_size"}   =$_FILES["$key"]["size"];
         $$key=${$key."_tmpname"}=$_FILES["$key"]["tmp_name"];
      }
   }
}
?>