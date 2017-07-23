<?php
	$title    = "测试模板";
	$file     = "这是一个模版测试的例子。<br>作者：cheng";
	$fp = fopen("D:\\work\\web-apps\\phpbook\\ch09\\template.htm","r");
	$content  = fread($fp,filesize("template.htm"));
	$content  = str_replace("{content}",$file,$content);
	$content  = str_replace("{title}",$title,$content);

	//echo $content;
   
	$filename = "testtp.html";
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