<?php
    if(!socket_create_pair(AF_UNIX, SOCK_STREAM, 0, $socket)){
        print("���ܽ�������!\n");
        exit();
    }
    $child = pcntl_fork();
    if($child == -1){
        print("���ܵ���fork����!\n");
        exit();
    }
    elseif($child > 0){
        //������
        socket_close($socket[0]);
        print("������: �ȴ���Ϣд��\n");
        $message = socket_read($socket[1], 1024, PHP_NORMAL_READ);
        print("������: ��ȡ��Ϣ--$message\n");
        socket_write($socket[1], "Hello, Child Process!\n");
        pcntl_waitpid($child, $status);
    }
    else {
        //�ӽ���
        socket_close($socket[1]);
        socket_write($socket[0], "Hello, Parent Process!\n");
        print("�ӽ���: �ȴ���Ϣд��\n");
        $message = socket_read($socket[0], 1024, PHP_NORMAL_READ);
        print("�ӽ���: ��ʼ��ȡ��Ϣ--$message\n");
        exit(0);
    }
?>
