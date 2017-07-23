<?php
//数据库操作类
class db{
	//类属性定义
	var $host = "localhost";//MYSQL主机
	var $user = "root";//连接帐户
	var $password = "";//连接密码
	var $database = "phpbook_ch12";//数据库名
	//变量引用
	function mysql($host,$user,$password,$database){
		$this->host=$host;
		$this->user=$user;
		$this->password=$password;
		$this->database=$database;
	}
	//创建MYSQL连接
	function mycon(){
		mysql_connect($this->host,$this->user,$this->password);
	}
	//选择相应的数据库
	function selectdb(){
		mysql_select_db($this->database);
	}
	//创建数据库连接并选择相应数据库
	function createcon(){
		mysql_connect($this->host,$this->user,$this->password);
		mysql_query("SET NAMES 'GBK'");
		mysql_select_db($this->database);
	}
	//执行SQL语句，并返回结果集
	function fetch_array($sql){
		$result=mysql_query($sql);
		return mysql_fetch_array($result);
	}
	//执行SQL语句
	function query($sql){
		return mysql_query($sql);
	}
	//取得结果集数组
	function loop_query($result){
		return mysql_fetch_array($result);
	}
	//关闭数据库连接
	function close() {
		return mysql_close();
	}
}
?>