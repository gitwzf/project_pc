<?php
//$_SERVER����Ч��ʵ��
echo            
	'PHP_SELF:'.$_SERVER['PHP_SELF'].'<BR/>'			 #��ǰ����ִ�нű����ļ�����
    .'argv:'.$_SERVER['argv'].'<BR/>'				 #���ݸ��ýű��Ĳ�����
    .'argc:'.$_SERVER['argc'].'<BR/>'				 #�������ݸ�����������в����ĸ��������������������ģʽ����
    .'GATEWAY_INTERFACE:'.$_SERVER['GATEWAY_INTERFACE'].'<BR/>'   #������ʹ�õ� CGI �淶�İ汾�����磬��CGI/1.1����
    .'SERVER_NAME:'.$_SERVER['SERVER_NAME'].'<br/>'		 #��ǰ���нű����ڷ��������������ơ�
    .'SERVER_SOFTWARE:'.$_SERVER['SERVER_SOFTWARE'].'<BR/>'	 #��������ʶ���ִ�������Ӧ����ʱ��ͷ���и�����
    .'SERVER_PROTOCOL:'.$_SERVER['SERVER_PROTOCOL'].'<BR/>'	 #����ҳ��ʱͨ��Э������ƺͰ汾�����磬��HTTP/1.0����
    .'REQUEST_METHOD:'.$_SERVER['REQUEST_METHOD'].'<BR/>'		 #����ҳ��ʱ�����󷽷������磺��GET������HEAD������POST������PUT����
    .'QUERY_STRING:'.$_SERVER['QUERY_STRING'].'<BR/>'		 #��ѯ(query)���ַ�����
    .'DOCUMENT_ROOT:'.$_SERVER['DOCUMENT_ROOT'].'<BR/>'		 #��ǰ���нű����ڵ��ĵ���Ŀ¼���ڷ����������ļ��ж��塣
    .'HTTP_ACCEPT:'.$_SERVER['HTTP_ACCEPT'].'<BR/>'		 #��ǰ����� Accept: ͷ�������ݡ�
    .'HTTP_ACCEPT_CHARSET:'.$_SERVER['HTTP_ACCEPT_CHARSET'].'<BR/>' #��ǰ����� Accept-Charset: ͷ�������ݡ����磺��iso-8859-1,*,utf-8����
	.'HTTP_ACCEPT_ENCODING:'.$_SERVER['HTTP_ACCEPT_ENCODING'].'<BR/>'#��ǰ����� Accept-Encoding: ͷ�������ݡ����磺��gzip����
	.'HTTP_ACCEPT_LANGUAGE:'.$_SERVER['HTTP_ACCEPT_LANGUAGE'].'<BR/>'#��ǰ����� Accept-Language: ͷ�������ݡ����磺��en����
	.'HTTP_CONNECTION:'.$_SERVER['HTTP_CONNECTION'].'<BR/>'	 #��ǰ����� Connection: ͷ�������ݡ����磺��Keep-Alive����
    .'HTTP_HOST:'.$_SERVER['HTTP_HOST'].'<BR/>'			 #��ǰ����� Host: ͷ�������ݡ�
    .'HTTP_REFERER:'.$_SERVER['HTTP_REFERER'].'<BR/>'		 #���ӵ���ǰҳ���ǰһҳ��� URL ��ַ��
    .'HTTP_USER_AGENT:'.$_SERVER['HTTP_USER_AGENT'].'<BR/>'	 #��ǰ����� User_Agent: ͷ�������ݡ�
    .'REMOTE_ADDR:'.$_SERVER['REMOTE_ADDR'].'<BR/>'		 #���������ǰҳ���û��� IP ��ַ��
    .'REMOTE_HOST:'.$_SERVER['REMOTE_HOST'].'<BR/>'		 #���������ǰҳ���û�����������
    .'REMOTE_PORT:'.$_SERVER['REMOTE_PORT'].'<BR/>'		 #�û����ӵ�������ʱ��ʹ�õĶ˿ڡ�
    .'SCRIPT_FILENAME:'.$_SERVER['SCRIPT_FILENAME'].'<BR/>'	 #��ǰִ�нű��ľ���·������
    .'SERVER_ADMIN:'.$_SERVER['SERVER_ADMIN'].'<BR/>'		 #����Ա��Ϣ
    .'SERVER_PORT:'.$_SERVER['SERVER_PORT'].'<BR/>'		 #��������ʹ�õĶ˿�
    .'SERVER_SIGNATURE:'.$_SERVER['SERVER_SIGNATURE'].'<BR/>'	 #�����������汾���������������ַ�����
    .'PATH_TRANSLATED:'.$_SERVER['PATH_TRANSLATED'].'<BR/>'	 #��ǰ�ű������ļ�ϵͳ�������ĵ���Ŀ¼���Ļ���·����
    .'SCRIPT_NAME:'.$_SERVER['SCRIPT_NAME'].'<BR/>'		 #������ǰ�ű���·��������ҳ����Ҫָ���Լ�ʱ�ǳ����á�
    .'REQUEST_URI:'.$_SERVER['REQUEST_URI'].'<BR/>'		 #���ʴ�ҳ������� URI�����磬��/index.html����
	.'PHP_AUTH_USER:'.$_SERVER['PHP_AUTH_USER'].'<BR/>'		 
	#�� PHP ������ Apache ģ�鷽ʽ�£���������ʹ�� HTTP ��֤���ܣ�������������û�������û�����
	.'PHP_AUTH_PW:'.$_SERVER['PHP_AUTH_PW'].'<BR/>'
	#�� PHP ������ Apache ģ�鷽ʽ�£���������ʹ�� HTTP ��֤���ܣ�������������û���������롣
	.'AUTH_TYPE:'.$_SERVER['AUTH_TYPE'].'<BR/>'
	#�� PHP ������ Apache ģ�鷽ʽ�£���������ʹ�� HTTP ��֤���ܣ��������������֤�����͡�
    ;
?>