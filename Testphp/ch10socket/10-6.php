<?php
    set_time_limit(0);
    //创建一个新的socket连接
    if(($socket = socket_create(AF_INET, SOCK_STREAM, 0)) < 0){
        echo("不能连接到SOCKET: " . socket_strerror(socket_last_error()) . "\n");
    }
    //绑定SOCKET到给定的地址和端口
    if(($error = socket_bind($socket, gethostbyname($_SERVER['HOSTNAME']), 12345)) < 0){
        echo("不能绑定SOCKET: " . socket_strerror(socket_last_error()) . "\n");
    }
    if(($error = socket_listen($socket, 5)) < 0){
        echo("不能监听SOCKET: " . socket_strerror(socket_last_error()) . "\n");
    }
    while(TRUE){
        //等待连接进入
        if(($accept = socket_accept($socket)) < 0){
            echo("读取错误: " . socket_strerror($message) . "\n");
            break;
        }
        //发送欢迎信息
        socket_write($accept, "已经成功建立连接\n");
        echo(date('Y-m-d H:i:s') . "已经接受到SOCKET连接！\n");
        ob_flush();
        while(TRUE){
            //客户端逐行读取内容
            if(FALSE === ($line = socket_read($accept, 1024))){
                echo("不能从SOCKET中读取信息: " .
                    socket_strerror(socket_last_error()) . "\n");
                break 2;
            }
            if(!@socket_write($accept, "echo: $line")){
                echo(date('Y-m-d H:i:s') . " 连接被非法中断！\n");
                break;
            }
            echo(date('Y-m-d H:i:s') . " READ: $line");
            ob_flush();
        }
        socket_close($accept);
    }
?>