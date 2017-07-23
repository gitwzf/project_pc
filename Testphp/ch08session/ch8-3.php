<?php
//打开操作
function my_open ($save_path, $session_name) {
global $sess_save_path, $sess_session_name;
$sess_save_path = $save_path;
$sess_session_name = $session_name;
return(true);
}
//关闭操作
function close() {
return(true);
}
//读取操作
function my_read ($id) {
global $sess_save_path, $sess_session_name;
$sess_file = "$sess_save_path/sess_$id";
if ($fp = @fopen($sess_file, "r")) {
$sess_data = fread($fp, filesize($sess_file));
return($sess_data);
} else {
return("");
}
}
//写入操作
function my_write ($id, $sess_data) {
global $sess_save_path, $sess_session_name;
$sess_file = "$sess_save_path/sess_$id";
if ($fp = @fopen($sess_file, "w")) {
return(fwrite($fp, $sess_data));
} else {
return(false);
}
}
//销毁操作
function my_destroy ($id) {
global $sess_save_path, $sess_session_name;
$sess_file = "$sess_save_path/sess_$id";
return(@unlink($sess_file));
}
/*********************************************
* 读者需要实现垃圾回收，碎片清理的算法
* *********************************************/
function my_gc ($maxlifetime) {
return true;
}
session_set_save_handler("my_open", " my_close", " my_read", " my_write", " my_destroy", " my_gc");
session_start();
//现在就可以象往常一样地使用session了。
?>