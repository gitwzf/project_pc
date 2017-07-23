<?php
//推荐专辑
require_once('../config.php');
require_once('../public.php');
require_once('../inc/db.class.php');
if(!is_admin()) error_quit3("错误，您不是管理员或还没有登录！");
$rec_file = "recommend.no.php";
html_head("修改推荐专辑");
if(isset($rec_id)){ //进行修改
	$db->query("SELECT COUNT(*) FROM cd WHERE cd_id = $rec_id");
	$chk = $db->num_rows();
	$db->close();
	if($chk == 0) {
		echo "错误的CD_ID: $rec_id <a href='$PHP_SELF'>返回</a>\n";
	} else {
		if($fd = fopen($rec_file, "w")) {
			fputs($fd, "<? \$recom_id = ".$rec_id."; ?>\n");
			fclose($fd);
			echo "修改成功！ <a href='$PHP_SELF'>返回</a>\n";
		} else {
			echo "无法打开 ".$rec_file."!  <a href='$PHP_SELF'>返回</a>\n";
		}
	}
}else { //选择歌手
	include_once($rec_file);
	echo "<h3>推荐专辑(1) -- 输入ID</h3>"
	."<form method=post action='".$_SERVER['PHP_SELF']."'>\n"
	."输入专辑ID：<input type=text name=rec_id value='$recom_id' class=input>\n"
	."<input type=submit class=button value='确定'>\n"
	."</form>\n";
}
echo "</body></html>";
exit;
?>