<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> ͼ���������ҳ </TITLE>
<META NAME="Author" CONTENT="cheng">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
<style>
body{ font-size:12px};
td{ font-size:12px};
</style>
</HEAD>
<BODY>
	<b>�������</b>
	<hr><br>
		<table border='1' width="70%">
		<thead>
			<tr align="center">
				<td> ���� </td>
				<td> ���� </td>
				<td> ��� </td>
				<td> ʱ�� </td>
			</tr>
		</thead>
		<tbody>
			<?php
				//����������ȡ
				$searchKey = $_GET["keywords"];
				$searchBy = $_GET["searchby"];
				echo "\$searchKey=".$searchKey."|\$searchBy=".$searchBy."<br>";
				echo "XML�ĵ���";
				include_once("common.php");
				$book = parserBookInfo();
				if(strcmp($searchBy, "isbn")==0){
					if( ($book = searchBookByISBN($books, $searchKey)) ){
						getBookDetail($book[$i]["title"],
								$book[$i]["authors"],$book[$i]["isbn"],
								$book[$i]["date"],$book[$i]["zone"]
						);
					}
				}elseif( strcmp($searchBy, "author")==0){
					for($i = 0 ; $i < count($books); $i ++){
						$authors = $books[$i]["authors"];
						for($j = 0 ; $j < count($authors)-1; $j ++){
							if(strstr(strtolower(trim($authors[$j])),
								strtolower(trim($searchKey))
							)){
								getBookDetail($book[$i]["title"],
									$book[$i]["authors"],$book[$i]["isbn"],
									$book[$i]["date"],$book[$i]["zone"]
								);
							}
						}
					}
				}elseif( strcmp($searchBy, "title")==0){
					for($i = 0 ; $i < count($books) ; $i ++){
						if(strstr(strtolower(trim($books[$i]["title"])),
								strtolower(trim($searchKey))
							)){
							getBookDetail($book[$i]["title"],
								$book[$i]["authors"],$book[$i]["isbn"],
								$book[$i]["date"],$book[$i]["zone"]
							);
						}
					}
				}	
			?>
		</tbody>
	</table>
</BODY>
</HTML>
