<?php
//FastTemplate ģ��ʾ��
header("Content-type: text/plain");
include("class.FastTemplate.php3");
$tpl = new FastTemplate("D:\work\web-apps\phpbook\ch09\fasttemplate");

$tpl->define(array(main => "main.tpl",table => "table.tpl",row => "row.tpl"));

$tpl->assign( array( TITLE => "FastTemplateģ����չʾʾ��") ); 

for ($n=1; $n <= 3; $n++)
{
	$Number = $n; 
	$BigNum = $n*10; 
	$tpl->assign( 
		array( 
			NUMBER => $Number, 
			BIG_NUMBER => $BigNum 
		) 
	); 
	$tpl->parse(ROWS,".row"); 
}

$tpl->parse(MAIN, array("table","main")); 

$tpl->FastPrint();
exit;
?>