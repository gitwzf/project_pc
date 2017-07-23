<?php
    //创建一个UDP的socket连接
    if(($socket = socket_create(AF_INET, SOCK_DGRAM, SOL_UDP)) < 0){
        echo("不能创建SOCKET连接: " . socket_strerror(socket_last_error()) . "\n");
    }
    //设置超时时间为60秒
    socket_set_option($socket, SOL_SOCKET,SO_RCVTIMEO, array('sec'=>60,'usec'=>0));
    //连接到服务器
    if(!socket_connect($socket, '196.168.1.100', 13456)){
        echo("SOCKET连接失败: " . socket_strerror(socket_last_error()) . "\n");
    }
    //对服务器发送请求
    socket_write($socket, "hello,everybody!");
    //获取服务器
    $server = array();
    while(FALSE !== ($line = @socket_read($socket, 4096))){
        //解析数据
        for($i=22; ($i+5) < strlen($line); $i += 7){
            $ip = ord(substr($line, $i+1, 1)) . '.' .
                ord(substr($line, $i+2, 1)) . '.' .
                ord(substr($line, $i+3, 1)) . '.' .
                ord(substr($line, $i+4, 1));
            $port = (ord(substr($line, $i+5, 1)) * 256) +
                ord(substr($line, $i+6, 1));
            $server[] = array('ip'=>$ip, 'port'=>$port);
        }
    }
    echo("<h1>" . count($server) . " Servers</h1>\n");
    //循环各个服务器变量，获取其状态
    foreach($server as $s){
        echo("<h1>{$s['ip']}:{$s['port']}</h1>\n");
        //连接到服务器
        if(!socket_connect($socket, $s['ip'], $s['port'])){
            echo("<p>\n" . socket_strerror(socket_last_error()) . "\n</p>\n");
            continue;
        }
        //发送状态请求
        socket_write($socket, "hello!");
        //获取服务器状态
        if(false === ($line = @socket_read($socket, 1024)))
        {
            echo("<p>\n" .socket_strerror(socket_last_error()) ."\n</p>\n");
            continue;
        }
        $oss = explode("\n", $line);
        //解析配置数据
        $config = explode("\\", $oss[1]);
        echo("<h2>Configuration</h2>\n");
        echo("<p>\n");
        for($s=1; $s < count($config); $s += 2){
            echo("\t\t{$config[$s]} = {$config[$s+1]}<br>\n");
        }
        echo("</p>\n");
        echo("<h2>客户端</h2>\n");
        $lastClient = count($oss) - 1;
        for($p=2; $p < $lastClient; $p++){
            $Client = explode(" ", $oss[$p]);
            echo("{$Client[2]} Score={$Client[0]} " .
                "Ping={$Client[1]}<br>\n");
        }
        echo("</p>\n");
        ob_flush();
    }
    echo("</table>\n");
    socket_close($socket);
?>