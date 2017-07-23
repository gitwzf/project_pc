<?php
$agent = $HTTP_SERVER_VARS['HTTP_USER_AGENT'];
if(!extension_loaded('sockets')){
	if(eregi('win',$agent)){
		dl('php_sockets.dll');
	}else{
		dl('sockets.so');
	}
}
?>