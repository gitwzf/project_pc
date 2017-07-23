<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> 图书搜索结果页 </TITLE>
<META NAME="Author" CONTENT="cheng">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
<style>
body{ font-size:12px};
td{ font-size:12px};
</style>
</HEAD>
<BODY>
	<b>搜索结果</b>
	<hr><br>
		<table border='1' width="70%">
		<thead>
			<tr align="center">
				<td> 标题 </td>
				<td> 作者 </td>
				<td> 编号 </td>
				<td> 时间 </td>
			</tr>
		</thead>
		<tbody>
			<?php
				//搜索条件获取
				$searchKey = $_GET["keywords"];
				$searchBy = $_GET["searchby"];
				echo "\$searchKey=".$searchKey."|\$searchBy=".$searchBy."<br>";
				echo "XML文档：";
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
