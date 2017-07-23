<?php
class sqlserver {
	var $Host = "192.168.0.100"; 	//SQL server的主机地址
	var $User = "sa";            			//连接数据库的用户
	var $Password = "password";  	//连接数据库的密码
	var $Database = "test";      		//数据库名称
	var $Link_ID = 0;            			// mssql_connect()返回的结果标识
	var $Query_ID = 0;           			//mssql_query()最近一次查询的标识
	var $CurrentRow = 0;      			 //当前行号
	var $Errno = 0;              			//错误号
	var $Error = "";             				//错误信息
	var $AffectNums=0;               	//操作受影响的行数
	
	//连接数据库，并且选择默认的数据库
	function Connect() {
		if ( 0 == $this->Link_ID ) {
			$this->Link_ID=mssql_connect($this->Host,$this->User,$this->Password) or die("不能连接到 SQL Server 服务器");
			$db=@mssql_select_db($this->Database,$this->Link_ID);
			if (!$this->Link_ID) {
				$this->Halt("Link-ID 为空, 创建mssql_connect 失败");
			}
		}
	}

	//关闭数据库，如果数据库连接已经打开则关闭它
	function Close() {
		if (0 != $this->Link_ID){
			mssql_close();
		}
	}
	
	//执行sql语句，有效的操作可以是select,update,insert,delete,
	//也可以通过这个方法来调用存储过程。
	function Query($Query_String) {
		$this->Connect();
		$this->Query_ID = mssql_query($Query_String);
		$this->Row = 0;
		if (!$this->Query_ID) {
			$msg=mssql_get_last_message();
			if($msg==null || $msg=="")
			{
				$this->AffectNums=1;
				return 1;
			}
			if(strtolower(substr($Query_String,0,6))!="select"){
				$this->AffectNums=1;
				return 1;
			}
			$this->Errno = 1;
			$this->Error = "执行SQL语句出错了，错误信息提示为".$msg;
			$this->halt("非法的SQL语句: ".$Query_String);
		}
		return $this->Query_ID;
	}
	
	//获取查询数据库的下一条记录
	function NextRecord() {
		$this->Record = array();
		mssql_next_result($this->Query_ID);
		$this->Record=mssql_fetch_array($this->Query_ID);
		$result=$this->Record;
		if(!is_array($result)) return $this->Record;
		foreach($result as $key => $value){
			$keylower=strtolower($key);
			if($keylower!=$key) $this->Record[$keylower]=$value;
		}
		return $this->Record;
	}
	
	//重新定位查询数据库的指针，需要校验参数的合法性
	function Seek($pos) {
		if($pos<=0) return;
		if(eregi("[0-9]",$pos)) mssql_data_seek($this->Query_ID,$pos);
	}
	
	//获取查询数据库得到的总行数
	function NumRows() {
		if($this->Query_ID) $num_rows=mssql_num_rows($this->Query_ID);
		else $num_rows=$this->AffectNums;
		return $num_rows;
	}
	
	//获取查询数据库得到的总字段数
	function NumFields() {
		return count($this->Record)/2;
	}
	
	//获取指定字段的值
	function FieldValue($Field_Name){
		return $this->Record[$Field_Name];
	}
	
	//获取数据库操作影响的行数，包括update,insert,delete
	function AffectedRows() {
		if($this->Query_ID) return mssql_num_rows($this->Query_ID);
		else{
			return $this->AffectNums;
		}
	}
	
	//显示页面处理的错误信息
	function Halt($msg) {
		printf("</td></tr></table><b>数据库出错:</b> %s<br>\n", $msg);
		printf("<b>mssql Error</b>: %s (%s)<br>\n",
		$this->Errno,
		$this->Error);
		die("进程已经被停止！");
	}
}
?>