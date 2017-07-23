<?php
class sqlserver {
	var $Host = "192.168.0.100"; 	//SQL server��������ַ
	var $User = "sa";            			//�������ݿ���û�
	var $Password = "password";  	//�������ݿ������
	var $Database = "test";      		//���ݿ�����
	var $Link_ID = 0;            			// mssql_connect()���صĽ����ʶ
	var $Query_ID = 0;           			//mssql_query()���һ�β�ѯ�ı�ʶ
	var $CurrentRow = 0;      			 //��ǰ�к�
	var $Errno = 0;              			//�����
	var $Error = "";             				//������Ϣ
	var $AffectNums=0;               	//������Ӱ�������
	
	//�������ݿ⣬����ѡ��Ĭ�ϵ����ݿ�
	function Connect() {
		if ( 0 == $this->Link_ID ) {
			$this->Link_ID=mssql_connect($this->Host,$this->User,$this->Password) or die("�������ӵ� SQL Server ������");
			$db=@mssql_select_db($this->Database,$this->Link_ID);
			if (!$this->Link_ID) {
				$this->Halt("Link-ID Ϊ��, ����mssql_connect ʧ��");
			}
		}
	}

	//�ر����ݿ⣬������ݿ������Ѿ�����ر���
	function Close() {
		if (0 != $this->Link_ID){
			mssql_close();
		}
	}
	
	//ִ��sql��䣬��Ч�Ĳ���������select,update,insert,delete,
	//Ҳ����ͨ��������������ô洢���̡�
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
			$this->Error = "ִ��SQL�������ˣ�������Ϣ��ʾΪ".$msg;
			$this->halt("�Ƿ���SQL���: ".$Query_String);
		}
		return $this->Query_ID;
	}
	
	//��ȡ��ѯ���ݿ����һ����¼
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
	
	//���¶�λ��ѯ���ݿ��ָ�룬��ҪУ������ĺϷ���
	function Seek($pos) {
		if($pos<=0) return;
		if(eregi("[0-9]",$pos)) mssql_data_seek($this->Query_ID,$pos);
	}
	
	//��ȡ��ѯ���ݿ�õ���������
	function NumRows() {
		if($this->Query_ID) $num_rows=mssql_num_rows($this->Query_ID);
		else $num_rows=$this->AffectNums;
		return $num_rows;
	}
	
	//��ȡ��ѯ���ݿ�õ������ֶ���
	function NumFields() {
		return count($this->Record)/2;
	}
	
	//��ȡָ���ֶε�ֵ
	function FieldValue($Field_Name){
		return $this->Record[$Field_Name];
	}
	
	//��ȡ���ݿ����Ӱ�������������update,insert,delete
	function AffectedRows() {
		if($this->Query_ID) return mssql_num_rows($this->Query_ID);
		else{
			return $this->AffectNums;
		}
	}
	
	//��ʾҳ�洦��Ĵ�����Ϣ
	function Halt($msg) {
		printf("</td></tr></table><b>���ݿ����:</b> %s<br>\n", $msg);
		printf("<b>mssql Error</b>: %s (%s)<br>\n",
		$this->Errno,
		$this->Error);
		die("�����Ѿ���ֹͣ��");
	}
}
?>