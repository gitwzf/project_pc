<?php
//db.class.php
class db_class {
	var $dbserver = "localhost"; 		//���ݿ������,��Ҫ�޸�
	var $dbuser = "root";        		//���ݿ��¼�ʺ�,��Ҫ�޸�
	var $dbpassword = "";        		//���ݿ�����,��Ҫ�޸�
	var $database = "phpbook_ch16";     //���ݿ���,��Ҫ�޸�

	//-----------------------------------------------------------------------------------
	// ���²��ֲ����޸�!
	//-----------------------------------------------------------------------------------
	var $link_id  = 0;             //���ݿ�����ID��
	var $query_id = 0;
	var $record   = array();       //��¼

	var $errdesc    = "";
	var $errno   = 0;
	var $reporterror = 1;
	var $returnerr = 0;

	function connect() {
		//�������ݿ�

		if ( 0 == $this->link_id ) {
			$this->link_id=mysql_connect($this->dbserver,$this->dbuser,$this->dbpassword);
			//ʹ��mysql_pconnect�ȽϽ�ʡ��Դ,����Ҫ��mysql_close�ر�����
			if (!$this->link_id) {
				$this->halt("Link-ID: FALSE, ���ݿ�����ʧ��");
			}
			if ($this->database!="") {
				if(!mysql_select_db($this->database, $this->link_id)) {
					$this->halt("�޷�ʹ�����ݿ� ".$this->database);
				}
			}
		}
	}

	function close() { //�رգ����á�:p ��Ϊpconnect
		$this->free_result();
		if($this->link_id != 0) return @mysql_close($this->link_id);
	}

	function geterrdesc() {
		//ȡ�÷�������MySql���ݿ�Ĵ�����Ϣ
		$this->error=mysql_error();
		return $this->error;
	}

	function geterrno() {
		//ȡ�÷�������mysql���ݿ�������
		$this->errno=mysql_errno();
		return $this->errno;
	}

	function select_db($database="") {
		//ѡ�����ݿ�,������Ҫ
		if ($database!="") {
			$this->database=$database;
		}

		if(!mysql_select_db($this->database, $this->link_id)) {
			$this->halt("�޷�ʹ�����ݿ� ".$this->database);
		}

	}

	function query($query_string) {
		//�ͳ�query�ַ�����Mysqlȥִ��.
		$this->query_id = mysql_query($query_string,$this->link_id);
		if (!$this->query_id) {
			$this->halt("Invalid SQL: ".$query_string);
		}

		return $this->query_id;
	}

	function fetch_array($query_id=-1) {
		//������������
		if ($query_id!=-1) {
			$this->query_id=$query_id;
		}
		$this->record = @mysql_fetch_array($this->query_id);

		return $this->record;
	}

	function free_result($query_id=-1) {
		//�ͷ��ڹ�
		if ($query_id!=-1) {
			$this->query_id=$query_id;
		}
		return @mysql_free_result($this->query_id);
	}

	function query_first($query_string) {
		// QueryȻ�󷴻ص�һ��
		$this->query($query_string);
		$returnarray=$this->fetch_array($this->query_id);
		return $returnarray;
	}

	function data_seek($pos,$query_id=-1) {
		// goes to row $pos
		if ($query_id!=-1) {
			$this->query_id=$query_id;
		}
		$status = @mysql_data_seek($this->query_id, $pos);
		//$this->free_result($this->$query_id);
		return $status;
	}

	function fetch_other($pos, $query_id=-1) { //cheng: ȡ��ĳ��
		if ($query_id!=-1) {
			$this->query_id=$query_id;
		}
		$status = @mysql_data_seek($this->query_id, $pos);
		$returnarray=$this->fetch_array($status);
		return $returnarray;
	}

	function num_rows($query_id=-1) {
		// returns number of rows in query
		if ($query_id!=-1) {
			$this->query_id=$query_id;
		}
		return mysql_num_rows($this->query_id);
	}

	function insert_id() {
		// returns last auto_increment field number assigned

		return mysql_insert_id($this->link_id);

	}

	function halt($msg) {
		$this->errdesc=mysql_error();
		$this->errno=mysql_errno();

		if ($this->reporterror==1) {
			$title = "���ݿ����";
			print $title;
			echo "
				<p>
				��ˢ�����������.</p>
				������Ϣ:".$msg."
				<p>
				�������������֣������
				<a href=mailto:cheng@sina.net?subject=".urlencode('���ݿ����⣺'.$msg).">cheng</a>.
				���߷��� <a href=\"http://forum.com.net\">cheng ��̳</a> Ѱ����.
				</p>
				<p>ʮ�ֱ�Ǹ�������ǵ������������Ĳ���.</p>";
			exit;
		}
	}
}

//������һ��connect
global $db;
$db = new db_class;
$db->connect();
?>
