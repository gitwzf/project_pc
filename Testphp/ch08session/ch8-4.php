<?php   
$DB_SERVER = "localhost"; 	/* 数据库主机地址 */
$DB_NAME = "phpbook"; 		/* 数据库名称 */
$DB_USER = "root"; 			/* 链接数据库的用户名 */
$DB_PASS = ""; 				/* 连接数据库的密码 */

$DB_SELECT_DB = "";
$SESS_LIFE = get_cfg_var("session.gc_maxlifetime");
//打开操作
function sess_open($save_path, $session_name) {
	global $DB_SERVER, $DB_NAME, $DB_USER, $DB_PASS, $DB_SELECT_DB;

	if (! $DB_SELECT_DB = mysql_pconnect($DB_SERVER, $DB_USER, $DB_PASS)) {
		echo "SORRY! MYSQL ERROR : Can't connect to $DB_SERVER as $DB_USER";
		echo "MySQL Error: ", mysql_error();
		die;
	}

	if (! mysql_select_db($DB_NAME, $DB_SELECT_DB)) {
		echo "SORRY! MYSQL ERROR : Unable to select database $DB_NAME";
		die;
	}

	return true;
}
//关闭操作
function sess_close() {
	return true;
}
//读取操作
function sess_read($SessionKey){
	global $DB_SELECT_DB, $SESS_LIFE;
	$Query = "SELECT SessionArray FROM phpbook_global_sessions WHERE SessionKey = '"
			.$SessionKey."' AND SessionExpTime > " . time();
	$Result = mysql_query($Query, $DB_SELECT_DB);

	if (list($SessionArray) = mysql_fetch_row($Result)) {
		return $SessionArray;
	}

	return false;
}
//写入操作
function sess_write($SessionKey, $VArray) {
	global $DB_SELECT_DB, $SESS_LIFE;

	$SessionExpTime = time() + $SESS_LIFE;
	$SessionArray = addslashes($VArray);

	$Query = "INSERT INTO phpbook_global_sessions (SessionKey,SessionExpTime,SessionArray) 
				VALUES ('".$SessionKey."','".$SessionExpTime."','".$SessionArray."')";
	$Result = mysql_query($Query, $DB_SELECT_DB);

	if (!$Result){
		$Query = "UPDATE phpbook_global_sessions SET SessionExpTime = '".$SessionExpTime."',
		 		SessionArray = '".$SessionArray."' WHERE SessionKey = '".$SessionKey."'
		 		AND SessionExpTime > " . time();
		$Result = mysql_query($Query, $DB_SELECT_DB);
	}
	return $Result;
}
//销毁操作
function sess_destroy($SessionKey) {
	global $DB_SELECT_DB;

	$Query = "DELETE FROM phpbook_global_sessions WHERE SessionKey = '".$SessionKey."'";
	$Result = mysql_query($Query, $DB_SELECT_DB);

	return $Result;
}
//回收操作
function sess_gc($maxlifetime) {
	global $DB_SELECT_DB;

	$Query = "DELETE FROM phpbook_global_sessions WHERE SessionExpTime < " . time();
	$Result = mysql_query($Query, $DB_SELECT_DB);

	return mysql_affected_rows($DB_SELECT_DB);
}
//注册为自己的session机制实现
session_set_save_handler(
"sess_open",
"sess_close",
"sess_read",
"sess_write",
"sess_destroy",
"sess_gc");
session_start();
?>