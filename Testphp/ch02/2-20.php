<?php
//不是静态变量的例子
function myfunc(){
	  $int = 0;
	  echo ($int+1)."<br>";
}
myfunc();//结果为1
myfunc();//结果为1
myfunc();//结果为1
?>