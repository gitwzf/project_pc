<?php
//db.class.php
class db_class {
  var $dbserver = "localhost"; //cheng.20070212: 数据库服务器,需要修改
  var $dbuser = "root";        //cheng.20070212: 数据库登录帐号,需要修改
  var $dbpassword = "12345678";        //cheng.20070212: 数据库密码,需要修改
  var $database = "503";     //cheng.20070212: 数据库名,需要修改
  
//-----------------------------------------------------------------------------------
// 以下部分不用修改!
//-----------------------------------------------------------------------------------
  var $link_id  = 0;             //cheng.20070212: 数据库连接ID号
  var $query_id = 0;
  var $record   = array();       //cheng.20070212: 记录

  var $errdesc    = "";
  var $errno   = 0;
  var $reporterror = 1;
  var $returnerr = 0;

  function connect() {
    //cheng.20070212: 连接数据库

    if ( 0 == $this->link_id ) {
      $this->link_id=mysql_connect($this->dbserver,$this->dbuser,$this->dbpassword);
      //cheng.20070212: 使用mysql_pconnect比较节省资源,不需要用mysql_close关闭连接
      if (!$this->link_id) {
        $this->halt("Link-ID: FALSE, 数据库连接失败");
      }
      if ($this->database!="") {
        if(!mysql_select_db($this->database, $this->link_id)) {
          $this->halt("无法使用数据库 ".$this->database);
        }
      }
    }
  }

  function close() { //关闭，无用。:p 若为pconnect
    $this->free_result();
    if($this->link_id != 0) return @mysql_close($this->link_id);
  }

  function geterrdesc() {
  	//cheng.20070212: 取得服务器上MySql数据库的错误信息
    $this->error=mysql_error();
    return $this->error;
  }

  function geterrno() {
  	//cheng.20070212: 取得服务器上mysql数据库错误代码
    $this->errno=mysql_errno();
    return $this->errno;
  }

  function select_db($database="") {
    //cheng.20070212: 选择数据库,好像不需要
    if ($database!="") {
      $this->database=$database;
    }

    if(!mysql_select_db($this->database, $this->link_id)) {
      $this->halt("无法使用数据库 ".$this->database);
    }

  }

  function query($query_string) {
    //cheng.20070212: 送出query字符串让Mysql去执行.
    $this->query_id = mysql_query($query_string,$this->link_id);    
    if (!$this->query_id) {
      $this->halt("Invalid SQL: ".$query_string);
    }

    return $this->query_id;
  }

  function fetch_array($query_id=-1) {
    //cheng.20070212: 返回数组资料
    if ($query_id!=-1) {
      $this->query_id=$query_id;
    }
    $this->record = @mysql_fetch_array($this->query_id);

    return $this->record;
  }

  function free_result($query_id=-1) {
    //cheng.20070212: 释放内顾
    if ($query_id!=-1) {
      $this->query_id=$query_id;
    }
    return @mysql_free_result($this->query_id);
  }

  function query_first($query_string) {
    //cheng.20070212:  Query然后反回第一行
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
  
  function fetch_other($pos, $query_id=-1) { //cheng: 取得某行
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
      $title = "数据库错误";
      print $title;
      echo "
<p>
好象数据库有些小问题.请刷新再试着浏览.</p>
错误信息:".$msg."
<p>
如果问题持续出现，请告诉
<a href=mailto:cheng@cheng.net?subject=".urlencode('数据库问题：'.$msg).">cheng</a>.
或者访问 <a href=\"http://forum.cheng.net\">cheng 论坛</a> 寻求解决.
</p>
<p>十分抱歉由于我们的疏忽造成了您的不便.</p>";
      exit;
    }
  }
}

//给它们一个connect
global $db;
$db = new db_class;
$db->connect();
$db->query("set names gbk");
?>
