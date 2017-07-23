<?php
class sendmail{
	var $lastmessage; //��¼��󷵻ص���Ӧ��Ϣ
	var $lastact;     //���Ķ������ַ�����ʽ
	var $welcome; 	//����HELO���棬��ӭ�û�
	var $debug; 	//�Ƿ���ʾ������Ϣ
	var $smtp; 		//smtp������
	var $port; 		//smtp�˿ں�
	var $fp; 		//socket���
	//�����ʼ�����
	function send_mail($smtp, $welcome="", $debug=false) {
		if(empty($smtp)) die("SMTP����Ϊ�գ�");
		$this->smtp=$smtp;
		if(empty($welcome)) {
			$this->welcome=gethostbyaddr("localhost");
		}else
		$this->welcome=$welcome;
		$this->debug=$debug;
		$this->lastmessage="";
		$this->lastact="";
		$this->port="25";
	}
	//��ʾ������Ϣ
	function show_debug($message, $inout) {
		if ($this->debug) {
			if($inout=="in"){ //��Ӧ��Ϣ
				$m='<< ';
			}else
			$m='>> ';
			if(!ereg("\n$", $message))
			$message .= "<br>";
			$message=nl2br($message);
			echo "<font color=#99FF99>${m}${message}</font>";
		}
	}
	//ִ�д��ݵ�����
	function do_command($command, $code) {
		$this->lastact=$command;
		$this->show_debug($this->lastact, "out");
		fputs ( $this->fp, $this->lastact );
		$this->lastmessage = fgets ( $this->fp, 512 );
		$this->show_debug($this->lastmessage, "in");
		if(!ereg("^$code", $this->lastmessage))
		return false;
		else
		return true;
	}
	//�ʼ����ʹ���
	function send( $to,$from,$subject,$message) {
		//���ӷ�����
		$this->lastact="connect";
		$this->show_debug("���ӵ�SMTP ������: ".$this->smtp, "out");
		$this->fp = fsockopen ( $this->smtp, $this->port );
		if ( $this->fp ) {
			set_socket_blocking( $this->fp, true );
			$this->lastmessage=fgets($this->fp,512);
			$this->show_debug($this->lastmessage, "in");
			if (! ereg ( "^220", $this->lastmessage ) ) {
				return false;
			}else{
				$this->lastact="HELO " . $this->welcome . "\n";
				if(!$this->do_command($this->lastact, "250")){
					fclose($this->fp);
					return false;
				}
				$this->lastact="MAIL FROM: $from" . "\n";
				if(!$this->do_command($this->lastact, "250")){
					fclose($this->fp);
					return false;
				}
				$this->lastact="RCPT TO: $to" . "\n";
				if(!$this->do_command($this->lastact, "250")){
					fclose($this->fp);
					return false;
				}
				//��ʼ�����ʼ�����
				$this->lastact="DATA\n";
				if(!$this->do_command($this->lastact, "354")){
					fclose($this->fp);
					return false;
				}
				//��ʼ�����ʼ�����ͷ
				$head="Subject: $subject\n";
				if(!empty($subject) && !ereg($head, $message)){
					$message = $head.$message;
				}
				//��ʼ�����ʼ�Fromͷ
				$head="From: $from\n";
				if(!empty($from) && !ereg($head, $message)) {
					$message = $head.$message;
				}
				//��ʼ�����ʼ�Toͷ
				$head="To: $to\n";
				if(!empty($to) && !ereg($head, $message)) {
					$message = $head.$message;
				}
				//���������
				if(!ereg("\n\.\n", $message))
				$message .= "\n.\n";
				$this->show_debug($message, "out");
				fputs($this->fp, $message);
				$this->lastact="QUIT\n";
				if(!$this->do_command($this->lastact, "250")){
					fclose($this->fp);
					return false;
				}
			}
			return true;
		}else{
			$this->show_debug("����ʧ�ܣ�!", "in");
			return false;
		}
	}
}
?>