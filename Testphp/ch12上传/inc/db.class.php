<?php
//���ݿ������
class db{
	//�����Զ���
	var $host = "localhost";//MYSQL����
	var $user = "root";//�����ʻ�
	var $password = "";//��������
	var $database = "phpbook_ch12";//���ݿ���
	//��������
	function mysql($host,$user,$password,$database){
		$this->host=$host;
		$this->user=$user;
		$this->password=$password;
		$this->database=$database;
	}
	//����MYSQL����
	function mycon(){
		mysql_connect($this->host,$this->user,$this->password);
	}
	//ѡ����Ӧ�����ݿ�
	function selectdb(){
		mysql_select_db($this->database);
	}
	//�������ݿ����Ӳ�ѡ����Ӧ���ݿ�
	function createcon(){
		mysql_connect($this->host,$this->user,$this->password);
		mysql_query("SET NAMES 'GBK'");
		mysql_select_db($this->database);
	}
	//ִ��SQL��䣬�����ؽ����
	function fetch_array($sql){
		$result=mysql_query($sql);
		return mysql_fetch_array($result);
	}
	//ִ��SQL���
	function query($sql){
		return mysql_query($sql);
	}
	//ȡ�ý��������
	function loop_query($result){
		return mysql_fetch_array($result);
	}
	//�ر����ݿ�����
	function close() {
		return mysql_close();
	}
}
?>