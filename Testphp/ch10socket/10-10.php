<?php
//����һЩ�����ı���
$host = "127.0.0.1"; //
$port = 12345;
//���ó�ʱʱ��
set_time_limit(0);
//����һ��Socket
$socket = socket_create(AF_INET, SOCK_STREAM, 0) or die("���ܽ���Socket���ӣ�\n");
//��Socket���˿�
$result = socket_bind($socket, $host, $port) or die("���ܰ�socket�ĸ����˿ڣ�\n");
//��ʼ��������
$result = socket_listen($socket, 3) or die("����socket��������ʧ�ܣ�\n");
//���ܽ�������������,��һ��Socket������ͨ��
$socket_a = socket_accept($socket) or die("���ܽ��ܿͻ��˵�socket����\n");
// ��ÿͻ��˵�����
$input = socket_read($socket_a, 4096) or die("��ȡ�ͻ��˵�����ʧ�ܣ�\n");
// ��������ַ���
$input = trim($input);
//����ͻ������벢���ؽ��
$output = strrev($input) . "\n";
socket_write($socket_a, $output, strlen ($output)) or die("���ܸ��ͻ��˷��ؽ����\n");
//�ر�sockets
socket_close($socket_a);//�رմ���ͨ�ŵ�socket_a
socket_close($socket);//�رռ����ͻ������ӵ�socket
?>