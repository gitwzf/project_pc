<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>���Է�����</title>
        </head>
        <body>
            <div style="text-align:center; color:red">
            <?php
                $link=mysql_connect("localhost","root","12345678");
                if(!$link) echo "MySQL���ݿ� ����ʧ��!";
                else echo "MySQL���ݿ� ���ӳɹ�!";
                mysql_close();
            ?>
            </div>
            <br/>
            <?php phpinfo(); ?>
        </body>
</html>