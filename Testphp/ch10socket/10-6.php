<?php
    set_time_limit(0);
    //����һ���µ�socket����
    if(($socket = socket_create(AF_INET, SOCK_STREAM, 0)) < 0){
        echo("�������ӵ�SOCKET: " . socket_strerror(socket_last_error()) . "\n");
    }
    //��SOCKET�������ĵ�ַ�Ͷ˿�
    if(($error = socket_bind($socket, gethostbyname($_SERVER['HOSTNAME']), 12345)) < 0){
        echo("���ܰ�SOCKET: " . socket_strerror(socket_last_error()) . "\n");
    }
    if(($error = socket_listen($socket, 5)) < 0){
        echo("���ܼ���SOCKET: " . socket_strerror(socket_last_error()) . "\n");
    }
    while(TRUE){
        //�ȴ����ӽ���
        if(($accept = socket_accept($socket)) < 0){
            echo("��ȡ����: " . socket_strerror($message) . "\n");
            break;
        }
        //���ͻ�ӭ��Ϣ
        socket_write($accept, "�Ѿ��ɹ���������\n");
        echo(date('Y-m-d H:i:s') . "�Ѿ����ܵ�SOCKET���ӣ�\n");
        ob_flush();
        while(TRUE){
            //�ͻ������ж�ȡ����
            if(FALSE === ($line = socket_read($accept, 1024))){
                echo("���ܴ�SOCKET�ж�ȡ��Ϣ: " .
                    socket_strerror(socket_last_error()) . "\n");
                break 2;
            }
            if(!@socket_write($accept, "echo: $line")){
                echo(date('Y-m-d H:i:s') . " ���ӱ��Ƿ��жϣ�\n");
                break;
            }
            echo(date('Y-m-d H:i:s') . " READ: $line");
            ob_flush();
        }
        socket_close($accept);
    }
?>