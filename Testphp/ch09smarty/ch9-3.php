<?php
	$title    = "����ģ��";
	$file     = "����һ��ģ����Ե����ӡ�<br>���ߣ�cheng";
	$fp = fopen("D:\\work\\web-apps\\phpbook\\ch09\\template.htm","r");
	$content  = fread($fp,filesize("template.htm"));
	$content  = str_replace("{content}",$file,$content);
	$content  = str_replace("{title}",$title,$content);

	//echo $content;
   
	$filename = "testtp.html";
	// ��������Ҫȷ���ļ����ڲ��ҿ�д��
	if (is_writable($filename)) {

		if (!$handle = fopen($filename, 'w')) {
			print "���ܴ��ļ� $filename";
			exit;
		}
		//��$somecontentд�뵽���Ǵ򿪵��ļ��С�
		if (!fwrite($handle, $content)) {
			print "����д�뵽�ļ� $filename";
			exit;
		}
		print "�ɹ��ؽ� $somecontent д�뵽�ļ�$filename";
		fclose($handle);

	} else {
		print "�ļ� $filename ����д";
	}
?>