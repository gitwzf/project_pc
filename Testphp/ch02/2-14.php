<?php
//常量定义
define("CONSTANT", "Hello,phpbook.");
echo "|first=".CONSTANT;//打印输出结果为"Hello,phpbook."
echo "|second=".Constant;//打印输出结果为"Constant"，因为前面没有定义
define("CONSTANT", "Hello,phpbook.",true);
//打印输出结果为"Hello,phpbook.",因为设定了不区分大小写
echo "|third=".Constant;
?>