<?php
//��̬����������
function myfunc(){
	  static $int;
	  $int = 0;
	  echo ($int+1)."<br>";
}
myfunc();//���Ϊ1
myfunc();//���Ϊ2
myfunc();//���Ϊ3
?>