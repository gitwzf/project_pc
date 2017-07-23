<?php
class upfile{
	//�ϴ��ļ���Ϣ
	var $filename;
	//������
	var $savename;
	//����·��
	var $savepath;
	//�ļ���ʽ�޶���Ϊ��ʱ�����Ƹ�ʽ
	var $format = "";
	//�����Ƿ񸲸�ģʽ.$overwrite = 0 ʱ������ͬ���ļ�;$overwrite = 1ʱ����ͬ���ļ�
	var $overwrite = 0;
	//�ļ�����ֽ�
	var $maxsize = 210000000;
	//�ļ���չ��
	var $ext;
	//�������
	var $errno = 0;
	/* ���캯��
	* $path ����·��
	* $format �ļ���ʽ(�ö��ŷֿ�)
	* $maxsize �ļ��������
	* $over ���ǲ���
	*/
	function upfile($path, $format = "", $maxsize = 0, $over = 0){
		$this->savepath = substr($path, -1) == "/" ? $path : $path."/";//����·��
		$this->overwrite = $over;//�Ƿ񸴸���ͬ�����ļ�
		$this->maxsize = !$maxsize ? $this->maxsize : $maxsize;//�ļ�����ֽ�
		$this->format = $format;
	}
	/*
	* ���ܣ���Ⲣ��֯�ļ�
	* $form �ļ�������
	* $file �ϴ��ļ��������ƣ�Ϊ�ջ����ϴ�����ļ�ʱ��ϵͳ�Զ���������
	*/
	function upload($form, $file = ""){
		if(!isset($_FILES[$form])){
			$this->halt("ָ�����ļ������Ʋ����ڡ�");
		}else{
			$filear = $_FILES[$form];
		}
		if(!is_writable($this->savepath)){
			$this->halt("ָ����·������д��");
		}
		if(is_array($filear["name"])){//�ϴ�ͬ�ļ������ƶ���ļ�
			for($i = 0; $i < count($filear["name"]); $i++){
				$ar["name"] = $filear["name"][$i];
				$ar["tmp_name"] = $filear["tmp_name"][$i];
				$ar["size"] = $filear["size"][$i];
				$ar["error"] = $filear["error"][$i];
				$this->getext($ar["name"]);//ȡ����չ��
				$this->set_savename();//���ñ����ļ���
				$this->copyfile($ar);
			}
		}else{//�ϴ������ļ�
			$this->getext($filear["name"]);//ȡ����չ��
			$this->set_savename($file);//���ñ����ļ���
			$this->copyfile($filear);
		}
		return true;
	}
	/*
	* ���ܣ���Ⲣ�����ϴ��ļ�
	* $filear �ϴ��ļ���������
	*/
	function copyfile($filear){
		if($filear["size"] > $this->maxsize){
			$this->halt("�ϴ��ļ� ".$filear["name"]." ��С����ϵͳ�޶�ֵ[".$this->maxsize." �ֽ�]�������ϴ���");
		}
		if(!$this->overwrite && file_exists($this->savename)){
			$this->halt($this->savename." �ļ����Ѿ����ڡ�");
		}
		if($this->format != "" && !in_array(strtolower($this->ext), explode(",", strtolower($this->format)))){
			$this->halt($this->ext." �ļ���ʽ�������ϴ���");
		}
		if(!copy($filear["tmp_name"], $this->savepath.$this->savename)){
			$errors = array(0=>"�ļ��ϴ��ɹ�",
			1=>"�ϴ����ļ������� php.ini �� upload_max_filesize ѡ�����Ƶ�ֵ��",
			2=>"�ϴ��ļ��Ĵ�С������HTML����MAX_FILE_SIZEѡ��ָ����ֵ��",
			3=>"�ļ�ֻ�в��ֱ��ϴ���",
			4=>"û���ļ����ϴ���");
			$this->halt($errors[$filear["error"]]);
		}else{
			@unlink($filear["tmp_name"]);//ɾ����ʱ�ļ�
		}
	}
	/*
	* ����: ȡ���ļ���չ��
	* $filename Ϊ�ļ�����
	*/
	function getext($filename){
		if($filename == "") return;
		$ext = explode(".", $filename);
		$this->ext = $ext[1];
	}
	/*
	* ����:�����ļ�������
	* $savename �����������Ϊ�գ���ϵͳ�Զ�����һ��������ļ���
	*/
	function set_savename($savename = "")
	{
		if ($savename == "")  //���δ�����ļ�����������һ������ļ���
		{
			srand ((double) microtime() * 1000000);
			$rnd = rand(100,999);
			$name = date('U') + $rnd;
			$name = $name.".".$this->ext;
		} else {
			$name = $savename;
		}
		$this->savename = $name;
	}
	/*
	* ���ܣ�������ʾ
	* $msg Ϊ�����Ϣ
	*/
	function halt($msg){
		echo "<strong>ע�⣺</strong>".$msg."&nbsp;<a href='upload.html'>��������</a>";
		exit;
	}
}
?>