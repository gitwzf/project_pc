<?php
    if(!socket_create_pair(AF_UNIX, SOCK_STREAM, 0, $socket)){
        print("不能建立连接!\n");
        exit();
    }
    $child = pcntl_fork();
    if($child == -1){
        print("不能调用fork函数!\n");
        exit();
    }
    elseif($child > 0){
        //父进程
        socket_close($socket[0]);
        print("父进程: 等待信息写入\n");
        $message = socket_read($socket[1], 1024, PHP_NORMAL_READ);
        print("父进程: 获取信息--$message\n");
        socket_write($socket[1], "Hello, Child Process!\n");
        pcntl_waitpid($child, $status);
    }
    else {
        //子进程
        socket_close($socket[1]);
        socket_write($socket[0], "Hello, Parent Process!\n");
        print("子进程: 等待信息写入\n");
        $message = socket_read($socket[0], 1024, PHP_NORMAL_READ);
        print("子进程: 开始获取信息--$message\n");
        exit(0);
    }
?>
