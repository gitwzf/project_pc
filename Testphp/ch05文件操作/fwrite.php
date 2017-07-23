<?php
$filename = 'test.txt';
$somecontent = "添加这些文字到文件\n";
// 首先我们要确定文件存在并且可写。
if (is_writable($filename)) {
    //在这个例子里，我们将使用添加模式打开$filename，
    //因此，文件指针将会在文件的开头，
    //那就是当我们使用fwrite()的时候，$somecontent将要写入的地方。
    if (!$handle = fopen($filename, 'a')) {
         print "不能打开文件 $filename";
         exit;
    }
    //将$somecontent写入到我们打开的文件中。
    if (!fwrite($handle, $somecontent)) {
        print "不能写入到文件 $filename";
        exit;
    }
    print "成功地将 $somecontent 写入到文件$filename";
    fclose($handle);
} else {
    print "文件 $filename 不可写";
}
?>