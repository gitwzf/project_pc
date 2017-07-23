<?php
$title = "结合数据库的模板测试静态页面";
$content   = "这是一个模版测试的例子，加上了数据库操作的部分。";

$fp       = fopen ("temp.html","r");
$content  = fread ($fp,filesize ("temp.html"));
$content .= str_replace ("{file}",$file,$content);
$content .= str_replace ("{title}",$title,$content);

//  生成列表开始
$list = "";
$link = mysql_connect("localhost", "user", "password")
        or die("连接到数据库失败: " . mysql_error());mysql_select_db('503',$link);
$sql  = "select id,title,filename from article";
$query = mysql_query ($sql) or die("查询失败！");
while ($result = mysql_fetch_array ($query)){
  $list .= '<a href='.$result['filename'].' target=_blank>'.$result['title'].'</a><br>';
}
$content .= str_replace ("{article}",$list,$content);

//开始生成静态页面
$filename = "test/test.html";
// 首先我们要确定文件存在并且可写。
if (is_writable($filename)) {
	if (!$handle = fopen($filename, 'w')) {
		print "不能打开文件 $filename";
		exit;
	}
	//将$somecontent写入到我们打开的文件中。
	if (!fwrite($handle, $content)) {
		print "不能写入到文件 $filename";
		exit;
	}
	print "成功地将 $somecontent 写入到文件$filename";
	fclose($handle);

} else {
	print "文件 $filename 不可写";
}
?>