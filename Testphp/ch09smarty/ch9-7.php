<?php
ob_start();
require('Smarty.class.php');
$smarty = new Smarty;
$id = $_POST['id'];
if(isset($id) && is_integer($id))
{
	$conn = mysql_connect('localhost','root','admin','phpbook');
	mysql_select_db("phpbook", $conn);
	$result = mysql_query("select * from article where id=".$id);
	while ($row = mysql_fetch_array($result)) {
		$smarty->assign(array("article_title",htmlspecialchars($result['title']),
		"article_content",$result['content']));
	}
	$smarty->display('article_index.tpl');
	//获取文章内容
	$this_my_f= ob_get_contents();
	ob_end_clean();
	$filename = $id.".html";
	if(tohtmlfile_cw($filename,$this_my_f))
	echo "成功生成文件". $filename;
	else
	echo "失败！";
	mysql_close($conn);
}
//生成文件函数
function tohtmlfile_cw($file_cw_name,$file_cw_content)
{
	if (is_file ($file_cw_name)){
		@unlink ($file_cw_name);
	}
	$cw_handle = fopen ($file_cw_name,"w");
	if (!is_writable ($file_cw_name)){
		return false;
	}
	if (!fwrite ($cw_handle,$file_cw_content)){
		return false;
	}
	fclose ($cw_handle); //关闭指针
	return $file_cw_name;
}
?>