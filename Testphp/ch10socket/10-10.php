<?php
//设置一些基本的变量
$host = "127.0.0.1"; //
$port = 12345;
//设置超时时间
set_time_limit(0);
//创建一个Socket
$socket = socket_create(AF_INET, SOCK_STREAM, 0) or die("不能建立Socket连接！\n");
//绑定Socket到端口
$result = socket_bind($socket, $host, $port) or die("不能绑定socket的给定端口！\n");
//开始监听连接
$result = socket_listen($socket, 3) or die("建立socket监听连接失败！\n");
//接受进来的连接请求,另一个Socket来处理通信
$socket_a = socket_accept($socket) or die("不能接受客户端的socket请求！\n");
// 获得客户端的输入
$input = socket_read($socket_a, 4096) or die("读取客户端的输入失败！\n");
// 清空输入字符串
$input = trim($input);
//处理客户端输入并返回结果
$output = strrev($input) . "\n";
socket_write($socket_a, $output, strlen ($output)) or die("不能给客户端返回结果！\n");
//关闭sockets
socket_close($socket_a);//关闭处理通信的socket_a
socket_close($socket);//关闭监听客户端连接的socket
?>