<?php
//常量定义
$file = "books.xml";
$currentTag = "";
$title = "";
$authors = array();
$isbn = "";
$date = "";
$zone = "";
$description = "";
$authorCount = 0;
$books = array();
function trustedFile($file)
{
    //仅仅处理本地的文件
    if (!eregi("^([a-z]+)://", $file)
        && fileowner($file) == getmyuid()) {
            return true;
    }
    return false;
}
//开始函数
function startElement($parser, $name, $attr){
	global $currentTag, $zone;
	$currentTag = $name;
	if(strcmp($name, "date") == 0){
		$zone = $attr["zone"];
	}
}
//结束函数
function endElement($parser, $name){
	global $title, $authors , $isbn, $date, $zone,
		$books, $authorCount, $description;
	if( strcmp($name, "book") == 0){
		$books[] = array("title"=>$title,
				"authors" => $authors,"isbn"=>$isbn,
				"date"=>$date,"zone"=>$zone,
				"description"=>$description
			);
		$title = "";
		$authors = array();
		$isbn = "";
		$date = "";
		$zone = "";
		$description = "";
	}elseif ( strcmp($name, "author") ==0){
		$authorCount ++;
		$authors[$authorCount] = "";
	}
}
//字符串回调函数
function characterData($parser, $data){
	global $title, $authors, $isbn, $date, $currentTag, $authorCount;
	if( strcmp($currentTag, "title") ==0 ){
		$title .= $data;
	}elseif ( strcmp($currentTag, "author") ==0){
		$authors .= $data; 
	}elseif ( strcmp($currentTag, "isbn") == 0){
		$isbn .= $data;
	}elseif ( strcmp($currentTag, "date") == 0){
		$date .= $data;
	}
}
//外部回调函数
function externalEntityHandler($parser, $entityName, $base,
	$systemId, $productId){
	global $description;
	if(!$systemId)
		return false;
	$description = $systemId;
	return true;
}
//建立处理指令处理器
function PIHandler($parser, $target, $data)
{
    switch (strtolower($target)) {
        case "php":
            global $parser_file;
            //验证要解析的文档是否安全，如果不是安全的，则首先显示它的代码
            if (trustedFile($parser_file[$parser])) {
                eval($data);
            } else {
                echof("不安全的PHP代码: <i>%s</i>",
                        htmlspecialchars($data));
            }
            break;
    }
}
function defaultHandler($parser, $data)
{
    if (substr($data, 0, 1) == "&" && substr($data, -1, 1) == ";") {
        printf('<font color="#aa00aa">%s</font>',
                htmlspecialchars($data));
    } else {
        printf('<font size="2" color="#7EA0FA">%s</font>',
                htmlspecialchars($data));
    }
}
//解析XML文档函数
function parserBookInfo(){
	global $file, $books;
	$xml_parser = xml_parser_create();
	xml_parser_set_option($xml_parser, XML_OPTION_CASE_FOLDING, 0);
	xml_set_element_handler($xml_parser, "startElement", "endElement");
	xml_set_character_data_handler($xml_parser, "characterData");
	xml_set_processing_instruction_handler($xml_parser,"PIHandler");
	xml_set_default_handler($xml_parser, "defaultHandler");
	xml_set_external_entity_ref_handler($xml_parser, "externalEntityHandler");

	if(!($fp= @fopen($file, "r"))){
		die("不能打开文件$file");
	}
	while(($data = fread($fp, 4096))){
		if(!xml_parse($xml_parser, $data, feof($fp))){
			die( echof ("XML读取错误，在 %d 行 %d 列", 
				xml_get_current_line_number($xml_parser),
				xml_get_current_column_number($xml_parser)));
		}
	}
	xml_parser_free($xml_parser);
	return $books;
}
//打印详细信息成表格
function getBookDetail($title, $authors, $isbn, $date, $zone){
	echo "<tr>";
	echo "	<td><a href='book_detail.php?isbn=".$isbn."'>"
			.$title."</a></td>";
	echo "	<td>";
	for($j = 0; $j < count($authors); $j ++){
		if($j > 0)
			echo ",";
		echo " ".$authors[$j];
	}
	echo "	</td>";
	echo "	<td>".$isbn."</td>";
	echo "	<td>".$date." ".$zone."</td>";
	echo "</tr>";
}
//根据isbn查找图书的详细信息
function searchBookByISBN($books, $isbn){
	//echo "isbn=".$isbn;
	for($i = 0; $i < count($books); $i ++){
		if(strcmp(trim($books[$i]["isbn"]), $isbn) ==0){
			return $books[$i];
		}
	}
	return null;
}
?>