<?php
//分页类，这个类仅用于处理数据结构，不负责处理显示的工作
Class Pager{
		var $PageSize;             //每页显示内容的数量
		var $CurrentPageID;        //当前所处的页面位置
		var $NextPageID;          //下一页
		var $PreviousPageID;       //上一页
		var $numPages;            //总页数
		var $numItems;            //总记录数
		var $isFirstPage;           //是否第一页
		var $isLastPage;           //是否最后一页
		var $sql;                  //sql查询语句

	/**
	 * 分页类的构造函数
	 * @param array $option 分页设置数组
	 * @return Pager
	 */
	function Pager($option){
		global $db;
		$this->_setOptions($option);
		//总条数
		if ( !isset($this->numItems) ){
			$res = $this->query($this->sql,$db);
			$this->numItems = $this->numRows($res);
		}
		// 总页数
		if ( $this->numItems > 0 ){
			if ( $this->numItems < $this->PageSize ){ $this->numPages = 1; }
			if ( $this->numItems % $this->PageSize ){
				$this->numPages= (int)($this->numItems / $this->PageSize) + 1;
			}else{
				$this->numPages = $this->numItems / $this->PageSize;
			}
		}else{
			$this->numPages = 0;
		}
		switch ( $this->CurrentPageID ){
			case $this->numPages == 1:
				$this->isFirstPage = true;
				$this->isLastPage = true;
				break;
			case 1:
				$this->isFirstPage = true;
				$this->isLastPage = false;
				break;
			case $this->numPages:
				$this->isFirstPage = false;
				$this->isLastPage = true;
				break;
			default:
				$this->isFirstPage = false;
				$this->isLastPage = false;
		}
		if ( $this->numPages > 1 ){
			if ( !$this->isLastPage ) { $this->NextPageID = $this->CurrentPageID + 1; }
			if ( !$this->isFirstPage ) { $this->PreviousPageID = $this->CurrentPageID - 1; }
		}

		return true;
	}

	/***
	* 返回结果集的数据库连接
	* @return 布尔型 true/false
	***/
	function getDataLink(){
		if ( $this->numItems )	{
			global $db;
			$PageID = $this->CurrentPageID;
			$from = ($PageID - 1)*$this->PageSize;
			$count = $this->PageSize;
			$link = $this->limitQuery($this->sql, $from, $count);
			//使用limitQuery方法保证数据库兼容性
			return $link;
		}else{
			return false;
		}
	}
	/**
	 * 以二维数组的格式返回结果集
	 * @return 布尔型 true/false
	 */
	function getPageData(){
		if ( $this->numItems )	{
			if ( $res = $this->getDataLink() )	{
				if ( $this->numRows($res) )	{
					while ( $row = $this->fetchrow($res) ){
						$result[] = $row;
					}
				}else{
					$result = array();
				}
				return $result;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	/**
	 * 按照给定条件进行查询
	 * @param string $sql 查询的基本语句
	 * @param int $from 查询的起始位置
	 * @param int $count 查询数据的条数
	 * @return 查询结果
	 */
	function limitQuery($sql,$from,$count){
		global $db;
		$nowsql = $sql.' limit '.$from.','.$count;
		return mysql_query($nowsql);
	}

	/**
	 * 获取结果集的数目
	 * @param $res
	 * @return
	 */
	function numRows($res){
		return mysql_num_rows($res);
	}
	/**
	 * 获取SQL的query结果
	 * @param string $sql
	 * @return 
	 */
	function query($sql){
		global $db;
		return mysql_query($sql,$db);
	}
	/**
	 * 从结果集中取得一行作为枚举数组
	 * @param $res 
	 * @return array 查询结果
	 */
	function fetchRow($res){
		return mysql_fetch_row($res);
	}
	/**
	 * 解析选项设置
	 * @param array $option
	 * @return 设置成功返回true
	 */
	function _setOptions($option){
		$allow_options = array('PageSize','CurrentPageID',	'sql',	'numItems'	);
		foreach ( $option as $key => $value ){
			if ( in_array($key, $allow_options) && ($value != null) ){
				$this->$key = $value;
			}
		}
		return true;
	}
}
?>