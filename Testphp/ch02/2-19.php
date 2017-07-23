<?php
//静态变量的例子
function myfunc(){
	  static $int;
	  $int = 0;
	  echo ($int+1)."<br>";
}
myfunc();//结果为1
myfunc();//结果为2
myfunc();//结果为3
?>