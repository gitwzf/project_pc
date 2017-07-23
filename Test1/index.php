<html>
<head>
<title>文件管理综合应用</title>
</head>
<body>
<table border=0 width=100%>
<tr>
<td align=center bgcolor=yellow>
<font size=4 color=green>我的PHP文件浏览器</font>
</td>
</tr>
</table>
<table border=0 width=100%>
<tr align=center bgcolor=pink>
<th>文件名</th><th>大小</th><th>创建时间</th><th>修改时间</th>
</tr>
<?php
//系统设置
unset($GLOBALS,$_ENV,$HTTP_ENV_VARS,$_REQUEST,$HTTP_POST_VARS,$HTTP_GET_VARS,$HTTP_POST_FILES,$HTTP_COOKIE_VARS);
if(!ini_get('register_globals')){
	@extract($_POST,EXTR_SKIP);
	@extract($_GET,EXTR_SKIP);
	@extract($_COOKIE,EXTR_SKIP);
	@extract($_FILES,EXTR_SKIP);
}
//如果目录没有设置，则设为当前目录
if(!isset($currentdir))$currentdir="./";
chdir($currentdir);
$id = opendir(".");
//循环读取目录信息
while($temp=readdir($id)){
	print("<tr bgcolor=lightblue><td width=30%>");
	//当$temp是一个目录时
	if(is_dir($temp)){
		//如果$temp是当前目录"."
		if($temp==".")
			print("<A HREF=\"".$PHP_SELF."?currentdir=".$currentdir."\">".$temp."</A>");
		//如果$temp是上一级目录".."
		elseif($temp==".."){
			//如果上一级目录不能通过截掉最后的目录名得到时
			//例如../的上一级目录是phpbook/时，只能通过../phpbook/得到
			if(strrpos($currentdir,".")==strlen($currentdir)-2){
				print("<A HREF=\"".$PHP_SELF."?currentdir=".$currentdir.$temp."/\">".$temp."</A>");
			}else{
				//如果上一级目录可以通过截掉最后的目录名得到时
				//例如../phpbook/haha/test的/上一级目录可截掉test/得到
				$tempdir = substr($currentdir,0,strlen($currentdir)-1);
				$tempdir = substr($tempdir,0,strrpos($tempdir,'/')+1);
				print("<A HREF=\"".$PHP_SELF."?currentdir=".$tempdir."\">".$temp."</A>");
			}
		}else print("<A HREF=\"".$PHP_SELF."?currentdir=".$currentdir.$temp."\">".$temp."</A>");
	}else{
		//当$temp是一个文件时
		//截取文件的扩展名
		$extname=substr($temp,strrpos($temp,"."),strlen($temp)-strrpos($temp,"."));
		$extname=strtoupper($extname);
		//如果文件类型为txt/htm/html则可以显示
		if($extname==".TXT"||$extname==".HTM"||$extname==".HTML")
		print("<A HREF=\"managefile.php?currentdir=".$currentdir."&filename=".$temp."&type=".$extname."\">".$temp."</A>");
		else
		print($temp);
	}
	//显示与文件/目录的有关信息，包括大小、创建时间和修改时间
	print("<td width=20% align=right>".(is_dir($temp)?"目录":round(filesize($temp)/1024)."K")."</td>");
	print("<td width=25% align=right>".date("y-m-dh:i:sA",filectime($temp))."</td>");
	print("<td width=25% align=right>".date("y-m-dh:i:sA",filemtime($temp))."</td>");
	print("</td></tr>\n");
}
closedir($id);
?>
</table>
</body>
</html>