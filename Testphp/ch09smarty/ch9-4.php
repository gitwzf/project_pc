<?php
$title = "������ݿ��ģ����Ծ�̬ҳ��";
$content   = "����һ��ģ����Ե����ӣ����������ݿ�����Ĳ��֡�";

$fp       = fopen ("temp.html","r");
$content  = fread ($fp,filesize ("temp.html"));
$content .= str_replace ("{file}",$file,$content);
$content .= str_replace ("{title}",$title,$content);

//  �����б�ʼ
$list = "";
$link = mysql_connect("localhost", "user", "password")
        or die("���ӵ����ݿ�ʧ��: " . mysql_error());mysql_select_db('503',$link);
$sql  = "select id,title,filename from article";
$query = mysql_query ($sql) or die("��ѯʧ�ܣ�");
while ($result = mysql_fetch_array ($query)){
  $list .= '<a href='.$result['filename'].' target=_blank>'.$result['title'].'</a><br>';
}
$content .= str_replace ("{article}",$list,$content);

//��ʼ���ɾ�̬ҳ��
$filename = "test/test.html";
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