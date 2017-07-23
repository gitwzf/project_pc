<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> 显示指定ISBN的书本目录 </TITLE>
<META NAME="Author" CONTENT="cheng">
<META NAME="Keywords" CONTENT="显示指定ISBN的书本目录">
<META NAME="Description" CONTENT="显示指定ISBN的书本目录">
<style>
body{ font-size:12px};
td{ font-size:12px};
</style>
</HEAD>
<BODY>
	<b>显示指定ISBN的书本目录</b>
	<hr>
	<?php
		require("common.php");
		$currentTag1 = "";
		$chapters = array();
		$chapterNo = 0;
		$modules = array();
		$modulesNo = 0;
		//开始
		function startBook($parser, $name, $attr){
			global $currentTag1 ;
			$currentTag1 = $name;
		}
		//结束
		function endBook($parser, $name){
			global $chapterNo , $modulesNo;
			if(strcmp($name, "chapter") == 0){
				$chapterNo ++;
			}elseif( strcmp($name, "module") == 0){
				$modulesNo ++;
			}
		}
		//回调函数
		function bookcharacterData($parser, $data){
			global $chapters, $chapterNo, $modules, $modulesNo,$currentTag1;
			if(strcmp($currentTag1, "chapter") == 0){
				$chapters[$chapterNo] .= $data;
			}else if (strcmp($currentTag1, "module") == 0){
				$modules[$modulesNo] .= $data;
			}
		}

		$books = parserBookInfo();
		//参数获取
		$isbn = $_GET["isbn"];
		if(!($book = searchBookByISBN($books, $isbn))){
			die("你查找的ISBN对应的书籍不存在！");
		}
		$title = $book["title"];
		$authors = $book["authors"];
		//打印输出图书信息
		echo "<br><b><font size='6'>".$title."</font></b>";
		echo "作者: ";
		for($j = 0 ; $j < count($authors); $j ++){
			if($j >0)
				echo ", ";
			echo "".$authors[$j];
		}
		//解析对应的xml文档
		$xml_parser = xml_parser_create();
		xml_set_element_handler($xml_parser, "startBook", "endBook");
		xml_set_character_data_handler($xml_parser, "bookcharacterData");
		xml_parser_set_option($xml_parser, XML_OPTION_CASE_FOLDING, 0);

		if(!($fp = fopen($book["description"], "r"))){
			die("不能读取文件".$file);
		}
		while (($data = fread($fp, 4096))){
			if(!xml_parse($xml_parser, $data, feof($fp))){
				die( printf ("XML读取错误，在 %d 行 %d 列", 
				xml_get_current_line_number($xml_parser),
				xml_get_current_column_number($xml_parser)));
			}
		}
		xml_parser_free($xml_parser);
	?>
	<hr>
	<b>内容信息表</b>
	<br>
	<?php
		for($i = 0; $i < count($chapters)-1; $i ++){
			echo "第 ".($i+1)." 章:".$chapters[$i]." <br>";
		}
		echo "<br>";
		for($i = 0;$i < count($modules)-1; $i ++){
			$j = chr(ord("A")+$i);
			printf("系统模块 %s : %s <br>\n", $j , $modules[$i]);
		}
	?>
</BODY>
</HTML>
