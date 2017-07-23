<?php
//$_SERVER运行效果实例
echo            
	'PHP_SELF:'.$_SERVER['PHP_SELF'].'<BR/>'			 #当前正在执行脚本的文件名。
    .'argv:'.$_SERVER['argv'].'<BR/>'				 #传递给该脚本的参数。
    .'argc:'.$_SERVER['argc'].'<BR/>'				 #包含传递给程序的命令行参数的个数（如果运行在命令行模式）。
    .'GATEWAY_INTERFACE:'.$_SERVER['GATEWAY_INTERFACE'].'<BR/>'   #服务器使用的 CGI 规范的版本。例如，“CGI/1.1”。
    .'SERVER_NAME:'.$_SERVER['SERVER_NAME'].'<br/>'		 #当前运行脚本所在服务器主机的名称。
    .'SERVER_SOFTWARE:'.$_SERVER['SERVER_SOFTWARE'].'<BR/>'	 #服务器标识的字串，在响应请求时的头部中给出。
    .'SERVER_PROTOCOL:'.$_SERVER['SERVER_PROTOCOL'].'<BR/>'	 #请求页面时通信协议的名称和版本。例如，“HTTP/1.0”。
    .'REQUEST_METHOD:'.$_SERVER['REQUEST_METHOD'].'<BR/>'		 #访问页面时的请求方法。例如：“GET”、“HEAD”，“POST”，“PUT”。
    .'QUERY_STRING:'.$_SERVER['QUERY_STRING'].'<BR/>'		 #查询(query)的字符串。
    .'DOCUMENT_ROOT:'.$_SERVER['DOCUMENT_ROOT'].'<BR/>'		 #当前运行脚本所在的文档根目录。在服务器配置文件中定义。
    .'HTTP_ACCEPT:'.$_SERVER['HTTP_ACCEPT'].'<BR/>'		 #当前请求的 Accept: 头部的内容。
    .'HTTP_ACCEPT_CHARSET:'.$_SERVER['HTTP_ACCEPT_CHARSET'].'<BR/>' #当前请求的 Accept-Charset: 头部的内容。例如：“iso-8859-1,*,utf-8”。
	.'HTTP_ACCEPT_ENCODING:'.$_SERVER['HTTP_ACCEPT_ENCODING'].'<BR/>'#当前请求的 Accept-Encoding: 头部的内容。例如：“gzip”。
	.'HTTP_ACCEPT_LANGUAGE:'.$_SERVER['HTTP_ACCEPT_LANGUAGE'].'<BR/>'#当前请求的 Accept-Language: 头部的内容。例如：“en”。
	.'HTTP_CONNECTION:'.$_SERVER['HTTP_CONNECTION'].'<BR/>'	 #当前请求的 Connection: 头部的内容。例如：“Keep-Alive”。
    .'HTTP_HOST:'.$_SERVER['HTTP_HOST'].'<BR/>'			 #当前请求的 Host: 头部的内容。
    .'HTTP_REFERER:'.$_SERVER['HTTP_REFERER'].'<BR/>'		 #链接到当前页面的前一页面的 URL 地址。
    .'HTTP_USER_AGENT:'.$_SERVER['HTTP_USER_AGENT'].'<BR/>'	 #当前请求的 User_Agent: 头部的内容。
    .'REMOTE_ADDR:'.$_SERVER['REMOTE_ADDR'].'<BR/>'		 #正在浏览当前页面用户的 IP 地址。
    .'REMOTE_HOST:'.$_SERVER['REMOTE_HOST'].'<BR/>'		 #正在浏览当前页面用户的主机名。
    .'REMOTE_PORT:'.$_SERVER['REMOTE_PORT'].'<BR/>'		 #用户连接到服务器时所使用的端口。
    .'SCRIPT_FILENAME:'.$_SERVER['SCRIPT_FILENAME'].'<BR/>'	 #当前执行脚本的绝对路径名。
    .'SERVER_ADMIN:'.$_SERVER['SERVER_ADMIN'].'<BR/>'		 #管理员信息
    .'SERVER_PORT:'.$_SERVER['SERVER_PORT'].'<BR/>'		 #服务器所使用的端口
    .'SERVER_SIGNATURE:'.$_SERVER['SERVER_SIGNATURE'].'<BR/>'	 #包含服务器版本和虚拟主机名的字符串。
    .'PATH_TRANSLATED:'.$_SERVER['PATH_TRANSLATED'].'<BR/>'	 #当前脚本所在文件系统（不是文档根目录）的基本路径。
    .'SCRIPT_NAME:'.$_SERVER['SCRIPT_NAME'].'<BR/>'		 #包含当前脚本的路径。这在页面需要指向自己时非常有用。
    .'REQUEST_URI:'.$_SERVER['REQUEST_URI'].'<BR/>'		 #访问此页面所需的 URI。例如，“/index.html”。
	.'PHP_AUTH_USER:'.$_SERVER['PHP_AUTH_USER'].'<BR/>'		 
	#当 PHP 运行在 Apache 模块方式下，并且正在使用 HTTP 认证功能，这个变量便是用户输入的用户名。
	.'PHP_AUTH_PW:'.$_SERVER['PHP_AUTH_PW'].'<BR/>'
	#当 PHP 运行在 Apache 模块方式下，并且正在使用 HTTP 认证功能，这个变量便是用户输入的密码。
	.'AUTH_TYPE:'.$_SERVER['AUTH_TYPE'].'<BR/>'
	#当 PHP 运行在 Apache 模块方式下，并且正在使用 HTTP 认证功能，这个变量便是认证的类型。
    ;
?>