<?php
//��ҳ�࣬���������ڴ������ݽṹ������������ʾ�Ĺ���
Class Pager{
		var $PageSize;             //ÿҳ��ʾ���ݵ�����
		var $CurrentPageID;        //��ǰ������ҳ��λ��
		var $NextPageID;          //��һҳ
		var $PreviousPageID;       //��һҳ
		var $numPages;            //��ҳ��
		var $numItems;            //�ܼ�¼��
		var $isFirstPage;           //�Ƿ��һҳ
		var $isLastPage;           //�Ƿ����һҳ
		var $sql;                  //sql��ѯ���

	/**
	 * ��ҳ��Ĺ��캯��
	 * @param array $option ��ҳ��������
	 * @return Pager
	 */
	function Pager($option){
		global $db;
		$this->_setOptions($option);
		//������
		if ( !isset($this->numItems) ){
			$res = $this->query($this->sql,$db);
			$this->numItems = $this->numRows($res);
		}
		// ��ҳ��
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
	* ���ؽ���������ݿ�����
	* @return ������ true/false
	***/
	function getDataLink(){
		if ( $this->numItems )	{
			global $db;
			$PageID = $this->CurrentPageID;
			$from = ($PageID - 1)*$this->PageSize;
			$count = $this->PageSize;
			$link = $this->limitQuery($this->sql, $from, $count);
			//ʹ��limitQuery������֤���ݿ������
			return $link;
		}else{
			return false;
		}
	}
	/**
	 * �Զ�ά����ĸ�ʽ���ؽ����
	 * @return ������ true/false
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
	 * ���ո����������в�ѯ
	 * @param string $sql ��ѯ�Ļ������
	 * @param int $from ��ѯ����ʼλ��
	 * @param int $count ��ѯ���ݵ�����
	 * @return ��ѯ���
	 */
	function limitQuery($sql,$from,$count){
		global $db;
		$nowsql = $sql.' limit '.$from.','.$count;
		return mysql_query($nowsql);
	}

	/**
	 * ��ȡ���������Ŀ
	 * @param $res
	 * @return
	 */
	function numRows($res){
		return mysql_num_rows($res);
	}
	/**
	 * ��ȡSQL��query���
	 * @param string $sql
	 * @return 
	 */
	function query($sql){
		global $db;
		return mysql_query($sql,$db);
	}
	/**
	 * �ӽ������ȡ��һ����Ϊö������
	 * @param $res 
	 * @return array ��ѯ���
	 */
	function fetchRow($res){
		return mysql_fetch_row($res);
	}
	/**
	 * ����ѡ������
	 * @param array $option
	 * @return ���óɹ�����true
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