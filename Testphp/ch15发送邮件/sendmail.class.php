<?php
class sendmail{
	var $lastmessage; //记录最后返回的响应信息
	var $lastact;     //最后的动作，字符串形式
	var $welcome; 	//用在HELO后面，欢迎用户
	var $debug; 	//是否显示调试信息
	var $smtp; 		//smtp服务器
	var $port; 		//smtp端口号
	var $fp; 		//socket句柄
	//发送邮件函数
	function send_mail($smtp, $welcome="", $debug=false) {
		if(empty($smtp)) die("SMTP不能为空！");
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
	//显示调试信息
	function show_debug($message, $inout) {
		if ($this->debug) {
			if($inout=="in"){ //响应信息
				$m='<< ';
			}else
			$m='>> ';
			if(!ereg("\n$", $message))
			$message .= "<br>";
			$message=nl2br($message);
			echo "<font color=#99FF99>${m}${message}</font>";
		}
	}
	//执行传递的命令
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
	//邮件发送处理
	function send( $to,$from,$subject,$message) {
		//连接服务器
		$this->lastact="connect";
		$this->show_debug("连接到SMTP 服务器: ".$this->smtp, "out");
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
				//开始发送邮件正文
				$this->lastact="DATA\n";
				if(!$this->do_command($this->lastact, "354")){
					fclose($this->fp);
					return false;
				}
				//开始处理邮件主题头
				$head="Subject: $subject\n";
				if(!empty($subject) && !ereg($head, $message)){
					$message = $head.$message;
				}
				//开始处理邮件From头
				$head="From: $from\n";
				if(!empty($from) && !ereg($head, $message)) {
					$message = $head.$message;
				}
				//开始处理邮件To头
				$head="To: $to\n";
				if(!empty($to) && !ereg($head, $message)) {
					$message = $head.$message;
				}
				//处理结束串
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
			$this->show_debug("连接失败！!", "in");
			return false;
		}
	}
}
?>