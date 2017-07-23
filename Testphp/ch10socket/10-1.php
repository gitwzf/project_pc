<?php
	$myProtocol = getprotobyname("tcp");
	$socket = socket_create(AF_INET, SOCK_STREAM, $myProtocol);
	socket_bind($socket, 'localhost', 13871);
	socket_listen($socket);
	//其他对socket的处理
?>