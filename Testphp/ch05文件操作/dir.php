<?
echo "ʹ��dir����ʾ>>dir()<BR>";
$id = dir("./");
print("Handleis:".$id->handle."<br>\n");
print("Pathis:".$id->path."<br>\n");
//ѭ����ȡĿ¼����
while($temp = $id->read()){
echo $temp."<br>\n";
}
$id->close();
?>