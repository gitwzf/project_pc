<?php
//��������
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
    //���������ص��ļ�
    if (!eregi("^([a-z]+)://", $file)
        && fileowner($file) == getmyuid()) {
            return true;
    }
    return false;
}
//��ʼ����
function startElement($parser, $name, $attr){
	global $currentTag, $zone;
	$currentTag = $name;
	if(strcmp($name, "date") == 0){
		$zone = $attr["zone"];
	}
}
//��������
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
//�ַ����ص�����
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
//�ⲿ�ص�����
function externalEntityHandler($parser, $entityName, $base,
	$systemId, $productId){
	global $description;
	if(!$systemId)
		return false;
	$description = $systemId;
	return true;
}
//��������ָ�����
function PIHandler($parser, $target, $data)
{
    switch (strtolower($target)) {
        case "php":
            global $parser_file;
            //��֤Ҫ�������ĵ��Ƿ�ȫ��������ǰ�ȫ�ģ���������ʾ���Ĵ���
            if (trustedFile($parser_file[$parser])) {
                eval($data);
            } else {
                echof("����ȫ��PHP����: <i>%s</i>",
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
//����XML�ĵ�����
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
		die("���ܴ��ļ�$file");
	}
	while(($data = fread($fp, 4096))){
		if(!xml_parse($xml_parser, $data, feof($fp))){
			die( echof ("XML��ȡ������ %d �� %d ��", 
				xml_get_current_line_number($xml_parser),
				xml_get_current_column_number($xml_parser)));
		}
	}
	xml_parser_free($xml_parser);
	return $books;
}
//��ӡ��ϸ��Ϣ�ɱ��
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
//����isbn����ͼ�����ϸ��Ϣ
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