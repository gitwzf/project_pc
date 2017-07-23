<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> ��ʾָ��ISBN���鱾Ŀ¼ </TITLE>
<META NAME="Author" CONTENT="cheng">
<META NAME="Keywords" CONTENT="��ʾָ��ISBN���鱾Ŀ¼">
<META NAME="Description" CONTENT="��ʾָ��ISBN���鱾Ŀ¼">
<style>
body{ font-size:12px};
td{ font-size:12px};
</style>
</HEAD>
<BODY>
	<b>��ʾָ��ISBN���鱾Ŀ¼</b>
	<hr>
	<?php
		require("common.php");
		$currentTag1 = "";
		$chapters = array();
		$chapterNo = 0;
		$modules = array();
		$modulesNo = 0;
		//��ʼ
		function startBook($parser, $name, $attr){
			global $currentTag1 ;
			$currentTag1 = $name;
		}
		//����
		function endBook($parser, $name){
			global $chapterNo , $modulesNo;
			if(strcmp($name, "chapter") == 0){
				$chapterNo ++;
			}elseif( strcmp($name, "module") == 0){
				$modulesNo ++;
			}
		}
		//�ص�����
		function bookcharacterData($parser, $data){
			global $chapters, $chapterNo, $modules, $modulesNo,$currentTag1;
			if(strcmp($currentTag1, "chapter") == 0){
				$chapters[$chapterNo] .= $data;
			}else if (strcmp($currentTag1, "module") == 0){
				$modules[$modulesNo] .= $data;
			}
		}

		$books = parserBookInfo();
		//������ȡ
		$isbn = $_GET["isbn"];
		if(!($book = searchBookByISBN($books, $isbn))){
			die("����ҵ�ISBN��Ӧ���鼮�����ڣ�");
		}
		$title = $book["title"];
		$authors = $book["authors"];
		//��ӡ���ͼ����Ϣ
		echo "<br><b><font size='6'>".$title."</font></b>";
		echo "����: ";
		for($j = 0 ; $j < count($authors); $j ++){
			if($j >0)
				echo ", ";
			echo "".$authors[$j];
		}
		//������Ӧ��xml�ĵ�
		$xml_parser = xml_parser_create();
		xml_set_element_handler($xml_parser, "startBook", "endBook");
		xml_set_character_data_handler($xml_parser, "bookcharacterData");
		xml_parser_set_option($xml_parser, XML_OPTION_CASE_FOLDING, 0);

		if(!($fp = fopen($book["description"], "r"))){
			die("���ܶ�ȡ�ļ�".$file);
		}
		while (($data = fread($fp, 4096))){
			if(!xml_parse($xml_parser, $data, feof($fp))){
				die( printf ("XML��ȡ������ %d �� %d ��", 
				xml_get_current_line_number($xml_parser),
				xml_get_current_column_number($xml_parser)));
			}
		}
		xml_parser_free($xml_parser);
	?>
	<hr>
	<b>������Ϣ��</b>
	<br>
	<?php
		for($i = 0; $i < count($chapters)-1; $i ++){
			echo "�� ".($i+1)." ��:".$chapters[$i]." <br>";
		}
		echo "<br>";
		for($i = 0;$i < count($modules)-1; $i ++){
			$j = chr(ord("A")+$i);
			printf("ϵͳģ�� %s : %s <br>\n", $j , $modules[$i]);
		}
	?>
</BODY>
</HTML>
