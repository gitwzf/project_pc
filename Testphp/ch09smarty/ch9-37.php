<?php
//FastTemplate 模版示例
header("Content-type: text/plain");
include("class.FastTemplate.php3");
$tpl = new FastTemplate("D:\work\web-apps\phpbook\ch09\fasttemplate");
$tpl->define(array(main => "main.tpl",table => "table.tpl",row => "row.tpl"));
$tpl->assign( array( title => "FastTemplate模版表格展示示例") );
for ($i=1; $i <= 3; $i++)
{
	$record = "第".$i."条订购记录";
	$tpl->assign(array(	bookid => $i,record => $record));
	$tpl->parse(rows,".row");
}
$tpl->parse(main_body, array("table","main"));
$tpl->FastPrint();
exit;
?>