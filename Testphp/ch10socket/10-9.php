<?php
    $socket = socket_create(AF_INET, SOCK_STREAM, SOL_TCP);
    echo('SO_BROADCAST: ' .
        socket_get_option($socket, SOL_SOCKET, SO_BROADCAST) . "<br>\n");
    echo('SO_DEBUG: ' . 
    	socket_get_option($socket, SOL_SOCKET, SO_DEBUG) . "<br>\n");
    echo('SO_DONTROUTE: ' .
        socket_get_option($socket, SOL_SOCKET, SO_DONTROUTE) . "<br>\n");
    echo('SO_ERROR: ' .
        socket_get_option($socket, SOL_SOCKET, SO_ERROR) . "<br>\n");
    echo('SO_KEEPALIVE: ' .
        socket_get_option($socket, SOL_SOCKET, SO_KEEPALIVE) . "<br>\n");
    echo('SO_LINGER: ' .
        print_r(socket_get_option($socket, SOL_SOCKET, SO_LINGER), TRUE) . "<br>\n");
    echo('SO_OOBINLINE: ' .
        socket_get_option($socket, SOL_SOCKET, SO_OOBINLINE) . "<br>\n");
    echo('SO_RCVBUF: ' .
        socket_get_option($socket, SOL_SOCKET, SO_RCVBUF) . "<br>\n");
    echo('SO_RCVLOWAT: ' .
        socket_get_option($socket, SOL_SOCKET, SO_RCVLOWAT) . "<br>\n");
    echo('SO_RCVTIMEO: ' .
        print_r(socket_get_option($socket, SOL_SOCKET, SO_RCVTIMEO), TRUE) . "<br>\n");
    echo('SO_REUSEADDR: ' .
        socket_get_option($socket, SOL_SOCKET, SO_REUSEADDR) . "<br>\n");
    echo('SO_SNDBUF: ' .
        socket_get_option($socket, SOL_SOCKET, SO_SNDBUF) . "<br>\n");
    echo('SO_SNDLOWAT: ' .
        socket_get_option($socket, SOL_SOCKET, SO_SNDLOWAT) . "<br>\n");
    echo('SO_SNDTIMEO: ' .
        print_r(socket_get_option($socket, SOL_SOCKET, SO_SNDTIMEO), TRUE) . "<br>\n");
    echo('SO_TYPE: ' .
        socket_get_option($socket, SOL_SOCKET, SO_TYPE) . "<br>\n");
?>