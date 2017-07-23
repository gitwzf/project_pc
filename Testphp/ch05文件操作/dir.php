<?
echo "使用dir类演示>>dir()<BR>";
$id = dir("./");
print("Handleis:".$id->handle."<br>\n");
print("Pathis:".$id->path."<br>\n");
//循环读取目录内容
while($temp = $id->read()){
echo $temp."<br>\n";
}
$id->close();
?>